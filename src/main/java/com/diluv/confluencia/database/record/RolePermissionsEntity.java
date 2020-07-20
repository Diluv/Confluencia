package com.diluv.confluencia.database.record;

import javax.persistence.Column;import javax.persistence.Entity;
import org.hibernate.annotations.DynamicUpdate;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity

@Table(name = "role_permissions")
@IdClass(RolePermissionsEntityPK.class)
public class RolePermissionsEntity {
    private long roleId;
    private String permission;

    @Id
    @Column(name = "role_id")
    public long getRoleId () {

        return this.roleId;
    }

    public void setRoleId (long roleId) {

        this.roleId = roleId;
    }

    @Id
    @Column(name = "permission")
    public String getPermission () {

        return this.permission;
    }

    public void setPermission (String permission) {

        this.permission = permission;
    }
}
