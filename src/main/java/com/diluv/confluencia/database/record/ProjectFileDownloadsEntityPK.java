package com.diluv.confluencia.database.record;

import java.io.Serializable;

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
}
