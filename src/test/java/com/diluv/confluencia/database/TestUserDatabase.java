package com.diluv.confluencia.database;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.diluv.confluencia.ConfluenciaTest;
import com.diluv.confluencia.database.record.UserMfaRecoveryEntity;
import com.diluv.confluencia.database.record.UsersEntity;

public class TestUserDatabase extends ConfluenciaTest {

    @Test
    public void countAll () {

        Assertions.assertEquals(4, ConfluenciaTest.USER.countAll());
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
    public void updateUser () {

        UsersEntity user = ConfluenciaTest.USER.findOneByUsername("darkhax");
        user.setDisplayName("Darkhax");
        Assertions.assertTrue(ConfluenciaTest.USER.updateUser(user));
    }

    @Test
    public void insertUserMFARecovery () {

        UsersEntity user = ConfluenciaTest.USER.findOneByUsername("lclc98");

        List<UserMfaRecoveryEntity> entities = new ArrayList<>();
        entities.add(new UserMfaRecoveryEntity(user, "11111111"));
        Assertions.assertTrue(ConfluenciaTest.USER.insertUserMFARecovery(entities));
    }

    @Test
    public void deleteUserMFARecovery () {

        UsersEntity user = ConfluenciaTest.USER.findOneByUsername("lclc98");
        Assertions.assertTrue(ConfluenciaTest.USER.deleteUserMFARecovery(user));
        Assertions.assertEquals(0, ConfluenciaTest.USER.findAllUserMfaRecoveryByUser(user).size());
    }
}
