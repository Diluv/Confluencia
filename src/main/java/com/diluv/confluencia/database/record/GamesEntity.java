package com.diluv.confluencia.database.record;

import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;

@Entity
@DynamicInsert
@Table(name = "games")
public class GamesEntity {

    @Id
    @Column(name = "slug")
    private String slug;

    @Column(name = "name")
    private String name;

    @Column(name = "url")
    private String url;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @OneToMany(mappedBy = "game")
    private List<ProjectTypesEntity> projectTypes;

    @OneToMany(mappedBy = "game")
    private List<GameVersionsEntity> gameVersions;

    @OneToOne(mappedBy = "game", fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    private GameDefaultProjectTypeEntity defaultProjectTypeEntity;

    public GamesEntity () {

    }

    public GamesEntity (String slug) {

        this.slug = slug;
    }

    public String getSlug () {

        return this.slug;
    }

    public void setSlug (String slug) {

        this.slug = slug;
    }

    public String getName () {

        return this.name;
    }

    public void setName (String name) {

        this.name = name;
    }

    public String getUrl () {

        return this.url;
    }

    public void setUrl (String url) {

        this.url = url;
    }

    public Timestamp getCreatedAt () {

        return this.createdAt;
    }

    public void setCreatedAt (Timestamp createdAt) {

        this.createdAt = createdAt;
    }

    public List<ProjectTypesEntity> getProjectTypes () {

        return this.projectTypes;
    }

    public void setProjectTypes (List<ProjectTypesEntity> projectTypes) {

        this.projectTypes = projectTypes;
    }

    public List<GameVersionsEntity> getGameVersions () {

        return this.gameVersions;
    }

    public void setGameVersions (List<GameVersionsEntity> gameVersions) {

        this.gameVersions = gameVersions;
    }

    public String getDefaultProjectTypeSlug () {

        return this.defaultProjectTypeEntity == null ? null : this.defaultProjectTypeEntity.getProjectTypeSlug();
    }

    public void setDefaultProjectTypeEntity (GameDefaultProjectTypeEntity defaultProjectTypeEntity) {

        this.defaultProjectTypeEntity = defaultProjectTypeEntity;
    }

    @Override
    public boolean equals (Object o) {

        if (this == o) return true;
        if (!(o instanceof GamesEntity)) return false;
        GamesEntity that = (GamesEntity) o;
        return Objects.equals(getSlug(), that.getSlug()) &&
            Objects.equals(getName(), that.getName()) &&
            Objects.equals(getUrl(), that.getUrl()) &&
            Objects.equals(getCreatedAt(), that.getCreatedAt());
    }

    @Override
    public int hashCode () {

        return Objects.hash(getSlug(), getName(), getUrl(), getCreatedAt());
    }
}