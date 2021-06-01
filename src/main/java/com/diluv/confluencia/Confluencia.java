package com.diluv.confluencia;

import java.util.Properties;
import java.util.function.Consumer;
import java.util.function.Function;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import com.diluv.confluencia.database.FileDatabase;
import com.diluv.confluencia.database.GameDatabase;
import com.diluv.confluencia.database.MiscDatabase;
import com.diluv.confluencia.database.NewsDatabase;
import com.diluv.confluencia.database.ProjectDatabase;
import com.diluv.confluencia.database.SecurityDatabase;
import com.diluv.confluencia.database.UserDatabase;
import com.diluv.confluencia.database.record.*;
import com.diluv.confluencia.utils.FlywayConnectionProvider;

public class Confluencia {

    public static final FileDatabase FILE = new FileDatabase();
    public static final GameDatabase GAME = new GameDatabase();
    public static final MiscDatabase MISC = new MiscDatabase();
    public static final NewsDatabase NEWS = new NewsDatabase();
    public static final ProjectDatabase PROJECT = new ProjectDatabase();
    public static final SecurityDatabase SECURITY = new SecurityDatabase();
    public static final UserDatabase USER = new UserDatabase();

    public static final Logger LOGGER = LogManager.getLogger("Confluencia");
    private static SessionFactory sessionFactory;

    public static void init (String url, String username, String password) throws Exception {

        Configuration configuration = new Configuration();

        Properties settings = new Properties();
        settings.put(Environment.DRIVER, "org.mariadb.jdbc.Driver");
        settings.put(Environment.URL, url + "?useLegacyDatetimeCode=false&serverTimezone=UTC");
        settings.put(Environment.USER, username);
        settings.put(Environment.PASS, password);
        settings.put(Environment.DIALECT, "org.hibernate.dialect.MariaDB103Dialect");
        settings.put(Environment.CONNECTION_PROVIDER, FlywayConnectionProvider.class.getName());
        settings.put(Environment.SHOW_SQL, false);
        settings.put(Environment.ENABLE_LAZY_LOAD_NO_TRANS, true);
        configuration.setProperties(settings);

        configuration.addAnnotatedClass(ContainsUsernameBlockListEntity.class);
        configuration.addAnnotatedClass(EmailBlockListEntity.class);
        configuration.addAnnotatedClass(EmailBounceEntity.class);
        configuration.addAnnotatedClass(EmailDomainBlocklistEntity.class);
        configuration.addAnnotatedClass(EmailSentEntity.class);
        configuration.addAnnotatedClass(FeaturedGamesEntity.class);
        configuration.addAnnotatedClass(FeaturedProjectsEntity.class);
        configuration.addAnnotatedClass(GameDefaultProjectTypeEntity.class);
        configuration.addAnnotatedClass(GamesEntity.class);
        configuration.addAnnotatedClass(GameVersionsEntity.class);
        configuration.addAnnotatedClass(ImagesEntity.class);
        configuration.addAnnotatedClass(NewsEntity.class);
        configuration.addAnnotatedClass(NodeCDNCommitsEntity.class);
        configuration.addAnnotatedClass(PasswordResetEntity.class);
        configuration.addAnnotatedClass(APITokensEntity.class);
        configuration.addAnnotatedClass(ProjectAuthorPermissionsEntity.class);
        configuration.addAnnotatedClass(ProjectAuthorsEntity.class);
        configuration.addAnnotatedClass(ProjectFileAntivirusEntity.class);
        configuration.addAnnotatedClass(ProjectFileDependenciesEntity.class);
        configuration.addAnnotatedClass(ProjectFileDownloadsEntity.class);
        configuration.addAnnotatedClass(ProjectFileGameVersionsEntity.class);
        configuration.addAnnotatedClass(ProjectFileLoadersEntity.class);
        configuration.addAnnotatedClass(ProjectFilesEntity.class);
        configuration.addAnnotatedClass(ProjectLinksEntity.class);
        configuration.addAnnotatedClass(ProjectRequestChangeEntity.class);
        configuration.addAnnotatedClass(ProjectReviewEntity.class);
        configuration.addAnnotatedClass(ProjectsEntity.class);
        configuration.addAnnotatedClass(ProjectTagsEntity.class);
        configuration.addAnnotatedClass(ProjectTypeLoadersEntity.class);
        configuration.addAnnotatedClass(ProjectTypesEntity.class);
        configuration.addAnnotatedClass(RegistrationCodesEntity.class);
        configuration.addAnnotatedClass(RolesEntity.class);
        configuration.addAnnotatedClass(TagsEntity.class);
        configuration.addAnnotatedClass(TempUsersEntity.class);
        configuration.addAnnotatedClass(UserChangeEmail.class);
        configuration.addAnnotatedClass(UserCompromisedPasswordsEntity.class);
        configuration.addAnnotatedClass(UserMfaEmailEntity.class);
        configuration.addAnnotatedClass(UserMfaRecoveryEntity.class);
        configuration.addAnnotatedClass(UsernameBlocklistEntity.class);
        configuration.addAnnotatedClass(UserRolesEntity.class);
        configuration.addAnnotatedClass(UsersEntity.class);

        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
            .applySettings(configuration.getProperties()).build();

        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
    }

    public static SessionFactory getSessionFactory () {

        return sessionFactory;
    }

    public static void getTransaction (Consumer<Session> call) {

        Transaction tx = null;
        try (Session session = Confluencia.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            call.accept(session);
            tx.commit();
        }
        catch (Exception e) {
            LOGGER.log(Level.ERROR, e);
            if (tx != null) {
                tx.rollback();
            }
            throw e;
        }
    }

    public static <R> R getTransaction (Function<Session, R> call) {

        Transaction tx = null;
        try (Session session = Confluencia.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            R r = call.apply(session);
            tx.commit();
            return r;
        }
        catch (Exception e) {
            LOGGER.log(Level.ERROR, e);
            if (tx != null) {
                tx.rollback();
            }
            throw e;
        }
    }
}