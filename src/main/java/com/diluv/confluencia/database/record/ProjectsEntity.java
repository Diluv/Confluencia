package com.diluv.confluencia.database.record;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.DynamicInsert;

@Entity
@DynamicInsert
@Table(name = "projects")
public class ProjectsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "slug")
    private String slug;

    @Column(name = "summary")
    private String summary;

    @Column(name = "description")
    private String description;

    @Column(name = "cached_downloads")
    private long cachedDownloads;

    @Column(name = "review")
    private boolean review;

    @Column(name = "released")
    private boolean released;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "updated_at")
    private Timestamp updatedAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UsersEntity user;

    @ManyToOne
    @JoinColumns({
        @JoinColumn(name = "project_type_slug"),
        @JoinColumn(name = "game_slug")
    })
    private ProjectTypesEntity projectType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "game_slug", insertable = false, updatable = false)
    private GamesEntity game;

    @OneToMany(mappedBy = "tag", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    private List<ProjectTagsEntity> tags;

    @OneToMany(mappedBy = "project", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    private List<ProjectLinksEntity> links;

    @OneToMany(mappedBy = "project", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    private List<ProjectAuthorsEntity> authors;


    public ProjectsEntity () {

    }

    public ProjectsEntity (long id) {

        this.id = id;
    }

    public long getId () {

        return this.id;
    }

    public void setId (long id) {

        this.id = id;
    }

    public String getName () {

        return this.name;
    }

    public void setName (String name) {

        this.name = name;
    }

    public String getSlug () {

        return this.slug;
    }

    public void setSlug (String slug) {

        this.slug = slug;
    }

    public String getSummary () {

        return this.summary;
    }

    public void setSummary (String summary) {

        this.summary = summary;
    }

    public String getDescription () {

        return this.description;
    }

    public void setDescription (String description) {

        this.description = description;
    }

    public long getCachedDownloads () {

        return this.cachedDownloads;
    }

    public void setCachedDownloads (long cachedDownloads) {

        this.cachedDownloads = cachedDownloads;
    }

    public boolean isReview () {

        return this.review;
    }

    public void setReview (boolean review) {

        this.review = review;
    }

    public boolean isReleased () {

        return this.released;
    }

    public void setReleased (boolean released) {

        this.released = released;
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

    public UsersEntity getOwner () {

        return this.user;
    }

    public void setOwner (UsersEntity user) {

        this.user = user;
    }

    public ProjectTypesEntity getProjectType () {

        return this.projectType;
    }

    public void setProjectType (ProjectTypesEntity projectType) {

        this.projectType = projectType;
    }

    public List<ProjectTagsEntity> getTags () {

        return this.tags;
    }

    public void setTags (List<ProjectTagsEntity> tags) {

        this.tags = tags;
    }

    public List<ProjectLinksEntity> getLinks () {

        return this.links;
    }

    public void setLinks (List<ProjectLinksEntity> links) {

        this.links = links;
    }

    public List<ProjectAuthorsEntity> getAuthors () {

        return this.authors;
    }

    public void setAuthors (List<ProjectAuthorsEntity> authors) {

        this.authors = authors;
    }

    public GamesEntity getGame () {

        return this.game;
    }

    public void setGame (GamesEntity game) {

        this.game = game;
    }
}
