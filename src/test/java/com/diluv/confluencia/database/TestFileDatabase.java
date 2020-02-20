package com.diluv.confluencia.database;

import java.sql.SQLException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.diluv.confluencia.ConfluenciaTest;

public class TestFileDatabase extends ConfluenciaTest {

    @Test
    public void findAllWherePending () {

        Assertions.assertEquals(1, ConfluenciaTest.FILE.findAllWherePending(5).size());
    }

    @Test
    public void updateFileQueueStatusById () throws SQLException {

        Assertions.assertTrue(ConfluenciaTest.FILE.updateFileQueueStatusById(1));
    }

    @Test
    public void getLatestFileQueueRecord () throws SQLException {

        Assertions.assertFalse(ConfluenciaTest.FILE.getLatestFileQueueRecord(5).isEmpty());
    }

    @Test
    public void findAllProjectFilesByGameSlugAndProjectTypeAndProjectSlug () {

        Assertions.assertEquals(0, ConfluenciaTest.FILE.findAllProjectFilesByGameSlugAndProjectTypeAndProjectSlug("invalid", "invalid", "invalid").size());
        Assertions.assertEquals(0, ConfluenciaTest.FILE.findAllProjectFilesByGameSlugAndProjectTypeAndProjectSlug("minecraft", "invalid", "invalid").size());
        Assertions.assertEquals(0, ConfluenciaTest.FILE.findAllProjectFilesByGameSlugAndProjectTypeAndProjectSlug("minecraft", "mods", "invalid").size());
        Assertions.assertEquals(1, ConfluenciaTest.FILE.findAllProjectFilesByGameSlugAndProjectTypeAndProjectSlug("minecraft", "mods", "project_1").size());
    }

    @Test
    public void findAllProjectFilesByGameSlugAndProjectTypeAndProjectSlugAuthorized () {

        Assertions.assertEquals(0, ConfluenciaTest.FILE.findAllProjectFilesByGameSlugAndProjectTypeAndProjectSlugAuthorized("invalid", "invalid", "invalid").size());
        Assertions.assertEquals(0, ConfluenciaTest.FILE.findAllProjectFilesByGameSlugAndProjectTypeAndProjectSlugAuthorized("minecraft", "invalid", "invalid").size());
        Assertions.assertEquals(0, ConfluenciaTest.FILE.findAllProjectFilesByGameSlugAndProjectTypeAndProjectSlugAuthorized("minecraft", "mods", "invalid").size());
        Assertions.assertEquals(5, ConfluenciaTest.FILE.findAllProjectFilesByGameSlugAndProjectTypeAndProjectSlugAuthorized("minecraft", "mods", "project_1").size());
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
