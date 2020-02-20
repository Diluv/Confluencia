package com.diluv.confluencia.database;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.diluv.confluencia.ConfluenciaTest;

public class TestNewsDatabase extends ConfluenciaTest {

    @Test
    public void findAll () {

        Assertions.assertEquals(1, ConfluenciaTest.NEWS.findAll().size());
    }

    @Test
    public void findOneByNewsSlug () {

        Assertions.assertNotNull(ConfluenciaTest.NEWS.findOneByNewsSlug("test"));
        Assertions.assertNull(ConfluenciaTest.NEWS.findOneByNewsSlug("invalid"));
    }
}
