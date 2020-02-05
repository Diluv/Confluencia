package com.diluv.confluencia.database;

import java.sql.Timestamp;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import com.diluv.confluencia.ConfluenciaTest;

public class TestUserDatabase extends ConfluenciaTest {

    @Test
    public void existsUserByEmail () {

        Assert.assertFalse(ConfluenciaTest.USER.existsUserByEmail("invalid@example.com"));
        Assert.assertTrue(ConfluenciaTest.USER.existsUserByEmail("test@example.com"));
        Assert.assertTrue(ConfluenciaTest.USER.existsUserByEmail("test2@example.com"));
    }

    @Test
    public void existsUserByUsername () {

        Assert.assertFalse(ConfluenciaTest.USER.existsUserByUsername("invalid"));
        Assert.assertTrue(ConfluenciaTest.USER.existsUserByUsername("test"));
        Assert.assertTrue(ConfluenciaTest.USER.existsUserByUsername("test2"));
    }

    @Test
    public void findOneByUsername () {

        Assert.assertNull(ConfluenciaTest.USER.findOneByUsername("invalid"));
        Assert.assertNotNull(ConfluenciaTest.USER.findOneByUsername("test"));
        Assert.assertNotNull(ConfluenciaTest.USER.findOneByUsername("test2"));
    }


    @Test
    public void insertUser () {

        // Duplicate
        Assert.assertFalse(ConfluenciaTest.USER.insertUser("test4@example.com", "test", "", "", new Timestamp(System.currentTimeMillis())));
        Assert.assertFalse(ConfluenciaTest.USER.insertUser("test@example.com", "test4", "", "", new Timestamp(System.currentTimeMillis())));
        Assert.assertFalse(ConfluenciaTest.USER.insertUser("test@example.com", "test", "", "", new Timestamp(System.currentTimeMillis())));

        // Valid
        Assert.assertTrue(ConfluenciaTest.USER.insertUser("test4@example.com", "test4", "", "", new Timestamp(System.currentTimeMillis())));
    }

    @Test
    public void existsTempUserByEmail () {

        Assert.assertFalse(ConfluenciaTest.USER.existsTempUserByEmail("invalid@example.com"));
        Assert.assertTrue(ConfluenciaTest.USER.existsTempUserByEmail("tempuser@example.com"));
        Assert.assertTrue(ConfluenciaTest.USER.existsTempUserByEmail("tempuser2@example.com"));
    }

    @Test
    public void existsTempUserByUsername () {

        Assert.assertFalse(ConfluenciaTest.USER.existsTempUserByUsername("invalid"));
        Assert.assertTrue(ConfluenciaTest.USER.existsTempUserByUsername("tempuser"));
        Assert.assertTrue(ConfluenciaTest.USER.existsTempUserByUsername("tempuser2"));
    }

    @Test
    public void findTempUserByEmailAndUsername () {

        Assert.assertNull(ConfluenciaTest.USER.findTempUserByEmailAndUsername("invalid@example.com", "invalid"));
        Assert.assertNull(ConfluenciaTest.USER.findTempUserByEmailAndUsername("tempuser@example.com", "invalid"));
        Assert.assertNotNull(ConfluenciaTest.USER.findTempUserByEmailAndUsername("tempuser@example.com", "tempuser"));
        Assert.assertNotNull(ConfluenciaTest.USER.findTempUserByEmailAndUsername("tempuser2@example.com", "tempuser2"));
    }


    @Test
    public void findTempUserByEmailAndCode () {

        Assert.assertNull(ConfluenciaTest.USER.findTempUserByEmailAndCode("invalid@example.com", "8e883fdd-85ef-415f-a158-d96427a28ede"));
        Assert.assertNotNull(ConfluenciaTest.USER.findTempUserByEmailAndCode("tempuser@example.com", "c1632ff7-367e-485f-91dd-92ab75903fa4"));
        Assert.assertNotNull(ConfluenciaTest.USER.findTempUserByEmailAndCode("tempuser2@example.com", "14964974-663a-4005-9cf2-d1f390c3b2cc"));
    }

    @Test
    public void insertAndDeleteTempUser () {

        Assert.assertTrue(ConfluenciaTest.USER.insertTempUser("itempuser@example.com", "itempuser", "", "", "2d60b654-a41a-467b-b7ce-b453c7f4b365"));
        Assert.assertFalse(ConfluenciaTest.USER.insertTempUser("itempuser@example.com", "itempuser2", "", "", "2d60b654-a41a-467b-b7ce-b453c7f4b365"));
        Assert.assertFalse(ConfluenciaTest.USER.insertTempUser("itempuser2@example.com", "itempuser", "", "", "2d60b654-a41a-467b-b7ce-b453c7f4b365"));

        Assert.assertTrue(ConfluenciaTest.USER.deleteTempUser("itempuser@example.com", "itempuser"));
        Assert.assertFalse(ConfluenciaTest.USER.deleteTempUser("itempuser2@example.com", "itempuser"));
        Assert.assertFalse(ConfluenciaTest.USER.deleteTempUser("itempuser@example.com", "itempuser2"));
        Assert.assertFalse(ConfluenciaTest.USER.deleteTempUser("invalid@example.com", "invalid"));
    }


    @Test
    public void updateTempUser () {

        Assert.assertTrue(ConfluenciaTest.USER.updateTempUser("tempuser3@example.com", "tempuser3", "2d60b654-a41a-467b-b7ce-b453c7f4b365"));
    }

    @Test
    public void findRefreshTokenByUserIdAndCode () {

        Assert.assertNull(ConfluenciaTest.USER.findRefreshTokenByUserIdAndCode(1, "invalid"));
        Assert.assertNotNull(ConfluenciaTest.USER.findRefreshTokenByUserIdAndCode(1, "9bd63558-3835-4e01-963f-66a0f467291c"));
        Assert.assertNotNull(ConfluenciaTest.USER.findRefreshTokenByUserIdAndCode(2, "592d3885-2fa1-4987-8626-e22c1e92e479"));
    }

    @Test
    public void insertAndDeleteRefreshTokenByUserIdAndCode () {

        String testCode1 = "5d8a8e30-94b1-4b52-8ddd-e9ce6b899d88";
        String testCode2 = "fca6ad9a-f344-44f5-8ba1-98056369780c";
        Assert.assertTrue(ConfluenciaTest.USER.insertRefreshToken(1, testCode1, new Timestamp(System.currentTimeMillis() + 1000 * 60 * 60)));
        Assert.assertTrue(ConfluenciaTest.USER.insertRefreshToken(2, testCode2, new Timestamp(System.currentTimeMillis() + 1000 * 60 * 60)));

        Assert.assertFalse(ConfluenciaTest.USER.deleteRefreshTokenByUserIdAndCode(1, "invalid"));
        Assert.assertTrue(ConfluenciaTest.USER.deleteRefreshTokenByUserIdAndCode(1, testCode1));
        Assert.assertTrue(ConfluenciaTest.USER.deleteRefreshTokenByUserIdAndCode(2, testCode2));
    }
}
