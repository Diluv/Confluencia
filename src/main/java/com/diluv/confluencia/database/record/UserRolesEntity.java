package com.diluv.confluencia.database.record;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "user_roles")
@IdClass(UserRolesEntityPK.class)
public class UserRolesEntity {

    @Id
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UsersEntity user;

    @Id
    @ManyToOne
    @JoinColumn(name = "role_id")
    private RolesEntity role;

    public UsersEntity getUser () {

        return this.user;
    }

    public void setUser (UsersEntity user) {

        this.user = user;
    }

    public RolesEntity getRole () {

        return this.role;
    }

    public void setRole (RolesEntity role) {

        this.role = role;
    }
}
