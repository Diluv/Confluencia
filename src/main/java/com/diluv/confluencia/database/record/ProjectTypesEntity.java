package com.diluv.confluencia.database.record;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;
import org.hibernate.annotations.Where;

import java.util.List;
import java.util.Objects;

import javax.persistence.*;

@Entity
@DynamicUpdate
@SelectBeforeUpdate
@Where(clause = "deleted=0")
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

    @OneToMany(mappedBy = "projectType", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    private List<ProjectTypeLoadersEntity> projectTypeLoaders;

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

    public List<ProjectTypeLoadersEntity> getProjectTypeLoaders () {

        return this.projectTypeLoaders;
    }

    public void setProjectTypeLoaders (List<ProjectTypeLoadersEntity> projectTypeLoaders) {

        this.projectTypeLoaders = projectTypeLoaders;
    }

    @Override
    public boolean equals (Object o) {

        if (this == o) return true;
        if (!(o instanceof ProjectTypesEntity)) return false;
        ProjectTypesEntity that = (ProjectTypesEntity) o;
        return getMaxFileSize() == that.getMaxFileSize() &&
            Objects.equals(getGame(), that.getGame()) &&
            Objects.equals(getSlug(), that.getSlug()) &&
            Objects.equals(getName(), that.getName());
    }

    @Override
    public int hashCode () {

        return Objects.hash(getGame(), getSlug(), getName(), getMaxFileSize());
    }
}
