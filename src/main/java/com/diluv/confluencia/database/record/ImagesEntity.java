package com.diluv.confluencia.database.record;

import java.sql.Timestamp;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "images")
public class ImagesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "url")
    private String url;

    @Column(name = "released")
    private boolean released;

    @Column(name = "created_at")
    private Timestamp createdAt;

    public long getId () {

        return this.id;
    }

    public void setId (long id) {

        this.id = id;
    }

    public String getUrl () {

        return this.url;
    }

    public void setUrl (String url) {

        this.url = url;
    }

    public boolean isReleased () {

        return this.released;
    }

    public void setReleased (boolean released) {

        this.released = released;
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
        if (!(o instanceof ImagesEntity)) return false;
        ImagesEntity that = (ImagesEntity) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode () {

        return Objects.hash(getId());
    }
}
