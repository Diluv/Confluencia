package com.diluv.confluencia;

import org.junit.jupiter.api.BeforeAll;
import org.testcontainers.containers.MariaDBContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
public abstract class ConfluenciaTest {

    static final MariaDBContainer CONTAINER;

    static {
        CONTAINER = new MariaDBContainer<>();
        CONTAINER.start();
    }

    private static boolean init;

    @BeforeAll
    public static void testData () {

        if (!init) {
            Confluencia.init(CONTAINER.getJdbcUrl(), CONTAINER.getUsername(), CONTAINER.getPassword());
            init = true;
        }
    }
}
