package com.diluv.confluencia.database.record;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "project_tags")
@IdClass(ProjectTagsEntityPK.class)
public class ProjectTagsEntity {

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

    public void setTag (TagsEntity tag) {

        this.tag = tag;
    }
}
