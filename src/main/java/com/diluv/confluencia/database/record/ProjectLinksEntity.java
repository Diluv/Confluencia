package com.diluv.confluencia.database.record;

import javax.persistence.Column;import javax.persistence.Entity;
import org.hibernate.annotations.DynamicUpdate;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity

@Table(name = "project_links")
@IdClass(ProjectLinksEntityPK.class)
public class ProjectLinksEntity {
    @Id
    @ManyToOne
    @JoinColumn(name = "project_id")
    private ProjectsEntity project;

    @Id
    @Column(name = "type")
    private String type;

    @Column(name = "url")
    private String url;

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

    public String getUrl () {

        return this.url;
    }

    public void setUrl (String url) {

        this.url = url;
    }
}
