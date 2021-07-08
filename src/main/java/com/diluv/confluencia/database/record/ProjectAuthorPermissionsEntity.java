package com.diluv.confluencia.database.record;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@DynamicInsert
@DynamicUpdate
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

    public ProjectAuthorPermissionsEntity () {

    }

    public ProjectAuthorPermissionsEntity (ProjectAuthorsEntity author, String permission) {

        this.author = author;
        this.permission = permission;
    }

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

    @Override
    public boolean equals (Object o) {

        if (this == o) return true;
        if (!(o instanceof ProjectAuthorPermissionsEntity)) return false;
        ProjectAuthorPermissionsEntity that = (ProjectAuthorPermissionsEntity) o;
        return Objects.equals(getAuthor(), that.getAuthor()) &&
            Objects.equals(getPermission(), that.getPermission());
    }

    @Override
    public int hashCode () {

        return Objects.hash(getAuthor(), getPermission());
    }
}
