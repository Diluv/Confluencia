package com.diluv.confluencia.database.record;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class NotificationProjectInvitePermissionsEntityPK implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name = "id")
    private NotificationProjectInvitesEntity projectInvite;

    @Id
    @Column(name = "permission")
    private String permission;

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
        if (!(o instanceof NotificationProjectInvitePermissionsEntityPK)) return false;

        NotificationProjectInvitePermissionsEntityPK that = (NotificationProjectInvitePermissionsEntityPK) o;

        if (!Objects.equals(projectInvite, that.projectInvite))
            return false;
        return Objects.equals(permission, that.permission);
    }

    @Override
    public int hashCode () {

        int result = this.projectInvite != null ? this.projectInvite.hashCode() : 0;
        result = 31 * result + (this.permission != null ? this.permission.hashCode() : 0);
        return result;
    }
}