package com.diluv.confluencia.database;

import java.sql.SQLException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.diluv.confluencia.ConfluenciaTest;
import com.diluv.confluencia.database.record.FileStatus;

public class TestFileDatabase extends ConfluenciaTest {

    @Test
    public void findAllWhereStatusAndLimit () {

        Assertions.assertEquals(1, ConfluenciaTest.FILE.findAllWhereStatusAndLimit(FileStatus.PENDING, 5).size());
    }

    @Test
    public void updateStatusById () throws SQLException {

        Assertions.assertTrue(ConfluenciaTest.FILE.updateStatusById(1, FileStatus.RUNNING));
    }

    @Test
    public void findAllByGameSlugAndProjectTypeAndProjectSlug () {

        Assertions.assertEquals(0, ConfluenciaTest.FILE.findAllByGameSlugAndProjectTypeAndProjectSlug("invalid", "invalid", "invalid").size());
        Assertions.assertEquals(0, ConfluenciaTest.FILE.findAllByGameSlugAndProjectTypeAndProjectSlug("minecraft", "invalid", "invalid").size());
        Assertions.assertEquals(0, ConfluenciaTest.FILE.findAllByGameSlugAndProjectTypeAndProjectSlug("minecraft", "mods", "invalid").size());
        Assertions.assertEquals(1, ConfluenciaTest.FILE.findAllByGameSlugAndProjectTypeAndProjectSlug("minecraft", "mods", "project_1").size());
    }

    @Test
    public void findAllProjectFilesByGameSlugAndProjectTypeAndProjectSlugAuthorized () {

        Assertions.assertEquals(0, ConfluenciaTest.FILE.findAllByGameSlugAndProjectTypeAndProjectSlugAuthorized("invalid", "invalid", "invalid").size());
        Assertions.assertEquals(0, ConfluenciaTest.FILE.findAllByGameSlugAndProjectTypeAndProjectSlugAuthorized("minecraft", "invalid", "invalid").size());
        Assertions.assertEquals(0, ConfluenciaTest.FILE.findAllByGameSlugAndProjectTypeAndProjectSlugAuthorized("minecraft", "mods", "invalid").size());
        Assertions.assertEquals(5, ConfluenciaTest.FILE.findAllByGameSlugAndProjectTypeAndProjectSlugAuthorized("minecraft", "mods", "project_1").size());
    }

    @Test
    public void insertProjectFile () {

        Long id = ConfluenciaTest.FILE.insertProjectFile("test.jar", 10, "", "sha512", 1, 1);
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
}
