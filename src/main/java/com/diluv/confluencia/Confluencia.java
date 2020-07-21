package com.diluv.confluencia;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.function.BiFunction;

import javax.persistence.criteria.CriteriaBuilder;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import com.diluv.confluencia.utils.FlywayConnectionProvider;

public class Confluencia {

    public static final Logger LOGGER = LogManager.getLogger("Confluencia");
    private static SessionFactory sessionFactory;

    public static void init (String url, String username, String password) {

        try {

            Configuration configuration = new Configuration();

            // Hibernate settings equivalent to hibernate.cfg.xml's properties
            Properties settings = new Properties();
            settings.put(Environment.DRIVER, "org.mariadb.jdbc.Driver");
            settings.put(Environment.URL, url + "?useLegacyDatetimeCode=false&serverTimezone=UTC");
            settings.put(Environment.USER, username);
            settings.put(Environment.PASS, password);
            settings.put(Environment.DIALECT, "org.hibernate.dialect.MariaDB103Dialect");
            settings.put(Environment.CONNECTION_PROVIDER, FlywayConnectionProvider.class.getName());
            settings.put(Environment.SHOW_SQL, true);
            settings.put(Environment.ENABLE_LAZY_LOAD_NO_TRANS, true);
            configuration.setProperties(settings);
            for (Class cls : getEntityClassesFromPackage("com.diluv.confluencia.database.record")) {
                configuration.addAnnotatedClass(cls);
            }

            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties()).build();

            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        }
        catch (Exception e) {

            e.printStackTrace();

        }
    }

    public static SessionFactory getSessionFactory () {

        return sessionFactory;
    }

    public static <R> R getQuery (BiFunction<Session, CriteriaBuilder, R> call) throws Exception {

        Transaction transaction = null;
        try (Session session = Confluencia.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            CriteriaBuilder cb = session.getCriteriaBuilder();
            R r = call.apply(session, cb);
            transaction.commit();
            return r;
        }
        catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
            throw new Exception(e);
        }
    }

    public static List<Class<?>> getEntityClassesFromPackage (String packageName) throws ClassNotFoundException, IOException, URISyntaxException {

        List<String> classNames = getClassNamesFromPackage(packageName);
        List<Class<?>> classes = new ArrayList<Class<?>>();
        for (String className : classNames) {
            Class<?> cls = Class.forName(packageName + "." + className);
            Annotation[] annotations = cls.getAnnotations();

            for (Annotation annotation : annotations) {
                if (annotation instanceof javax.persistence.Entity) {
                    classes.add(cls);
                }
            }
        }

        return classes;
    }

    public static ArrayList<String> getClassNamesFromPackage (String packageName) throws IOException, URISyntaxException, ClassNotFoundException {

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        ArrayList<String> names = new ArrayList<String>();

        packageName = packageName.replace(".", "/");
        URL packageURL = classLoader.getResource(packageName);

        URI uri = new URI(packageURL.toString());
        File folder = new File(uri.getPath());
        File[] files = folder.listFiles();
        for (File file : files) {
            String name = file.getName();
            name = name.substring(0, name.lastIndexOf('.'));  // remove ".class"
            names.add(name);
        }

        return names;
    }
}