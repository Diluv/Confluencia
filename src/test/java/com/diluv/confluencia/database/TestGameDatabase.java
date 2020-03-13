package com.diluv.confluencia.database;

import com.diluv.confluencia.database.sort.GameSort;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.diluv.confluencia.ConfluenciaTest;

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

        Assertions.assertEquals(1, ConfluenciaTest.GAME.findAll(GameSort.NEW).size());
    }

    @Test
    public void findFeaturedGames () {

        Assertions.assertEquals(1, ConfluenciaTest.GAME.findFeaturedGames().size());
    }

    @Test
    public void findAllGameVersionsByGameSlug () {

        Assertions.assertEquals(0, ConfluenciaTest.GAME.findAllGameVersionsByGameSlug("invalid").size());
        Assertions.assertFalse(ConfluenciaTest.GAME.findAllGameVersionsByGameSlug("minecraft").isEmpty());
    }
}
