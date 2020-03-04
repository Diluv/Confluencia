package com.diluv.confluencia.database;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.diluv.confluencia.ConfluenciaTest;
import com.diluv.confluencia.utils.Pagination;

public class TestGameDatabase extends ConfluenciaTest {

    @Test
    public void findAll () {

        Assertions.assertFalse(ConfluenciaTest.GAME.findAll(new Pagination(0), 1).isEmpty());
    }

    @Test
    public void findOneBySlug () {

        // Allowed
        Assertions.assertNotNull(ConfluenciaTest.GAME.findOneBySlug("minecraft"));

        // Not found
        Assertions.assertNull(ConfluenciaTest.GAME.findOneBySlug("notfound"));
    }
}
