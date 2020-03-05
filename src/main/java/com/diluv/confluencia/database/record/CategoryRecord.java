package com.diluv.confluencia.database.record;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoryRecord {
    private final long id;
    private final String gameSlug;
    private final String slug;
    private final String projectTypeSlug;
    private final String name;
    private final String iconURL;

    public CategoryRecord (ResultSet rs) throws SQLException {

        this.id = rs.getLong("id");
        this.gameSlug = rs.getString("game_slug");
        this.projectTypeSlug = rs.getString("project_type_slug");
        this.slug = rs.getString("slug");
        this.name = rs.getString("name");
        this.iconURL = rs.getString("icon_url");
    }

    public long getId () {

        return this.id;
    }

    public String getGameSlug () {

        return this.gameSlug;
    }

    public String getSlug () {

        return this.slug;
    }

    public String getProjectTypeSlug () {

        return this.projectTypeSlug;
    }

    public String getName () {

        return this.name;
    }

    public String getIconURL () {

        return this.iconURL;
    }
}
