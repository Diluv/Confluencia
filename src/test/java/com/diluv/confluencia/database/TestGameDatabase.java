package com.diluv.confluencia.database;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.diluv.confluencia.ConfluenciaTest;

public class TestGameDatabase extends ConfluenciaTest {

    @Test
    public void findAll () {

        Assertions.assertFalse(ConfluenciaTest.GAME.findAll().isEmpty());
    }

    @Test
    public void findOneBySlug () {

        // Allowed
        Assertions.assertNotNull(ConfluenciaTest.GAME.findOneBySlug("minecraft"));

        // Not found
        Assertions.assertNull(ConfluenciaTest.GAME.findOneBySlug("notfound"));
    }
}
