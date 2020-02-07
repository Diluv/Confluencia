package com.diluv.confluencia.database.record;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BaseUserRecord {
    private final long id;
    private final String username;
    private final String email;
    private final String password;
    private final String passwordType;
    private final long createdAt;

    protected BaseUserRecord (ResultSet rs) throws SQLException {

        this.id = rs.getLong("id");
        this.username = rs.getString("username");
        this.email = rs.getString("email");
        this.password = rs.getString("password");
        this.passwordType = rs.getString("password_type");
        this.createdAt = rs.getTimestamp("created_at").getTime();
    }

    public long getId () {

        return this.id;
    }

    public String getUsername () {

        return this.username;
    }

    public String getEmail () {

        return this.email;
    }

    public String getPassword () {

        return this.password;
    }

    public String getPasswordType () {

        return this.passwordType;
    }

    public long getCreatedAt () {

        return this.createdAt;
    }
}
