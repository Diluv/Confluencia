package com.diluv.confluencia.database.record;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class ProjectTypesEntityPK implements Serializable {
    @Id
    @ManyToOne
    @JoinColumn(name = "game_slug")
    private GamesEntity game;

    @Id
    @Column(name = "slug")
    private String slug;

    public GamesEntity getGame () {

        return this.game;
    }

    public void setGame (GamesEntity game) {

        this.game = game;
    }

    public String getSlug () {

        return this.slug;
    }

    public void setSlug (String slug) {

        this.slug = slug;
    }
}
