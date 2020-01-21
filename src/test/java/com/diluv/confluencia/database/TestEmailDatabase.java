package com.diluv.confluencia.database;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import com.diluv.confluencia.ConfluenciaTest;

public class TestEmailDatabase extends ConfluenciaTest {

    @Test
    public void existsBlacklist () {

        // Allowed
        Assert.assertFalse(ConfluenciaTest.EMAIL.existsBlacklist("test@example.com", "example.com"));

        // Blacklisted email
        Assert.assertTrue(ConfluenciaTest.EMAIL.existsBlacklist("blacklisted@example.com", "example.com"));

        // Blacklisted domain
        Assert.assertTrue(ConfluenciaTest.EMAIL.existsBlacklist("test@diluv.com", "diluv.com"));
    }
}
