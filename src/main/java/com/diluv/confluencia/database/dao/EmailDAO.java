package com.diluv.confluencia.database.dao;

public interface EmailDAO {

    boolean existsBlacklist (String email, String domain);
}
