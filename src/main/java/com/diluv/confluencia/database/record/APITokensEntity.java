package com.diluv.confluencia.database.record;

import java.time.Instant;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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

    @OneToMany(mappedBy = "apiToken", cascade = CascadeType.ALL)
    private List<APITokenPermissionsEntity> permissions;

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

    public List<APITokenPermissionsEntity> getPermissions () {

        return this.permissions;
    }

    public void setPermissions (List<APITokenPermissionsEntity> permissions) {

        this.permissions = permissions;
    }

    @Override
    public boolean equals (Object o) {

        if (this == o) return true;
        if (!(o instanceof APITokensEntity)) return false;

        APITokensEntity that = (APITokensEntity) o;

        if (this.id != that.id) return false;
        if (this.deleted != that.deleted) return false;
        if (!Objects.equals(this.token, that.token)) return false;
        if (!Objects.equals(this.name, that.name)) return false;
        if (!Objects.equals(this.createdAt, that.createdAt)) return false;
        if (!Objects.equals(this.lastUsed, that.lastUsed)) return false;
        if (!Objects.equals(this.user, that.user)) return false;
        return Objects.equals(this.permissions, that.permissions);
    }

    @Override
    public int hashCode () {

        int result = (int) (this.id ^ (this.id >>> 32));
        result = 31 * result + (this.token != null ? this.token.hashCode() : 0);
        result = 31 * result + (this.name != null ? this.name.hashCode() : 0);
        result = 31 * result + (this.createdAt != null ? this.createdAt.hashCode() : 0);
        result = 31 * result + (this.lastUsed != null ? this.lastUsed.hashCode() : 0);
        result = 31 * result + (this.deleted ? 1 : 0);
        result = 31 * result + (this.user != null ? this.user.hashCode() : 0);
        result = 31 * result + (this.permissions != null ? this.permissions.hashCode() : 0);
        return result;
    }
}
