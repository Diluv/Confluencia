package com.diluv.confluencia.database.record;

import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.DynamicUpdate;

@Entity

@Table(name = "project_types")
@IdClass(ProjectTypesEntityPK.class)
public class ProjectTypesEntity {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "game_slug")
    private GamesEntity game;

    @Id
    @Column(name = "slug")
    private String slug;

    @Column(name = "name")
    private String name;

    @Column(name = "max_file_size")
    private long maxFileSize;

    @OneToMany(mappedBy = "projectType", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    private List<TagsEntity> tags;

    public ProjectTypesEntity () {

    }

    public ProjectTypesEntity (GamesEntity game, String projectTypeSlug) {

        this.game = game;
        this.slug = projectTypeSlug;
    }

    public GamesEntity getGame () {

        return this.game;
    }

    public void setGame (GamesEntity game) {

        this.game = game;
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

    public long getMaxFileSize () {

        return this.maxFileSize;
    }

    public void setMaxFileSize (long maxFileSize) {

        this.maxFileSize = maxFileSize;
    }

    public List<TagsEntity> getTags () {

        return this.tags;
    }

    public void setTags (List<TagsEntity> tags) {

        this.tags = tags;
    }
}
