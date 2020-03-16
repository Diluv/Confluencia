package com.diluv.confluencia.database.record;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PasswordResetRecord {
    private final long userId;
    private final String code;
    private final long createdAt;

    public PasswordResetRecord (ResultSet rs) throws SQLException {

        this.userId = rs.getLong("user_id");
        this.code = rs.getString("code");
        this.createdAt = rs.getTimestamp("created_at").getTime();
    }

    public long getUserId () {

        return this.userId;
    }

    public String getCode () {

        return this.code;
    }

    public long getCreatedAt () {

        return this.createdAt;
    }
}
