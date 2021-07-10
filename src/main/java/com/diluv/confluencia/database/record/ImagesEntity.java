package com.diluv.confluencia.database.record;

import java.time.Instant;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

@Entity
@DynamicUpdate
@IdClass(ImagesEntityPK.class)
@Table(name = "images")
public class ImagesEntity {

    @Id
    @Column(name = "id")
    private String id;

    @Id
    @Column(name = "ext")
    private String ext;

    @Id
    @Column(name = "width")
    private int width;

    @Column(name = "released")
    private boolean released;

    @Column(name = "created_at")
    private Instant createdAt;

    public String getId () {

        return this.id;
    }

    public void setId (String id) {

        this.id = id;
    }

    public String getExt () {

        return this.ext;
    }

    public void setExt (String ext) {

        this.ext = ext;
    }

    public int getWidth () {

        return this.width;
    }

    public void setWidth (int width) {

        this.width = width;
    }

    public boolean isReleased () {

        return this.released;
    }

    public void setReleased (boolean released) {

        this.released = released;
    }

    public Instant getCreatedAt () {

        return this.createdAt;
    }

    public void setCreatedAt (Instant createdAt) {

        this.createdAt = createdAt;
    }

    @Override
    public boolean equals (Object o) {

        if (this == o) return true;
        if (!(o instanceof ImagesEntity)) return false;
        ImagesEntity that = (ImagesEntity) o;
        return Objects.equals(getId(), that.getId()) &&
            Objects.equals(getExt(), that.getExt()) &&
            Objects.equals(getWidth(), that.getWidth());
    }

    @Override
    public int hashCode () {

        return Objects.hash(getId(), getExt(), getWidth());
    }
}
