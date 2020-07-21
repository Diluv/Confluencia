package com.diluv.confluencia.database.record;

import java.io.Serializable;

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
}
