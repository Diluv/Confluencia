package com.diluv.confluencia.database.record;

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
}