package com.diluv.confluencia.database;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import com.diluv.confluencia.ConfluenciaTest;

public class TestGameDatabase extends ConfluenciaTest {

    @Test
    public void findAll () {

        Assert.assertFalse(ConfluenciaTest.GAME.findAll().isEmpty());
    }

    @Test
    public void findOneBySlug () {

        // Allowed
        Assert.assertNotNull(ConfluenciaTest.GAME.findOneBySlug("minecraft"));

        // Not found
        Assert.assertNull(ConfluenciaTest.GAME.findOneBySlug("notfound"));
    }
}
