package com.diluv.confluencia.database.record;

import java.sql.Timestamp;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "temp_users")
public class TempUsersEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "username")
    private String username;

    @Column(name = "display_name")
    private String displayName;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "password_type")
    private String passwordType;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "code")
    private String code;

    public long getId () {

        return this.id;
    }

    public void setId (long id) {

        this.id = id;
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

    public String getEmail () {

        return this.email;
    }

    public void setEmail (String email) {

        this.email = email;
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

    public Timestamp getCreatedAt () {

        return this.createdAt;
    }

    public void setCreatedAt (Timestamp createdAt) {

        this.createdAt = createdAt;
    }

    public String getCode () {

        return this.code;
    }

    public void setCode (String code) {

        this.code = code;
    }

    @Override
    public boolean equals (Object o) {

        if (this == o) return true;
        if (!(o instanceof TempUsersEntity)) return false;
        TempUsersEntity that = (TempUsersEntity) o;
        return getId() == that.getId() &&
            Objects.equals(getUsername(), that.getUsername()) &&
            Objects.equals(getDisplayName(), that.getDisplayName()) &&
            Objects.equals(getEmail(), that.getEmail()) &&
            Objects.equals(getPassword(), that.getPassword()) &&
            Objects.equals(getPasswordType(), that.getPasswordType()) &&
            Objects.equals(getCreatedAt(), that.getCreatedAt()) &&
            Objects.equals(getCode(), that.getCode());
    }

    @Override
    public int hashCode () {

        return Objects.hash(getId(), getUsername(), getDisplayName(), getEmail(), getPassword(), getPasswordType(), getCreatedAt(), getCode());
    }
}
