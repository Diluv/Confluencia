package com.diluv.confluencia.database.record;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;import javax.persistence.Entity;
import org.hibernate.annotations.DynamicUpdate;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity

@Table(name = "game_versions")
public class GameVersionsEntity {

    @Id
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
}
