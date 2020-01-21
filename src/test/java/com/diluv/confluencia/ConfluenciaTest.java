package com.diluv.confluencia;

import org.junit.jupiter.api.BeforeAll;
import org.testcontainers.containers.MariaDBContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.diluv.confluencia.database.EmailDatabase;
import com.diluv.confluencia.database.FileDatabase;
import com.diluv.confluencia.database.GameDatabase;
import com.diluv.confluencia.database.ProjectDatabase;
import com.diluv.confluencia.database.UserDatabase;
import com.diluv.confluencia.database.dao.EmailDAO;
import com.diluv.confluencia.database.dao.FileDAO;
import com.diluv.confluencia.database.dao.GameDAO;
import com.diluv.confluencia.database.dao.ProjectDAO;
import com.diluv.confluencia.database.dao.UserDAO;
import com.diluv.confluencia.util.TestSQLHandler;

@Testcontainers
public abstract class ConfluenciaTest {

    static final MariaDBContainer CONTAINER;

    static {
        CONTAINER = new MariaDBContainer<>();
        CONTAINER.start();
    }

    public static final EmailDAO EMAIL = new EmailDatabase();
    public static final FileDAO FILE = new FileDatabase();
    public static final GameDAO GAME = new GameDatabase();
    public static final ProjectDAO PROJECT = new ProjectDatabase();
    public static final UserDAO USER = new UserDatabase();

    private static boolean init;

    @BeforeAll
    public static void testData () {

        if (!init) {
            Confluencia.init(ConfluenciaTest.CONTAINER.getJdbcUrl(), ConfluenciaTest.CONTAINER.getUsername(), ConfluenciaTest.CONTAINER.getPassword(), true);

            TestSQLHandler.loadData("email");
            TestSQLHandler.loadData("user");
            TestSQLHandler.loadData("temp_user");
            TestSQLHandler.loadData("refresh_token");
            TestSQLHandler.loadData("project");
            TestSQLHandler.loadData("project_file");
            init = true;
        }
    }
}
