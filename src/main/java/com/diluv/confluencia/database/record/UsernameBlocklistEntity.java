package com.diluv.confluencia.database.record;

import javax.persistence.Column;import javax.persistence.Entity;
import org.hibernate.annotations.DynamicUpdate;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity

@Table(name = "username_blocklist")
public class UsernameBlocklistEntity {
    private String username;

    @Id
    @Column(name = "username")
    public String getUsername () {

        return this.username;
    }

    public void setUsername (String username) {

        this.username = username;
    }
}