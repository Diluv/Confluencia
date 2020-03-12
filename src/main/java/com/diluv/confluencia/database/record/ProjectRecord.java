package com.diluv.confluencia.database.record;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProjectRecord {

    private final long id;
    private final String name;
    private final String slug;
    private final String summary;
    private final String description;
    private final long cachedDownloads;
    private final long createdAt;
    private final long updatedAt;
    private final String gameSlug;
    private final String projectTypeSlug;
    private final boolean released;
    private final boolean review;
    private final long userId;
    private final String username;
    private final String userDisplayName;
    private final long userCreatedAt;

    public ProjectRecord (ResultSet rs) throws SQLException {

        this.id = rs.getLong("id");
        this.name = rs.getString("name");
        this.slug = rs.getString("slug");
        this.summary = rs.getString("summary");
        this.description = rs.getString("description");
        this.cachedDownloads = rs.getLong("cached_downloads");
        this.createdAt = rs.getTimestamp("created_at").getTime();
        this.updatedAt = rs.getTimestamp("updated_at").getTime();
        this.gameSlug = rs.getString("game_slug");
        this.projectTypeSlug = rs.getString("project_type_slug");
        this.review = rs.getBoolean("review");
        this.released = rs.getBoolean("released");
        this.userId = rs.getLong("user_id");
        this.username = rs.getString("username");
        this.userDisplayName = rs.getString("display_name");
        this.userCreatedAt = rs.getTimestamp("user_created_at").getTime();
    }

    public ProjectRecord (long id, String name, String slug, String summary, String description, long cachedDownloads, long createdAt, long updatedAt, String gameSlug, String projectTypeSlug, boolean released, boolean review, long userId, String username, String userDisplayName, long userCreatedAt) {

        this.id = id;
        this.name = name;
        this.slug = slug;
        this.summary = summary;
        this.description = description;
        this.cachedDownloads = cachedDownloads;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.gameSlug = gameSlug;
        this.projectTypeSlug = projectTypeSlug;
        this.released = released;
        this.review = review;
        this.userId = userId;
        this.username = username;
        this.userDisplayName = userDisplayName;
        this.userCreatedAt = userCreatedAt;
    }

    public long getId () {

        return this.id;
    }

    public String getName () {

        return this.name;
    }

    public String getSlug () {

        return this.slug;
    }

    public String getSummary () {

        return this.summary;
    }

    public String getDescription () {

        return this.description;
    }

    public long getCachedDownloads () {

        return this.cachedDownloads;
    }

    public long getCreatedAt () {

        return this.createdAt;
    }

    public long getUpdatedAt () {

        return this.updatedAt;
    }

    public String getGameSlug () {

        return this.gameSlug;
    }

    public String getProjectTypeSlug () {

        return this.projectTypeSlug;
    }

    public boolean isReleased () {

        return this.released;
    }

    public boolean isReview () {

        return this.review;
    }

    public long getUserId () {

        return this.userId;
    }

    public String getUsername () {

        return this.username;
    }

    public String getUserDisplayName () {

        return this.userDisplayName;
    }

    public long getUserCreatedAt () {

        return this.userCreatedAt;
    }
}
