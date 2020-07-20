package com.diluv.confluencia.database.record;

import javax.persistence.Basic;
import javax.persistence.Column;import javax.persistence.Entity;
import org.hibernate.annotations.DynamicUpdate;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity

@Table(name = "user_mfa_recovery")
@IdClass(UserMfaRecoveryEntityPK.class)
public class UserMfaRecoveryEntity {
    private long userId;
    private String code;
    private Byte valid;

    @Id
    @Column(name = "user_id")
    public long getUserId () {

        return this.userId;
    }

    public void setUserId (long userId) {

        this.userId = userId;
    }

    @Id
    @Column(name = "code")
    public String getCode () {

        return this.code;
    }

    public void setCode (String code) {

        this.code = code;
    }

    @Basic
    @Column(name = "valid")
    public Byte getValid () {

        return this.valid;
    }

    public void setValid (Byte valid) {

        this.valid = valid;
    }
}