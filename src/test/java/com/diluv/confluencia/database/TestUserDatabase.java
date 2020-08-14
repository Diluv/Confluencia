package com.diluv.confluencia.database;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.diluv.confluencia.Confluencia;
import com.diluv.confluencia.ConfluenciaTest;
import com.diluv.confluencia.database.record.UserMfaRecoveryEntity;
import com.diluv.confluencia.database.record.UsersEntity;

public class TestUserDatabase extends ConfluenciaTest {

    @Test
    public void countAll () {

        Assertions.assertEquals(4, Confluencia.USER.countAll());
    }

    @Test
    public void findOneByUsername () {

        Assertions.assertNull(Confluencia.USER.findOneByUsername("invalid"));
        Assertions.assertNotNull(Confluencia.USER.findOneByUsername("darkhax"));
        Assertions.assertNotNull(Confluencia.USER.findOneByUsername("jaredlll08"));
    }

    @Test
    public void findOneByUserId () {

        Assertions.assertNull(Confluencia.USER.findOneByUserId(0));
        Assertions.assertNotNull(Confluencia.USER.findOneByUserId(1));
        Assertions.assertNotNull(Confluencia.USER.findOneByUserId(2));
    }

    @Test
    public void updateUser () {

        UsersEntity user = Confluencia.USER.findOneByUsername("darkhax");
        user.setDisplayName("Darkhax");
        Assertions.assertTrue(Confluencia.USER.updateUser(user));
    }

    @Test
    public void insertUserMFARecovery () {

        UsersEntity user = Confluencia.USER.findOneByUsername("lclc98");

        List<UserMfaRecoveryEntity> entities = new ArrayList<>();
        entities.add(new UserMfaRecoveryEntity(user, "11111111"));
        Assertions.assertTrue(Confluencia.USER.insertUserMFARecovery(entities));
    }

    @Test
    public void deleteUserMFARecovery () {

        UsersEntity user = Confluencia.USER.findOneByUsername("lclc98");
        Assertions.assertTrue(Confluencia.USER.deleteUserMFARecovery(user));
        Assertions.assertEquals(0, Confluencia.USER.findAllUserMfaRecoveryByUser(user).size());
    }
}
