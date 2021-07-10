package com.diluv.confluencia.database.record;

import java.time.Instant;
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

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "password_type")
    private String passwordType;

    @Column(name = "created_at")
    private Instant createdAt;

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

    public Instant getCreatedAt () {

        return this.createdAt;
    }

    public void setCreatedAt (Instant createdAt) {

        this.createdAt = createdAt;
    }

    @Override
    public boolean equals (Object o) {

        if (this == o) return true;
        if (!(o instanceof TempUsersEntity)) return false;
        TempUsersEntity that = (TempUsersEntity) o;
        return getId() == that.getId() &&
            Objects.equals(getUsername(), that.getUsername()) &&
            Objects.equals(getEmail(), that.getEmail()) &&
            Objects.equals(getPassword(), that.getPassword()) &&
            Objects.equals(getPasswordType(), that.getPasswordType()) &&
            Objects.equals(getCreatedAt(), that.getCreatedAt());
    }

    @Override
    public int hashCode () {

        return Objects.hash(getId(), getUsername(), getEmail(), getPassword(), getPasswordType(), getCreatedAt());
    }
}
