package com.diluv.confluencia.database;

import com.diluv.confluencia.ConfluenciaTest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestSecurityDatabase extends ConfluenciaTest {

    @Test
    public void findPersistedGrantByKeyAndType () {

        Assertions.assertNull(ConfluenciaTest.SECURITY.findPersistedGrantByKeyAndType("invalid", "invalid"));
        Assertions.assertNull(ConfluenciaTest.SECURITY.findPersistedGrantByKeyAndType("w36TSM/IUyxY5P2pA1WaE3bWW8aI8YV43ne+Up2K2w4=", "invalid"));
        Assertions.assertNotNull(ConfluenciaTest.SECURITY.findPersistedGrantByKeyAndType("w36TSM/IUyxY5P2pA1WaE3bWW8aI8YV43ne+Up2K2w4=", "reference_token"));
    }
}
