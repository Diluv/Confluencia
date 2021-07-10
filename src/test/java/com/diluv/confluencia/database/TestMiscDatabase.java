package com.diluv.confluencia.database;

import com.diluv.confluencia.Confluencia;
import com.diluv.confluencia.ConfluenciaTest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Instant;

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
            Assertions.assertEquals(1, Confluencia.MISC.updateAllImagesForRelease(session, Instant.now()));
        });
    }

    @Test
    public void findAllUnusedRegistrationCodes () {

        Confluencia.getTransaction(session -> {
            Assertions.assertEquals(1, Confluencia.MISC.findAllRegistrationCodesByUser(session, 1).size());
        });
    }
}
