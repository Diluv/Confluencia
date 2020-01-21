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
}
