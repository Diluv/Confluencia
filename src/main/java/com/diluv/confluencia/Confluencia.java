package com.diluv.confluencia;

import java.util.List;
import java.util.Properties;
import java.util.function.BiFunction;

import javax.persistence.NoResultException;
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
import com.github.fluent.hibernate.cfg.scanner.EntityScanner;

public class Confluencia {

    public static final Logger LOGGER = LogManager.getLogger("Confluencia");
    private static SessionFactory sessionFactory;

    public static void init (String url, String username, String password) {

        try {
            Configuration configuration = new Configuration();

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

            List<Class<?>> classes = EntityScanner.scanPackages("com.diluv.confluencia.database.record").result();

            for (Class<?> annotatedClass : classes) {
                configuration.addAnnotatedClass(annotatedClass);
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

    public static <R> R getQuery (BiFunction<Session, CriteriaBuilder, R> call)  {

        Transaction transaction = null;
        try (Session session = Confluencia.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            CriteriaBuilder cb = session.getCriteriaBuilder();
            R r = call.apply(session, cb);
            transaction.commit();
            return r;
        }
        catch (NoResultException e) {
            throw e;
        }
        catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
            throw e;
        }
    }
}