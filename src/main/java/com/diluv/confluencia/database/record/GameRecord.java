package com.diluv.confluencia.database.record;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GameRecord {
    private final String slug;
    private final String name;
    private final String url;
    private final String defaultProjectType;

    public GameRecord (ResultSet rs) throws SQLException {

        this.slug = rs.getString("slug");
        this.name = rs.getString("name");
        this.url = rs.getString("url");
        this.defaultProjectType = rs.getString("default_project_type");
    }

    public String getSlug () {

        return slug;
    }

    public String getName () {

        return this.name;
    }

    public String getUrl () {

        return this.url;
    }

    public String getDefaultProjectType () {

        return this.defaultProjectType;
    }
}
