package com.diluv.confluencia.database;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.diluv.confluencia.ConfluenciaTest;
import com.diluv.confluencia.database.record.FileProcessingStatus;
import com.diluv.confluencia.database.record.GamesEntity;
import com.diluv.confluencia.database.record.ProjectFileAntivirusEntity;
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
        Assertions.assertTrue(ConfluenciaTest.FILE.insertProjectFileAntivirus(projectFileAntivirus));
    }

    @Test
    public void findAllWhereStatusAndLimit () {

        Assertions.assertEquals(5, ConfluenciaTest.FILE.findAllWhereStatusAndLimit(FileProcessingStatus.PENDING, 5).size());
    }

    @Test
    public void getLatestFiles () {

        Assertions.assertEquals(1, ConfluenciaTest.FILE.getLatestFiles(1).size());
    }

    @Test
    public void updateStatusById () {

        Assertions.assertTrue(ConfluenciaTest.FILE.updateStatusById(FileProcessingStatus.RUNNING, 1));
    }

    @Test
    public void updateStatusByStatus () {

        Assertions.assertTrue(ConfluenciaTest.FILE.updateStatusByStatus(FileProcessingStatus.PENDING, FileProcessingStatus.RUNNING));
        Assertions.assertTrue(ConfluenciaTest.FILE.updateStatusByStatus(FileProcessingStatus.PENDING, FileProcessingStatus.RUNNING));
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

        Assertions.assertTrue(ConfluenciaTest.FILE.insertProjectFile(projectFile));
        Assertions.assertTrue(projectFile.getId() > 1L);
    }

    @Test
    public void findOneById () {

        Assertions.assertNotNull(ConfluenciaTest.FILE.findOneById(1));
        Assertions.assertNull(ConfluenciaTest.FILE.findOneById(100));
    }

    @Test
    public void findAllByProjectId () {

        ProjectsEntity project = new ProjectsEntity(1);
        project.setGame(new GamesEntity("minecraft-je"));
        Assertions.assertEquals(16, ConfluenciaTest.FILE.findAllByProject(project, true, 1, 25, ProjectFileSort.NEW, null).size());
        Assertions.assertEquals(1, ConfluenciaTest.FILE.findAllByProject(project, true, 1, 25, ProjectFileSort.NEW, "1.12.2").size());
        Assertions.assertEquals(0, ConfluenciaTest.FILE.findAllByProject(project, true, 1, 25, ProjectFileSort.NEW, "invalid").size());
    }

    @Test
    public void existsByProjectIdAndVersion () {

        Assertions.assertTrue(ConfluenciaTest.FILE.existsByProjectIdAndVersion(1, "1.0.0"));
        Assertions.assertFalse(ConfluenciaTest.FILE.existsByProjectIdAndVersion(1, "invalid"));
    }
}
