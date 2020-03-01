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
    public void findAllByUsername () {

        Assertions.assertEquals(0, ConfluenciaTest.PROJECT.findAllByUsername("invalid").size());
        Assertions.assertEquals(1, ConfluenciaTest.PROJECT.findAllByUsername("test").size());
        Assertions.assertEquals(0, ConfluenciaTest.PROJECT.findAllByUsername("test2").size());
    }

    @Test
    public void findAllByUsernameWhereAuthorized () {

        Assertions.assertEquals(0, ConfluenciaTest.PROJECT.findAllByUsernameWhereAuthorized("invalid").size());
        Assertions.assertEquals(2, ConfluenciaTest.PROJECT.findAllByUsernameWhereAuthorized("test").size());
        Assertions.assertEquals(0, ConfluenciaTest.PROJECT.findAllByUsernameWhereAuthorized("test2").size());
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

        Assertions.assertEquals(0, ConfluenciaTest.PROJECT.findAllProjectsByGameSlugAndProjectType("invalid", "invalid").size());
        Assertions.assertEquals(0, ConfluenciaTest.PROJECT.findAllProjectsByGameSlugAndProjectType("minecraft", "invalid").size());
        Assertions.assertEquals(1, ConfluenciaTest.PROJECT.findAllProjectsByGameSlugAndProjectType("minecraft", "mods").size());
    }

    @Test
    public void findOneProjectByGameSlugAndProjectTypeSlugAndProjectSlug () {

        Assertions.assertNull(ConfluenciaTest.PROJECT.findOneProjectByGameSlugAndProjectTypeSlugAndProjectSlug("invalid", "invalid", "invalid"));
        Assertions.assertNull(ConfluenciaTest.PROJECT.findOneProjectByGameSlugAndProjectTypeSlugAndProjectSlug("minecraft", "invalid", "invalid"));
        Assertions.assertNull(ConfluenciaTest.PROJECT.findOneProjectByGameSlugAndProjectTypeSlugAndProjectSlug("minecraft", "mods", "invalid"));
        Assertions.assertNotNull(ConfluenciaTest.PROJECT.findOneProjectByGameSlugAndProjectTypeSlugAndProjectSlug("minecraft", "mods", "project_1"));
    }

    @Test
    public void findAllProjectAuthorsByProjectId () {

        Assertions.assertEquals(2, ConfluenciaTest.PROJECT.findAllProjectAuthorsByProjectId(1).size());
    }

    @Test
    public void insertProject () {

        Assertions.assertTrue(ConfluenciaTest.PROJECT.insertProject("project_insert", "Insert", "Insert Summary", "Insert Description", 3, "minecraft", "mods"));
    }
}
