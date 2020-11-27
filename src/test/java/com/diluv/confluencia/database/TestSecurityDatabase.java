package com.diluv.confluencia.database;

import java.util.UUID;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.diluv.confluencia.Confluencia;
import com.diluv.confluencia.ConfluenciaTest;
import com.diluv.confluencia.database.record.NodeCDNCommitsEntity;

public class TestSecurityDatabase extends ConfluenciaTest {

    @Test
    public void findAPITokensByToken () {

        Confluencia.getTransaction(session -> {
            Assertions.assertNull(Confluencia.SECURITY.findAPITokensByToken(session, "invalid"));
            Assertions.assertNotNull(Confluencia.SECURITY.findAPITokensByToken(session, "91e048664cf660bf7005b4002bece81b5099888fa8771654621fdfbf5702ceba4e7c447c301b11bdd64b19016ac4c57aca1b77cde97fbc016f74a2235293b9e3"));
            Assertions.assertNotNull(Confluencia.SECURITY.findAPITokensByToken(session, "50c42db342f7f7d403fce41c79fb4010fb2cc4fb9d061914c5836ae5af432eed0f1aa468cc363509b7b931c28687c215df835a6cfc9fdded5ec828a614a11be8"));
        });
    }

    @Test
    public void findLatestNodeCDNCommits () {

        Confluencia.getTransaction(session -> {
            Assertions.assertNotNull(Confluencia.SECURITY.findLatestNodeCDNCommits(session));
        });
    }

    @Test
    public void findOneNodeCDNCommitsByHash () {

        Confluencia.getTransaction(session -> {
            Assertions.assertNotNull(Confluencia.SECURITY.findOneNodeCDNCommitsByHash(session, "d9f5bb5b-22af-4f58-bb15-f6c8a373aae9"));
        });
    }

    @Test
    public void insertNodeCDNCommits () {

        Confluencia.getTransaction(session -> {
            NodeCDNCommitsEntity entity = new NodeCDNCommitsEntity();
            entity.setHash(UUID.randomUUID().toString());
            Assertions.assertNotNull(session.save(entity));
        });
    }

    @Test
    public void updateNodeCDNCommits () {

        Confluencia.getTransaction(session -> {
            NodeCDNCommitsEntity entity = Confluencia.SECURITY.findOneNodeCDNCommitsByHash(session, "d9f5bb5b-22af-4f58-bb15-f6c8a373aae9");
            entity.setCompleted(true);
            session.update(entity);

            NodeCDNCommitsEntity e2 = Confluencia.SECURITY.findOneNodeCDNCommitsByHash(session, "d9f5bb5b-22af-4f58-bb15-f6c8a373aae9");
            Assertions.assertNull(e2);
        });
    }

    @Test
    public void existsUsernameBlockList () {

        Confluencia.getTransaction(session -> {
            Assertions.assertFalse(Confluencia.SECURITY.existsUsernameBlockList(session, "valid"));
            Assertions.assertTrue(Confluencia.SECURITY.existsUsernameBlockList(session, "blocked"));
        });
    }

    @Test
    public void existsContainsUsernameBlockList () {

        Confluencia.getTransaction(session -> {
            Assertions.assertFalse(Confluencia.SECURITY.existsContainsUsernameBlockList(session, "adminaccount"));
            Assertions.assertTrue(Confluencia.SECURITY.existsContainsUsernameBlockList(session, "admindiluvaccount"));
            Assertions.assertTrue(Confluencia.SECURITY.existsContainsUsernameBlockList(session, "admindiluv"));
            Assertions.assertTrue(Confluencia.SECURITY.existsContainsUsernameBlockList(session, "diluvaccount"));
            Assertions.assertTrue(Confluencia.SECURITY.existsContainsUsernameBlockList(session, "diluvstaff"));
        });
    }

    @Test
    public void existsEmailDomainBlockList () {

        Confluencia.getTransaction(session -> {
            Assertions.assertFalse(Confluencia.SECURITY.existsEmailDomainBlockList(session, "diluv.com"));
            Assertions.assertTrue(Confluencia.SECURITY.existsEmailDomainBlockList(session, "banned.com"));
            Assertions.assertTrue(Confluencia.SECURITY.existsEmailDomainBlockList(session, "banned2.com"));
        });
    }

    @Test
    public void existsEmailBlockList () {

        Confluencia.getTransaction(session -> {
            Assertions.assertFalse(Confluencia.SECURITY.existsEmailBlockList(session, "email@diluv.com"));
            Assertions.assertTrue(Confluencia.SECURITY.existsEmailBlockList(session, "blocked@diluv.com"));
        });
    }
}
