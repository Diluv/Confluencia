package com.diluv.confluencia.database;

import com.diluv.confluencia.ConfluenciaTest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.List;

public class TestUserDatabase extends ConfluenciaTest {

    @Test
    public void existsUserByEmail () {

        Assertions.assertFalse(ConfluenciaTest.USER.existsUserByEmail("invalid@example.com"));
        Assertions.assertTrue(ConfluenciaTest.USER.existsUserByEmail("darkhax@diluv.com"));
        Assertions.assertTrue(ConfluenciaTest.USER.existsUserByEmail("jaredlll08@diluv.com"));
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
    public void insertUser () {

        // Duplicate
        Assertions.assertFalse(ConfluenciaTest.USER.insertUser("darkhax@diluv.com", "darkhax", "darkhax", "", "", new Timestamp(System.currentTimeMillis())));
        Assertions.assertFalse(ConfluenciaTest.USER.insertUser("jaredlll08@diluv.com", "jaredlll08", "jaredlll08", "", "", new Timestamp(System.currentTimeMillis())));

        // Valid
        Assertions.assertTrue(ConfluenciaTest.USER.insertUser("test4@example.com", "test4", "Test4", "", "", new Timestamp(System.currentTimeMillis())));
    }

    @Test
    public void updateUserPasswordByUserId () {

        Assertions.assertTrue(ConfluenciaTest.USER.updateUserPasswordByUserId(2, "password"));
    }

    @Test
    public void existsTempUserByEmail () {

        Assertions.assertFalse(ConfluenciaTest.USER.existsTempUserByEmail("invalid@diluv.com"));
        Assertions.assertTrue(ConfluenciaTest.USER.existsTempUserByEmail("tempuser@diluv.com"));
        Assertions.assertTrue(ConfluenciaTest.USER.existsTempUserByEmail("tempuser2@diluv.com"));
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
    public void findTempUserByEmailAndCode () {

        Assertions.assertNull(ConfluenciaTest.USER.findTempUserByEmailAndCode("invalid@diluv.com", "8e883fdd-85ef-415f-a158-d96427a28ede"));
        Assertions.assertNotNull(ConfluenciaTest.USER.findTempUserByEmailAndCode("tempuser@diluv.com", "c1632ff7-367e-485f-91dd-92ab75903fa4"));
        Assertions.assertNotNull(ConfluenciaTest.USER.findTempUserByEmailAndCode("tempuser2@diluv.com", "14964974-663a-4005-9cf2-d1f390c3b2cc"));
    }

    @Test
    public void insertAndDeleteTempUser () {

        Assertions.assertTrue(ConfluenciaTest.USER.insertTempUser("itempuser@example.com", "itempuser", "", "", "2d60b654-a41a-467b-b7ce-b453c7f4b365"));
        Assertions.assertFalse(ConfluenciaTest.USER.insertTempUser("itempuser@example.com", "itempuser2", "", "", "2d60b654-a41a-467b-b7ce-b453c7f4b365"));
        Assertions.assertFalse(ConfluenciaTest.USER.insertTempUser("itempuser2@example.com", "itempuser", "", "", "2d60b654-a41a-467b-b7ce-b453c7f4b365"));

        Assertions.assertTrue(ConfluenciaTest.USER.deleteTempUser("itempuser@example.com", "itempuser"));
        Assertions.assertFalse(ConfluenciaTest.USER.deleteTempUser("itempuser2@example.com", "itempuser"));
        Assertions.assertFalse(ConfluenciaTest.USER.deleteTempUser("itempuser@example.com", "itempuser2"));
        Assertions.assertFalse(ConfluenciaTest.USER.deleteTempUser("invalid@example.com", "invalid"));
    }


    @Test
    public void updateTempUser () {

        Assertions.assertTrue(ConfluenciaTest.USER.updateTempUser("tempuser3@diluv.com", "tempuser3", "2d60b654-a41a-467b-b7ce-b453c7f4b365"));
    }

    @Test
    public void findRefreshTokenByUserIdAndCode () {

        Assertions.assertNull(ConfluenciaTest.USER.findRefreshTokenByUserIdAndCode(1, "invalid"));
        Assertions.assertNotNull(ConfluenciaTest.USER.findRefreshTokenByUserIdAndCode(1, "9bd63558-3835-4e01-963f-66a0f467291c"));
        Assertions.assertNotNull(ConfluenciaTest.USER.findRefreshTokenByUserIdAndCode(2, "592d3885-2fa1-4987-8626-e22c1e92e479"));
    }

    @Test
    public void deleteAllRefreshTokensByUserId () {

        Assertions.assertTrue(ConfluenciaTest.USER.deleteAllRefreshTokensByUserId(3));
    }

    @Test
    public void insertAndDeleteRefreshTokenByUserIdAndCode () {

        String testCode1 = "5d8a8e30-94b1-4b52-8ddd-e9ce6b899d88";
        String testCode2 = "fca6ad9a-f344-44f5-8ba1-98056369780c";
        Assertions.assertTrue(ConfluenciaTest.USER.insertRefreshToken(1, testCode1, new Timestamp(System.currentTimeMillis() + 1000 * 60 * 60)));
        Assertions.assertTrue(ConfluenciaTest.USER.insertRefreshToken(2, testCode2, new Timestamp(System.currentTimeMillis() + 1000 * 60 * 60)));

        Assertions.assertFalse(ConfluenciaTest.USER.deleteRefreshTokenByUserIdAndCode(1, "invalid"));
        Assertions.assertTrue(ConfluenciaTest.USER.deleteRefreshTokenByUserIdAndCode(1, testCode1));
        Assertions.assertTrue(ConfluenciaTest.USER.deleteRefreshTokenByUserIdAndCode(2, testCode2));
    }

    @Test
    public void insertAPITokens () {

        List<String> permission = Collections.singletonList("file.edit");
        String testCode1 = "5d8a8e30-94b1-4b52-8ddd-e9ce6b899d88";
        Assertions.assertTrue(ConfluenciaTest.USER.insertAPITokens(1, testCode1, "CI Token"));
        Assertions.assertTrue(ConfluenciaTest.USER.insertAPITokenPermissions(1, testCode1, permission));

        Assertions.assertFalse(ConfluenciaTest.USER.deleteAPITokenByUserIdAndCode(1, "invalid"));
        Assertions.assertTrue(ConfluenciaTest.USER.deleteAPITokenByUserIdAndCode(1, testCode1));
    }

    @Test
    public void findAPITokenByUserIdAndCode () {

        Assertions.assertNull(ConfluenciaTest.USER.findAPITokenByUserIdAndCode(1, "invalid"));
        Assertions.assertNotNull(ConfluenciaTest.USER.findAPITokenByUserIdAndCode(1, "4b3b85e3-f7ac-4c7b-b71a-df972909b213"));
    }

    @Test
    public void findAllUserRolesByUserId () {

        Assertions.assertEquals(0, ConfluenciaTest.USER.findAllUserRolesByUserId(1).size());
        Assertions.assertEquals(1, ConfluenciaTest.USER.findAllUserRolesByUserId(2).size());
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
