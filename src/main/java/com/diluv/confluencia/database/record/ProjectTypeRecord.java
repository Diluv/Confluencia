package com.diluv.confluencia.database.record;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProjectTypeRecord {
    private final String slug;
    private final String name;
    private final String gameSlug;
    private final String gameName;
    private final long maxFileSize;
    private final long projectCount;

    public ProjectTypeRecord (ResultSet rs) throws SQLException {

        this.slug = rs.getString("slug");
        this.name = rs.getString("name");
        this.gameSlug = rs.getString("game_slug");
        this.gameName = rs.getString("game_name");
        this.maxFileSize = rs.getLong("max_file_size");
        this.projectCount = rs.getLong("project_count");
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

    public String getGameName () {

        return this.gameName;
    }

    public long getMaxFileSize () {

        return this.maxFileSize;
    }

    public long getProjectCount () {

        return this.projectCount;
    }
}
