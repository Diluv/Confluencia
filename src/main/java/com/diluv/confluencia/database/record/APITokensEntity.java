package com.diluv.confluencia.database.record;

import java.sql.Timestamp;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "api_tokens")
public class APITokensEntity {

    @Id
    @Column(name = "token")
    private String token;

    @Column(name = "name")
    private String name;

    @Column(name = "created_at", updatable = false)
    private Timestamp createdAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UsersEntity user;

    public String getToken () {

        return this.token;
    }

    public void setToken (String token) {

        this.token = token;
    }

    public String getName () {

        return this.name;
    }

    public void setName (String name) {

        this.name = name;
    }

    public Timestamp getCreatedAt () {

        return this.createdAt;
    }

    public void setCreatedAt (Timestamp createdAt) {

        this.createdAt = createdAt;
    }

    public UsersEntity getUser () {

        return this.user;
    }

    public void setUser (UsersEntity user) {

        this.user = user;
    }

    @Override
    public boolean equals (Object o) {

        if (this == o) return true;
        if (!(o instanceof APITokensEntity)) return false;
        APITokensEntity that = (APITokensEntity) o;
        return Objects.equals(getToken(), that.getToken());
    }

    @Override
    public int hashCode () {

        return Objects.hash(getToken());
    }
}
