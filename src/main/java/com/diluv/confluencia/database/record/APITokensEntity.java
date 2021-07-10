package com.diluv.confluencia.database.record;

import java.time.Instant;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Where;

@Entity
@DynamicInsert
@DynamicUpdate
@Where(clause = "deleted=0")
@Table(name = "api_tokens")
public class APITokensEntity {

    @Id
    @Column(name = "id")
    private long id;

    @Column(name = "token")
    private String token;

    @Column(name = "name")
    private String name;

    @Column(name = "created_at", updatable = false)
    private Instant createdAt;

    @Column(name = "last_used")
    private Instant lastUsed;

    @Column(name = "deleted")
    private boolean deleted;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UsersEntity user;

    public long getId () {

        return this.id;
    }

    public void setId (long id) {

        this.id = id;
    }

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

    public Instant getCreatedAt () {

        return this.createdAt;
    }

    public void setCreatedAt (Instant createdAt) {

        this.createdAt = createdAt;
    }

    public Instant getLastUsed () {

        return this.lastUsed;
    }

    public void setLastUsed (Instant lastUsed) {

        this.lastUsed = lastUsed;
    }

    public boolean isDeleted () {

        return this.deleted;
    }

    public void setDeleted (boolean deleted) {

        this.deleted = deleted;
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
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode () {

        return Objects.hash(getId());
    }
}
