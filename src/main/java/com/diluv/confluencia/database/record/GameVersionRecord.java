package com.diluv.confluencia.database.record;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GameVersionRecord {
    private final long id;
    private final String gameSlug;
    private final String version;
    private final String type;
    private final long released;

    public GameVersionRecord (ResultSet rs) throws SQLException {

        this.id = rs.getLong("id");
        this.gameSlug = rs.getString("game_slug");
        this.version = rs.getString("version");
        this.type = rs.getString("type");
        this.released = rs.getTimestamp("released").getTime();
    }

    public long getId () {

        return this.id;
    }

    public String getGameSlug () {

        return this.gameSlug;
    }

    public String getVersion () {

        return this.version;
    }

    public String getType () {

        return this.type;
    }

    public long getReleased () {

        return this.released;
    }
}


