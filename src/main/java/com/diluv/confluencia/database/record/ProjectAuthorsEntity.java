package com.diluv.confluencia.database.record;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.*;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "project_authors")
public class ProjectAuthorsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProjectAuthorPermissionsEntity> permissions = new ArrayList<>();

    public ProjectAuthorsEntity () {

    }

    public ProjectAuthorsEntity (UsersEntity user, String role) {

        this.user = user;
        this.role = role;
    }

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

    public void addPermission (String permission) {

        this.permissions.add(new ProjectAuthorPermissionsEntity(this, permission));
    }

    public void setPermissions (List<ProjectAuthorPermissionsEntity> permissions) {

        this.permissions = permissions;
    }

    @Override
    public boolean equals (Object o) {

        if (this == o) return true;
        if (!(o instanceof ProjectAuthorsEntity)) return false;
        ProjectAuthorsEntity that = (ProjectAuthorsEntity) o;
        return getId() == that.getId() &&
            Objects.equals(getProject(), that.getProject()) &&
            Objects.equals(getUser(), that.getUser()) &&
            Objects.equals(getRole(), that.getRole()) &&
            Objects.equals(getPermissions(), that.getPermissions());
    }

    @Override
    public int hashCode () {

        return Objects.hash(getId(), getProject(), getUser(), getRole(), getPermissions());
    }
}
