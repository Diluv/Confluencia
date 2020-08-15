package com.diluv.confluencia.database.record;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class ProjectFileDownloadsEntityPK implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name = "project_file_id")
    private ProjectFilesEntity projectFile;

    @Id
    @JoinColumn(name = "ip")
    private String ip;

    public ProjectFilesEntity getProjectFile () {

        return this.projectFile;
    }

    public void setProjectFile (ProjectFilesEntity projectFile) {

        this.projectFile = projectFile;
    }

    public String getIp () {

        return this.ip;
    }

    public void setIp (String ip) {

        this.ip = ip;
    }

    @Override
    public boolean equals (Object o) {

        if (this == o) return true;
        if (!(o instanceof ProjectFileDownloadsEntityPK)) return false;
        ProjectFileDownloadsEntityPK that = (ProjectFileDownloadsEntityPK) o;
        return Objects.equals(getProjectFile(), that.getProjectFile()) &&
            Objects.equals(getIp(), that.getIp());
    }

    @Override
    public int hashCode () {

        return Objects.hash(getProjectFile(), getIp());
    }
}
