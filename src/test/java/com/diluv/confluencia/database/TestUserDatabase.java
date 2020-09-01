package com.diluv.confluencia.database;

import com.diluv.confluencia.Confluencia;
import com.diluv.confluencia.ConfluenciaTest;
import com.diluv.confluencia.database.record.UserEmailEntity;
import com.diluv.confluencia.database.record.UserMfaRecoveryEntity;
import com.diluv.confluencia.database.record.UsersEntity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

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

    @Test
    public void existsByEmail () {

        Assertions.assertTrue(Confluencia.USER.existsByEmail("lclc98@diluv.com"));
        Assertions.assertFalse(Confluencia.USER.existsByEmail("tempuser2@diluv.com"));
    }

    @Test
    public void existsTempUserByEmail () {

        Assertions.assertTrue(Confluencia.USER.existsTempUserByEmail("tempuser@diluv.com"));
        Assertions.assertFalse(Confluencia.USER.existsTempUserByEmail("lclc98@diluv.com"));
    }

    @Test
    public void existUserEmailByUser () {

        Assertions.assertTrue(Confluencia.USER.existUserEmailByUser(new UsersEntity(4)));
        Assertions.assertFalse(Confluencia.USER.existUserEmailByUser(new UsersEntity(1)));
    }

    @Test
    public void existUserEmailByEmail () {

        Assertions.assertTrue(Confluencia.USER.existUserEmailByEmail("newemail@diluv.com"));
        Assertions.assertFalse(Confluencia.USER.existUserEmailByEmail("lclc98@diluv.com"));
    }

    @Test
    public void insertUserNewEmail () {

        UserEmailEntity userEmail = new UserEmailEntity();
        userEmail.setUser(new UsersEntity(3));
        userEmail.setEmail("newnew@diluv.com");
        userEmail.setCode("12345678");

        Assertions.assertTrue(Confluencia.USER.insertUserEmail(userEmail));
    }
}
