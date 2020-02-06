package com.diluv.confluencia.database;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import com.diluv.confluencia.ConfluenciaTest;

public class TestEmailDatabase extends ConfluenciaTest {

    @Test
    public void insertDomainBlacklist () {

        Assert.assertTrue(ConfluenciaTest.EMAIL.insertDomainBlacklist(new String[]{"blacklisted2.com"}));
    }

    @Test
    public void existsBlacklist () {

        // Allowed
        Assert.assertFalse(ConfluenciaTest.EMAIL.existsBlacklist("test@example.com", "example.com"));

        // Blacklisted email
        Assert.assertTrue(ConfluenciaTest.EMAIL.existsBlacklist("blacklisted@example.com", "example.com"));

        // Blacklisted domain
        Assert.assertTrue(ConfluenciaTest.EMAIL.existsBlacklist("test@diluv.com", "diluv.com"));
    }

    @Test
    public void insertEmailSent () {

        Assert.assertTrue(ConfluenciaTest.EMAIL.insertEmailSent("f77a5a12-2144-4ba5-afec-f756240eeeb3", "test@example.com", "verification"));
        Assert.assertFalse(ConfluenciaTest.EMAIL.insertEmailSent("f77a5a12-2144-4ba5-afec-f756240eeeb3", "test@example.com", "verification"));
    }

    @Test
    public void existsEmailSent () {

        Assert.assertTrue(ConfluenciaTest.EMAIL.existsEmailSent("e4a291f7-740a-4b88-bc32-63e97e2d0812"));
        Assert.assertFalse(ConfluenciaTest.EMAIL.existsEmailSent("0788b719-a88f-4937-b7b5-ee615f345aed"));
    }

    @Test
    public void findEmailSentByEmailAndType () {

        Assert.assertNotNull(ConfluenciaTest.EMAIL.findEmailSentByEmailAndType("test@example.com", "test"));
        Assert.assertNull(ConfluenciaTest.EMAIL.findEmailSentByEmailAndType("test@example.com", "invalid"));
    }

    @Test
    public void findEmailSentByEmail () {

        Assert.assertFalse(ConfluenciaTest.EMAIL.findEmailSentByEmail("test@example.com").isEmpty());
        Assert.assertTrue(ConfluenciaTest.EMAIL.findEmailSentByEmail("test2@example.com").isEmpty());
    }
}
