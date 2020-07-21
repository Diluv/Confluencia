package com.diluv.confluencia.database;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.diluv.confluencia.ConfluenciaTest;
import com.diluv.confluencia.database.record.GamesEntity;
import com.diluv.confluencia.database.record.ProjectTypesEntity;
import com.diluv.confluencia.database.record.ProjectsEntity;
import com.diluv.confluencia.database.record.UsersEntity;
import com.diluv.confluencia.database.sort.ProjectSort;

public class TestProjectDatabase extends ConfluenciaTest {

    @Test
    public void countAllProjectsBySlug () {

        Assertions.assertEquals(129, ConfluenciaTest.PROJECT.countAllByGameSlug("minecraft-je"));
    }

    @Test
    public void countAllByGameSlugAndProjectTypeSlug () {

        Assertions.assertEquals(0, ConfluenciaTest.PROJECT.countAllByGameSlugAndProjectTypeSlug("invalid", "invalid"));
        Assertions.assertEquals(0, ConfluenciaTest.PROJECT.countAllByGameSlugAndProjectTypeSlug("minecraft-je", "invalid"));
        Assertions.assertEquals(129, ConfluenciaTest.PROJECT.countAllByGameSlugAndProjectTypeSlug("minecraft-je", "forge-mods"));
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
    public void findOneProjectTypeByGameSlugAndProjectTypeSlug () {

        Assertions.assertNull(ConfluenciaTest.PROJECT.findOneProjectTypeByGameSlugAndProjectTypeSlug("invalid", "invalid"));
        Assertions.assertNull(ConfluenciaTest.PROJECT.findOneProjectTypeByGameSlugAndProjectTypeSlug("minecraft-je", "invalid"));
        Assertions.assertNotNull(ConfluenciaTest.PROJECT.findOneProjectTypeByGameSlugAndProjectTypeSlug("minecraft-je", "forge-mods"));
    }

    @Test
    public void countAllByUsername () {

        Assertions.assertEquals(0, ConfluenciaTest.PROJECT.countAllByUsername("invalid", false));
        Assertions.assertEquals(84, ConfluenciaTest.PROJECT.countAllByUsername("darkhax", false));
        Assertions.assertEquals(51, ConfluenciaTest.PROJECT.countAllByUsername("jaredlll08", false));
    }

    @Test
    public void findAllByUsername () {

        Assertions.assertEquals(0, ConfluenciaTest.PROJECT.findAllByUsername("invalid", false, 1, 200, ProjectSort.NEW).size());
        Assertions.assertEquals(84, ConfluenciaTest.PROJECT.findAllByUsername("darkhax", false, 1, 200, ProjectSort.NEW).size());
        Assertions.assertEquals(51, ConfluenciaTest.PROJECT.findAllByUsername("jaredlll08", false, 1, 200, ProjectSort.NEW).size());
    }

    @Test
    public void findAllProjectsByProjectIds () {

        Assertions.assertEquals(3, ConfluenciaTest.PROJECT.findAllProjectsByProjectIds(new long[]{1, 2, 3}).size());
        Assertions.assertEquals(2, ConfluenciaTest.PROJECT.findAllProjectsByProjectIds(new long[]{1000, 2, 3}).size());
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
    public void insertProject () {

        ProjectsEntity project = new ProjectsEntity();
        project.setSlug("project_insert");
        project.setName("Insert");
        project.setSummary("Insert Summary");
        project.setDescription("Insert Description");
        project.setOwner(new UsersEntity(2));
        project.setProjectType(new ProjectTypesEntity(new GamesEntity("minecraft-je"), "forge-mods"));
        Assertions.assertTrue(ConfluenciaTest.PROJECT.insertProject(project));
    }
}
