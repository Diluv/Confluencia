package com.diluv.confluencia.database.record;

import java.sql.Timestamp;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "game_versions")
public class GameVersionsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "version")
    private String version;

    @Column(name = "type")
    private String type;

    @Column(name = "released")
    private Timestamp releasedAt;

    @ManyToOne
    @JoinColumn(name = "game_slug")
    private GamesEntity game;

    public GameVersionsEntity () {

    }

    public GameVersionsEntity (GamesEntity game, String version) {

        this.game = game;
        this.version = version;
    }

    public long getId () {

        return this.id;
    }

    public void setId (long id) {

        this.id = id;
    }

    public String getVersion () {

        return this.version;
    }

    public void setVersion (String version) {

        this.version = version;
    }

    public String getType () {

        return this.type;
    }

    public void setType (String type) {

        this.type = type;
    }

    public Timestamp getReleasedAt () {

        return this.releasedAt;
    }

    public void setReleasedAt (Timestamp releasedAt) {

        this.releasedAt = releasedAt;
    }

    public GamesEntity getGame () {

        return this.game;
    }

    public void setGame (GamesEntity game) {

        this.game = game;
    }

    @Override
    public boolean equals (Object o) {

        if (this == o) return true;
        if (!(o instanceof GameVersionsEntity)) return false;
        GameVersionsEntity that = (GameVersionsEntity) o;
        return getId() == that.getId() &&
            Objects.equals(getVersion(), that.getVersion()) &&
            Objects.equals(getType(), that.getType()) &&
            Objects.equals(getReleasedAt(), that.getReleasedAt()) &&
            Objects.equals(getGame(), that.getGame());
    }

    @Override
    public int hashCode () {

        return Objects.hash(getId(), getVersion(), getType(), getReleasedAt(), getGame());
    }
}
