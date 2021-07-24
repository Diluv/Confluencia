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
@Table(name = "nodecdn_commits")
public class NodeCDNCommitsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "hash")
    private String hash;

    @Column(name = "completed")
    private boolean completed;

    @Column(name = "created_at")
    private Instant createdAt;

    public NodeCDNCommitsEntity () {

    }

    public NodeCDNCommitsEntity (long id) {

        this.id = id;
    }

    public long getId () {

        return this.id;
    }

    public void setId (long id) {

        this.id = id;
    }

    public String getHash () {

        return this.hash;
    }

    public void setHash (String hash) {

        this.hash = hash;
    }

    public boolean isCompleted () {

        return this.completed;
    }

    public void setCompleted (boolean completed) {

        this.completed = completed;
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
        if (!(o instanceof NodeCDNCommitsEntity)) return false;
        NodeCDNCommitsEntity entity = (NodeCDNCommitsEntity) o;
        return getId() == entity.getId() &&
            isCompleted() == entity.isCompleted() &&
            Objects.equals(getHash(), entity.getHash()) &&
            Objects.equals(getCreatedAt(), entity.getCreatedAt());
    }

    @Override
    public int hashCode () {

        return Objects.hash(getId(), getHash(), isCompleted(), getCreatedAt());
    }
}
