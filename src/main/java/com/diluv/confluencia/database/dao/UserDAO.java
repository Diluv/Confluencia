package com.diluv.confluencia.database.dao;

import java.sql.Timestamp;
import java.util.List;

import com.diluv.confluencia.database.record.APITokenRecord;
import com.diluv.confluencia.database.record.RefreshTokenRecord;
import com.diluv.confluencia.database.record.TempUserRecord;
import com.diluv.confluencia.database.record.UserRecord;

public interface UserDAO {

    boolean existsUserByEmail (String email);

    boolean existsUserByUsername (String username);

    UserRecord findOneByUsername (String username);

    boolean insertUser (String email, String username, String password, String passwordType, Timestamp createdAt);

    boolean existsTempUserByEmail (String email);

    boolean existsTempUserByUsername (String username);

    boolean insertTempUser (String email, String username, String password, String passwordType, String verificationCode);

    boolean updateTempUser (String email, String username, String verificationCode);

    TempUserRecord findTempUserByEmailAndUsername (String email, String username);

    TempUserRecord findTempUserByEmailAndCode (String email, String code);

    boolean deleteTempUser (String email, String username);

    boolean insertRefreshToken (long userId, String code, Timestamp time);

    RefreshTokenRecord findRefreshTokenByUserIdAndCode (long userId, String code);

    boolean deleteRefreshTokenByUserIdAndCode (long userId, String code);

    boolean insertAPITokens (long userId, String code, String name);

    boolean insertAPITokenPermissions (long userId, String code, List<String> permissions);

    APITokenRecord findAPITokenByUserIdAndCode (long userId, String code);

    boolean deleteAPITokenByUserIdAndCode (long userId, String code);
}
