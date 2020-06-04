package com.diluv.confluencia.database;

import java.sql.SQLException;
import java.util.Arrays;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.diluv.confluencia.ConfluenciaTest;
import com.diluv.confluencia.database.record.FileProcessingStatus;
import com.diluv.confluencia.database.sort.Order;
import com.diluv.confluencia.database.sort.ProjectFileSort;
import com.diluv.confluencia.database.sort.Sort;

public class TestFileDatabase extends ConfluenciaTest {

    @Test
    public void findAllWhereStatusAndLimit () {

        Assertions.assertEquals(5, ConfluenciaTest.FILE.findAllWhereStatusAndLimit(FileProcessingStatus.PENDING, 5).size());
    }

    @Test
    public void updateStatusById () throws SQLException {

        Assertions.assertTrue(ConfluenciaTest.FILE.updateStatusById(FileProcessingStatus.RUNNING, 1));
    }

    @Test
    public void updateStatusByStatus () {

        Assertions.assertTrue(ConfluenciaTest.FILE.updateStatusByStatus(FileProcessingStatus.PENDING, FileProcessingStatus.RUNNING));
        Assertions.assertTrue(ConfluenciaTest.FILE.updateStatusByStatus(FileProcessingStatus.PENDING, FileProcessingStatus.RUNNING));
    }

    @Test
    public void findAllByProjectId () {

        Assertions.assertEquals(0, ConfluenciaTest.FILE.findAllByProjectId(0, false, 1, 1, ProjectFileSort.NEW).size());
        Assertions.assertEquals(1, ConfluenciaTest.FILE.findAllByProjectId(1, false, 1, 1, ProjectFileSort.NEW).size());

        Assertions.assertEquals(0, ConfluenciaTest.FILE.findAllByProjectId(2, false, 1, 1, ProjectFileSort.NEW).size());
        Assertions.assertEquals(0, ConfluenciaTest.FILE.findAllByProjectId(2, true, 1, 1, ProjectFileSort.NEW).size());
    }

    @Test
    public void findAllByProjectIdWhereVersion () {

        Assertions.assertEquals(1, ConfluenciaTest.FILE.findAllByProjectIdWhereVersion(1, false, 1, 1, ProjectFileSort.NEW, "1.15.2").size());
    }

    @Test
    public void insertProjectFile () {

        Long id = ConfluenciaTest.FILE.insertProjectFile("test.jar", "1.0.20", 10, "", "sha512", "release", "binary", 1, 1);
        Assertions.assertNotNull(id);
        Assertions.assertTrue(id > 1L);
    }

    @Test
    public void findOneProjectFileQueueByFileId () {

        Assertions.assertNotNull(ConfluenciaTest.FILE.findOneProjectFileQueueByFileId(1));
        Assertions.assertNull(ConfluenciaTest.FILE.findOneProjectFileQueueByFileId(100));
    }

    @Test
    public void insertProjectFileAntivirus () {

        Assertions.assertTrue(ConfluenciaTest.FILE.insertProjectFileAntivirus(5, "Java.Malware.Agent-5601374-0"));
    }

    @Test
    public void getLatestFiles () {

        try {
            Assertions.assertEquals(1, ConfluenciaTest.FILE.getLatestFiles(1).size());
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void findAllProjectDependenciesById () {

        Assertions.assertEquals(2, ConfluenciaTest.FILE.findAllProjectDependenciesById(1).size());
    }

    @Test
    public void findAllGameVersionsById () {

        Assertions.assertEquals(3, ConfluenciaTest.FILE.findAllGameVersionsById(1).size());
    }

    @Test
    public void insertProjectFileDependency () {

        Assertions.assertTrue(ConfluenciaTest.FILE.insertProjectFileDependency(2, Arrays.asList(2L, 3L)));
    }

    @Test
    public void insertProjectFileGameVersions () {

        Assertions.assertTrue(ConfluenciaTest.FILE.insertProjectFileGameVersions(1, Arrays.asList(1L, 2L)));
    }

    @Test
    public void existsByProjectIdAndVersion () {

        Assertions.assertTrue(ConfluenciaTest.FILE.existsByProjectIdAndVersion(1, "1.0.0"));
        Assertions.assertFalse(ConfluenciaTest.FILE.existsByProjectIdAndVersion(1, "invalid"));
    }
}
