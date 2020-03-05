package com.diluv.confluencia.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.diluv.confluencia.Confluencia;
import com.diluv.confluencia.database.dao.GameDAO;
import com.diluv.confluencia.database.record.GameRecord;
import com.diluv.confluencia.database.record.GameVersionRecord;
import com.diluv.confluencia.utils.Pagination;
import com.diluv.confluencia.utils.SQLHandler;

public class GameDatabase implements GameDAO {

    private static final String FIND_ALL = SQLHandler.readFile("game/findAll");
    private static final String FIND_ONE_BY_SLUG = SQLHandler.readFile("game/findOneBySlug");
    private static final String FIND_ALL_GAME_VERSIONS_BY_GAMESLUG = SQLHandler.readFile("game/findAllGameVersionsByGameSlug");

    @Override
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

    @Override
    public List<GameRecord> findAll (Pagination cursor, int limit) {

        List<GameRecord> gameRecords = new ArrayList<>();
        try (PreparedStatement stmt = Confluencia.connection().prepareStatement(FIND_ALL)) {
            stmt.setLong(1, cursor.offset);
            stmt.setLong(2, limit);
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

    @Override
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
}
