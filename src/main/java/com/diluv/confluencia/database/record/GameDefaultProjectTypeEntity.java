package com.diluv.confluencia.database.record;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "game_default_project_type")
public class GameDefaultProjectTypeEntity implements Serializable {

    @Id
    @OneToOne
    @JoinColumn(name = "game_slug", referencedColumnName = "slug")
    private GamesEntity game;

    @Column(name = "project_type_slug", nullable = false)
    private String projectTypeSlug;

    public GameDefaultProjectTypeEntity () {

    }

    public GameDefaultProjectTypeEntity (GamesEntity game, String projectTypeSlug) {

        this.game = game;
        this.projectTypeSlug = projectTypeSlug;
    }

    public GamesEntity getGame () {

        return this.game;
    }

    public void setGame (GamesEntity game) {

        this.game = game;
    }

    public String getProjectTypeSlug () {

        return this.projectTypeSlug;
    }

    public void setProjectType (String projectTypeSlug) {

        this.projectTypeSlug = projectTypeSlug;
    }

    @Override
    public boolean equals (Object o) {

        if (this == o) return true;
        if (!(o instanceof GameDefaultProjectTypeEntity)) return false;
        GameDefaultProjectTypeEntity that = (GameDefaultProjectTypeEntity) o;
        return Objects.equals(getGame(), that.getGame()) &&
            Objects.equals(getProjectTypeSlug(), that.getProjectTypeSlug());
    }

    @Override
    public int hashCode () {

        return Objects.hash(getGame(), getProjectTypeSlug());
    }
}
