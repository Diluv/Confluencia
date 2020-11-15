package com.diluv.confluencia.database.record;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "project_file_dependencies")
@IdClass(ProjectFileDependenciesEntityPK.class)
public class ProjectFileDependenciesEntity {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_file_id")
    private ProjectFilesEntity projectFile;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dependency_project_id")
    private ProjectsEntity dependencyProject;

    @Column(name = "type")
    private String type;

    public ProjectFilesEntity getProjectFile () {

        return this.projectFile;
    }

    public void setProjectFile (ProjectFilesEntity projectFile) {

        this.projectFile = projectFile;
    }

    public ProjectsEntity getDependencyProject () {

        return this.dependencyProject;
    }

    public void setDependencyProject (ProjectsEntity dependencyProject) {

        this.dependencyProject = dependencyProject;
    }

    public String getType () {

        return this.type;
    }

    public void setType (String type) {

        this.type = type;
    }

    @Override
    public boolean equals (Object o) {

        if (this == o) return true;
        if (!(o instanceof ProjectFileDependenciesEntity)) return false;
        ProjectFileDependenciesEntity that = (ProjectFileDependenciesEntity) o;
        return Objects.equals(getProjectFile(), that.getProjectFile()) &&
            Objects.equals(getDependencyProject(), that.getDependencyProject());
    }

    @Override
    public int hashCode () {

        return Objects.hash(getProjectFile(), getDependencyProject());
    }
}
