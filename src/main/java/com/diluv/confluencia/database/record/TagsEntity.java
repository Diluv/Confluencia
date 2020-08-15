package com.diluv.confluencia.database.record;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tags")
public class TagsEntity {

    @Id
    @Column(name = "id")
    private long id;

    @Column(name = "slug")
    private String slug;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumns({
        @JoinColumn(name = "project_type_slug"),
        @JoinColumn(name = "game_slug")
    })
    private ProjectTypesEntity projectType;

    @ManyToOne
    @JoinColumn(name = "game_slug", insertable = false, updatable = false)
    private GamesEntity game;

    public long getId () {

        return this.id;
    }

    public void setId (long id) {

        this.id = id;
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

    public ProjectTypesEntity getProjectType () {

        return this.projectType;
    }

    public void setProjectType (ProjectTypesEntity projectType) {

        this.projectType = projectType;
    }

    public GamesEntity getGame () {

        return this.game;
    }

    public void setGame (GamesEntity game) {

        this.game = game;
    }

    @Override
    public boolean equals (Object o) {

        if (this == o) return true;
        if (!(o instanceof TagsEntity)) return false;
        TagsEntity that = (TagsEntity) o;
        return getId() == that.getId() &&
            Objects.equals(getSlug(), that.getSlug()) &&
            Objects.equals(getName(), that.getName()) &&
            Objects.equals(getProjectType(), that.getProjectType()) &&
            Objects.equals(getGame(), that.getGame());
    }

    @Override
    public int hashCode () {

        return Objects.hash(getId(), getSlug(), getName(), getProjectType(), getGame());
    }
}
