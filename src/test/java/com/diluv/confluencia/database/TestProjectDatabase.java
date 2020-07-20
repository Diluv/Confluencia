package com.diluv.confluencia.database;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.diluv.confluencia.ConfluenciaTest;

public class TestProjectDatabase extends ConfluenciaTest {

    @Test
    public void countAllByGameSlugAndProjectTypeSlug () {

        Assertions.assertEquals(0, ConfluenciaTest.PROJECT.countAllByGameSlugAndProjectTypeSlug("invalid", "invalid"));
        Assertions.assertEquals(0, ConfluenciaTest.PROJECT.countAllByGameSlugAndProjectTypeSlug("minecraft-je", "invalid"));
        Assertions.assertEquals(128, ConfluenciaTest.PROJECT.countAllByGameSlugAndProjectTypeSlug("minecraft-je", "forge-mods"));
    }

    @Test
    public void findOneProjectByProjectId () {

        Assertions.assertNotNull(ConfluenciaTest.PROJECT.findOneProjectByProjectId(1));
    }

    @Test
    public void findOneProjectByGameSlugAndProjectTypeSlugAndProjectSlug () {

        Assertions.assertNull(ConfluenciaTest.PROJECT.findOneProjectByGameSlugAndProjectTypeSlugAndProjectSlug("invalid", "invalid", "invalid"));
        Assertions.assertNull(ConfluenciaTest.PROJECT.findOneProjectByGameSlugAndProjectTypeSlugAndProjectSlug("minecraft-je", "invalid", "invalid"));
        Assertions.assertNull(ConfluenciaTest.PROJECT.findOneProjectByGameSlugAndProjectTypeSlugAndProjectSlug("minecraft-je", "forge-mods", "invalid"));
        Assertions.assertNotNull(ConfluenciaTest.PROJECT.findOneProjectByGameSlugAndProjectTypeSlugAndProjectSlug("minecraft-je", "forge-mods", "bookshelf"));
    }
}
