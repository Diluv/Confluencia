package com.diluv.confluencia.database;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.diluv.confluencia.ConfluenciaTest;
import com.diluv.confluencia.database.sort.GameSort;

public class TestGameDatabase extends ConfluenciaTest {

    @Test
    public void countAllBySearch () {

        Assertions.assertEquals(2, ConfluenciaTest.GAME.countAllBySearch(""));
        Assertions.assertEquals(1, ConfluenciaTest.GAME.countAllBySearch("bedrock"));
    }

    @Test
    public void findOneBySlug () {

        // Allowed
        Assertions.assertNotNull(ConfluenciaTest.GAME.findOneBySlug("minecraft-je"));

        // Not found
        Assertions.assertNull(ConfluenciaTest.GAME.findOneBySlug("notfound"));
    }

    @Test
    public void findAll () {

        Assertions.assertEquals(2, ConfluenciaTest.GAME.findAll(1, 25, GameSort.NEW, "").size());
    }

    @Test
    public void findFeaturedGames () {

        Assertions.assertEquals(2, ConfluenciaTest.GAME.findFeaturedGames().size());
    }

    @Test
    public void countAllProjectTypes () {

        Assertions.assertEquals(3, ConfluenciaTest.GAME.countAllProjectTypes());
    }
}
