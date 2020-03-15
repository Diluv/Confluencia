package com.diluv.confluencia.database.record;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProjectLinkRecord {

    private final long projectId;
    private final String type;
    private final String url;

    public ProjectLinkRecord (ResultSet rs) throws SQLException {

        this.projectId = rs.getLong("project_id");
        this.type = rs.getString("type");
        this.url = rs.getString("url");
    }

    public long getProjectId () {

        return this.projectId;
    }

    public String getType () {

        return this.type;
    }

    public String getUrl () {

        return this.url;
    }
}
