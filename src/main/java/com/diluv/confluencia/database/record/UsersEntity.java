package com.diluv.confluencia.database.record;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class UsersEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "email")
    private String email;

    @Column(name = "username")
    private String username;

    @Column(name = "display_name")
    private String displayName;

    @Column(name = "password")
    private String password;

    @Column(name = "password_type")
    private String passwordType;

    @Column(name = "mfa")
    private boolean mfa;

    @Column(name = "mfa_secret")
    private String mfaSecret;

    @Column(name = "created_at")
    private Timestamp createdAt;

    public UsersEntity () {

    }

    public UsersEntity (long id) {

        this.id = id;
    }

    public long getId () {

        return this.id;
    }

    public void setId (long id) {

        this.id = id;
    }

    public String getEmail () {

        return this.email;
    }

    public void setEmail (String email) {

        this.email = email;
    }

    public String getUsername () {

        return this.username;
    }

    public void setUsername (String username) {

        this.username = username;
    }

    public String getDisplayName () {

        return this.displayName;
    }

    public void setDisplayName (String displayName) {

        this.displayName = displayName;
    }

    public String getPassword () {

        return this.password;
    }

    public void setPassword (String password) {

        this.password = password;
    }

    public String getPasswordType () {

        return this.passwordType;
    }

    public void setPasswordType (String passwordType) {

        this.passwordType = passwordType;
    }

    public boolean isMfa () {

        return this.mfa;
    }

    public void setMfa (boolean mfa) {

        this.mfa = mfa;
    }

    public String getMfaSecret () {

        return this.mfaSecret;
    }

    public void setMfaSecret (String mfaSecret) {

        this.mfaSecret = mfaSecret;
    }

    public Timestamp getCreatedAt () {

        return this.createdAt;
    }

    public void setCreatedAt (Timestamp createdAt) {

        this.createdAt = createdAt;
    }
}
