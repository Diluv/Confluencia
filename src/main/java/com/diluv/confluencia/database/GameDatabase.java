package com.diluv.confluencia.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

import com.diluv.confluencia.Confluencia;
import com.diluv.confluencia.database.record.GameRecord;
import com.diluv.confluencia.database.record.GameVersionRecord;
import com.diluv.confluencia.database.sort.GameSort;
import com.diluv.confluencia.utils.SQLHandler;

public class GameDatabase {

    private static final String FIND_ALL = SQLHandler.readFile("game/findAll");
    private static final String FIND_ONE_BY_SLUG = SQLHandler.readFile("game/findOneBySlug");

    private static final String FIND_FEATURED_GAMES = SQLHandler.readFile("game/findFeaturedGames");

    private static final String FIND_ALL_GAME_VERSIONS_BY_GAMESLUG = SQLHandler.readFile("game/findAllGameVersionsByGameSlug");
    private static final String FIND_GAME_VERSIONS_BY_GAME_SLUG_AND_VERSIONS = SQLHandler.readFile("game/findGameVersionsByGameSlugAndVersions");

    public List<GameRecord> findAll (GameSort sort) {

        List<GameRecord> gameRecords = new ArrayList<>();
        try (PreparedStatement stmt = sort.getQuery(FIND_ALL)) {
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    gameRecords.add(new GameRecord(rs));
                }
            }
        }
        catch (SQLException e) {
            Confluencia.LOGGER.error("Failed to run findAll games database script.", e);
        }
        return gameRecords;
    }

    public GameRecord findOneBySlug (String name) {

        try (PreparedStatement stmt = Confluencia.connection().prepareStatement(FIND_ONE_BY_SLUG)) {
            stmt.setString(1, name);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new GameRecord(rs);
                }
            }
        }
        catch (SQLException e) {
            Confluencia.LOGGER.error("Failed to run findOneBySlug game database script with game {}.", name, e);
        }
        return null;
    }

    public List<GameRecord> findFeaturedGames () {

        List<GameRecord> gameRecords = new ArrayList<>();
        try (PreparedStatement stmt = Confluencia.connection().prepareStatement(FIND_FEATURED_GAMES)) {
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    gameRecords.add(new GameRecord(rs));
                }
            }
        }
        catch (SQLException e) {
            Confluencia.LOGGER.error("Failed to run findAll games database script.", e);
        }
        return gameRecords;
    }

    public List<GameVersionRecord> findAllGameVersionsByGameSlug (String gameSlug) {

        List<GameVersionRecord> gameVersions = new ArrayList<>();
        try (PreparedStatement stmt = Confluencia.connection().prepareStatement(FIND_ALL_GAME_VERSIONS_BY_GAMESLUG)) {
            stmt.setString(1, gameSlug);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    gameVersions.add(new GameVersionRecord(rs));
                }
            }
        }
        catch (SQLException e) {
            Confluencia.LOGGER.error("Failed to run findAllGameVersionsByGameSlug games database script.", e);
        }
        return gameVersions;
    }

    public List<GameVersionRecord> findGameVersionsByGameSlugAndVersions (String gameSlug, String[] versions) {

        StringJoiner b = new StringJoiner(",");
        for (int i = 0; i < versions.length; i++) {
            b.add("?");
        }
        List<GameVersionRecord> gameVersions = new ArrayList<>();
        try (PreparedStatement stmt = Confluencia.connection().prepareStatement(FIND_GAME_VERSIONS_BY_GAME_SLUG_AND_VERSIONS.replace("(?)", "(" + b.toString() + ")"))) {

            stmt.setString(1, gameSlug);
            for (int i = 0; i < versions.length; i++) {
                stmt.setString(i + 2, versions[i]);
            }
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    gameVersions.add(new GameVersionRecord(rs));
                }
            }
        }
        catch (SQLException e) {
            Confluencia.LOGGER.error("Failed to run findGameVersionsByGameSlugAndVersions games database script.", e);
        }
        return gameVersions;
    }
}
