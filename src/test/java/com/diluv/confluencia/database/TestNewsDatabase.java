package com.diluv.confluencia.database;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.diluv.confluencia.Confluencia;
import com.diluv.confluencia.ConfluenciaTest;
import com.diluv.confluencia.database.sort.NewsSort;

public class TestNewsDatabase extends ConfluenciaTest {

    @Test
    public void findAll () {

        Confluencia.getTransaction(session -> {
            Assertions.assertEquals(1, Confluencia.NEWS.findAll(session, 1, 1, NewsSort.NEW).size());
        });
    }

    @Test
    public void findOneByNewsSlug () {

        Confluencia.getTransaction(session -> {
            Assertions.assertNotNull(Confluencia.NEWS.findOneByNewsSlug(session, "example"));
            Assertions.assertNull(Confluencia.NEWS.findOneByNewsSlug(session, "invalid"));
        });
    }
}
