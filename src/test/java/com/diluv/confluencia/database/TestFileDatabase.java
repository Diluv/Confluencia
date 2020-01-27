package com.diluv.confluencia.database;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import com.diluv.confluencia.ConfluenciaTest;

public class TestFileDatabase extends ConfluenciaTest {


    @Test
    public void findAllProjectFilesByGameSlugAndProjectType () {

        Assert.assertEquals(0, ConfluenciaTest.FILE.findAllProjectFilesByGameSlugAndProjectType("invalid", "invalid", "invalid").size());
        Assert.assertEquals(0, ConfluenciaTest.FILE.findAllProjectFilesByGameSlugAndProjectType("minecraft", "invalid", "invalid").size());
        Assert.assertEquals(0, ConfluenciaTest.FILE.findAllProjectFilesByGameSlugAndProjectType("minecraft", "mods", "invalid").size());
        Assert.assertEquals(1, ConfluenciaTest.FILE.findAllProjectFilesByGameSlugAndProjectType("minecraft", "mods", "project_1").size());
    }

    @Test
    public void insertProjectFileQueue () {

        long test = ConfluenciaTest.FILE.insertProjectFileQueue("test.jar", "", 1, 1);
        System.out.println(test);
    }
}
