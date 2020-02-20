package com.diluv.confluencia.database;

import java.sql.SQLException;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import com.diluv.confluencia.ConfluenciaTest;

public class TestFileDatabase extends ConfluenciaTest {

    @Test
    public void findAllWherePending () {

        Assert.assertEquals(1, ConfluenciaTest.FILE.findAllWherePending(5).size());
    }

    @Test
    public void updateFileQueueStatusById () throws SQLException {

        Assert.assertTrue(ConfluenciaTest.FILE.updateFileQueueStatusById(1));
    }

    @Test
    public void getLatestFileQueueRecord () throws SQLException {

        Assert.assertFalse(ConfluenciaTest.FILE.getLatestFileQueueRecord(5).isEmpty());
    }

    @Test
    public void findAllProjectFilesByGameSlugAndProjectTypeAndProjectSlug () {

        Assert.assertEquals(0, ConfluenciaTest.FILE.findAllProjectFilesByGameSlugAndProjectTypeAndProjectSlug("invalid", "invalid", "invalid").size());
        Assert.assertEquals(0, ConfluenciaTest.FILE.findAllProjectFilesByGameSlugAndProjectTypeAndProjectSlug("minecraft", "invalid", "invalid").size());
        Assert.assertEquals(0, ConfluenciaTest.FILE.findAllProjectFilesByGameSlugAndProjectTypeAndProjectSlug("minecraft", "mods", "invalid").size());
        Assert.assertEquals(1, ConfluenciaTest.FILE.findAllProjectFilesByGameSlugAndProjectTypeAndProjectSlug("minecraft", "mods", "project_1").size());
    }

    @Test
    public void findAllProjectFilesByGameSlugAndProjectTypeAndProjectSlugAuthorized () {

        Assert.assertEquals(0, ConfluenciaTest.FILE.findAllProjectFilesByGameSlugAndProjectTypeAndProjectSlugAuthorized("invalid", "invalid", "invalid").size());
        Assert.assertEquals(0, ConfluenciaTest.FILE.findAllProjectFilesByGameSlugAndProjectTypeAndProjectSlugAuthorized("minecraft", "invalid", "invalid").size());
        Assert.assertEquals(0, ConfluenciaTest.FILE.findAllProjectFilesByGameSlugAndProjectTypeAndProjectSlugAuthorized("minecraft", "mods", "invalid").size());
        Assert.assertEquals(5, ConfluenciaTest.FILE.findAllProjectFilesByGameSlugAndProjectTypeAndProjectSlugAuthorized("minecraft", "mods", "project_1").size());
    }

    @Test
    public void insertProjectFile () {

        Long id = ConfluenciaTest.FILE.insertProjectFile("test.jar", 10, "", 1, 1);
        Assert.assertNotNull(id);
        Assert.assertEquals(6L, id.longValue());
    }

    @Test
    public void findOneProjectFileQueueByFileId () {

        Assert.assertNotNull(ConfluenciaTest.FILE.findOneProjectFileQueueByFileId(1));
        Assert.assertNull(ConfluenciaTest.FILE.findOneProjectFileQueueByFileId(100));
    }

    @Test
    public void insertProjectFileHash () {
        Assert.assertTrue(ConfluenciaTest.FILE.insertProjectFileHash(3, "00a77e35-09cb-4170-9a19-3e4ea29a4a6e"));
    }
}
