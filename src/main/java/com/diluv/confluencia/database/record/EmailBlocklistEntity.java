package com.diluv.confluencia.database.record;

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
}
