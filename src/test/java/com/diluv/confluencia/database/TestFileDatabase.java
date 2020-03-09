package com.diluv.confluencia.database;

import java.sql.SQLException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.diluv.confluencia.ConfluenciaTest;
import com.diluv.confluencia.database.record.FileProcessingStatus;
import com.diluv.confluencia.utils.Pagination;

public class TestFileDatabase extends ConfluenciaTest {

    @Test
    public void findAllWhereStatusAndLimit () {

        Assertions.assertEquals(1, ConfluenciaTest.FILE.findAllWhereStatusAndLimit(FileProcessingStatus.PENDING, 5).size());
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
    public void findAllByGameSlugAndProjectTypeAndProjectSlug () {

        Assertions.assertEquals(0, ConfluenciaTest.FILE.findAllByGameSlugAndProjectTypeAndProjectSlug("invalid", "invalid", "invalid", new Pagination(0), 1).size());
        Assertions.assertEquals(0, ConfluenciaTest.FILE.findAllByGameSlugAndProjectTypeAndProjectSlug("minecraft", "invalid", "invalid", new Pagination(0), 1).size());
        Assertions.assertEquals(0, ConfluenciaTest.FILE.findAllByGameSlugAndProjectTypeAndProjectSlug("minecraft", "mods", "invalid", new Pagination(0), 1).size());
        Assertions.assertEquals(1, ConfluenciaTest.FILE.findAllByGameSlugAndProjectTypeAndProjectSlug("minecraft", "mods", "bookshelf", new Pagination(0), 1).size());
    }

    @Test
    public void findAllProjectFilesByGameSlugAndProjectTypeAndProjectSlugAuthorized () {

        Assertions.assertEquals(0, ConfluenciaTest.FILE.findAllByGameSlugAndProjectTypeAndProjectSlugAuthorized("invalid", "invalid", "invalid", new Pagination(0), 1).size());
        Assertions.assertEquals(0, ConfluenciaTest.FILE.findAllByGameSlugAndProjectTypeAndProjectSlugAuthorized("minecraft", "invalid", "invalid", new Pagination(0), 1).size());
        Assertions.assertEquals(0, ConfluenciaTest.FILE.findAllByGameSlugAndProjectTypeAndProjectSlugAuthorized("minecraft", "mods", "invalid", new Pagination(0), 1).size());
        Assertions.assertEquals(5, ConfluenciaTest.FILE.findAllByGameSlugAndProjectTypeAndProjectSlugAuthorized("minecraft", "mods", "bookshelf", new Pagination(0), 5).size());
    }

    @Test
    public void insertProjectFile () {

        Long id = ConfluenciaTest.FILE.insertProjectFile("test.jar", 10, "", "sha512", "release", "binary", 1, 1);
        Assertions.assertNotNull(id);
        Assertions.assertEquals(6L, id.longValue());
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
    public void findAllGameVersionsByProjectFile () {

        Assertions.assertEquals(1, ConfluenciaTest.FILE.findAllGameVersionsByProjectFile(1).size());
    }
}
