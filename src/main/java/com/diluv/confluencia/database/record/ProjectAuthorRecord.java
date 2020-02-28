package com.diluv.confluencia.database.record;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class ProjectAuthorRecord {

    private final long userId;
    private final String username;
    private final long createdAt;
    private final String role;
    private final List<String> permissions;

    public ProjectAuthorRecord (ResultSet rs) throws SQLException {

        this.userId = rs.getLong("id");
        this.username = rs.getString("username");
        this.createdAt = rs.getTimestamp("created_at").getTime();
        this.role = rs.getString("role");
        this.permissions = Arrays.asList(rs.getString("permissions").split(" "));
    }

    public long getUserId () {

        return this.userId;
    }

    public String getUsername () {

        return this.username;
    }

    public long getCreatedAt () {

        return this.createdAt;
    }

    public String getRole () {

        return this.role;
    }

    public List<String> getPermissions () {

        return this.permissions;
    }
}
