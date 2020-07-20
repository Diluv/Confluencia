package com.diluv.confluencia.database.record;

import javax.persistence.Column;import javax.persistence.Entity;
import org.hibernate.annotations.DynamicUpdate;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity

@Table(name = "user_roles")
@IdClass(UserRolesEntityPK.class)
public class UserRolesEntity {
    private long userId;
    private long roleId;

    @Id
    @Column(name = "user_id")
    public long getUserId () {

        return this.userId;
    }

    public void setUserId (long userId) {

        this.userId = userId;
    }

    @Id
    @Column(name = "role_id")
    public long getRoleId () {

        return this.roleId;
    }

    public void setRoleId (long roleId) {

        this.roleId = roleId;
    }
}
