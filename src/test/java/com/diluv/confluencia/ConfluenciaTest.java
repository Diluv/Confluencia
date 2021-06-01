package com.diluv.confluencia;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.testcontainers.containers.MariaDBContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
public abstract class ConfluenciaTest {

    static final MariaDBContainer CONTAINER;

    static {
        CONTAINER = new MariaDBContainer<>("mariadb:10.3.29");
        CONTAINER.start();
    }

    private static boolean init;

    @BeforeAll
    public static void testData () {

        if (!init) {
            try {
                Confluencia.init(CONTAINER.getJdbcUrl(), CONTAINER.getUsername(), CONTAINER.getPassword());
            }
            catch (Exception e) {
                e.printStackTrace();
                Assertions.fail();
            }
            init = true;
        }
    }
}
