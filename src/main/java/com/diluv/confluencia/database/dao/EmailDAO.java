package com.diluv.confluencia.database.dao;

public interface EmailDAO {

    boolean insertDomainBlacklist (String[] domains);

    boolean existsBlacklist (String email, String domain);
}
