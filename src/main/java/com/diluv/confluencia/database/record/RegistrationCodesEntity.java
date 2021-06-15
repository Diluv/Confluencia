package com.diluv.confluencia.database.record;

import java.sql.Timestamp;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "registration_codes")
public class RegistrationCodesEntity {

    @Id
    @Column(name = "code")
    private String code;

    @Column(name = "valid")
    private boolean valid = true;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UsersEntity user;

    @Column(name = "created_at", updatable = false)
    private Timestamp createdAt;

    public RegistrationCodesEntity () {

    }

    public RegistrationCodesEntity (String code, UsersEntity user) {

        this.code = code;
        this.user = user;
    }

    public String getCode () {

        return this.code;
    }

    public void setCode (String code) {

        this.code = code;
    }

    public boolean isValid () {

        return this.valid;
    }

    public void setValid (boolean valid) {

        this.valid = valid;
    }

    public UsersEntity getUser () {

        return this.user;
    }

    public void setUser (UsersEntity user) {

        this.user = user;
    }

    public Timestamp getCreatedAt () {

        return this.createdAt;
    }

    @Override
    public boolean equals (Object o) {

        if (this == o) return true;
        if (!(o instanceof RegistrationCodesEntity)) return false;
        RegistrationCodesEntity that = (RegistrationCodesEntity) o;
        return Objects.equals(getCode(), that.getCode());
    }

    @Override
    public int hashCode () {

        return Objects.hash(getCode());
    }
}
