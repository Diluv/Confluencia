package com.diluv.confluencia.database.dao;

import com.diluv.confluencia.database.record.CompromisedPasswordRecord;
import com.diluv.confluencia.database.record.EmailSendRecord;
import com.diluv.confluencia.database.record.ReferenceTokenRecord;

import java.util.List;
import java.util.Map;

public interface SecurityDAO {

    boolean insertDomainBlacklist (String[] domains);

    boolean existsBlacklist (String email, String domain);

    boolean insertEmailSent (String messageId, String email, String type);

    boolean existsEmailSent (String messageId);

    EmailSendRecord findEmailSentByEmailAndType (String email, String type);

    List<EmailSendRecord> findEmailSentByEmail (String email);

    boolean insertPassword (Map<String, Long> hashOccurrences);

    CompromisedPasswordRecord findOnePasswordByHash (String hash);

    ReferenceTokenRecord findPersistedGrantByKeyAndType (String key, String type);
}