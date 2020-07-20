package com.diluv.confluencia.database.record;

import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity

@Table(name = "featured_games")
public class FeaturedGamesEntity {
    @Id
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
