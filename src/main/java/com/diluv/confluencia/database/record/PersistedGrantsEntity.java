package com.diluv.confluencia.database.record;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PersistedGrants")
public class PersistedGrantsEntity {

    @Id
    @Column(name = "Key")
    private String key;

    @Column(name = "Type")
    private String type;

    @Column(name = "SubjectId")
    private String subjectId;

    @Column(name = "SessionId")
    private String sessionId;

    @Column(name = "ClientId")
    private String clientId;

    @Column(name = "Description")
    private String description;

    @Column(name = "CreationTime")
    private Timestamp creationTime;

    @Column(name = "Expiration")
    private Timestamp expiration;

    @Column(name = "Data")
    private String data;

    public String getKey () {

        return this.key;
    }

    public void setKey (String key) {

        this.key = key;
    }

    public String getType () {

        return this.type;
    }

    public void setType (String type) {

        this.type = type;
    }

    public String getSubjectId () {

        return this.subjectId;
    }

    public void setSubjectId (String subjectId) {

        this.subjectId = subjectId;
    }

    public String getSessionId () {

        return this.sessionId;
    }

    public void setSessionId (String sessionId) {

        this.sessionId = sessionId;
    }

    public String getClientId () {

        return this.clientId;
    }

    public void setClientId (String clientId) {

        this.clientId = clientId;
    }

    public String getDescription () {

        return this.description;
    }

    public void setDescription (String description) {

        this.description = description;
    }

    public Timestamp getCreationTime () {

        return this.creationTime;
    }

    public void setCreationTime (Timestamp creationTime) {

        this.creationTime = creationTime;
    }

    public Timestamp getExpiration () {

        return this.expiration;
    }

    public void setExpiration (Timestamp expiration) {

        this.expiration = expiration;
    }

    public String getData () {

        return this.data;
    }

    public void setData (String data) {

        this.data = data;
    }
}
