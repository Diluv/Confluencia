package com.diluv.confluencia.database;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.diluv.confluencia.ConfluenciaTest;
import com.diluv.confluencia.utils.Pagination;

public class TestGameDatabase extends ConfluenciaTest {

    @Test
    public void findOneBySlug () {

        // Allowed
        Assertions.assertNotNull(ConfluenciaTest.GAME.findOneBySlug("minecraft"));

        // Not found
        Assertions.assertNull(ConfluenciaTest.GAME.findOneBySlug("notfound"));
    }

    @Test
    public void findAll () {

        Assertions.assertEquals(1, ConfluenciaTest.GAME.findAll(new Pagination(0), 10).size());
    }

    @Test
    public void findAllGameVersionsByGameSlug () {

        Assertions.assertEquals(0, ConfluenciaTest.GAME.findAllGameVersionsByGameSlug("invalid", new Pagination(0), 10).size());
        Assertions.assertEquals(1, ConfluenciaTest.GAME.findAllGameVersionsByGameSlug("minecraft", new Pagination(0), 10).size());
    }
}
