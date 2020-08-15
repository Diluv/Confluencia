package com.diluv.confluencia.database.record;

import java.io.Serializable;
import java.util.Objects;

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

    @Override
    public boolean equals (Object o) {

        if (this == o) return true;
        if (!(o instanceof ProjectFileGameVersionsEntityPK)) return false;
        ProjectFileGameVersionsEntityPK that = (ProjectFileGameVersionsEntityPK) o;
        return Objects.equals(getGameVersion(), that.getGameVersion()) &&
            Objects.equals(getProjectFile(), that.getProjectFile());
    }

    @Override
    public int hashCode () {

        return Objects.hash(getGameVersion(), getProjectFile());
    }
}
