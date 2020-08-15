package com.diluv.confluencia.database.record;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "project_file_antivirus")
public class ProjectFileAntivirusEntity implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name = "project_file_id")
    private ProjectFilesEntity projectFile;

    @Column(name = "malware")
    private String malware;

    public ProjectFilesEntity getProjectFile () {

        return this.projectFile;
    }

    public void setProjectFile (ProjectFilesEntity projectFile) {

        this.projectFile = projectFile;
    }

    public String getMalware () {

        return this.malware;
    }

    public void setMalware (String malware) {

        this.malware = malware;
    }

    @Override
    public boolean equals (Object o) {

        if (this == o) return true;
        if (!(o instanceof ProjectFileAntivirusEntity)) return false;
        ProjectFileAntivirusEntity that = (ProjectFileAntivirusEntity) o;
        return Objects.equals(getProjectFile(), that.getProjectFile()) &&
            Objects.equals(getMalware(), that.getMalware());
    }

    @Override
    public int hashCode () {

        return Objects.hash(getProjectFile(), getMalware());
    }
}
