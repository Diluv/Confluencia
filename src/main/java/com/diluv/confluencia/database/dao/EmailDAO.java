package com.diluv.confluencia.database.dao;

import java.util.List;

import com.diluv.confluencia.database.record.EmailSendRecord;

public interface EmailDAO {

    boolean insertDomainBlacklist (String[] domains);

    boolean existsBlacklist (String email, String domain);

    boolean insertEmailSent (String messageId, String email, String type);

    boolean existsEmailSent (String messageId);

    EmailSendRecord findEmailSentByEmailAndType (String email, String type);

    List<EmailSendRecord> findEmailSentByEmail (String email);
}