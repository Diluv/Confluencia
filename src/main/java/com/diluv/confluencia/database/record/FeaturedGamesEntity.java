package com.diluv.confluencia.database.record;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "featured_games")
public class FeaturedGamesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private byte id;

    @ManyToOne
    @JoinColumn(name = "slug")
    private GamesEntity game;

    public byte getId () {

        return this.id;
    }

    public void setId (byte id) {

        this.id = id;
    }

    public GamesEntity getGame () {

        return this.game;
    }

    public void setGame (GamesEntity game) {

        this.game = game;
    }
}
