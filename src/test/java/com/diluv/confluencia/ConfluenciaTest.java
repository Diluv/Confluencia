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

@Testcontainers
public abstract class ConfluenciaTest {

    static final MariaDBContainer CONTAINER;

    static {
        CONTAINER = new MariaDBContainer<>();
        CONTAINER.start();
    }

    public static final SecurityDatabase SECURITY = new SecurityDatabase();
    public static final FileDatabase FILE = new FileDatabase();
    public static final GameDatabase GAME = new GameDatabase();
    public static final ProjectDatabase PROJECT = new ProjectDatabase();
    public static final UserDatabase USER = new UserDatabase();
    public static final NewsDatabase NEWS = new NewsDatabase();

    private static boolean init;

    @BeforeAll
    public static void testData () {

        if (!init) {
            Confluencia.init(CONTAINER.getJdbcUrl(), CONTAINER.getUsername(), CONTAINER.getPassword());
            init = true;
        }
    }
}
