package com.diluv.confluencia.database.dao;

import java.sql.Timestamp;
import java.util.List;

import com.diluv.confluencia.database.record.APITokenRecord;
import com.diluv.confluencia.database.record.PasswordResetRecord;
import com.diluv.confluencia.database.record.RefreshTokenRecord;
import com.diluv.confluencia.database.record.TempUserRecord;
import com.diluv.confluencia.database.record.UserRecord;
import com.diluv.confluencia.database.record.UserRoleRecord;

public interface UserDAO {

    boolean existsUserByEmail (String email);

    boolean existsUserByUsername (String username);

    UserRecord findOneByUsername (String username);

    UserRecord findOneByUserId (long userId);

    boolean insertUser (String email, String username, String displayName, String password, String passwordType, Timestamp createdAt);

    boolean updateUserPasswordByUserId (long userId, String password);

    boolean existsTempUserByEmail (String email);

    boolean existsTempUserByUsername (String username);

    boolean insertTempUser (String email, String username, String password, String passwordType, String verificationCode);

    boolean updateTempUser (String email, String username, String verificationCode);

    TempUserRecord findTempUserByEmailAndUsername (String email, String username);

    TempUserRecord findTempUserByEmailAndCode (String email, String code);

    boolean deleteTempUser (String email, String username);

    boolean deleteAllRefreshTokensByUserId (long userId);

    List<UserRoleRecord> findAllUserRolesByUserId (long userId);

    boolean insertPasswordReset (long userId, String code);

    boolean deletePasswordReset (long userId, String code);

    PasswordResetRecord findOnePasswordResetByEmailAndCode (String email, String code);
}
