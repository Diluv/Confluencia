package com.diluv.confluencia.database.record;

import java.sql.Timestamp;

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
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "hash")
    private String hash;

    @Column(name = "completed")
    private boolean completed;

    @Column(name = "created_at")
    private Timestamp createdAt;

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

    public Timestamp getCreatedAt () {

        return this.createdAt;
    }

    public void setCreatedAt (Timestamp createdAt) {

        this.createdAt = createdAt;
    }
}
