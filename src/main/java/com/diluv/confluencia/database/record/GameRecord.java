package com.diluv.confluencia.database.record;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GameRecord {
    private String slug;
    private String name;
    private String url;

    public GameRecord () {

    }

    public GameRecord (ResultSet rs) throws SQLException {

        this.slug = rs.getString("slug");
        this.name = rs.getString("name");
        this.url = rs.getString("url");
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
}
