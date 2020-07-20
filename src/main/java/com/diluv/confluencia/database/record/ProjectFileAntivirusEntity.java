package com.diluv.confluencia.database.record;

import javax.persistence.Basic;
import javax.persistence.Column;import javax.persistence.Entity;
import org.hibernate.annotations.DynamicUpdate;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

@Entity

@Table(name = "project_file_antivirus")
public class ProjectFileAntivirusEntity {
    private long projectFileId;
    private String malware;

    @Id
    @Column(name = "project_file_id")
    public long getProjectFileId () {

        return this.projectFileId;
    }

    public void setProjectFileId (long projectFileId) {

        this.projectFileId = projectFileId;
    }

    @Basic
    @Column(name = "malware")
    public String getMalware () {

        return this.malware;
    }

    public void setMalware (String malware) {

        this.malware = malware;
    }
}
