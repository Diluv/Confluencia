package com.diluv.confluencia.database.record;

import javax.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "user_roles")
public class UserRolesEntity implements Serializable {

    @Id
    @OneToOne
    @JoinColumn(name = "user_id")
    private UsersEntity user;

    @OneToOne
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
