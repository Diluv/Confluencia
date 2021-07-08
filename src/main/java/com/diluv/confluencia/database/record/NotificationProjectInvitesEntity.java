package com.diluv.confluencia.database.record;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.*;

import org.hibernate.annotations.DiscriminatorFormula;
import org.hibernate.annotations.DiscriminatorOptions;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@DynamicInsert
@DynamicUpdate
@PrimaryKeyJoinColumn(referencedColumnName = "id")
@DiscriminatorValue("PROJECT_INVITE")
@Table(name = "notification_project_invites")
public class NotificationProjectInvitesEntity extends NotificationsEntity implements Serializable {

    @Column(name = "role")
    private String role;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private NotificationProjectInvitesStatus status = NotificationProjectInvitesStatus.PENDING;

    @Column(name = "updated_at")
    private Timestamp updatedAt;

    @ManyToOne
    @JoinColumn(name = "sender_id")
    private UsersEntity sender;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private ProjectsEntity project;

    @OneToMany(mappedBy = "projectInvite", cascade = CascadeType.ALL)
    private List<NotificationProjectInvitePermissionsEntity> permissions = new ArrayList<>();

    public NotificationProjectInvitesEntity () {

    }

    public String getRole () {

        return this.role;
    }

    public void setRole (String role) {

        this.role = role;
    }

    public NotificationProjectInvitesStatus getStatus () {

        return this.status;
    }

    public void setStatus (NotificationProjectInvitesStatus status) {

        this.status = status;
    }

    public Timestamp getUpdatedAt () {

        return this.updatedAt;
    }

    public void setUpdatedAt (Timestamp updatedAt) {

        this.updatedAt = updatedAt;
    }

    public UsersEntity getSender () {

        return this.sender;
    }

    public void setSender (UsersEntity sender) {

        this.sender = sender;
    }

    public ProjectsEntity getProject () {

        return this.project;
    }

    public void setProject (ProjectsEntity project) {

        this.project = project;
    }

    public List<NotificationProjectInvitePermissionsEntity> getPermissions () {

        return this.permissions;
    }

    public void addPermission (String permission) {

        this.permissions.add(new NotificationProjectInvitePermissionsEntity(this, permission));
    }

    @Override
    public boolean equals (Object o) {

        if (this == o) return true;
        if (!(o instanceof NotificationProjectInvitesEntity)) return false;

        NotificationProjectInvitesEntity that = (NotificationProjectInvitesEntity) o;

        if (!super.equals(o)) return false;
        if (!Objects.equals(this.role, that.role)) return false;
        if (that.status != this.status) return false;
        if (!Objects.equals(this.updatedAt, that.updatedAt)) return false;
        if (!Objects.equals(this.sender, that.sender)) return false;
        if (!Objects.equals(this.project, that.project)) return false;
        return Objects.equals(this.permissions, that.permissions);
    }

    @Override
    public int hashCode () {

        int result = super.hashCode();
        result = 31 * result + (this.role != null ? this.role.hashCode() : 0);
        result = 31 * result + (this.status != null ? this.status.hashCode() : 0);
        result = 31 * result + (this.updatedAt != null ? this.updatedAt.hashCode() : 0);
        result = 31 * result + (this.sender != null ? this.sender.hashCode() : 0);
        result = 31 * result + (this.project != null ? this.project.hashCode() : 0);
        result = 31 * result + (this.permissions != null ? this.permissions.hashCode() : 0);
        return result;
    }
}
