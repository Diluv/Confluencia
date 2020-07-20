package com.diluv.confluencia.database.record;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class ProjectLinksEntityPK implements Serializable {
    @Id
    @ManyToOne
    @JoinColumn(name = "project_id")
    private ProjectsEntity project;

    @Id
    @Column(name = "type")
    private String type;

    public ProjectsEntity getProject () {

        return this.project;
    }

    public void setProject (ProjectsEntity project) {

        this.project = project;
    }

    public String getType () {

        return this.type;
    }

    public void setType (String type) {

        this.type = type;
    }
}
