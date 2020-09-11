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
@Table(name = "project_type_loaders")
public class ProjectTypeLoadersEntity {

    @Id
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumns({
        @JoinColumn(name = "project_type_slug"),
        @JoinColumn(name = "game_slug")
    })
    private ProjectTypesEntity projectType;

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
            Objects.equals(getProjectType(), that.getProjectType());
    }

    @Override
    public int hashCode () {

        return Objects.hash(getId(), getName(), getProjectType());
    }
}
