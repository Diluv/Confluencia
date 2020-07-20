package com.diluv.confluencia.database.record;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;import javax.persistence.Entity;
import org.hibernate.annotations.DynamicUpdate;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity

@Table(name = "PersistedGrants")
public class PersistedGrantsEntity {
    private String key;
    private String type;
    private String subjectId;
    private String sessionId;
    private String clientId;
    private String description;
    private Timestamp creationTime;
    private Timestamp expiration;
    private String data;

    @Id
    @Column(name = "Key")
    public String getKey () {

        return this.key;
    }

    public void setKey (String key) {

        this.key = key;
    }

    @Basic
    @Column(name = "Type")
    public String getType () {

        return this.type;
    }

    public void setType (String type) {

        this.type = type;
    }

    @Basic
    @Column(name = "SubjectId")
    public String getSubjectId () {

        return this.subjectId;
    }

    public void setSubjectId (String subjectId) {

        this.subjectId = subjectId;
    }

    @Basic
    @Column(name = "SessionId")
    public String getSessionId () {

        return this.sessionId;
    }

    public void setSessionId (String sessionId) {

        this.sessionId = sessionId;
    }

    @Basic
    @Column(name = "ClientId")
    public String getClientId () {

        return this.clientId;
    }

    public void setClientId (String clientId) {

        this.clientId = clientId;
    }

    @Basic
    @Column(name = "Description")
    public String getDescription () {

        return this.description;
    }

    public void setDescription (String description) {

        this.description = description;
    }

    @Basic
    @Column(name = "CreationTime")
    public Timestamp getCreationTime () {

        return this.creationTime;
    }

    public void setCreationTime (Timestamp creationTime) {

        this.creationTime = creationTime;
    }

    @Basic
    @Column(name = "Expiration")
    public Timestamp getExpiration () {

        return this.expiration;
    }

    public void setExpiration (Timestamp expiration) {

        this.expiration = expiration;
    }

    @Basic
    @Column(name = "Data")
    public String getData () {

        return this.data;
    }

    public void setData (String data) {

        this.data = data;
    }
}
