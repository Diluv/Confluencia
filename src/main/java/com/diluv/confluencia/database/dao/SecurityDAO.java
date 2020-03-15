package com.diluv.confluencia.database.dao;

import java.util.List;
import java.util.Map;

import com.diluv.confluencia.database.record.CompromisedPasswordRecord;
import com.diluv.confluencia.database.record.EmailSendRecord;

public interface SecurityDAO {

    boolean insertDomainBlacklist (String[] domains);

    boolean existsBlacklist (String email, String domain);

    boolean insertEmailSent (String messageId, String email, String type);

    boolean existsEmailSent (String messageId);

    EmailSendRecord findEmailSentByEmailAndType (String email, String type);

    List<EmailSendRecord> findEmailSentByEmail (String email);

    boolean insertPassword (Map<String, Long> hashOccurrences);

    CompromisedPasswordRecord findOnePasswordByHash (String hash);
}