package com.diluv.confluencia.database;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.diluv.confluencia.ConfluenciaTest;
import com.diluv.confluencia.database.sort.ProjectSort;

import java.util.Arrays;

public class TestProjectDatabase extends ConfluenciaTest {

    @Test
    public void countAll () {

        Assertions.assertEquals(129, ConfluenciaTest.PROJECT.countAll());
    }

    @Test
    public void countAllByGameSlug () {

        Assertions.assertEquals(0, ConfluenciaTest.PROJECT.countAllByGameSlug("invalid"));
        Assertions.assertEquals(128, ConfluenciaTest.PROJECT.countAllByGameSlug("minecraft-je"));
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

    @Test
    public void countAllByUsername () {
        Assertions.assertEquals(0, ConfluenciaTest.PROJECT.countAllByUsername("invalid", false));
        Assertions.assertEquals(83, ConfluenciaTest.PROJECT.countAllByUsername("darkhax", false));
        Assertions.assertEquals(46, ConfluenciaTest.PROJECT.countAllByUsername("jaredlll08", false));
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
        Assertions.assertEquals(2, ConfluenciaTest.PROJECT.findAllProjectTypesByGameSlug("minecraft-je").size());
    }

    @Test
    public void findOneProjectTypeByGameSlugAndProjectTypeSlug () {

        Assertions.assertNull(ConfluenciaTest.PROJECT.findOneProjectTypeByGameSlugAndProjectTypeSlug("invalid", "invalid"));
        Assertions.assertNull(ConfluenciaTest.PROJECT.findOneProjectTypeByGameSlugAndProjectTypeSlug("minecraft-je", "invalid"));
        Assertions.assertNotNull(ConfluenciaTest.PROJECT.findOneProjectTypeByGameSlugAndProjectTypeSlug("minecraft-je", "forge-mods"));
    }

    @Test
    public void findAllProjectsByGameSlugAndProjectType () {

        Assertions.assertEquals(0, ConfluenciaTest.PROJECT.findAllByGameAndProjectType("invalid", "invalid", "", 1, 10, ProjectSort.NEW).size());
        Assertions.assertEquals(0, ConfluenciaTest.PROJECT.findAllByGameAndProjectType("minecraft-je", "invalid", "", 1, 10, ProjectSort.NEW).size());
        Assertions.assertEquals(10, ConfluenciaTest.PROJECT.findAllByGameAndProjectType("minecraft-je", "forge-mods", "", 1, 10, ProjectSort.NEW).size());
    }

    @Test
    public void findAllProjectsByGameSlugAndProjectTypeAndVersion () {

        Assertions.assertEquals(1, ConfluenciaTest.PROJECT.findAllByGameAndProjectType("minecraft-je", "forge-mods", "", 1, 10, ProjectSort.NEW, "1.15.2", new String[0]).size());
        Assertions.assertEquals(1, ConfluenciaTest.PROJECT.findAllByGameAndProjectType("minecraft-je", "forge-mods", "", 1, 10, ProjectSort.NEW, "1.15.2", new String[]{"tech"}).size());
        Assertions.assertEquals(1, ConfluenciaTest.PROJECT.findAllByGameAndProjectType("minecraft-je", "forge-mods", "", 1, 10, ProjectSort.NEW, "1.15.2", new String[]{"tech"}).size());
        Assertions.assertEquals(1, ConfluenciaTest.PROJECT.findAllByGameAndProjectType("minecraft-je", "forge-mods", "", 1, 10, ProjectSort.NEW, null, new String[]{"tech", "magic"}).size());
        Assertions.assertEquals(2, ConfluenciaTest.PROJECT.findAllByGameAndProjectType("minecraft-je", "forge-mods", "", 1, 10, ProjectSort.NEW, null, new String[]{"magic"}).size());
        Assertions.assertEquals(2, ConfluenciaTest.PROJECT.findAllByGameAndProjectType("minecraft-je", "forge-mods", "", 1, 10, ProjectSort.NEW, null, new String[]{"tech"}).size());
    }

    @Test
    public void findFeaturedProjects () {

        Assertions.assertEquals(4, ConfluenciaTest.PROJECT.findFeaturedProjects().size());
    }

    @Test
    public void findAllProjectAuthorsByProjectId () {

        Assertions.assertEquals(2, ConfluenciaTest.PROJECT.findAllProjectAuthorsByProjectId(1).size());
    }

    @Test
    public void insertProject () {

        Assertions.assertNotNull(ConfluenciaTest.PROJECT.insertProject("project_insert", "Insert", "Insert Summary", "Insert Description", 2, "minecraft-je", "forge-mods"));
    }

    @Test
    public void findAllTagsByGameSlugAndProjectTypeSlug () {

        Assertions.assertEquals(0, ConfluenciaTest.PROJECT.findAllTagsByGameSlugAndProjectTypeSlug("invalid", "invalid").size());
        Assertions.assertEquals(0, ConfluenciaTest.PROJECT.findAllTagsByGameSlugAndProjectTypeSlug("minecraft-je", "invalid").size());
        Assertions.assertEquals(2, ConfluenciaTest.PROJECT.findAllTagsByGameSlugAndProjectTypeSlug("minecraft-je", "forge-mods").size());
    }

    @Test
    public void findAllTagsByProjectId () {

        Assertions.assertEquals(1, ConfluenciaTest.PROJECT.findAllTagsByProjectId(1).size());
    }

    @Test
    public void findAllLinksByProjectId () {

        Assertions.assertEquals(1, ConfluenciaTest.PROJECT.findAllLinksByProjectId(1).size());
    }

    @Test
    public void insertProjectTags () {

        Assertions.assertTrue(ConfluenciaTest.PROJECT.insertProjectTags(25, Arrays.asList(1L, 2L)));
    }

    @Test
    public void updateProject(){
        Assertions.assertTrue(ConfluenciaTest.PROJECT.updateProject(48, "Surge 2", "Surge 2 summary", "Surge Description Testing"));
    }
}
