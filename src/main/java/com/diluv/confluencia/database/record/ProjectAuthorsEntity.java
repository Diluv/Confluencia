package com.diluv.confluencia.database.record;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;import javax.persistence.Entity;
import org.hibernate.annotations.DynamicUpdate;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity

@Table(name = "project_authors")
public class ProjectAuthorsEntity {

    @Id
    @Column(name = "id")
    private long id;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private ProjectsEntity project;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UsersEntity user;

    @Column(name = "role")
    private String role;

    @OneToMany(mappedBy = "author", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    private List<ProjectAuthorPermissionsEntity> permissions;

    public long getId () {

        return this.id;
    }

    public void setId (long id) {

        this.id = id;
    }

    public ProjectsEntity getProject () {

        return this.project;
    }

    public void setProject (ProjectsEntity project) {

        this.project = project;
    }

    public UsersEntity getUser () {

        return this.user;
    }

    public void setUser (UsersEntity user) {

        this.user = user;
    }

    public String getRole () {

        return this.role;
    }

    public void setRole (String role) {

        this.role = role;
    }

    public List<ProjectAuthorPermissionsEntity> getPermissions () {

        return this.permissions;
    }

    public void setPermissions (List<ProjectAuthorPermissionsEntity> permissions) {

        this.permissions = permissions;
    }
}
