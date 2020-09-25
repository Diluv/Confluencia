package com.diluv.confluencia.database.record;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

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

    @Override
    public boolean equals (Object o) {

        if (this == o) return true;
        if (!(o instanceof UserRolesEntity)) return false;
        UserRolesEntity that = (UserRolesEntity) o;
        return Objects.equals(getUser(), that.getUser()) &&
            Objects.equals(getRole(), that.getRole());
    }

    @Override
    public int hashCode () {

        return Objects.hash(getUser(), getRole());
    }
}
