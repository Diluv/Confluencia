package com.diluv.confluencia.database.record;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.sql.Timestamp;
import java.util.Objects;

import javax.persistence.*;

@Entity
@DynamicUpdate
@Table(name = "project_review")
public class ProjectReviewEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", insertable = false, updatable = false)
    private long id;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private ProjectsEntity project;

    @ManyToOne
    @JoinColumn(name = "reviewed_by")
    private UsersEntity reviewedBy;

    @Column(name = "created_at", insertable = false)
    private Timestamp createdAt;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "projectReview")
    private ProjectRequestChangeEntity projectRequestChange;

    public long getId () {

        return this.id;
    }

    public void setId (long id) {

        this.id = id;
    }

    public ProjectsEntity getProject () {

        return this.project;
    }

    public void setProject (ProjectsEntity project) {

        this.project = project;
    }

    public UsersEntity getReviewedBy () {

        return this.reviewedBy;
    }

    public void setReviewedBy (UsersEntity reviewedBy) {

        this.reviewedBy = reviewedBy;
    }

    public Timestamp getCreatedAt () {

        return this.createdAt;
    }

    public void setCreatedAt (Timestamp createdAt) {

        this.createdAt = createdAt;
    }

    public ProjectRequestChangeEntity getProjectRequestChange () {

        return this.projectRequestChange;
    }

    public void setProjectRequestChange (ProjectRequestChangeEntity projectRequestChange) {

        projectRequestChange.setProjectReview(this);
        this.projectRequestChange = projectRequestChange;
    }

    @Override
    public boolean equals (Object o) {

        if (this == o) return true;
        if (!(o instanceof ProjectReviewEntity)) return false;
        ProjectReviewEntity that = (ProjectReviewEntity) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode () {

        return Objects.hash(getId());
    }
}
