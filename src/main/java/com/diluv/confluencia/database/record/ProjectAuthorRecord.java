package com.diluv.confluencia.database.record;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class ProjectAuthorRecord {

    private final long userId;
    private final String username;
    private final String role;
    private final List<String> permissions;

    public ProjectAuthorRecord (ResultSet rs) throws SQLException {
        this.userId = rs.getLong("user_id");
        this.username = rs.getString("username");
        this.role = rs.getString("role");
        this.permissions = Arrays.asList(rs.getString("permissions").split(" "));
    }

    public long getUserId () {

        return this.userId;
    }

    public String getUsername () {

        return this.username;
    }

    public String getRole () {

        return this.role;
    }

    public List<String> getPermissions () {

        return this.permissions;
    }
}
