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
@IdClass(APITokenPermissionsEntityPK.class)
@Table(name = "api_token_permissions")
public class APITokenPermissionsEntity {

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
        if (!(o instanceof APITokenPermissionsEntity)) return false;
        APITokenPermissionsEntity that = (APITokenPermissionsEntity) o;
        return Objects.equals(getApiToken(), that.getApiToken()) &&
            Objects.equals(getPermission(), that.getPermission());
    }

    @Override
    public int hashCode () {

        return Objects.hash(getApiToken(), getPermission());
    }
}
