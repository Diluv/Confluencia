package com.diluv.confluencia.database.record;

import java.io.Serializable;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class ProjectTagsEntityPK implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name = "project_id")
    private ProjectsEntity project;

    @Id
    @ManyToOne
    @JoinColumn(name = "tag_id")
    private TagsEntity tag;

    public ProjectsEntity getProject () {

        return this.project;
    }

    public void setProject (ProjectsEntity project) {

        this.project = project;
    }

    public TagsEntity getTag () {

        return this.tag;
    }

    public void setTag (TagsEntity tags) {

        this.tag = tags;
    }
}
