package com.diluv.confluencia.database;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.diluv.confluencia.ConfluenciaTest;

public class TestUserDatabase extends ConfluenciaTest {

    @Test
    public void countAll () {

        Assertions.assertEquals(4, ConfluenciaTest.USER.countAll());
    }

    @Test
    public void findOneByUsername () {

        Assertions.assertNull(ConfluenciaTest.USER.findOneByUsername("invalid"));
        Assertions.assertNotNull(ConfluenciaTest.USER.findOneByUsername("darkhax"));
        Assertions.assertNotNull(ConfluenciaTest.USER.findOneByUsername("jaredlll08"));
    }

    @Test
    public void findOneByUserId () {

        Assertions.assertNull(ConfluenciaTest.USER.findOneByUserId(0));
        Assertions.assertNotNull(ConfluenciaTest.USER.findOneByUserId(1));
        Assertions.assertNotNull(ConfluenciaTest.USER.findOneByUserId(2));
    }
}
