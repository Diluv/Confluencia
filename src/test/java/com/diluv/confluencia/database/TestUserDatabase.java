package com.diluv.confluencia.database;

import java.sql.Timestamp;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.diluv.confluencia.ConfluenciaTest;

public class TestUserDatabase extends ConfluenciaTest {

    @Test
    public void countAll () {

        Assertions.assertEquals(3, ConfluenciaTest.USER.countAll());
    }

    @Test
    public void existsUserByUsername () {

        Assertions.assertFalse(ConfluenciaTest.USER.existsUserByUsername("invalid"));
        Assertions.assertTrue(ConfluenciaTest.USER.existsUserByUsername("darkhax"));
        Assertions.assertTrue(ConfluenciaTest.USER.existsUserByUsername("jaredlll08"));
    }

    @Test
    public void findOneByUsername () {

        Assertions.assertNull(ConfluenciaTest.USER.findOneByUsername("invalid"));
        Assertions.assertNotNull(ConfluenciaTest.USER.findOneByUsername("darkhax"));
        Assertions.assertNotNull(ConfluenciaTest.USER.findOneByUsername("jaredlll08"));
    }

    @Test
    public void findOneByUserId () {

        Assertions.assertNull(ConfluenciaTest.USER.findOneByUserId(0));
        Assertions.assertNotNull(ConfluenciaTest.USER.findOneByUserId(1));
        Assertions.assertNotNull(ConfluenciaTest.USER.findOneByUserId(2));
    }

    @Test
    public void updateUserPasswordByUserId () {

        Assertions.assertTrue(ConfluenciaTest.USER.updateUserPasswordByUserId(2, "password"));
    }

    @Test
    public void existsTempUserByUsername () {

        Assertions.assertFalse(ConfluenciaTest.USER.existsTempUserByUsername("invalid"));
        Assertions.assertTrue(ConfluenciaTest.USER.existsTempUserByUsername("tempuser"));
        Assertions.assertTrue(ConfluenciaTest.USER.existsTempUserByUsername("tempuser2"));
    }

    @Test
    public void findTempUserByEmailAndUsername () {

        Assertions.assertNull(ConfluenciaTest.USER.findTempUserByEmailAndUsername("invalid@diluv.com", "invalid"));
        Assertions.assertNull(ConfluenciaTest.USER.findTempUserByEmailAndUsername("tempuser@diluv.com", "invalid"));
        Assertions.assertNotNull(ConfluenciaTest.USER.findTempUserByEmailAndUsername("tempuser@diluv.com", "tempuser"));
        Assertions.assertNotNull(ConfluenciaTest.USER.findTempUserByEmailAndUsername("tempuser2@diluv.com", "tempuser2"));
    }

    @Test
    public void deleteTempUser () {

        Assertions.assertFalse(ConfluenciaTest.USER.deleteTempUser("invalid@example.com", "invalid"));
    }


    @Test
    public void updateTempUser () {

        Assertions.assertTrue(ConfluenciaTest.USER.updateTempUser("tempuser3@diluv.com", "tempuser3", "08965118"));
    }

    @Test
    public void deleteAllRefreshTokensByUserId () {

        Assertions.assertTrue(ConfluenciaTest.USER.deleteAllRefreshTokensByUserId(3));
    }

    @Test
    public void insertPasswordReset () {

        Assertions.assertTrue(ConfluenciaTest.USER.insertPasswordReset(1, "code"));
        Assertions.assertFalse(ConfluenciaTest.USER.insertPasswordReset(1, "code"));

        Assertions.assertTrue(ConfluenciaTest.USER.deletePasswordReset(1, "code"));
        Assertions.assertFalse(ConfluenciaTest.USER.deletePasswordReset(1, "invalid"));
    }

    @Test
    public void findOnePasswordResetByEmailAndCode () {

        Assertions.assertTrue(ConfluenciaTest.USER.insertPasswordReset(1, "testing"));
        Assertions.assertNotNull(ConfluenciaTest.USER.findOnePasswordResetByEmailAndCode("darkhax@diluv.com", "testing"));

        Assertions.assertNull(ConfluenciaTest.USER.findOnePasswordResetByEmailAndCode("test@example.com", "invalid"));
    }
}
