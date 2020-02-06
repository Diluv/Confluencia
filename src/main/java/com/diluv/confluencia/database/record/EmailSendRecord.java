package com.diluv.confluencia.database.record;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmailSendRecord {
    private final String messageId;
    private final String email;
    private final String type;
    private final long sentAt;

    public EmailSendRecord (ResultSet rs) throws SQLException {
        this.messageId = rs.getString("message_id");
        this.email = rs.getString("email");
        this.type = rs.getString("type");
        this.sentAt = rs.getTimestamp("sent_at").getTime();
    }

    public String getMessageId () {

        return this.messageId;
    }

    public String getEmail () {

        return this.email;
    }

    public String getType () {

        return this.type;
    }

    public long getSentAt () {

        return this.sentAt;
    }
}
