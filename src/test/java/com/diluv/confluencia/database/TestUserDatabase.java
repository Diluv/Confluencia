package com.diluv.confluencia.database;

import com.diluv.confluencia.Confluencia;
import com.diluv.confluencia.ConfluenciaTest;
import com.diluv.confluencia.database.record.UserChangeEmail;
import com.diluv.confluencia.database.record.UserMfaRecoveryEntity;
import com.diluv.confluencia.database.record.UsersEntity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestUserDatabase extends ConfluenciaTest {

    @Test
    public void countAll () {

        Confluencia.getTransaction(session -> {
            Assertions.assertEquals(4, Confluencia.USER.countAll(session));
        });
    }

    @Test
    public void findOneByUsername () {

        Confluencia.getTransaction(session -> {
            Assertions.assertNull(Confluencia.USER.findOneByUsername(session, "invalid"));
            Assertions.assertNotNull(Confluencia.USER.findOneByUsername(session, "darkhax"));
            Assertions.assertNotNull(Confluencia.USER.findOneByUsername(session, "jaredlll08"));
        });
    }

    @Test
    public void findOneByEmail () {

        Confluencia.getTransaction(session -> {
            Assertions.assertNull(Confluencia.USER.findOneByEmail(session, "invalid"));
            Assertions.assertNotNull(Confluencia.USER.findOneByEmail(session, "darkhax@diluv.com"));
            Assertions.assertNotNull(Confluencia.USER.findOneByEmail(session, "jaredlll08@diluv.com"));
        });
    }

    @Test
    public void findOneByUserId () {

        Confluencia.getTransaction(session -> {
            Assertions.assertNull(Confluencia.USER.findOneByUserId(session, 0));
            Assertions.assertNotNull(Confluencia.USER.findOneByUserId(session, 1));
            Assertions.assertNotNull(Confluencia.USER.findOneByUserId(session, 2));
        });
    }

    @Test
    public void updateUser () {

        Confluencia.getTransaction(session -> {
            UsersEntity user = Confluencia.USER.findOneByUsername(session, "darkhax");
            user.setDisplayName("DARKHAX");
            session.update(user);
            Assertions.assertEquals("DARKHAX", Confluencia.USER.findOneByUsername(session, "darkhax").getDisplayName());
        });
    }

    @Test
    public void insertUserMFARecovery () {

        Confluencia.getTransaction(session -> {
            UsersEntity user = Confluencia.USER.findOneByUsername(session, "lclc98");
            session.save(new UserMfaRecoveryEntity(user, "11111111"));
            Assertions.assertEquals(3, Confluencia.USER.findAllUserMfaRecoveryByUser(session, user).size());
        });
    }

    @Test
    public void deleteUserMFARecovery () {

        Confluencia.getTransaction(session -> {
            UsersEntity user = Confluencia.USER.findOneByUsername(session, "lclc98");
            Assertions.assertTrue(Confluencia.USER.deleteUserMFARecovery(session, user));
            Assertions.assertEquals(0, Confluencia.USER.findAllUserMfaRecoveryByUser(session, user).size());
        });
    }

    @Test
    public void existsByEmail () {

        Confluencia.getTransaction(session -> {
            Assertions.assertTrue(Confluencia.USER.existsByEmail(session, "lclc98@diluv.com"));
            Assertions.assertFalse(Confluencia.USER.existsByEmail(session, "tempuser2@diluv.com"));
        });
    }

    @Test
    public void existsTempUserByEmail () {

        Confluencia.getTransaction(session -> {
            Assertions.assertTrue(Confluencia.USER.existsTempUserByEmail(session, "tempuser@diluv.com"));
            Assertions.assertFalse(Confluencia.USER.existsTempUserByEmail(session, "lclc98@diluv.com"));
        });
    }

    @Test
    public void existUserChangeEmailByUser () {

        Confluencia.getTransaction(session -> {
            Assertions.assertTrue(Confluencia.USER.existUserChangeEmailByUser(session, new UsersEntity(4)));
            Assertions.assertFalse(Confluencia.USER.existUserChangeEmailByUser(session, new UsersEntity(1)));
        });
    }

    @Test
    public void existUserChangeEmailByEmail () {

        Confluencia.getTransaction(session -> {
            Assertions.assertTrue(Confluencia.USER.existUserChangeEmailByEmail(session, "newemail@diluv.com"));
            Assertions.assertFalse(Confluencia.USER.existUserChangeEmailByEmail(session, "lclc98@diluv.com"));
        });
    }

    @Test
    public void insertUserChangeEmail () {

        Confluencia.getTransaction(session -> {
            UserChangeEmail userEmail = new UserChangeEmail();
            userEmail.setUser(new UsersEntity(3));
            userEmail.setEmail("newnew@diluv.com");
            userEmail.setCode("12345678");

            session.save(userEmail);

            Assertions.assertTrue(Confluencia.USER.existUserChangeEmailByEmail(session, "newnew@diluv.com"));
        });
    }

    @Test
    public void deleteUserChangeEmail () {

        Confluencia.getTransaction(session -> {
            Assertions.assertTrue(Confluencia.USER.deleteUserChangeEmail(session, new UsersEntity(1)));
            Assertions.assertTrue(Confluencia.USER.deleteUserChangeEmail(session, new UsersEntity(3)));
        });
    }

    @Test
    public void findUserMFARecovery () {

        Confluencia.getTransaction(session -> {
            Assertions.assertNull(Confluencia.USER.findUserMFARecovery(session, new UsersEntity(1), "22222222"));
            Assertions.assertNotNull(Confluencia.USER.findUserMFARecovery(session, new UsersEntity(4), "99999999"));
        });
    }

    @Test
    public void findUserMFAEmail () {

        Confluencia.getTransaction(session -> {
            Assertions.assertNull(Confluencia.USER.findUserMFAEmail(session, new UsersEntity(1), "22222222"));
            Assertions.assertNotNull(Confluencia.USER.findUserMFAEmail(session, new UsersEntity(2), "22222222"));
        });
    }

    @Test
    public void findTempUserByUsername () {

        Confluencia.getTransaction(session -> {
            Assertions.assertNull(Confluencia.USER.findTempUserByUsername(session, "invalid"));
            Assertions.assertNotNull(Confluencia.USER.findTempUserByUsername(session, "tempuser2"));
        });
    }

    @Test
    public void findTempUserByEmail () {

        Confluencia.getTransaction(session -> {
            Assertions.assertNull(Confluencia.USER.findTempUserByEmail(session, "invalid"));
            Assertions.assertNotNull(Confluencia.USER.findTempUserByEmail(session, "tempuser3@diluv.com"));
        });
    }
}
