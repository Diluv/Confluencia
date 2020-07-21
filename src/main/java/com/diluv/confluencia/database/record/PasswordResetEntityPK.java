package com.diluv.confluencia.database.record;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class PasswordResetEntityPK implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UsersEntity user;

    @Id
    @Column(name = "code")
    private String code;

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
}
