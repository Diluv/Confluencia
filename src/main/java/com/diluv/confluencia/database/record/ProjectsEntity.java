package com.diluv.confluencia.database.record;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;
import org.hibernate.annotations.Where;

import javax.persistence.*;

import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

@Entity
@DynamicInsert
@DynamicUpdate
@SelectBeforeUpdate
@Where(clause = "deleted=0")
@Table(name = "projects")
public class ProjectsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", insertable = false, updatable = false)
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

    @Column(name = "created_at", insertable = false)
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

    @OneToMany(mappedBy = "project", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    private List<ProjectTagsEntity> tags;

    @OneToMany(mappedBy = "project", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    private List<ProjectLinksEntity> links;

    @OneToMany(mappedBy = "project", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    private List<ProjectAuthorsEntity> authors;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private List<ProjectReviewEntity> reviews;

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

    public List<ProjectReviewEntity> getReviews () {

        return this.reviews;
    }

    public void setReviews (List<ProjectReviewEntity> reviews) {

        this.reviews = reviews;
    }

    public void addReview (ProjectReviewEntity review) {

        review.setProject(this);
        this.reviews.add(review);
    }

    @Override
    public boolean equals (Object o) {

        if (this == o) return true;
        if (!(o instanceof ProjectsEntity)) return false;
        ProjectsEntity that = (ProjectsEntity) o;
        return getId() == that.getId() &&
            getCachedDownloads() == that.getCachedDownloads() &&
            isReview() == that.isReview() &&
            isReleased() == that.isReleased() &&
            Objects.equals(getName(), that.getName()) &&
            Objects.equals(getSlug(), that.getSlug()) &&
            Objects.equals(getSummary(), that.getSummary()) &&
            Objects.equals(getDescription(), that.getDescription()) &&
            Objects.equals(getCreatedAt(), that.getCreatedAt()) &&
            Objects.equals(getUpdatedAt(), that.getUpdatedAt()) &&
            Objects.equals(user, that.user) &&
            Objects.equals(getProjectType(), that.getProjectType()) &&
            Objects.equals(getTags(), that.getTags()) &&
            Objects.equals(getLinks(), that.getLinks()) &&
            Objects.equals(getAuthors(), that.getAuthors());
    }

    @Override
    public int hashCode () {

        return Objects.hash(getId(), getName(), getSlug(), getSummary(), getDescription(), getCachedDownloads(), isReview(), isReleased(), getCreatedAt(), getUpdatedAt(), user, getProjectType(), getTags(), getLinks(), getAuthors());
    }
}
