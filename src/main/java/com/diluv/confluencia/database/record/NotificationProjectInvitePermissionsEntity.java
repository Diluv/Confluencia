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
@Table(name = "notification_project_invite_permissions")
@IdClass(NotificationProjectInvitePermissionsEntityPK.class)
public class NotificationProjectInvitePermissionsEntity {

    @Id
    @ManyToOne
    @JoinColumn(name = "id", columnDefinition = "id")
    private NotificationProjectInvitesEntity projectInvite;

    @Id
    @Column(name = "permission")
    private String permission;

    public NotificationProjectInvitePermissionsEntity () {

    }

    public NotificationProjectInvitePermissionsEntity (NotificationProjectInvitesEntity projectInvite, String permission) {

        this.projectInvite = projectInvite;
        this.permission = permission;
    }

    public NotificationProjectInvitesEntity getProjectInvite () {

        return this.projectInvite;
    }

    public void setProjectInvite (NotificationProjectInvitesEntity projectInvite) {

        this.projectInvite = projectInvite;
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
        if (!(o instanceof NotificationProjectInvitePermissionsEntity)) return false;

        NotificationProjectInvitePermissionsEntity that = (NotificationProjectInvitePermissionsEntity) o;

        if (!Objects.equals(this.projectInvite, that.projectInvite))
            return false;
        return Objects.equals(this.permission, that.permission);
    }

    @Override
    public int hashCode () {

        int result = this.projectInvite != null ? this.projectInvite.hashCode() : 0;
        result = 31 * result + (this.permission != null ? this.permission.hashCode() : 0);
        return result;
    }
}
