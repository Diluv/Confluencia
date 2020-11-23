package com.diluv.confluencia.database;

import java.sql.Timestamp;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.diluv.confluencia.Confluencia;
import com.diluv.confluencia.ConfluenciaTest;

public class TestMiscDatabase extends ConfluenciaTest {

    @Test
    public void existsImagesForRelease () {

        Confluencia.getTransaction(session -> {
            Assertions.assertTrue(Confluencia.MISC.existsImagesForRelease(session));
        });
    }

    @Test
    public void updateAllImagesForRelease () {

        Confluencia.getTransaction(session -> {
            Assertions.assertEquals(1, Confluencia.MISC.updateAllImagesForRelease(session, new Timestamp(System.currentTimeMillis())));
        });
    }
}
