package com.diluv.confluencia.database;

import java.sql.Timestamp;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.diluv.confluencia.Confluencia;
import com.diluv.confluencia.ConfluenciaTest;
import com.diluv.confluencia.database.record.FileProcessingStatus;
import com.diluv.confluencia.database.record.GamesEntity;
import com.diluv.confluencia.database.record.ProjectFileAntivirusEntity;
import com.diluv.confluencia.database.record.ProjectFileDownloadsEntity;
import com.diluv.confluencia.database.record.ProjectFilesEntity;
import com.diluv.confluencia.database.record.ProjectsEntity;
import com.diluv.confluencia.database.record.UsersEntity;
import com.diluv.confluencia.database.sort.ProjectFileSort;
import com.diluv.confluencia.database.sort.ProjectSort;

public class TestFileDatabase extends ConfluenciaTest {

    @Test
    public void insertProjectFileAntivirus () {

        Confluencia.getTransaction(session -> {
            ProjectFileAntivirusEntity projectFileAntivirus = new ProjectFileAntivirusEntity();
            projectFileAntivirus.setProjectFile(new ProjectFilesEntity(16));
            projectFileAntivirus.setMalware("Testing malware");
            session.save(projectFileAntivirus);
            Assertions.assertTrue(true);
        });
    }

    @Test
    public void findAllWhereStatusAndLimit () {

        Confluencia.getTransaction(session -> {
            Assertions.assertEquals(5, Confluencia.FILE.findAllWhereStatusAndLimit(session, FileProcessingStatus.PENDING, 5).size());
        });
    }

    @Test
    public void getLatestFiles () {

        Confluencia.getTransaction(session -> {
            Assertions.assertEquals(1, Confluencia.FILE.getLatestFiles(session, 1).size());
        });
    }

    @Test
    public void updateStatusById () {

        Confluencia.getTransaction(session -> {
            Assertions.assertTrue(Confluencia.FILE.updateStatusById(session, FileProcessingStatus.RUNNING, 1));
        });
    }

    @Test
    public void updateStatusByStatus () {

        Confluencia.getTransaction(session -> {
            Assertions.assertTrue(Confluencia.FILE.updateStatusByStatus(session, FileProcessingStatus.PENDING, FileProcessingStatus.RUNNING));
            Assertions.assertTrue(Confluencia.FILE.updateStatusByStatus(session, FileProcessingStatus.PENDING, FileProcessingStatus.RUNNING));
        });
    }

    @Test
    public void insertProjectFile () {

        Confluencia.getTransaction(session -> {
            ProjectFilesEntity projectFile = new ProjectFilesEntity();
            projectFile.setName("test.jar");
            projectFile.setVersion("1.0.20");
            projectFile.setSize(10);
            projectFile.setChangelog("");
            projectFile.setSha512("sha512");
            projectFile.setReleaseType("release");
            projectFile.setClassifier("binary");
            projectFile.setProject(new ProjectsEntity(1));
            projectFile.setUser(new UsersEntity(1));
            session.save(projectFile);
            Assertions.assertTrue(projectFile.getId() > 1L);
        });
    }

    @Test
    public void findOneById () {

        Confluencia.getTransaction(session -> {
            Assertions.assertNotNull(Confluencia.FILE.findOneById(session, 1));
            Assertions.assertNull(Confluencia.FILE.findOneById(session, 100));
        });
    }

    @Test
    public void findAllByProjectId () {

        Confluencia.getTransaction(session -> {
            ProjectsEntity project = new ProjectsEntity(1);
            project.setGame(new GamesEntity("minecraft-je"));
            Assertions.assertEquals(16, Confluencia.FILE.findAllByProject(session, project, true, 1, 25, ProjectFileSort.NEW, null).size());
            Assertions.assertEquals(1, Confluencia.FILE.findAllByProject(session, project, true, 1, 25, ProjectFileSort.NEW, "1.12.2").size());
            Assertions.assertEquals(0, Confluencia.FILE.findAllByProject(session, project, true, 1, 25, ProjectFileSort.NEW, "invalid").size());
        });
    }

    @Test
    public void existsByProjectIdAndVersion () {

        Confluencia.getTransaction(session -> {
            Assertions.assertTrue(Confluencia.FILE.existsByProjectIdAndVersion(session, 1, "1.0.0"));
            Assertions.assertFalse(Confluencia.FILE.existsByProjectIdAndVersion(session, 1, "invalid"));
        });
    }

    @Test
    public void findProjectFilesByHash () {

        Confluencia.getTransaction(session -> {
            Assertions.assertEquals(8, Confluencia.FILE.findProjectFilesByHash(session, "5E96A9A98839D073C298BBD0AC73A510E1F13A64151E2C4895440ECDBCD6D483EDA994D2CD5E69C5C00A96783280F7BC1E933667B4A25C53CE3918007D5C77E3", 1, 10, ProjectSort.NEW).size());
        });
    }

    @Test
    public void insertProjectFileDownload () {

        Confluencia.getTransaction(session -> {
            ProjectFileDownloadsEntity entity = new ProjectFileDownloadsEntity(new ProjectFilesEntity(1), "811a90e1c8e86c7b4c0eef5b2c0bf0ec1b19c4b1b5a242e6455be93787cb473cb7bc9b0fdeb960d00d5c6881c2094dd63c5c900ce9057255e2a4e271fc25fef1");
            session.save(entity);
            session.save(entity);
        });
    }

    @Test
    public void updateAllForRelease () {

        Confluencia.getTransaction(session -> {
            Assertions.assertEquals(1, Confluencia.FILE.updateAllForRelease(session, new Timestamp(System.currentTimeMillis())));
        });
    }
}
