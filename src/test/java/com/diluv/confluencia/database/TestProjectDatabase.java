package com.diluv.confluencia.database;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.diluv.confluencia.Confluencia;
import com.diluv.confluencia.ConfluenciaTest;
import com.diluv.confluencia.database.record.GamesEntity;
import com.diluv.confluencia.database.record.ProjectTypesEntity;
import com.diluv.confluencia.database.record.ProjectsEntity;
import com.diluv.confluencia.database.record.UsersEntity;
import com.diluv.confluencia.database.sort.ProjectSort;

public class TestProjectDatabase extends ConfluenciaTest {

    @Test
    public void countAllProjectsBySlug () {

        Assertions.assertEquals(130, Confluencia.PROJECT.countAllByGameSlug("minecraft-je"));
    }

    @Test
    public void countAllByGameSlugAndProjectTypeSlug () {

        Assertions.assertEquals(0, Confluencia.PROJECT.countAllByGameSlugAndProjectTypeSlug("invalid", "invalid"));
        Assertions.assertEquals(0, Confluencia.PROJECT.countAllByGameSlugAndProjectTypeSlug("minecraft-je", "invalid"));
        Assertions.assertEquals(129, Confluencia.PROJECT.countAllByGameSlugAndProjectTypeSlug("minecraft-je", "forge-mods"));
    }

    @Test
    public void findOneProjectByProjectId () {

        Assertions.assertNotNull(Confluencia.PROJECT.findOneProjectByProjectId(1));
    }

    @Test
    public void findOneProjectByGameSlugAndProjectTypeSlugAndProjectSlug () {

        Assertions.assertNull(Confluencia.PROJECT.findOneProjectByGameSlugAndProjectTypeSlugAndProjectSlug("invalid", "invalid", "invalid"));
        Assertions.assertNull(Confluencia.PROJECT.findOneProjectByGameSlugAndProjectTypeSlugAndProjectSlug("minecraft-je", "invalid", "invalid"));
        Assertions.assertNull(Confluencia.PROJECT.findOneProjectByGameSlugAndProjectTypeSlugAndProjectSlug("minecraft-je", "forge-mods", "invalid"));
        Assertions.assertNotNull(Confluencia.PROJECT.findOneProjectByGameSlugAndProjectTypeSlugAndProjectSlug("minecraft-je", "forge-mods", "bookshelf"));
    }

    @Test
    public void findOneProjectTypeByGameSlugAndProjectTypeSlug () {

        Assertions.assertNull(Confluencia.PROJECT.findOneProjectTypeByGameSlugAndProjectTypeSlug("invalid", "invalid"));
        Assertions.assertNull(Confluencia.PROJECT.findOneProjectTypeByGameSlugAndProjectTypeSlug("minecraft-je", "invalid"));
        Assertions.assertNotNull(Confluencia.PROJECT.findOneProjectTypeByGameSlugAndProjectTypeSlug("minecraft-je", "forge-mods"));
    }

    @Test
    public void countAllByUsername () {

        Assertions.assertEquals(0, Confluencia.PROJECT.countAllByUsername("invalid", false));
        Assertions.assertEquals(84, Confluencia.PROJECT.countAllByUsername("darkhax", false));
        Assertions.assertEquals(51, Confluencia.PROJECT.countAllByUsername("jaredlll08", false));
    }

    @Test
    public void findAllByUsername () {

        Assertions.assertEquals(0, Confluencia.PROJECT.findAllByUsername("invalid", false, 1, 200, ProjectSort.NEW).size());
        Assertions.assertEquals(84, Confluencia.PROJECT.findAllByUsername("darkhax", false, 1, 200, ProjectSort.NEW).size());
        Assertions.assertEquals(51, Confluencia.PROJECT.findAllByUsername("jaredlll08", false, 1, 200, ProjectSort.NEW).size());
    }

    @Test
    public void findAllProjectsByProjectIds () {

        Assertions.assertEquals(3, Confluencia.PROJECT.findAllProjectsByProjectIds(new long[]{1, 2, 3}).size());
        Assertions.assertEquals(2, Confluencia.PROJECT.findAllProjectsByProjectIds(new long[]{1000, 2, 3}).size());
    }


    @Test
    public void findAllProjectsByGameSlugAndProjectType () {

        Assertions.assertEquals(0, Confluencia.PROJECT.findAllByGameAndProjectType("invalid", "invalid", "", 1, 10, ProjectSort.NEW).size());
        Assertions.assertEquals(0, Confluencia.PROJECT.findAllByGameAndProjectType("minecraft-je", "invalid", "", 1, 10, ProjectSort.NEW).size());
        Assertions.assertEquals(10, Confluencia.PROJECT.findAllByGameAndProjectType("minecraft-je", "forge-mods", "", 1, 10, ProjectSort.NEW).size());
    }

    @Test
    public void findAllProjectsByGameSlugAndProjectTypeAndVersion () {

        Assertions.assertEquals(1, Confluencia.PROJECT.findAllByGameAndProjectType("minecraft-je", "forge-mods", "", 1, 10, ProjectSort.NEW, "1.15.2", new String[0]).size());
        Assertions.assertEquals(1, Confluencia.PROJECT.findAllByGameAndProjectType("minecraft-je", "forge-mods", "", 1, 10, ProjectSort.NEW, "1.15.2", new String[]{"tech"}).size());
        Assertions.assertEquals(1, Confluencia.PROJECT.findAllByGameAndProjectType("minecraft-je", "forge-mods", "", 1, 10, ProjectSort.NEW, "1.15.2", new String[]{"tech"}).size());
        Assertions.assertEquals(1, Confluencia.PROJECT.findAllByGameAndProjectType("minecraft-je", "forge-mods", "", 1, 10, ProjectSort.NEW, null, new String[]{"tech", "magic"}).size());
        Assertions.assertEquals(2, Confluencia.PROJECT.findAllByGameAndProjectType("minecraft-je", "forge-mods", "", 1, 10, ProjectSort.NEW, null, new String[]{"magic"}).size());
        Assertions.assertEquals(2, Confluencia.PROJECT.findAllByGameAndProjectType("minecraft-je", "forge-mods", "", 1, 10, ProjectSort.NEW, null, new String[]{"tech"}).size());
    }

    @Test
    public void findFeaturedProjects () {

        Assertions.assertEquals(4, Confluencia.PROJECT.findFeaturedProjects().size());
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
        Assertions.assertTrue(Confluencia.PROJECT.insertProject(project));
    }

    @Test
    public void updateProject () {

        ProjectsEntity project = Confluencia.PROJECT.findOneProjectByProjectId(5);
        project.setName("Testing");

        Assertions.assertTrue(Confluencia.PROJECT.updateProject(project));
    }

    @Test
    public void findProjectsByProjectFileHash () {

        Assertions.assertEquals(1, Confluencia.PROJECT.findProjectsByProjectFileHash("5E96A9A98839D073C298BBD0AC73A510E1F13A64151E2C4895440ECDBCD6D483EDA994D2CD5E69C5C00A96783280F7BC1E933667B4A25C53CE3918007D5C77E3", 1, 10, ProjectSort.NEW).size());
    }
}
