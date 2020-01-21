package com.diluv.confluencia.database.record;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProjectFileRecord {

    private String name;
    private String sha512;
    private long size;
    private String changelog;
    private long createdAt;
    private long updatedAt;
    private boolean released;
    private long projectId;
    private long userId;


    public ProjectFileRecord () {

    }

    public ProjectFileRecord (ResultSet rs) throws SQLException {

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
