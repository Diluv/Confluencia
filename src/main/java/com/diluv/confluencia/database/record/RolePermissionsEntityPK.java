package com.diluv.confluencia.database.record;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class RolePermissionsEntityPK implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name = "role_id")
    private RolesEntity role;

    @Id
    @Column(name = "permission")
    private String permission;

    public RolesEntity getRole () {

        return this.role;
    }

    public void setRole (RolesEntity role) {

        this.role = role;
    }

    public String getPermission () {

        return this.permission;
    }

    public void setPermission (String permission) {

        this.permission = permission;
    }

    @Override
    public boolean equals (Object o) {

        if (this == o) return true;
        if (!(o instanceof RolePermissionsEntityPK)) return false;
        RolePermissionsEntityPK that = (RolePermissionsEntityPK) o;
        return Objects.equals(getRole(), that.getRole()) &&
            Objects.equals(getPermission(), that.getPermission());
    }

    @Override
    public int hashCode () {

        return Objects.hash(getRole(), getPermission());
    }
}
