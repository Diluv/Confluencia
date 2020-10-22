package com.diluv.confluencia.database;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.diluv.confluencia.Confluencia;
import com.diluv.confluencia.ConfluenciaTest;
import com.diluv.confluencia.database.record.GamesEntity;
import com.diluv.confluencia.database.sort.GameSort;

public class TestGameDatabase extends ConfluenciaTest {

    @Test
    public void countAllBySearch () {

        Confluencia.getTransaction(session -> {
            Assertions.assertEquals(4, Confluencia.GAME.countAllBySearch(session, ""));
            Assertions.assertEquals(1, Confluencia.GAME.countAllBySearch(session, "bedrock"));
        });
    }

    @Test
    public void findOneBySlug () {

        Confluencia.getTransaction(session -> {
            // Allowed
            Assertions.assertNotNull(Confluencia.GAME.findOneBySlug(session, "minecraft-je"));

            // Not found
            Assertions.assertNull(Confluencia.GAME.findOneBySlug(session, "notfound"));
        });
    }

    @Test
    public void findAll () {

        Confluencia.getTransaction(session -> {
            Assertions.assertEquals(4, Confluencia.GAME.findAll(session, 1, 25, GameSort.NEW, "").size());
        });
    }

    @Test
    public void findFeaturedGames () {

        Confluencia.getTransaction(session -> {
            Assertions.assertEquals(2, Confluencia.GAME.findFeaturedGames(session).size());
        });
    }

    @Test
    public void countAllProjectTypes () {

        Confluencia.getTransaction(session -> {
            Assertions.assertEquals(5, Confluencia.GAME.countAllProjectTypes(session));
        });
    }

    @Test
    public void findAllProjectTypesByGameSlug () {

        Confluencia.getTransaction(session -> {
            Assertions.assertEquals(2, Confluencia.GAME.findAllProjectTypesByGameSlug(session, "minecraft-je").size());
        });
    }

    @Test
    public void insertGame () {

        Confluencia.getTransaction(session -> {
            GamesEntity game = new GamesEntity();
            game.setSlug("testing");
            game.setName("Testing");
            game.setUrl("https://example.com");
            session.save(game);
            Assertions.assertNotNull(Confluencia.GAME.findOneBySlug(session, "testing"));
        });
    }
}
