package com.diluv.confluencia.database;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.diluv.confluencia.ConfluenciaTest;
import com.diluv.confluencia.database.filter.NewsFilter;

public class TestNewsDatabase extends ConfluenciaTest {

    @Test
    public void findAll () {

        Assertions.assertEquals(1, ConfluenciaTest.NEWS.findAll(1, 1, NewsFilter.NEW).size());
    }

    @Test
    public void findOneByNewsSlug () {

        Assertions.assertNotNull(ConfluenciaTest.NEWS.findOneByNewsSlug("test"));
        Assertions.assertNull(ConfluenciaTest.NEWS.findOneByNewsSlug("invalid"));
    }
}
