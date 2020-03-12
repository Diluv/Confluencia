package com.diluv.confluencia.database;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.diluv.confluencia.ConfluenciaTest;

public class TestProjectDatabase extends ConfluenciaTest {

    @Test
    public void findOneProjectByProjectId () {

        Assertions.assertNotNull(ConfluenciaTest.PROJECT.findOneProjectByProjectId(1));
    }

    @Test
    public void findOneProjectByGameSlugAndProjectTypeSlugAndProjectSlug () {

        Assertions.assertNull(ConfluenciaTest.PROJECT.findOneProjectByGameSlugAndProjectTypeSlugAndProjectSlug("invalid", "invalid", "invalid"));
        Assertions.assertNull(ConfluenciaTest.PROJECT.findOneProjectByGameSlugAndProjectTypeSlugAndProjectSlug("minecraft", "invalid", "invalid"));
        Assertions.assertNull(ConfluenciaTest.PROJECT.findOneProjectByGameSlugAndProjectTypeSlugAndProjectSlug("minecraft", "mods", "invalid"));
        Assertions.assertNotNull(ConfluenciaTest.PROJECT.findOneProjectByGameSlugAndProjectTypeSlugAndProjectSlug("minecraft", "mods", "bookshelf"));
    }

    @Test
    public void findAllByUsername () {

        Assertions.assertEquals(0, ConfluenciaTest.PROJECT.findAllByUsername("invalid", 1, 10).size());
        Assertions.assertEquals(10, ConfluenciaTest.PROJECT.findAllByUsername("test", 1, 10).size());
        Assertions.assertEquals(10, ConfluenciaTest.PROJECT.findAllByUsername("test2", 1, 10).size());
    }

    @Test
    public void findAllByUsernameWhereAuthorized () {

        Assertions.assertEquals(0, ConfluenciaTest.PROJECT.findAllByUsernameWhereAuthorized("invalid", 1, 10).size());
        Assertions.assertEquals(10, ConfluenciaTest.PROJECT.findAllByUsernameWhereAuthorized("test", 1, 10).size());
        Assertions.assertEquals(10, ConfluenciaTest.PROJECT.findAllByUsernameWhereAuthorized("test2", 1, 10).size());
    }

    @Test
    public void findAllProjectTypesByGameSlug () {

        Assertions.assertEquals(0, ConfluenciaTest.PROJECT.findAllProjectTypesByGameSlug("invalid").size());
        Assertions.assertEquals(1, ConfluenciaTest.PROJECT.findAllProjectTypesByGameSlug("minecraft").size());
    }

    @Test
    public void findOneProjectTypeByGameSlugAndProjectTypeSlug () {

        Assertions.assertNull(ConfluenciaTest.PROJECT.findOneProjectTypeByGameSlugAndProjectTypeSlug("invalid", "invalid"));
        Assertions.assertNull(ConfluenciaTest.PROJECT.findOneProjectTypeByGameSlugAndProjectTypeSlug("minecraft", "invalid"));
        Assertions.assertNotNull(ConfluenciaTest.PROJECT.findOneProjectTypeByGameSlugAndProjectTypeSlug("minecraft", "mods"));
    }

    @Test
    public void findAllProjectsByGameSlugAndProjectType () {

        Assertions.assertEquals(0, ConfluenciaTest.PROJECT.findAllProjectsByGameSlugAndProjectType("invalid", "invalid", 1, 10).size());
        Assertions.assertEquals(0, ConfluenciaTest.PROJECT.findAllProjectsByGameSlugAndProjectType("minecraft", "invalid", 1, 10).size());
        Assertions.assertEquals(10, ConfluenciaTest.PROJECT.findAllProjectsByGameSlugAndProjectType("minecraft", "mods", 1, 10).size());
    }

    @Test
    public void findFeaturedProjects () {

        Assertions.assertEquals(3, ConfluenciaTest.PROJECT.findFeaturedProjects().size());
    }

    @Test
    public void findAllProjectAuthorsByProjectId () {

        Assertions.assertEquals(2, ConfluenciaTest.PROJECT.findAllProjectAuthorsByProjectId(1).size());
    }

    @Test
    public void insertProject () {

        Assertions.assertTrue(ConfluenciaTest.PROJECT.insertProject("project_insert", "Insert", "Insert Summary", "Insert Description", 3, "minecraft", "mods"));
    }

    @Test
    public void findAllCategoriesByGameSlugAndProjectTypeSlug () {

        Assertions.assertEquals(0, ConfluenciaTest.PROJECT.findAllCategoriesByGameSlugAndProjectTypeSlug("invalid", "invalid").size());
        Assertions.assertEquals(0, ConfluenciaTest.PROJECT.findAllCategoriesByGameSlugAndProjectTypeSlug("minecraft", "invalid").size());
        Assertions.assertEquals(1, ConfluenciaTest.PROJECT.findAllCategoriesByGameSlugAndProjectTypeSlug("minecraft", "mods").size());
    }

    @Test
    public void findAllCategoriesByProjectId () {
        Assertions.assertEquals(1, ConfluenciaTest.PROJECT.findAllCategoriesByProjectId(1).size());
    }
}
