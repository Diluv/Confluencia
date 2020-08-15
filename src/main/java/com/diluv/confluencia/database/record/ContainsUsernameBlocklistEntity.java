package com.diluv.confluencia.database.record;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "contains_username_blocklist")
public class ContainsUsernameBlocklistEntity {

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
        if (!(o instanceof ContainsUsernameBlocklistEntity)) return false;
        ContainsUsernameBlocklistEntity that = (ContainsUsernameBlocklistEntity) o;
        return Objects.equals(getUsername(), that.getUsername());
    }

    @Override
    public int hashCode () {

        return Objects.hash(getUsername());
    }
}
