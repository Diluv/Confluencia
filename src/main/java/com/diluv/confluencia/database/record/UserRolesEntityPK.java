package com.diluv.confluencia.database.record;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class UserRolesEntityPK implements Serializable {

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

    @Override
    public boolean equals (Object o) {

        if (this == o) return true;
        if (!(o instanceof UserRolesEntityPK)) return false;
        UserRolesEntityPK that = (UserRolesEntityPK) o;
        return Objects.equals(getUser(), that.getUser()) &&
            Objects.equals(getRole(), that.getRole());
    }

    @Override
    public int hashCode () {

        return Objects.hash(getUser(), getRole());
    }
}
