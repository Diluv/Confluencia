package com.diluv.confluencia.database.record;

import java.sql.Timestamp;

import javax.persistence.Basic;
import javax.persistence.Column;import javax.persistence.Entity;
import org.hibernate.annotations.DynamicUpdate;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity

@Table(name = "temp_users")
public class TempUsersEntity {
    private long id;
    private String username;
    private String displayName;
    private String email;
    private String password;
    private String passwordType;
    private Timestamp createdAt;
    private String code;

    @Id
    @Column(name = "id")
    public long getId () {

        return this.id;
    }

    public void setId (long id) {

        this.id = id;
    }

    @Basic
    @Column(name = "username")
    public String getUsername () {

        return this.username;
    }

    public void setUsername (String username) {

        this.username = username;
    }

    @Basic
    @Column(name = "display_name")
    public String getDisplayName () {

        return this.displayName;
    }

    public void setDisplayName (String displayName) {

        this.displayName = displayName;
    }

    @Basic
    @Column(name = "email")
    public String getEmail () {

        return this.email;
    }

    public void setEmail (String email) {

        this.email = email;
    }

    @Basic
    @Column(name = "password")
    public String getPassword () {

        return this.password;
    }

    public void setPassword (String password) {

        this.password = password;
    }

    @Basic
    @Column(name = "password_type")
    public String getPasswordType () {

        return this.passwordType;
    }

    public void setPasswordType (String passwordType) {

        this.passwordType = passwordType;
    }

    @Basic
    @Column(name = "created_at")
    public Timestamp getCreatedAt () {

        return this.createdAt;
    }

    public void setCreatedAt (Timestamp createdAt) {

        this.createdAt = createdAt;
    }

    @Basic
    @Column(name = "code")
    public String getCode () {

        return this.code;
    }

    public void setCode (String code) {

        this.code = code;
    }
}
