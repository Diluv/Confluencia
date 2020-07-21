package com.diluv.confluencia.database.record;

import java.sql.Timestamp;

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
    private Timestamp bouncedAt;

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

    public Timestamp getBouncedAt () {

        return this.bouncedAt;
    }

    public void setBouncedAt (Timestamp bouncedAt) {

        this.bouncedAt = bouncedAt;
    }
}
