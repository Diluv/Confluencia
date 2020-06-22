package com.diluv.confluencia.database.record;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TempUserRecord extends BaseUserRecord {
    private final String verificationCode;

    public TempUserRecord (ResultSet rs) throws SQLException {

        super(rs);
        this.verificationCode = rs.getString("verification_code");
    }

    public String getVerificationCode () {

        return this.verificationCode;
    }
}
