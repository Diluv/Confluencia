package com.diluv.confluencia.database.record;import javax.persistence.Entity;
import org.hibernate.annotations.DynamicUpdate;
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
    @ManyToOne
    @JoinColumn(name = "project_file_id")
    private ProjectFilesEntity projectFile;

    @Id
    @ManyToOne
    @JoinColumn(name = "dependency_project_id")
    private ProjectsEntity dependencyProject;

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
}
