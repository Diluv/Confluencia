package com.diluv.confluencia.database.record;

import java.sql.Timestamp;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_compromised_passwords")
public class UserCompromisedPasswordsEntity {

    @Id
    @Column(name = "password_hash")
    private String passwordHash;

    @Column(name = "occurrences")
    private long occurrences;

    @Column(name = "last_updated")
    private Timestamp lastUpdated;

    public String getPasswordHash () {

        return this.passwordHash;
    }

    public void setPasswordHash (String passwordHash) {

        this.passwordHash = passwordHash;
    }

    public long getOccurrences () {

        return this.occurrences;
    }

    public void setOccurrences (long occurrences) {

        this.occurrences = occurrences;
    }

    public Timestamp getLastUpdated () {

        return this.lastUpdated;
    }

    public void setLastUpdated (Timestamp lastUpdated) {

        this.lastUpdated = lastUpdated;
    }

    @Override
    public boolean equals (Object o) {

        if (this == o) return true;
        if (!(o instanceof UserCompromisedPasswordsEntity)) return false;
        UserCompromisedPasswordsEntity that = (UserCompromisedPasswordsEntity) o;
        return getOccurrences() == that.getOccurrences() &&
            Objects.equals(getPasswordHash(), that.getPasswordHash()) &&
            Objects.equals(getLastUpdated(), that.getLastUpdated());
    }

    @Override
    public int hashCode () {

        return Objects.hash(getPasswordHash(), getOccurrences(), getLastUpdated());
    }
}
