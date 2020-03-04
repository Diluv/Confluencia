package com.diluv.confluencia.database.record;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class APITokenRecord {

    private final long userId;
    private final String code;
    private final String name;
    private final List<String> permissions;

    public APITokenRecord (ResultSet rs) throws SQLException {

        this.userId = rs.getLong("user_id");
        this.code = rs.getString("code");
        this.name = rs.getString("name");

        String perm = rs.getString("permissions");
        this.permissions = perm == null ? Collections.emptyList() : Arrays.asList(perm.split(" "));
    }

    public long getUserId () {

        return this.userId;
    }

    public String getCode () {

        return this.code;
    }

    public String getName () {

        return this.name;
    }

    public List<String> getPermissions () {

        return this.permissions;
    }
}
