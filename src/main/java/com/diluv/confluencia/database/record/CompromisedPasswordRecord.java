package com.diluv.confluencia.database.record;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CompromisedPasswordRecord {
    private final String passwordHash;
    private final long occurrences;
    private final long lastUpdated;

    public CompromisedPasswordRecord (ResultSet rs) throws SQLException {

        this.passwordHash = rs.getString("password_hash");
        this.occurrences = rs.getLong("occurrences");
        this.lastUpdated = rs.getTimestamp("last_updated").getTime();
    }

    public String getPasswordHash () {

        return this.passwordHash;
    }

    public long getOccurrences () {

        return this.occurrences;
    }

    public long getLastUpdated () {

        return this.lastUpdated;
    }
}
