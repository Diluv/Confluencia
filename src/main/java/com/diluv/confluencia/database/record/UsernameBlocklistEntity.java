package com.diluv.confluencia.database.record;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "username_blocklist")
public class UsernameBlocklistEntity {

    @Id
    @Column(name = "username")
    private String username;

    public String getUsername () {

        return this.username;
    }

    public void setUsername (String username) {

        this.username = username;
    }

    @Override
    public boolean equals (Object o) {

        if (this == o) return true;
        if (!(o instanceof UsernameBlocklistEntity)) return false;
        UsernameBlocklistEntity that = (UsernameBlocklistEntity) o;
        return Objects.equals(getUsername(), that.getUsername());
    }

    @Override
    public int hashCode () {

        return Objects.hash(getUsername());
    }
}