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

        Assertions.assertNull(Confluencia.SECURITY.findPersistedGrantByKeyAndType("invalid", "invalid"));
        Assertions.assertNull(Confluencia.SECURITY.findPersistedGrantByKeyAndType("w36TSM/IUyxY5P2pA1WaE3bWW8aI8YV43ne+Up2K2w4=", "invalid"));
        Assertions.assertNotNull(Confluencia.SECURITY.findPersistedGrantByKeyAndType("w36TSM/IUyxY5P2pA1WaE3bWW8aI8YV43ne+Up2K2w4=", "reference_token"));
    }

    @Test
    public void findAllNodeCDNCommits () {

        Assertions.assertNotNull(Confluencia.SECURITY.findOneNodeCDNCommits());
    }

    @Test
    public void findOneNodeCDNCommitsByHash () {

        Assertions.assertNotNull(Confluencia.SECURITY.findOneNodeCDNCommitsByHash("d9f5bb5b-22af-4f58-bb15-f6c8a373aae9"));
    }

    @Test
    public void insertNodeCDNCommits () {

        NodeCDNCommitsEntity entity = new NodeCDNCommitsEntity();
        entity.setHash(UUID.randomUUID().toString());
        Assertions.assertTrue(Confluencia.SECURITY.insertNodeCDNCommits(entity));
    }

    @Test
    public void updateNodeCDNCommits () {

        NodeCDNCommitsEntity entity = Confluencia.SECURITY.findOneNodeCDNCommitsByHash("d9f5bb5b-22af-4f58-bb15-f6c8a373aae9");
        Assertions.assertTrue(Confluencia.update(entity));
    }
}
