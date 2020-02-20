package com.diluv.confluencia.database;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.diluv.confluencia.ConfluenciaTest;

public class TestEmailDatabase extends ConfluenciaTest {

    @Test
    public void insertDomainBlacklist () {

        Assertions.assertTrue(ConfluenciaTest.EMAIL.insertDomainBlacklist(new String[]{"blacklisted2.com"}));
    }

    @Test
    public void existsBlacklist () {

        // Allowed
        Assertions.assertFalse(ConfluenciaTest.EMAIL.existsBlacklist("test@example.com", "example.com"));

        // Blacklisted email
        Assertions.assertTrue(ConfluenciaTest.EMAIL.existsBlacklist("blacklisted@example.com", "example.com"));

        // Blacklisted domain
        Assertions.assertTrue(ConfluenciaTest.EMAIL.existsBlacklist("test@diluv.com", "diluv.com"));
    }

    @Test
    public void insertEmailSent () {

        Assertions.assertTrue(ConfluenciaTest.EMAIL.insertEmailSent("f77a5a12-2144-4ba5-afec-f756240eeeb3", "test@example.com", "verification"));
        Assertions.assertFalse(ConfluenciaTest.EMAIL.insertEmailSent("f77a5a12-2144-4ba5-afec-f756240eeeb3", "test@example.com", "verification"));
    }

    @Test
    public void existsEmailSent () {

        Assertions.assertTrue(ConfluenciaTest.EMAIL.existsEmailSent("e4a291f7-740a-4b88-bc32-63e97e2d0812"));
        Assertions.assertFalse(ConfluenciaTest.EMAIL.existsEmailSent("0788b719-a88f-4937-b7b5-ee615f345aed"));
    }

    @Test
    public void findEmailSentByEmailAndType () {

        Assertions.assertNotNull(ConfluenciaTest.EMAIL.findEmailSentByEmailAndType("test@example.com", "test"));
        Assertions.assertNull(ConfluenciaTest.EMAIL.findEmailSentByEmailAndType("test@example.com", "invalid"));
    }

    @Test
    public void findEmailSentByEmail () {

        Assertions.assertFalse(ConfluenciaTest.EMAIL.findEmailSentByEmail("test@example.com").isEmpty());
        Assertions.assertTrue(ConfluenciaTest.EMAIL.findEmailSentByEmail("test2@example.com").isEmpty());
    }
}
