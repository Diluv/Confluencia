package com.diluv.confluencia.database;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import com.diluv.confluencia.ConfluenciaTest;

import java.sql.SQLException;

public class TestFileDatabase extends ConfluenciaTest {

    @Test
    public void findAllWherePending () {

        Assert.assertEquals(2, ConfluenciaTest.FILE.findAllWherePending(5).size());
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
    public void findAllProjectFilesByGameSlugAndProjectType () {

        Assert.assertEquals(0, ConfluenciaTest.FILE.findAllProjectFilesByGameSlugAndProjectType("invalid", "invalid", "invalid").size());
        Assert.assertEquals(0, ConfluenciaTest.FILE.findAllProjectFilesByGameSlugAndProjectType("minecraft", "invalid", "invalid").size());
        Assert.assertEquals(0, ConfluenciaTest.FILE.findAllProjectFilesByGameSlugAndProjectType("minecraft", "mods", "invalid").size());
        Assert.assertEquals(1, ConfluenciaTest.FILE.findAllProjectFilesByGameSlugAndProjectType("minecraft", "mods", "project_1").size());
    }

    @Test
    public void insertProjectFileQueue () {

        Long id = ConfluenciaTest.FILE.insertProjectFileQueue("test.jar", "", 1, 1);
        Assert.assertNotNull(id);
        Assert.assertEquals(2L, id.longValue());
    }
}
