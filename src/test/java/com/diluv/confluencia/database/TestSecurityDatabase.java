package com.diluv.confluencia.database;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.diluv.confluencia.ConfluenciaTest;

public class TestSecurityDatabase extends ConfluenciaTest {

    @Test
    public void insertDomainBlocklist () {

        Assertions.assertTrue(ConfluenciaTest.SECURITY.insertDomainBlocklist(new String[]{"blocked.com"}));
    }

    @Test
    public void existsBlocklist () {

        // Allowed
        Assertions.assertFalse(ConfluenciaTest.SECURITY.existsBlocklist("test@diluv.com", "diluv.com"));

        // Blocked email
        Assertions.assertTrue(ConfluenciaTest.SECURITY.existsBlocklist("blocked@diluv.com", "diluv.com"));

        // Blocked domain
        Assertions.assertTrue(ConfluenciaTest.SECURITY.existsBlocklist("test@banned.com", "banned.com"));
    }

    @Test
    public void insertEmailSent () {

        Assertions.assertTrue(ConfluenciaTest.SECURITY.insertEmailSent("f77a5a12-2144-4ba5-afec-f756240eeeb3", "test@example.com", "verification"));
        Assertions.assertFalse(ConfluenciaTest.SECURITY.insertEmailSent("f77a5a12-2144-4ba5-afec-f756240eeeb3", "test@example.com", "verification"));
    }

    @Test
    public void existsEmailSent () {

        Assertions.assertTrue(ConfluenciaTest.SECURITY.existsEmailSent("e4a291f7-740a-4b88-bc32-63e97e2d0812"));
        Assertions.assertFalse(ConfluenciaTest.SECURITY.existsEmailSent("0788b719-a88f-4937-b7b5-ee615f345aed"));
    }

    @Test
    public void findEmailSentByEmailAndType () {

        Assertions.assertNotNull(ConfluenciaTest.SECURITY.findEmailSentByEmailAndType("test@diluv.com", "test"));
        Assertions.assertNull(ConfluenciaTest.SECURITY.findEmailSentByEmailAndType("test@diluv.com", "invalid"));
    }

    @Test
    public void findEmailSentByEmail () {

        Assertions.assertFalse(ConfluenciaTest.SECURITY.findEmailSentByEmail("test@example.com").isEmpty());
        Assertions.assertTrue(ConfluenciaTest.SECURITY.findEmailSentByEmail("test2@example.com").isEmpty());
    }

    @Test
    public void insertOrUpdatePassword () {

        Map<String, Long> hashOccurrences = new HashMap<>();
        hashOccurrences.put("00EC740DE1EC7D871E4D780C25FF9FED3A7", 2L);
        Assertions.assertTrue(ConfluenciaTest.SECURITY.insertPassword(hashOccurrences));
    }


    @Test
    public void findOnePasswordByHash () {

        Assertions.assertNotNull(ConfluenciaTest.SECURITY.findOnePasswordByHash("025160DEE13179BC80BB05102CE5B3CD3FE"));
        Assertions.assertNull(ConfluenciaTest.SECURITY.findOnePasswordByHash("1078A18A6C6D0C99ED7375A90D6B995D350"));
    }

    @Test
    public void findPersistedGrantByKeyAndType () {

        Assertions.assertNull(ConfluenciaTest.SECURITY.findPersistedGrantByKeyAndType("invalid", "invalid"));
        Assertions.assertNull(ConfluenciaTest.SECURITY.findPersistedGrantByKeyAndType("w36TSM/IUyxY5P2pA1WaE3bWW8aI8YV43ne+Up2K2w4=", "invalid"));
        Assertions.assertNotNull(ConfluenciaTest.SECURITY.findPersistedGrantByKeyAndType("w36TSM/IUyxY5P2pA1WaE3bWW8aI8YV43ne+Up2K2w4=", "reference_token"));
    }
}
