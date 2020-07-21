package com.diluv.confluencia.database.record;

import java.io.Serializable;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class ProjectFileGameVersionsEntityPK implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name = "game_version_id")
    private GameVersionsEntity gameVersion;

    @Id
    @ManyToOne
    @JoinColumn(name = "project_file_id")
    private ProjectFilesEntity projectFile;

    public GameVersionsEntity getGameVersion () {

        return this.gameVersion;
    }

    public void setGameVersion (GameVersionsEntity gameVersion) {

        this.gameVersion = gameVersion;
    }

    public ProjectFilesEntity getProjectFile () {

        return this.projectFile;
    }

    public void setProjectFile (ProjectFilesEntity projectFile) {

        this.projectFile = projectFile;
    }
}
