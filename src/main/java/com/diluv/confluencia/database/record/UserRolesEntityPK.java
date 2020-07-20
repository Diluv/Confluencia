package com.diluv.confluencia.database.record;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;

public class UserRolesEntityPK implements Serializable {
    private long userId;
    private long roleId;

    @Column(name = "user_id")
    @Id
    public long getUserId () {

        return this.userId;
    }

    public void setUserId (long userId) {

        this.userId = userId;
    }

    @Column(name = "role_id")
    @Id
    public long getRoleId () {

        return this.roleId;
    }

    public void setRoleId (long roleId) {

        this.roleId = roleId;
    }
}
