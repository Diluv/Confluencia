package com.diluv.confluencia.database.record;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;import javax.persistence.Entity;
import org.hibernate.annotations.DynamicUpdate;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity

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

    @OneToOne(mappedBy = "game", fetch = FetchType.LAZY)
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

    public ProjectTypesEntity getDefaultProjectTypeEntity () {

        return this.defaultProjectTypeEntity.getProjectType();
    }

    public void setDefaultProjectTypeEntity (GameDefaultProjectTypeEntity defaultProjectTypeEntity) {

        this.defaultProjectTypeEntity = defaultProjectTypeEntity;
    }
}