package com.diluv.confluencia.database;

import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import com.diluv.confluencia.ConfluenciaTest;

public class TestProjectDatabase extends ConfluenciaTest {

    @Test
    public void findAllByUsername () {

        Assert.assertEquals(0, ConfluenciaTest.PROJECT.findAllByUsername("invalid").size());
        Assert.assertEquals(1, ConfluenciaTest.PROJECT.findAllByUsername("test").size());
        Assert.assertEquals(0, ConfluenciaTest.PROJECT.findAllByUsername("test2").size());
    }

    @Test
    public void findAllByUsernameWhereAuthorized () {

        Assert.assertEquals(0, ConfluenciaTest.PROJECT.findAllByUsernameWhereAuthorized("invalid").size());
        Assert.assertEquals(2, ConfluenciaTest.PROJECT.findAllByUsernameWhereAuthorized("test").size());
        Assert.assertEquals(0, ConfluenciaTest.PROJECT.findAllByUsernameWhereAuthorized("test2").size());
    }

    @Test
    public void findAllProjectTypesByGameSlug () {

        Assert.assertEquals(0, ConfluenciaTest.PROJECT.findAllProjectTypesByGameSlug("invalid").size());
        Assert.assertEquals(1, ConfluenciaTest.PROJECT.findAllProjectTypesByGameSlug("minecraft").size());
    }

    @Test
    public void findOneProjectTypeByGameSlugAndProjectTypeSlug () {

        Assert.assertNull(ConfluenciaTest.PROJECT.findOneProjectTypeByGameSlugAndProjectTypeSlug("invalid", "invalid"));
        Assert.assertNull(ConfluenciaTest.PROJECT.findOneProjectTypeByGameSlugAndProjectTypeSlug("minecraft", "invalid"));
        Assert.assertNotNull(ConfluenciaTest.PROJECT.findOneProjectTypeByGameSlugAndProjectTypeSlug("minecraft", "mods"));
    }

    @Test
    public void findAllProjectsByGameSlugAndProjectType () {

        Assert.assertEquals(0, ConfluenciaTest.PROJECT.findAllProjectsByGameSlugAndProjectType("invalid", "invalid").size());
        Assert.assertEquals(0, ConfluenciaTest.PROJECT.findAllProjectsByGameSlugAndProjectType("minecraft", "invalid").size());
        Assert.assertEquals(1, ConfluenciaTest.PROJECT.findAllProjectsByGameSlugAndProjectType("minecraft", "mods").size());
    }

    @Test
    public void findOneProjectByGameSlugAndProjectTypeSlugAndProjectSlug () {

        Assert.assertNull(ConfluenciaTest.PROJECT.findOneProjectByGameSlugAndProjectTypeSlugAndProjectSlug("invalid", "invalid", "invalid"));
        Assert.assertNull(ConfluenciaTest.PROJECT.findOneProjectByGameSlugAndProjectTypeSlugAndProjectSlug("minecraft", "invalid", "invalid"));
        Assert.assertNull(ConfluenciaTest.PROJECT.findOneProjectByGameSlugAndProjectTypeSlugAndProjectSlug("minecraft", "mods", "invalid"));
        Assert.assertNotNull(ConfluenciaTest.PROJECT.findOneProjectByGameSlugAndProjectTypeSlugAndProjectSlug("minecraft", "mods", "project_1"));
    }

    @Before
    @Test
    public void insertProject () {

        Assert.assertTrue(ConfluenciaTest.PROJECT.insertProject("project_insert", "Insert", "Insert Summary", "Insert Description", 3, "minecraft", "mods"));
    }
}
