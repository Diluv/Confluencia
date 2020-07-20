package com.diluv.confluencia.database.record;

import java.sql.Timestamp;

import javax.persistence.Basic;
import javax.persistence.Column;import javax.persistence.Entity;
import org.hibernate.annotations.DynamicUpdate;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity

@Table(name = "user_compromised_passwords")
public class UserCompromisedPasswordsEntity {
    private String passwordHash;
    private long occurrences;
    private Timestamp lastUpdated;

    @Id
    @Column(name = "password_hash")
    public String getPasswordHash () {

        return this.passwordHash;
    }

    public void setPasswordHash (String passwordHash) {

        this.passwordHash = passwordHash;
    }

    @Basic
    @Column(name = "occurrences")
    public long getOccurrences () {

        return this.occurrences;
    }

    public void setOccurrences (long occurrences) {

        this.occurrences = occurrences;
    }

    @Basic
    @Column(name = "last_updated")
    public Timestamp getLastUpdated () {

        return this.lastUpdated;
    }

    public void setLastUpdated (Timestamp lastUpdated) {

        this.lastUpdated = lastUpdated;
    }
}
