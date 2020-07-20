package com.diluv.confluencia.database.record;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;import javax.persistence.Entity;
import org.hibernate.annotations.DynamicUpdate;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity

@Table(name = "password_reset")
@IdClass(PasswordResetEntityPK.class)
public class PasswordResetEntity {
    private long userId;
    private String code;
    private Timestamp createdAt;

    @Id
    @Column(name = "user_id")
    public long getUserId () {

        return this.userId;
    }

    public void setUserId (long userId) {

        this.userId = userId;
    }

    @Id
    @Column(name = "code")
    public String getCode () {

        return this.code;
    }

    public void setCode (String code) {

        this.code = code;
    }

    @Basic
    @Column(name = "created_at")
    public Timestamp getCreatedAt () {

        return this.createdAt;
    }

    public void setCreatedAt (Timestamp createdAt) {

        this.createdAt = createdAt;
    }
}
