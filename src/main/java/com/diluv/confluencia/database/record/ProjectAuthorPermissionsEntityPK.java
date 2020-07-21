package com.diluv.confluencia.database.record;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class ProjectAuthorPermissionsEntityPK implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name = "project_author_id")
    private ProjectAuthorsEntity author;

    @Id
    @Column(name = "permission")
    private String permission;

    public ProjectAuthorsEntity getAuthor () {

        return this.author;
    }

    public void setAuthor (ProjectAuthorsEntity author) {

        this.author = author;
    }

    public String getPermission () {

        return this.permission;
    }

    public void setPermission (String permission) {

        this.permission = permission;
    }
}