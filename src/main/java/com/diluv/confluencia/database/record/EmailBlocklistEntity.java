package com.diluv.confluencia.database.record;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "email_blocklist")
public class EmailBlocklistEntity {

    @Id
    @Column(name = "email")
    private String email;

    @Column(name = "reason")
    private String reason;

    public String getEmail () {

        return this.email;
    }

    public void setEmail (String email) {

        this.email = email;
    }

    public String getReason () {

        return this.reason;
    }

    public void setReason (String reason) {

        this.reason = reason;
    }

    @Override
    public boolean equals (Object o) {

        if (this == o) return true;
        if (!(o instanceof EmailBlocklistEntity)) return false;
        EmailBlocklistEntity that = (EmailBlocklistEntity) o;
        return Objects.equals(getEmail(), that.getEmail()) &&
            Objects.equals(getReason(), that.getReason());
    }

    @Override
    public int hashCode () {

        return Objects.hash(getEmail(), getReason());
    }
}
