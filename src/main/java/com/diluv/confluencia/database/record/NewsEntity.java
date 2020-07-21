package com.diluv.confluencia.database.record;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "news")
public class NewsEntity {

    @Id
    @Column(name = "slug")
    private String slug;

    @Column(name = "title")
    private String title;

    @Column(name = "summary")
    private String summary;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UsersEntity user;

    @Column(name = "created_at")
    private Timestamp createdAt;

    public String getSlug () {

        return this.slug;
    }

    public void setSlug (String slug) {

        this.slug = slug;
    }

    public String getTitle () {

        return this.title;
    }

    public void setTitle (String title) {

        this.title = title;
    }

    public String getSummary () {

        return this.summary;
    }

    public void setSummary (String summary) {

        this.summary = summary;
    }

    public String getDescription () {

        return this.description;
    }

    public void setDescription (String description) {

        this.description = description;
    }

    public UsersEntity getUser () {

        return this.user;
    }

    public void setUser (UsersEntity user) {

        this.user = user;
    }

    public Timestamp getCreatedAt () {

        return this.createdAt;
    }

    public void setCreatedAt (Timestamp createdAt) {

        this.createdAt = createdAt;
    }
}
