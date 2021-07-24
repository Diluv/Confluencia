package com.diluv.confluencia.database.record;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import java.io.Serializable;
import java.util.Objects;

public class APITokenPermissionsEntityPK implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name = "id")
    private APITokensEntity apiToken;

    @Id
    @Column(name = "permission")
    private String permission;

    public APITokensEntity getApiToken () {

        return this.apiToken;
    }

    public void setApiToken (APITokensEntity apiToken) {

        this.apiToken = apiToken;
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
        if (!(o instanceof APITokenPermissionsEntityPK)) return false;
        APITokenPermissionsEntityPK that = (APITokenPermissionsEntityPK) o;
        return Objects.equals(getApiToken(), that.getApiToken()) &&
            Objects.equals(getPermission(), that.getPermission());
    }

    @Override
    public int hashCode () {

        return Objects.hash(getApiToken(), getPermission());
    }
}