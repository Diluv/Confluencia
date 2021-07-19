package com.diluv.confluencia.database.record;

import java.time.Instant;
import java.util.Objects;

import javax.persistence.*;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@DynamicInsert
@DynamicUpdate
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("GENERIC")
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "notifications")
public class NotificationsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "text")
    private String text;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", insertable = false, updatable = false)
    private NotificationType type;

    @Column(name = "created_at", updatable = false)
    private Instant createdAt;

    @Column(name = "viewed_at")
    private Instant viewedAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UsersEntity user;

    public long getId () {

        return this.id;
    }

    public void setId (long id) {

        this.id = id;
    }

    public String getText () {

        return this.text;
    }

    public void setText (String text) {

        this.text = text;
    }

    public NotificationType getType () {

        return this.type;
    }

    public void setType (NotificationType type) {

        this.type = type;
    }

    public Instant getCreatedAt () {

        return this.createdAt;
    }

    public void setCreatedAt (Instant createdAt) {

        this.createdAt = createdAt;
    }

    public Instant getViewedAt () {

        return this.viewedAt;
    }

    public void setViewedAt (Instant viewedAt) {

        this.viewedAt = viewedAt;
    }

    public UsersEntity getUser () {

        return this.user;
    }

    public void setUser (UsersEntity user) {

        this.user = user;
    }

    @Override
    public boolean equals (Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NotificationsEntity that = (NotificationsEntity) o;

        if (this.id != that.id) return false;
        if (!Objects.equals(this.text, that.text)) return false;
        if (!Objects.equals(this.type, that.type)) return false;
        if (!Objects.equals(this.createdAt, that.createdAt)) return false;
        if (!Objects.equals(this.viewedAt, that.viewedAt)) return false;
        return Objects.equals(this.user, that.user);
    }

    @Override
    public int hashCode () {

        int result = (int) (this.id ^ (this.id >>> 32));
        result = 31 * result + (this.text != null ? this.text.hashCode() : 0);
        result = 31 * result + (this.type != null ? this.type.hashCode() : 0);
        result = 31 * result + (this.createdAt != null ? this.createdAt.hashCode() : 0);
        result = 31 * result + (this.viewedAt != null ? this.viewedAt.hashCode() : 0);
        result = 31 * result + (this.user != null ? this.user.hashCode() : 0);
        return result;
    }
}
