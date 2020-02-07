package com.diluv.confluencia.database.record;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BaseProjectFileRecord {

    /**
     * An internally unique identifier for the file.
     */
    private final long id;

    /**
     * The display name of the file.
     */
    private final String name;

    /**
     * The size of the file in bytes.
     */
    private final long size;

    /**
     * The change log text for the file.
     */
    private final String changelog;

    /**
     * The date the file was initially uploaded.
     */
    private final long createdAt;

    /**
     * The id of the project that this file belongs to.
     */
    private final long projectId;

    /**
     * The id of the user who uploaded the file.
     */
    private final long userId;

    /**
     * The username of the user who uploaded the file.
     */
    private final String username;

    public BaseProjectFileRecord (ResultSet rs) throws SQLException {

        this.id = rs.getLong("id");
        this.name = rs.getString("name");
        this.size = rs.getLong("size");
        this.changelog = rs.getString("changelog");
        this.createdAt = rs.getTimestamp("created_at").getTime();
        this.projectId = rs.getLong("project_id");
        this.userId = rs.getLong("user_id");
        this.username = rs.getString("username");
    }

    public long getId () {

        return this.id;
    }

    public String getName () {

        return this.name;
    }

    public long getSize () {

        return this.size;
    }

    public String getChangelog () {

        return this.changelog;
    }

    public long getCreatedAt () {

        return this.createdAt;
    }

    public long getProjectId () {

        return this.projectId;
    }

    public long getUserId () {

        return this.userId;
    }

    public String getUsername () {

        return this.username;
    }
}
