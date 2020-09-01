package com.diluv.confluencia.database.record;

import org.hibernate.annotations.DynamicInsert;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@DynamicInsert
@Table(name = "user_email")
public class UserEmailEntity implements Serializable {

    @Id
    @OneToOne
    @JoinColumn(name = "user_id")
    private UsersEntity user;

    @Column(name = "email")
    private String email;

    @Column(name = "code")
    private String code;

    @Column(name = "created_at")
    private Timestamp createdAt;

    public UsersEntity getUser () {

        return this.user;
    }

    public void setUser (UsersEntity user) {

        this.user = user;
    }

    public String getEmail () {

        return this.email;
    }

    public void setEmail (String email) {

        this.email = email;
    }

    public String getCode () {

        return this.code;
    }

    public void setCode (String code) {

        this.code = code;
    }

    public Timestamp getCreatedAt () {

        return this.createdAt;
    }

    public void setCreatedAt (Timestamp createdAt) {

        this.createdAt = createdAt;
    }

    @Override
    public boolean equals (Object o) {

        if (this == o) return true;
        if (!(o instanceof UserEmailEntity)) return false;
        UserEmailEntity that = (UserEmailEntity) o;
        return getUser() == that.getUser() &&
            Objects.equals(getEmail(), that.getEmail()) &&
            Objects.equals(getCode(), that.getCode()) &&
            Objects.equals(getCreatedAt(), that.getCreatedAt());
    }

    @Override
    public int hashCode () {

        return Objects.hash(getUser(), getEmail(), getCode(), getCreatedAt());
    }
}
