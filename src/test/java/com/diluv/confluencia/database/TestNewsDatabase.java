package com.diluv.confluencia.database;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import com.diluv.confluencia.ConfluenciaTest;

public class TestNewsDatabase extends ConfluenciaTest {

    @Test
    public void findAll () {

        Assert.assertEquals(1, ConfluenciaTest.NEWS.findAll().size());
    }

    @Test
    public void findOneByNewsSlug () {

        Assert.assertNotNull(ConfluenciaTest.NEWS.findOneByNewsSlug("test"));
        Assert.assertNull(ConfluenciaTest.NEWS.findOneByNewsSlug("invalid"));
    }
}
