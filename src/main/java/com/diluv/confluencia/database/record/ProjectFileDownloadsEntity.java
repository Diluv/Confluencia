package com.diluv.confluencia.database.record;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.SQLInsert;

@Entity
@Table(name = "project_file_downloads")
@IdClass(ProjectFileDownloadsEntityPK.class)
@SQLInsert(sql = "INSERT IGNORE INTO project_file_downloads(project_file_id, ip) VALUES (?, ?)")
public class ProjectFileDownloadsEntity {

    @Id
    @ManyToOne
    @JoinColumn(name = "project_file_id")
    private ProjectFilesEntity projectFile;

    @Id
    @JoinColumn(name = "ip")
    private String ip;

    public ProjectFileDownloadsEntity () {

    }

    public ProjectFileDownloadsEntity (ProjectFilesEntity projectFile, String ip) {

        this.projectFile = projectFile;
        this.ip = ip;
    }

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
        if (!(o instanceof ProjectFileDownloadsEntity)) return false;
        ProjectFileDownloadsEntity that = (ProjectFileDownloadsEntity) o;
        return Objects.equals(getProjectFile(), that.getProjectFile()) &&
            Objects.equals(getIp(), that.getIp());
    }

    @Override
    public int hashCode () {

        return Objects.hash(getProjectFile(), getIp());
    }
}
