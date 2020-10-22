package com.diluv.confluencia.database;

import java.util.UUID;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.diluv.confluencia.Confluencia;
import com.diluv.confluencia.ConfluenciaTest;
import com.diluv.confluencia.database.record.NodeCDNCommitsEntity;

public class TestSecurityDatabase extends ConfluenciaTest {

    @Test
    public void findPersistedGrantByKeyAndType () {

        Confluencia.getTransaction(session -> {
            Assertions.assertNull(Confluencia.SECURITY.findPersistedGrantByKeyAndType(session, "invalid", "invalid"));
            Assertions.assertNull(Confluencia.SECURITY.findPersistedGrantByKeyAndType(session, "w36TSM/IUyxY5P2pA1WaE3bWW8aI8YV43ne+Up2K2w4=", "invalid"));
            Assertions.assertNotNull(Confluencia.SECURITY.findPersistedGrantByKeyAndType(session, "w36TSM/IUyxY5P2pA1WaE3bWW8aI8YV43ne+Up2K2w4=", "reference_token"));
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
}
