package com.diluv.confluencia.database.record;

import java.sql.ResultSet;
import java.sql.SQLException;

public class NewsRecord {
    private final String slug;
    private final String title;
    private final String summary;
    private final String description;
    private final String username;
    private final long createdAt;

    public NewsRecord (ResultSet rs) throws SQLException {

        this.slug = rs.getString("slug");
        this.title = rs.getString("title");
        this.summary = rs.getString("summary");
        this.description = rs.getString("description");
        this.username = rs.getString("username");
        this.createdAt = rs.getTimestamp("created_at").getTime();
    }

    public String getSlug () {

        return this.slug;
    }

    public String getTitle () {

        return this.title;
    }

    public String getSummary () {

        return this.summary;
    }

    public String getDescription () {

        return this.description;
    }

    public String getUsername () {

        return this.username;
    }

    public long getCreatedAt () {

        return this.createdAt;
    }
}
