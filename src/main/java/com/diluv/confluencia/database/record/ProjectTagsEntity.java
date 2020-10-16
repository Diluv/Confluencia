package com.diluv.confluencia.database.record;

import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "project_id")
    private ProjectsEntity project;

    @Id
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
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

    @Override
    public boolean equals (Object o) {

        if (this == o) return true;
        if (!(o instanceof ProjectTagsEntity)) return false;
        ProjectTagsEntity that = (ProjectTagsEntity) o;
        return Objects.equals(getProject(), that.getProject()) &&
            Objects.equals(getTag(), that.getTag());
    }

    @Override
    public int hashCode () {

        return Objects.hash(getProject(), getTag());
    }
}
