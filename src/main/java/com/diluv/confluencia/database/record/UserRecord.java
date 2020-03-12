package com.diluv.confluencia.database.record;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRecord extends BaseUserRecord {
    private final String displayName;
    private final boolean mfa;
    private final String mfaSecret;

    public UserRecord (ResultSet rs) throws SQLException {

        super(rs);
        this.displayName = rs.getString("display_name");
        this.mfa = rs.getBoolean("mfa");
        this.mfaSecret = rs.getString("mfa_secret");
    }

    public String getDisplayName () {

        return this.displayName;
    }

    public boolean isMfa () {

        return this.mfa;
    }

    public String getMfaSecret () {

        return this.mfaSecret;
    }
}
