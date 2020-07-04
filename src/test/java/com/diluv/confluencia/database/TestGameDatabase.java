package com.diluv.confluencia.database;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.diluv.confluencia.ConfluenciaTest;
import com.diluv.confluencia.database.sort.GameSort;

public class TestGameDatabase extends ConfluenciaTest {

    @Test
    public void findOneBySlug () {

        // Allowed
        Assertions.assertNotNull(ConfluenciaTest.GAME.findOneBySlug("minecraft-je"));

        // Not found
        Assertions.assertNull(ConfluenciaTest.GAME.findOneBySlug("notfound"));
    }

    @Test
    public void countAll () {

        Assertions.assertEquals(2, ConfluenciaTest.GAME.countAll());
        Assertions.assertEquals(1, ConfluenciaTest.GAME.countAll("bedrock"));
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
    public void findAllGameVersionsByGameSlug () {

        Assertions.assertEquals(0, ConfluenciaTest.GAME.findAllGameVersionsByGameSlug("invalid").size());
        Assertions.assertFalse(ConfluenciaTest.GAME.findAllGameVersionsByGameSlug("minecraft-je").isEmpty());
    }

    @Test
    public void findGameVersionsByGameSlugAndVersions () {

        Assertions.assertEquals(2, ConfluenciaTest.GAME.findGameVersionsByGameSlugAndVersions("minecraft-je", new String[]{"1.15.2", "1.12.2"}).size());
        Assertions.assertEquals(1, ConfluenciaTest.GAME.findGameVersionsByGameSlugAndVersions("minecraft-je", new String[]{"1.15.2", "invalid"}).size());
        Assertions.assertEquals(0, ConfluenciaTest.GAME.findGameVersionsByGameSlugAndVersions("minecraft-je", new String[]{"invalid"}).size());
    }

    @Test
    public void countAllProjectTypes () {

        Assertions.assertEquals(3, ConfluenciaTest.GAME.countAllProjectTypes());
    }
}
