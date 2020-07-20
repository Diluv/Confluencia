package com.diluv.confluencia.database.record;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;

public class RolePermissionsEntityPK implements Serializable {
    private long roleId;
    private String permission;

    @Column(name = "role_id")
    @Id
    public long getRoleId () {

        return this.roleId;
    }

    public void setRoleId (long roleId) {

        this.roleId = roleId;
    }

    @Column(name = "permission")
    @Id
    public String getPermission () {

        return this.permission;
    }

    public void setPermission (String permission) {

        this.permission = permission;
    }
}
