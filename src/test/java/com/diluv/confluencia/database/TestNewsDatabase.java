package com.diluv.confluencia.database;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.diluv.confluencia.Confluencia;
import com.diluv.confluencia.ConfluenciaTest;
import com.diluv.confluencia.database.sort.NewsSort;

public class TestNewsDatabase extends ConfluenciaTest {

    @Test
    public void findAll () {

        Assertions.assertEquals(1, Confluencia.NEWS.findAll(1, 1, NewsSort.NEW).size());
    }

    @Test
    public void findOneByNewsSlug () {

        Assertions.assertNotNull(Confluencia.NEWS.findOneByNewsSlug("example"));
        Assertions.assertNull(Confluencia.NEWS.findOneByNewsSlug("invalid"));
    }
}
