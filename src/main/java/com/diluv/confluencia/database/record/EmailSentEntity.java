package com.diluv.confluencia.database.record;

import java.time.Instant;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "email_sent")
public class EmailSentEntity {

    @Id
    @Column(name = "message_id")
    private String messageId;

    @Column(name = "email")
    private String email;

    @Column(name = "type")
    private String type;

    @Column(name = "sent_at")
    private Instant sentAt;

    public String getMessageId () {

        return this.messageId;
    }

    public void setMessageId (String messageId) {

        this.messageId = messageId;
    }

    public String getEmail () {

        return this.email;
    }

    public void setEmail (String email) {

        this.email = email;
    }

    public String getType () {

        return this.type;
    }

    public void setType (String type) {

        this.type = type;
    }

    public Instant getSentAt () {

        return this.sentAt;
    }

    public void setSentAt (Instant sentAt) {

        this.sentAt = sentAt;
    }

    @Override
    public boolean equals (Object o) {

        if (this == o) return true;
        if (!(o instanceof EmailSentEntity)) return false;
        EmailSentEntity that = (EmailSentEntity) o;
        return Objects.equals(getMessageId(), that.getMessageId()) &&
            Objects.equals(getEmail(), that.getEmail()) &&
            Objects.equals(getType(), that.getType()) &&
            Objects.equals(getSentAt(), that.getSentAt());
    }

    @Override
    public int hashCode () {

        return Objects.hash(getMessageId(), getEmail(), getType(), getSentAt());
    }
}
