package com.diluv.confluencia.database.record;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "game_default_project_type")
public class GameDefaultProjectTypeEntity implements Serializable {

    @Id
    @OneToOne
    @JoinColumn(name = "game_slug", referencedColumnName = "slug")
    private GamesEntity game;

    @OneToOne
    @JoinColumns({
        @JoinColumn(name = "project_type_slug", referencedColumnName = "slug"),
        @JoinColumn(name = "game_slug", referencedColumnName = "game_slug")
    })
    private ProjectTypesEntity projectType;

    public GamesEntity getGame () {

        return this.game;
    }

    public void setGame (GamesEntity game) {

        this.game = game;
    }

    public ProjectTypesEntity getProjectType () {

        return this.projectType;
    }

    public void setProjectType (ProjectTypesEntity projectType) {

        this.projectType = projectType;
    }

    @Override
    public boolean equals (Object o) {

        if (this == o) return true;
        if (!(o instanceof GameDefaultProjectTypeEntity)) return false;
        GameDefaultProjectTypeEntity that = (GameDefaultProjectTypeEntity) o;
        return Objects.equals(getGame(), that.getGame()) &&
            Objects.equals(getProjectType(), that.getProjectType());
    }

    @Override
    public int hashCode () {

        return Objects.hash(getGame(), getProjectType());
    }
}
