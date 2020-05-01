package com.diluv.confluencia.database.record;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReferenceTokenRecord {

    private final String key;
    private final String type;
    private final String subjectId;
    private final String sessionId;
    private final String clientId;
    private final String description;
    private final long creationTime;
    private final long expiration;
    private final String data;

    public ReferenceTokenRecord (ResultSet rs) throws SQLException {

        this.key = rs.getString("Key");
        this.type = rs.getString("Type");
        this.subjectId = rs.getString("SubjectId");
        this.sessionId = rs.getString("SessionId");
        this.clientId = rs.getString("ClientId");
        this.description = rs.getString("Description");
        this.creationTime = rs.getTimestamp("CreationTime").getTime();
        this.expiration = rs.getTimestamp("Expiration").getTime();
        this.data = rs.getString("Data");
    }

    public String getKey () {

        return this.key;
    }

    public String getType () {

        return this.type;
    }

    public String getSubjectId () {

        return this.subjectId;
    }

    public String getSessionId () {

        return this.sessionId;
    }

    public String getClientId () {

        return this.clientId;
    }

    public String getDescription () {

        return this.description;
    }

    public long getCreationTime () {

        return this.creationTime;
    }

    public long getExpiration () {

        return this.expiration;
    }

    public String getData () {

        return this.data;
    }
}
