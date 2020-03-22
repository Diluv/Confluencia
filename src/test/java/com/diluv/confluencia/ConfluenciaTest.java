package com.diluv.confluencia;

import org.junit.jupiter.api.BeforeAll;
import org.testcontainers.containers.MariaDBContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.diluv.confluencia.database.FileDatabase;
import com.diluv.confluencia.database.GameDatabase;
import com.diluv.confluencia.database.NewsDatabase;
import com.diluv.confluencia.database.ProjectDatabase;
import com.diluv.confluencia.database.SecurityDatabase;
import com.diluv.confluencia.database.UserDatabase;
import com.diluv.confluencia.database.dao.FileDAO;
import com.diluv.confluencia.database.dao.GameDAO;
import com.diluv.confluencia.database.dao.NewsDAO;
import com.diluv.confluencia.database.dao.ProjectDAO;
import com.diluv.confluencia.database.dao.SecurityDAO;
import com.diluv.confluencia.database.dao.UserDAO;

@Testcontainers
public abstract class ConfluenciaTest {

    static final MariaDBContainer CONTAINER;

    static {
        CONTAINER = new MariaDBContainer<>();
        CONTAINER.start();
    }

    public static final SecurityDAO SECURITY = new SecurityDatabase();
    public static final FileDAO FILE = new FileDatabase();
    public static final GameDAO GAME = new GameDatabase();
    public static final ProjectDAO PROJECT = new ProjectDatabase();
    public static final UserDAO USER = new UserDatabase();
    public static final NewsDAO NEWS = new NewsDatabase();

    private static boolean init;

    @BeforeAll
    public static void testData () {

        if (!init) {
            Confluencia.init(ConfluenciaTest.CONTAINER.getJdbcUrl(), ConfluenciaTest.CONTAINER.getUsername(), ConfluenciaTest.CONTAINER.getPassword(), true);
            init = true;
        }
    }
}
