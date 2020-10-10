package com.diluv.confluencia.database.record;

import javax.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "project_type_loaders")
public class ProjectTypeLoadersEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "slug")
    private String slug;

    @ManyToOne
    @JoinColumns({
        @JoinColumn(name = "project_type_slug"),
        @JoinColumn(name = "game_slug")
    })
    private ProjectTypesEntity projectType;

    public ProjectTypeLoadersEntity () {

    }

    public ProjectTypeLoadersEntity (String slug, String name) {

        this.slug = slug;
        this.name = name;
    }

    public long getId () {

        return this.id;
    }

    public void setId (long id) {

        this.id = id;
    }

    public String getName () {

        return this.name;
    }

    public void setName (String name) {

        this.name = name;
    }

    public String getSlug () {

        return this.slug;
    }

    public void setSlug (String slug) {

        this.slug = slug;
    }

    public ProjectTypesEntity getProjectType () {

        return this.projectType;
    }

    public void setProjectType (ProjectTypesEntity projectType) {

        this.projectType = projectType;
    }

    @Override
    public boolean equals (Object o) {

        if (this == o) return true;
        if (!(o instanceof ProjectTypeLoadersEntity)) return false;
        ProjectTypeLoadersEntity that = (ProjectTypeLoadersEntity) o;
        return getId() == that.getId() &&
            Objects.equals(getName(), that.getName()) &&
            Objects.equals(getSlug(), that.getSlug()) &&
            Objects.equals(getProjectType(), that.getProjectType());
    }

    @Override
    public int hashCode () {

        return Objects.hash(getId(), getName(), getSlug(), getProjectType());
    }
}
