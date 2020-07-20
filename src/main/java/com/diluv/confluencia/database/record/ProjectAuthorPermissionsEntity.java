package com.diluv.confluencia.database.record;

import javax.persistence.*;

@Entity
@Table(name = "project_author_permissions")
@IdClass(ProjectAuthorPermissionsEntityPK.class)
public class ProjectAuthorPermissionsEntity {

    @Id
    @ManyToOne
    @JoinColumn(name = "project_author_id", columnDefinition = "id")
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
