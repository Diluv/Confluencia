package com.diluv.confluencia.database.record;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class UserRoleRecord {
    private final long id;
    private final String name;
    private final List<String> permissions;

    public UserRoleRecord (ResultSet rs) throws SQLException {

        this.id = rs.getLong("id");
        this.name = rs.getString("name");
        String perm = rs.getString("permissions");
        this.permissions = perm == null ? Collections.emptyList() : Arrays.asList(perm.split(" "));
    }

    public long getId () {

        return this.id;
    }

    public String getName () {

        return this.name;
    }

    public List<String> getPermissions () {

        return this.permissions;
    }
}
