package com.diluv.confluencia.database.record;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "project_file_loaders")
@IdClass(ProjectFileLoadersEntityPK.class)
public class ProjectFileLoadersEntity {

    @Id
    @ManyToOne
    @JoinColumn(name = "project_file_id")
    private ProjectFilesEntity projectFile;

    @Id
    @ManyToOne
    @JoinColumn(name = "loader_id")
    private ProjectTypeLoadersEntity loader;

    public ProjectFilesEntity getProjectFile () {

        return this.projectFile;
    }

    public void setProjectFile (ProjectFilesEntity projectFile) {

        this.projectFile = projectFile;
    }

    public ProjectTypeLoadersEntity getLoader () {

        return this.loader;
    }

    public void setLoader (ProjectTypeLoadersEntity loader) {

        this.loader = loader;
    }

    @Override
    public boolean equals (Object o) {

        if (this == o) return true;
        if (!(o instanceof ProjectFileLoadersEntity)) return false;
        ProjectFileLoadersEntity that = (ProjectFileLoadersEntity) o;
        return Objects.equals(getProjectFile(), that.getProjectFile()) &&
            Objects.equals(getLoader(), that.getLoader());
    }

    @Override
    public int hashCode () {

        return Objects.hash(getProjectFile(), getLoader());
    }
}
