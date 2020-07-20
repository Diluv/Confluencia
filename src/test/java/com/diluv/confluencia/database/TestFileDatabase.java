package com.diluv.confluencia.database;

import com.diluv.confluencia.ConfluenciaTest;
import com.diluv.confluencia.database.record.FileProcessingStatus;
import com.diluv.confluencia.database.record.ProjectFilesEntity;
import com.diluv.confluencia.database.record.ProjectsEntity;
import com.diluv.confluencia.database.record.UsersEntity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestFileDatabase extends ConfluenciaTest {

    @Test
    public void findAllWhereStatusAndLimit () {

        Assertions.assertEquals(5, ConfluenciaTest.FILE.findAllWhereStatusAndLimit(FileProcessingStatus.PENDING, 5).size());
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
    public void findOneProjectFileQueueByFileId () {

        Assertions.assertNotNull(ConfluenciaTest.FILE.findOneById(1));
        Assertions.assertNull(ConfluenciaTest.FILE.findOneById(100));
    }
}
