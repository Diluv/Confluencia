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

public class TestFileDatabase extends ConfluenciaTest {

    @Test
    public void insertProjectFileAntivirus () {

        ProjectFileAntivirusEntity projectFileAntivirus = new ProjectFileAntivirusEntity();
        projectFileAntivirus.setProjectFile(new ProjectFilesEntity(16));
        projectFileAntivirus.setMalware("Testing malware");
        Assertions.assertTrue(Confluencia.FILE.insertProjectFileAntivirus(projectFileAntivirus));
    }

    @Test
    public void findAllWhereStatusAndLimit () {

        Assertions.assertEquals(5, Confluencia.FILE.findAllWhereStatusAndLimit(FileProcessingStatus.PENDING, 5).size());
    }

    @Test
    public void getLatestFiles () {

        Assertions.assertEquals(1, Confluencia.FILE.getLatestFiles(1).size());
    }

    @Test
    public void updateStatusById () {

        Assertions.assertTrue(Confluencia.FILE.updateStatusById(FileProcessingStatus.RUNNING, 1));
    }

    @Test
    public void updateStatusByStatus () {

        Assertions.assertTrue(Confluencia.FILE.updateStatusByStatus(FileProcessingStatus.PENDING, FileProcessingStatus.RUNNING));
        Assertions.assertTrue(Confluencia.FILE.updateStatusByStatus(FileProcessingStatus.PENDING, FileProcessingStatus.RUNNING));
    }

    @Test
    public void insertProjectFile () {

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

        Assertions.assertTrue(Confluencia.FILE.insertProjectFile(projectFile));
        Assertions.assertTrue(projectFile.getId() > 1L);
    }

    @Test
    public void findOneById () {

        Assertions.assertNotNull(Confluencia.FILE.findOneById(1));
        Assertions.assertNull(Confluencia.FILE.findOneById(100));
    }

    @Test
    public void findAllByProjectId () {

        ProjectsEntity project = new ProjectsEntity(1);
        project.setGame(new GamesEntity("minecraft-je"));
        Assertions.assertEquals(16, Confluencia.FILE.findAllByProject(project, true, 1, 25, ProjectFileSort.NEW, null).size());
        Assertions.assertEquals(1, Confluencia.FILE.findAllByProject(project, true, 1, 25, ProjectFileSort.NEW, "1.12.2").size());
        Assertions.assertEquals(0, Confluencia.FILE.findAllByProject(project, true, 1, 25, ProjectFileSort.NEW, "invalid").size());
    }

    @Test
    public void existsByProjectIdAndVersion () {

        Assertions.assertTrue(Confluencia.FILE.existsByProjectIdAndVersion(1, "1.0.0"));
        Assertions.assertFalse(Confluencia.FILE.existsByProjectIdAndVersion(1, "invalid"));
    }

    @Test
    public void insertProjectFileDownload () {

        ProjectFileDownloadsEntity entity = new ProjectFileDownloadsEntity(new ProjectFilesEntity(1), "811a90e1c8e86c7b4c0eef5b2c0bf0ec1b19c4b1b5a242e6455be93787cb473cb7bc9b0fdeb960d00d5c6881c2094dd63c5c900ce9057255e2a4e271fc25fef1");
        Assertions.assertTrue(Confluencia.FILE.insertProjectFileDownloads(entity));
        Assertions.assertTrue(Confluencia.FILE.insertProjectFileDownloads(entity));
    }

    @Test
    public void updateAllForRelease () {

        Assertions.assertTrue(Confluencia.FILE.updateAllForRelease(new Timestamp(System.currentTimeMillis())));
    }
}
