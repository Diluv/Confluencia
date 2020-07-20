package com.diluv.confluencia.database.record;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;import javax.persistence.Entity;
import org.hibernate.annotations.DynamicUpdate;
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
    private Timestamp sentAt;

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

    public Timestamp getSentAt () {

        return this.sentAt;
    }

    public void setSentAt (Timestamp sentAt) {

        this.sentAt = sentAt;
    }
}
