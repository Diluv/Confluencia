package com.diluv.confluencia.database.record;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.DynamicInsert;

@Entity
@DynamicInsert
@Table(name = "project_files")
public class ProjectFilesEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "version")
    private String version;

    @Column(name = "name")
    private String name;

    @Column(name = "size")
    private long size;

    @Column(name = "sha512")
    private String sha512;

    @Column(name = "changelog")
    private String changelog;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "updated_at")
    private Timestamp updatedAt;

    @Column(name = "release_type")
    private String releaseType;

    @Column(name = "classifier")
    private String classifier;

    @Column(name = "processing_status")
    private FileProcessingStatus processingStatus;

    @Column(name = "processing_status_changed")
    private Timestamp processingStatusChanged;

    @Column(name = "released")
    private boolean released;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private ProjectsEntity project;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UsersEntity user;

    @OneToMany(mappedBy = "projectFile", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    private List<ProjectFileGameVersionsEntity> gameVersions;

    @OneToMany(mappedBy = "dependencyProject", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    private List<ProjectFileDependenciesEntity> dependencies;

    public long getId () {

        return this.id;
    }

    public void setId (long id) {

        this.id = id;
    }

    public String getVersion () {

        return this.version;
    }

    public void setVersion (String version) {

        this.version = version;
    }

    public String getName () {

        return this.name;
    }

    public void setName (String name) {

        this.name = name;
    }

    public long getSize () {

        return this.size;
    }

    public void setSize (long size) {

        this.size = size;
    }

    public String getSha512 () {

        return this.sha512;
    }

    public void setSha512 (String sha512) {

        this.sha512 = sha512;
    }

    public String getChangelog () {

        return this.changelog;
    }

    public void setChangelog (String changelog) {

        this.changelog = changelog;
    }

    public Timestamp getCreatedAt () {

        return this.createdAt;
    }

    public void setCreatedAt (Timestamp createdAt) {

        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt () {

        return this.updatedAt;
    }

    public void setUpdatedAt (Timestamp updatedAt) {

        this.updatedAt = updatedAt;
    }

    public String getReleaseType () {

        return this.releaseType;
    }

    public void setReleaseType (String releaseType) {

        this.releaseType = releaseType;
    }

    public String getClassifier () {

        return this.classifier;
    }

    public void setClassifier (String classifier) {

        this.classifier = classifier;
    }

    public FileProcessingStatus getProcessingStatus () {

        return this.processingStatus;
    }

    public void setProcessingStatus (FileProcessingStatus processingStatus) {

        this.processingStatus = processingStatus;
    }

    public Timestamp getProcessingStatusChanged () {

        return this.processingStatusChanged;
    }

    public void setProcessingStatusChanged (Timestamp processingStatusChanged) {

        this.processingStatusChanged = processingStatusChanged;
    }

    public boolean isReleased () {

        return this.released;
    }

    public void setReleased (boolean released) {

        this.released = released;
    }

    public ProjectsEntity getProject () {

        return this.project;
    }

    public void setProject (ProjectsEntity project) {

        this.project = project;
    }

    public UsersEntity getUser () {

        return this.user;
    }

    public void setUser (UsersEntity user) {

        this.user = user;
    }

    public List<ProjectFileGameVersionsEntity> getGameVersions () {

        return this.gameVersions;
    }

    public void setGameVersions (List<ProjectFileGameVersionsEntity> gameVersions) {

        this.gameVersions = gameVersions;
    }

    public List<ProjectFileDependenciesEntity> getDependencies () {

        return this.dependencies;
    }

    public void setDependencies (List<ProjectFileDependenciesEntity> dependencies) {

        this.dependencies = dependencies;
    }
}
