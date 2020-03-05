package com.diluv.confluencia.database.record;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ModLoaderRecord {
    private final long id;
    private final String name;
    private final String projectTypeSlug;
    private final String gameSlug;

    public ModLoaderRecord (ResultSet rs) throws SQLException {

        this.id = rs.getLong("id");
        this.name = rs.getString("name");
        this.projectTypeSlug = rs.getString("project_type_slug");
        this.gameSlug = rs.getString("game_slug");
    }

    public long getId () {

        return this.id;
    }

    public String getName () {

        return this.name;
    }

    public String getProjectTypeSlug () {

        return this.projectTypeSlug;
    }

    public String getGameSlug () {

        return this.gameSlug;
    }
}
