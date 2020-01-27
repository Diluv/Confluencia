package com.diluv.confluencia.database.record;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class ProjectFileRecord {

    /**
     * An internally unique identifier for the file.
     */
    public long id;

    /**
     * The display name of the file.
     */
    public String name;

    /**
     * A SHA 512 hash of the file.
     */
    public String sha512;

    /**
     * The size of the file in bytes.
     */
    public long size;

    /**
     * The change log text for the file.
     */
    public String changelog;

    /**
     * The date the file was initially uploaded.
     */
    public long createdAt;

    /**
     * The date the file was last modified.
     */
    public long updatedAt;

    /**
     * Whether or not the file has been released.
     */
    public boolean released;

    /**
     * The id of the project that this file belongs to.
     */
    public long projectId;

    /**
     * The id of the user who uploaded the file.
     */
    public long userId;

    public ProjectFileRecord () {

    }

    public ProjectFileRecord (ResultSet rs) throws SQLException {

        this.id = rs.getLong("id");
        this.name = rs.getString("name");
        this.sha512 = rs.getString("sha512");
        this.size = rs.getLong("size");
        this.changelog = rs.getString("changelog");
        this.createdAt = rs.getTimestamp("created_at").getTime();
        this.updatedAt = rs.getTimestamp("updated_at").getTime();
        this.released = rs.getBoolean("released");
        this.projectId = rs.getLong("project_id");
        this.userId = rs.getLong("user_id");
    }

    public String getName () {

        return this.name;
    }

    public String getSha512 () {

        return this.sha512;
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

    public long getUpdatedAt () {

        return this.updatedAt;
    }

    public boolean isReleased () {

        return this.released;
    }

    public long getProjectId () {

        return this.projectId;
    }

    public long getUserId () {

        return this.userId;
    }
}
