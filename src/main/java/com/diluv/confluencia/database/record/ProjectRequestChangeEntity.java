package com.diluv.confluencia.database.record;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "project_request_change")
public class ProjectRequestChangeEntity implements Serializable {

    @Id
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_review_id")
    private ProjectReviewEntity projectReview;

    @Column(name = "reason")
    private String reason;

    public ProjectRequestChangeEntity () {

    }

    public ProjectRequestChangeEntity (String reason) {

        this.reason = reason;
    }

    public ProjectReviewEntity getProjectReview () {

        return this.projectReview;
    }

    public void setProjectReview (ProjectReviewEntity projectReview) {

        this.projectReview = projectReview;
    }

    public String getReason () {

        return this.reason;
    }

    public void setReason (String reason) {

        this.reason = reason;
    }

    @Override
    public boolean equals (Object o) {

        if (this == o) return true;
        if (!(o instanceof ProjectRequestChangeEntity)) return false;
        ProjectRequestChangeEntity that = (ProjectRequestChangeEntity) o;
        return Objects.equals(getProjectReview(), that.getProjectReview());
    }

    @Override
    public int hashCode () {

        return Objects.hash(getProjectReview());
    }
}
