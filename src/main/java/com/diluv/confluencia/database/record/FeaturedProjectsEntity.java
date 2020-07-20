package com.diluv.confluencia.database.record;

import javax.persistence.Column;import javax.persistence.Entity;
import org.hibernate.annotations.DynamicUpdate;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity

@Table(name = "featured_projects")
public class FeaturedProjectsEntity {
    @Id
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
}
