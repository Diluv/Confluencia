package com.diluv.confluencia.database.record;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProjectTypeRecord {
    private String slug;
    private String name;
    private String gameSlug;
    private long maxSize;

    public ProjectTypeRecord () {

    }

    public ProjectTypeRecord (ResultSet rs) throws SQLException {

        this.slug = rs.getString("slug");
        this.name = rs.getString("name");
        this.gameSlug = rs.getString("game_slug");
        this.maxSize = rs.getLong("max_size");
    }

    public String getSlug () {

        return this.slug;
    }

    public String getName () {

        return this.name;
    }

    public String getGameSlug () {

        return this.gameSlug;
    }

    public long getMaxSize () {

        return this.maxSize;
    }
}
