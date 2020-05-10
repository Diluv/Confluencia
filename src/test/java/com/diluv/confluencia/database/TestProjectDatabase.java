package com.diluv.confluencia.database;

import com.diluv.confluencia.ConfluenciaTest;
import com.diluv.confluencia.database.sort.ProjectSort;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

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

        Assertions.assertEquals(0, ConfluenciaTest.PROJECT.findAllByUsername("invalid", false, 1, 10, ProjectSort.NEW).size());
        Assertions.assertEquals(10, ConfluenciaTest.PROJECT.findAllByUsername("darkhax", false, 1, 10, ProjectSort.NEW).size());
        Assertions.assertEquals(10, ConfluenciaTest.PROJECT.findAllByUsername("jaredlll08", false, 1, 10, ProjectSort.NEW).size());
    }

    @Test
    public void findAllProjectsByProjectIds () {

        Assertions.assertEquals(3, ConfluenciaTest.PROJECT.findAllProjectsByProjectIds(new long[]{1, 2, 3}).size());
        Assertions.assertEquals(2, ConfluenciaTest.PROJECT.findAllProjectsByProjectIds(new long[]{1000, 2, 3}).size());
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

        Assertions.assertEquals(0, ConfluenciaTest.PROJECT.findAllProjectsByGameSlugAndProjectType("invalid", "invalid", 1, 10, ProjectSort.NEW).size());
        Assertions.assertEquals(0, ConfluenciaTest.PROJECT.findAllProjectsByGameSlugAndProjectType("minecraft", "invalid", 1, 10, ProjectSort.NEW).size());
        Assertions.assertEquals(10, ConfluenciaTest.PROJECT.findAllProjectsByGameSlugAndProjectType("minecraft", "mods", 1, 10, ProjectSort.NEW).size());
    }

    @Test
    public void findAllProjectsByGameSlugAndProjectTypeAndVersion () {

        Assertions.assertEquals(1, ConfluenciaTest.PROJECT.findAllProjectsByGameSlugAndProjectTypeAndVersion("minecraft", "mods", 1, 10, ProjectSort.NEW, "1.15.2").size());
    }

    @Test
    public void findFeaturedProjects () {

        Assertions.assertEquals(6, ConfluenciaTest.PROJECT.findFeaturedProjects().size());
    }

    @Test
    public void findAllProjectAuthorsByProjectId () {

        Assertions.assertEquals(2, ConfluenciaTest.PROJECT.findAllProjectAuthorsByProjectId(1).size());
    }

    @Test
    public void insertProject () {

        Assertions.assertTrue(ConfluenciaTest.PROJECT.insertProject("project_insert", "Insert", "Insert Summary", "Insert Description", 2, "minecraft", "mods"));
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

    @Test
    public void findAllLinksByProjectId () {

        Assertions.assertEquals(1, ConfluenciaTest.PROJECT.findAllLinksByProjectId(1).size());
    }
}
