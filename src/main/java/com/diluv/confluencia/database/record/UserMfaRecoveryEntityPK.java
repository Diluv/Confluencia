package com.diluv.confluencia.database.record;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;

public class UserMfaRecoveryEntityPK implements Serializable {
    private long userId;
    private String code;

    @Column(name = "user_id")
    @Id
    public long getUserId () {

        return this.userId;
    }

    public void setUserId (long userId) {

        this.userId = userId;
    }

    @Column(name = "code")
    @Id
    public String getCode () {

        return this.code;
    }

    public void setCode (String code) {

        this.code = code;
    }
}
