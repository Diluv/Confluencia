package com.diluv.confluencia.database.record;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProjectFileQueueRecord {
    private long id;
    private String name;
    private String changelog;
    private long createdAt;
    private long projectId;
    private long userId;

    public ProjectFileQueueRecord (ResultSet rs) throws SQLException {

        this.id = rs.getLong("id");
        this.name = rs.getString("name");
        this.changelog = rs.getString("changelog");
        this.createdAt = rs.getTimestamp("created_at").getTime();
        this.projectId = rs.getLong("project_id");
        this.userId = rs.getLong("user_id");
    }

    public long getId () {

        return this.id;
    }

    public String getName () {

        return this.name;
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
}
