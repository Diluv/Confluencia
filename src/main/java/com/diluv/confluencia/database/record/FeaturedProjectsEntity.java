package com.diluv.confluencia.database.record;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "featured_projects")
public class FeaturedProjectsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private byte id;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private ProjectsEntity project;

    public byte getId () {

        return this.id;
    }

    public void setId (byte id) {

        this.id = id;
    }

    public ProjectsEntity getProject () {

        return this.project;
    }

    public void setProject (ProjectsEntity project) {

        this.project = project;
    }

    @Override
    public boolean equals (Object o) {

        if (this == o) return true;
        if (!(o instanceof FeaturedProjectsEntity)) return false;
        FeaturedProjectsEntity that = (FeaturedProjectsEntity) o;
        return getId() == that.getId() &&
            Objects.equals(getProject(), that.getProject());
    }

    @Override
    public int hashCode () {

        return Objects.hash(getId(), getProject());
    }
}
