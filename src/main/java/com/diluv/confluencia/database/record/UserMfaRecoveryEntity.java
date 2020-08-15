package com.diluv.confluencia.database.record;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "user_mfa_recovery")
@IdClass(UserMfaRecoveryEntityPK.class)
public class UserMfaRecoveryEntity {

    @Id
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UsersEntity user;

    @Id
    @Column(name = "code")
    private String code;

    @Column(name = "valid")
    private boolean valid;

    public UserMfaRecoveryEntity () {

    }

    public UserMfaRecoveryEntity (UsersEntity user, String code) {

        this.user = user;
        this.code = code;
    }

    public UsersEntity getUser () {

        return this.user;
    }

    public void setUser (UsersEntity user) {

        this.user = user;
    }

    public String getCode () {

        return this.code;
    }

    public void setCode (String code) {

        this.code = code;
    }

    public boolean isValid () {

        return this.valid;
    }

    public void setValid (boolean valid) {

        this.valid = valid;
    }

    @Override
    public boolean equals (Object o) {

        if (this == o) return true;
        if (!(o instanceof UserMfaRecoveryEntity)) return false;
        UserMfaRecoveryEntity entity = (UserMfaRecoveryEntity) o;
        return isValid() == entity.isValid() &&
            Objects.equals(getUser(), entity.getUser()) &&
            Objects.equals(getCode(), entity.getCode());
    }

    @Override
    public int hashCode () {

        return Objects.hash(getUser(), getCode(), isValid());
    }
}