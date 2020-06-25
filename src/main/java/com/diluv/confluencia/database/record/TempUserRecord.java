package com.diluv.confluencia.database.record;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TempUserRecord extends BaseUserRecord {
    private final String code;

    public TempUserRecord (ResultSet rs) throws SQLException {

        super(rs);
        this.code = rs.getString("code");
    }

    public String getCode () {

        return this.code;
    }
}
