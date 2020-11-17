package com.diluv.confluencia.database;

import java.util.HashSet;
import java.util.Set;

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

        Confluencia.getTransaction(session -> {
            Assertions.assertEquals(128, Confluencia.PROJECT.countAllByGameSlug(session, "minecraft-je"));
        });
    }

    @Test
    public void countAllByGameSlugAndProjectTypeSlug () {

        Confluencia.getTransaction(session -> {

            Assertions.assertEquals(0, Confluencia.PROJECT.countAllByGameSlugAndProjectTypeSlug(session, "invalid", "invalid"));
            Assertions.assertEquals(0, Confluencia.PROJECT.countAllByGameSlugAndProjectTypeSlug(session, "minecraft-je", "invalid"));
            Assertions.assertEquals(129, Confluencia.PROJECT.countAllByGameSlugAndProjectTypeSlug(session, "minecraft-je", "mods"));
        });
    }

    @Test
    public void findOneProjectByProjectId () {

        Confluencia.getTransaction(session -> {
            Assertions.assertNotNull(Confluencia.PROJECT.findOneProjectByProjectId(session, 1));
        });
    }

    @Test
    public void findOneProjectByGameSlugAndProjectTypeSlugAndProjectSlug () {

        Confluencia.getTransaction(session -> {
            Assertions.assertNull(Confluencia.PROJECT.findOneProject(session, "invalid", "invalid", "invalid"));
            Assertions.assertNull(Confluencia.PROJECT.findOneProject(session, "minecraft-je", "invalid", "invalid"));
            Assertions.assertNull(Confluencia.PROJECT.findOneProject(session, "minecraft-je", "mods", "invalid"));
            Assertions.assertNotNull(Confluencia.PROJECT.findOneProject(session, "minecraft-je", "mods", "bookshelf"));
        });
    }

    @Test
    public void findOneProjectTypeByGameSlugAndProjectTypeSlug () {

        Confluencia.getTransaction(session -> {
            Assertions.assertNull(Confluencia.PROJECT.findOneProjectTypeByGameSlugAndProjectTypeSlug(session, "invalid", "invalid"));
            Assertions.assertNull(Confluencia.PROJECT.findOneProjectTypeByGameSlugAndProjectTypeSlug(session, "minecraft-je", "invalid"));
            Assertions.assertNotNull(Confluencia.PROJECT.findOneProjectTypeByGameSlugAndProjectTypeSlug(session, "minecraft-je", "mods"));
        });
    }


    @Test
    public void countAllByUsername () {

        Confluencia.getTransaction(session -> {
            Assertions.assertEquals(0, Confluencia.PROJECT.countAllByUsername(session, "invalid", false));
            Assertions.assertEquals(83, Confluencia.PROJECT.countAllByUsername(session, "darkhax", false));
            Assertions.assertEquals(51, Confluencia.PROJECT.countAllByUsername(session, "jaredlll08", false));
        });
    }

    @Test
    public void findAllByUsername () {

        Confluencia.getTransaction(session -> {
            Assertions.assertEquals(0, Confluencia.PROJECT.findAllByUsername(session, "invalid", false, 1, 200, ProjectSort.NEW).size());
            Assertions.assertEquals(83, Confluencia.PROJECT.findAllByUsername(session, "darkhax", false, 1, 200, ProjectSort.NEW).size());
            Assertions.assertEquals(51, Confluencia.PROJECT.findAllByUsername(session, "jaredlll08", false, 1, 200, ProjectSort.NEW).size());
        });
    }

    @Test
    public void findAllProjectsByProjectIds () {

        Confluencia.getTransaction(session -> {
            Set<Long> set = new HashSet<>();
            set.add(1L);
            set.add(2L);
            set.add(3L);
            Assertions.assertEquals(3, Confluencia.PROJECT.findAllProjectsByProjectIds(session, set).size());
            set.add(1000L);
            Assertions.assertEquals(3, Confluencia.PROJECT.findAllProjectsByProjectIds(session, set).size());
        });
    }


    @Test
    public void findAllProjectsByGameSlugAndProjectType () {

        Confluencia.getTransaction(session -> {
            Assertions.assertEquals(0, Confluencia.PROJECT.findAllByGameAndProjectType(session, "invalid", "invalid", "", 1, 10, ProjectSort.NEW).size());
            Assertions.assertEquals(0, Confluencia.PROJECT.findAllByGameAndProjectType(session, "minecraft-je", "invalid", "", 1, 10, ProjectSort.NEW).size());
            Assertions.assertEquals(10, Confluencia.PROJECT.findAllByGameAndProjectType(session, "minecraft-je", "mods", "", 1, 10, ProjectSort.NEW).size());
        });
    }

    @Test
    public void findAllProjectsByGameSlugAndProjectTypeAndVersion () {

        Confluencia.getTransaction(session -> {
            Assertions.assertEquals(1, Confluencia.PROJECT.findAllByGameAndProjectType(session, "minecraft-je", "mods", "", 1, 10, ProjectSort.NEW, "1.15.2", new String[0], new String[0]).size());
            Assertions.assertEquals(1, Confluencia.PROJECT.findAllByGameAndProjectType(session, "minecraft-je", "mods", "", 1, 10, ProjectSort.NEW, "1.15.2", new String[]{"tech"}, new String[0]).size());
            Assertions.assertEquals(1, Confluencia.PROJECT.findAllByGameAndProjectType(session, "minecraft-je", "mods", "", 1, 10, ProjectSort.NEW, "1.15.2", new String[]{"tech"}, new String[]{"forge"}).size());
            Assertions.assertEquals(0, Confluencia.PROJECT.findAllByGameAndProjectType(session, "minecraft-je", "mods", "", 1, 10, ProjectSort.NEW, null, new String[]{"tech", "magic"}, new String[]{"forge"}).size());
            Assertions.assertEquals(1, Confluencia.PROJECT.findAllByGameAndProjectType(session, "minecraft-je", "mods", "", 1, 10, ProjectSort.NEW, null, new String[0], new String[]{"forge"}).size());
            Assertions.assertEquals(2, Confluencia.PROJECT.findAllByGameAndProjectType(session, "minecraft-je", "mods", "", 1, 10, ProjectSort.NEW, null, new String[]{"magic"}, new String[0]).size());
            Assertions.assertEquals(1, Confluencia.PROJECT.findAllByGameAndProjectType(session, "minecraft-je", "mods", "", 1, 10, ProjectSort.NEW, null, new String[]{"tech"}, new String[]{"forge"}).size());
        });
    }

    @Test
    public void findFeaturedProjects () {

        Confluencia.getTransaction(session -> {
            Assertions.assertEquals(4, Confluencia.PROJECT.findFeaturedProjects(session).size());
        });
    }

    @Test
    public void insertProject () {

        Confluencia.getTransaction(session -> {
            ProjectsEntity project = new ProjectsEntity();
            project.setSlug("project_insert");
            project.setName("Insert");
            project.setSummary("Insert Summary");
            project.setDescription("Insert Description");
            project.setOwner(new UsersEntity(2));
            project.setProjectType(new ProjectTypesEntity(new GamesEntity("minecraft-je"), "mods"));
            session.save(project);
            Assertions.assertTrue(project.getId() > 0);
        });
    }

    @Test
    public void updateProject () {

        Confluencia.getTransaction(session -> {
            ProjectsEntity project = Confluencia.PROJECT.findOneProjectByProjectId(session, 5);
            project.setName("Testing");

            session.update(project);
            Assertions.assertEquals("Testing", Confluencia.PROJECT.findOneProjectByProjectId(session, 5).getName());
        });
    }

    @Test
    public void findAllTagsByGameSlugAndProjectTypeSlug () {

        Confluencia.getTransaction(session -> {
            Assertions.assertEquals(0, Confluencia.PROJECT.findAllTagsByGameSlugAndProjectTypeSlug(session, new ProjectTypesEntity(new GamesEntity("invalid"), "invalid")).size());
            Assertions.assertEquals(0, Confluencia.PROJECT.findAllTagsByGameSlugAndProjectTypeSlug(session, new ProjectTypesEntity(new GamesEntity("minecraft-je"), "invalid")).size());
            Assertions.assertEquals(2, Confluencia.PROJECT.findAllTagsByGameSlugAndProjectTypeSlug(session, new ProjectTypesEntity(new GamesEntity("minecraft-je"), "mods")).size());
        });
    }

    @Test
    public void findAllByReview () {

        Confluencia.getTransaction(session -> {
            Assertions.assertEquals(7, Confluencia.PROJECT.findAllByReview(session, 1, 10).size());
        });
    }
}
