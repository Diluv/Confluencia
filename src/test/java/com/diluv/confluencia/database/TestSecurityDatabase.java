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

        Assertions.assertNotNull(Confluencia.SECURITY.findAllNodeCDNCommits());
    }

    @Test
    public void insertNodeCDNCommits () {

        NodeCDNCommitsEntity entity = new NodeCDNCommitsEntity();
        entity.setHash(UUID.randomUUID().toString());
        Assertions.assertTrue(Confluencia.SECURITY.insertNodeCDNCommits(entity));
    }

    @Test
    public void updateNodeCDNCommits () {

        Assertions.assertTrue(Confluencia.SECURITY.updateNodeCDNCommits(1));
    }
}
