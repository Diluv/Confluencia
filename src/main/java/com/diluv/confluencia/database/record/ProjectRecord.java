package com.diluv.confluencia.database.record;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProjectRecord {

    private String name;
    private String slug;
    private String summary;
    private String description;
    private long cachedDownloads;
    private long createdAt;
    private long updatedAt;
    private String gameSlug;
    private String projectTypeSlug;
    private boolean released;
    private boolean reviewed;
    private long userId;

    public ProjectRecord (ResultSet rs) throws SQLException {

        this.name = rs.getString("name");
        this.slug = rs.getString("slug");
        this.summary = rs.getString("summary");
        this.description = rs.getString("description");
        this.cachedDownloads = rs.getLong("cached_downloads");
        this.createdAt = rs.getTimestamp("created_at").getTime();
        this.updatedAt = rs.getTimestamp("updated_at").getTime();
        this.gameSlug = rs.getString("game_slug");
        this.projectTypeSlug = rs.getString("project_type_slug");
        this.reviewed = rs.getBoolean("reviewed");
        this.released = rs.getBoolean("released");
        this.userId = rs.getLong("user_id");
    }

    public ProjectRecord (String name, String slug, String summary, String description, long cachedDownloads, long createdAt, long updatedAt, String gameSlug, String projectTypeSlug, boolean released, boolean reviewed, long userId) {

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
        this.reviewed = reviewed;
        this.userId = userId;
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

    public boolean isReviewed () {

        return this.reviewed;
    }

    public long getUserId () {

        return this.userId;
    }
}
