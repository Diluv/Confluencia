package com.diluv.confluencia.database;

import com.diluv.confluencia.ConfluenciaTest;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class TestFileDatabase extends ConfluenciaTest {


    @Test
    public void findAllProjectFilesByGameSlugAndProjectType () {

        Assert.assertEquals(0, ConfluenciaTest.FILE.findAllProjectFilesByGameSlugAndProjectType("invalid", "invalid", "invalid").size());
        Assert.assertEquals(0, ConfluenciaTest.FILE.findAllProjectFilesByGameSlugAndProjectType("minecraft", "invalid", "invalid").size());
        Assert.assertEquals(0, ConfluenciaTest.FILE.findAllProjectFilesByGameSlugAndProjectType("minecraft", "mods", "invalid").size());
        Assert.assertEquals(1, ConfluenciaTest.FILE.findAllProjectFilesByGameSlugAndProjectType("minecraft", "mods", "project_1").size());
    }
}
