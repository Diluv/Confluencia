package com.diluv.confluencia.database.record;

import java.time.Instant;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "email_bounce")
public class EmailBounceEntity {

    @Id
    @Column(name = "message_id")
    private String messageId;

    @Column(name = "bounce_type")
    private String bounceType;

    @Column(name = "description")
    private String description;

    @Column(name = "bounced_at")
    private Instant bouncedAt;

    public String getMessageId () {

        return this.messageId;
    }

    public void setMessageId (String messageId) {

        this.messageId = messageId;
    }

    public String getBounceType () {

        return this.bounceType;
    }

    public void setBounceType (String bounceType) {

        this.bounceType = bounceType;
    }

    public String getDescription () {

        return this.description;
    }

    public void setDescription (String description) {

        this.description = description;
    }

    public Instant getBouncedAt () {

        return this.bouncedAt;
    }

    public void setBouncedAt (Instant bouncedAt) {

        this.bouncedAt = bouncedAt;
    }

    @Override
    public boolean equals (Object o) {

        if (this == o) return true;
        if (!(o instanceof EmailBounceEntity)) return false;
        EmailBounceEntity that = (EmailBounceEntity) o;
        return Objects.equals(getMessageId(), that.getMessageId()) &&
            Objects.equals(getBounceType(), that.getBounceType()) &&
            Objects.equals(getDescription(), that.getDescription()) &&
            Objects.equals(getBouncedAt(), that.getBouncedAt());
    }

    @Override
    public int hashCode () {

        return Objects.hash(getMessageId(), getBounceType(), getDescription(), getBouncedAt());
    }
}
