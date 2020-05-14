package com.diluv.confluencia.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.diluv.confluencia.Confluencia;
import com.diluv.confluencia.database.dao.UserDAO;
import com.diluv.confluencia.database.record.PasswordResetRecord;
import com.diluv.confluencia.database.record.TempUserRecord;
import com.diluv.confluencia.database.record.UserRecord;
import com.diluv.confluencia.database.record.UserRoleRecord;
import com.diluv.confluencia.utils.SQLHandler;

public class UserDatabase implements UserDAO {

    private static final String COUNT_ALL = SQLHandler.readFile("user/countAll");

    private static final String EXISTS_USER_BY_EMAIL = SQLHandler.readFile("user/existsUserByEmail");
    private static final String EXISTS_USER_BY_USERNAME = SQLHandler.readFile("user/existsUserByUsername");
    private static final String FIND_USER_BY_USERNAME = SQLHandler.readFile("user/findUserByUsername");
    private static final String FIND_USER_BY_USER_ID = SQLHandler.readFile("user/findUserByUserId");
    private static final String INSERT_USER = SQLHandler.readFile("user/insertUser");
    private static final String UPDATE_PASSWORD_BY_USERID = SQLHandler.readFile("user/updatePasswordByUserId");

    private static final String EXISTS_TEMPUSER_BY_EMAIL = SQLHandler.readFile("temp_user/existsTempUserByEmail");
    private static final String EXISTS_TEMPUSER_BY_USERNAME = SQLHandler.readFile("temp_user/existsTempUserByUsername");
    private static final String INSERT_TEMPUSER = SQLHandler.readFile("temp_user/insertTempUser");
    private static final String UPDATE_TEMPUSER = SQLHandler.readFile("temp_user/updateTempUser");
    private static final String FIND_TEMPUSER_BY_EMAIL_AND_USERNAME = SQLHandler.readFile("temp_user/findTempUserByEmailAndUsername");
    private static final String FIND_TEMPUSER_BY_EMAIL_AND_CODE = SQLHandler.readFile("temp_user/findTempUserByEmailAndCode");
    private static final String DELETE_TEMPUSER = SQLHandler.readFile("temp_user/deleteTempUser");

    private static final String DELETE_ALL_REFRESH_TOKENS_BY_USERID = SQLHandler.readFile("refresh_token/deleteRefreshTokenByUserId");

    private static final String FIND_ALL_USER_ROLES_BY_USER_ID = SQLHandler.readFile("user_roles/findAllUserRolesByUserId");

    private static final String INSERT_PASSWORD_RESET = SQLHandler.readFile("user_reset/insertPasswordReset");
    private static final String DELETE_PASSWORD_RESET = SQLHandler.readFile("user_reset/deletePasswordReset");
    private static final String FIND_ONE_PASSWORD_RESET_BY_EMAIL_AND_CODE = SQLHandler.readFile("user_reset/findOnePasswordResetByEmailAndCode");

    @Override
    public long countAll () {

        try (PreparedStatement stmt = Confluencia.connection().prepareStatement(COUNT_ALL)) {
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getLong(1);
                }
            }
        }
        catch (SQLException e) {
            Confluencia.LOGGER.error("Failed to run countAll.", e);
        }
        return 0;
    }

    @Override
    public boolean existsUserByEmail (String email) {

        try (PreparedStatement stmt = Confluencia.connection().prepareStatement(EXISTS_USER_BY_EMAIL)) {
            stmt.setString(1, email);

            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }
        }
        catch (SQLException e) {
            Confluencia.LOGGER.error("Failed to find user for email.", e);
        }
        return false;
    }

    @Override
    public boolean existsUserByUsername (String username) {

        try (PreparedStatement stmt = Confluencia.connection().prepareStatement(EXISTS_USER_BY_USERNAME)) {
            stmt.setString(1, username);

            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }
        }
        catch (SQLException e) {
            Confluencia.LOGGER.error("Failed to find user by username.");
        }
        return false;
    }

    @Override
    public UserRecord findOneByUsername (String username) {

        try (PreparedStatement stmt = Confluencia.connection().prepareStatement(FIND_USER_BY_USERNAME)) {
            stmt.setString(1, username);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new UserRecord(rs);
                }
            }
        }
        catch (SQLException e) {
            Confluencia.LOGGER.error("Failed to find user by username.", e);
        }
        return null;
    }

    @Override
    public UserRecord findOneByUserId (long userId) {

        try (PreparedStatement stmt = Confluencia.connection().prepareStatement(FIND_USER_BY_USER_ID)) {
            stmt.setLong(1, userId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new UserRecord(rs);
                }
            }
        }
        catch (SQLException e) {
            Confluencia.LOGGER.error("Failed to find user by id.", e);
        }
        return null;
    }

    @Override
    public boolean insertUser (String email, String username, String displayName, String password, String passwordType, Timestamp createdAt) {

        try (PreparedStatement stmt = Confluencia.connection().prepareStatement(INSERT_USER)) {
            stmt.setString(1, email);
            stmt.setString(2, username);
            stmt.setString(3, displayName);
            stmt.setString(4, password);
            stmt.setString(5, passwordType);
            stmt.setTimestamp(6, createdAt);

            return stmt.executeUpdate() == 1;
        }
        catch (SQLException e) {
            Confluencia.LOGGER.error("Failed to insert user.", e);
        }
        return false;
    }

    @Override
    public boolean updateUserPasswordByUserId (long userId, String password) {

        try (PreparedStatement stmt = Confluencia.connection().prepareStatement(UPDATE_PASSWORD_BY_USERID)) {
            stmt.setString(1, password);
            stmt.setLong(2, userId);

            return stmt.executeUpdate() == 1;
        }
        catch (SQLException e) {
            Confluencia.LOGGER.error("Failed to updateUserPassword.", e);
        }
        return false;
    }

    @Override
    public boolean existsTempUserByEmail (String email) {

        try (PreparedStatement stmt = Confluencia.connection().prepareStatement(EXISTS_TEMPUSER_BY_EMAIL)) {
            stmt.setString(1, email);

            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }
        }
        catch (SQLException e) {
            Confluencia.LOGGER.error("Failed to check if temp user exists.", e);
        }
        return true;
    }

    @Override
    public boolean existsTempUserByUsername (String username) {

        try (PreparedStatement stmt = Confluencia.connection().prepareStatement(EXISTS_TEMPUSER_BY_USERNAME)) {
            stmt.setString(1, username);

            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }
        }
        catch (SQLException e) {
            Confluencia.LOGGER.error("Failed to check existTempUserByUsername.", e);
        }
        return true;
    }

    @Override
    public boolean insertTempUser (String email, String username, String password, String passwordType, String verificationCode) {

        try (PreparedStatement stmt = Confluencia.connection().prepareStatement(INSERT_TEMPUSER)) {
            stmt.setString(1, email);
            stmt.setString(2, username);
            stmt.setString(3, password);
            stmt.setString(4, passwordType);
            stmt.setString(5, verificationCode);

            return stmt.executeUpdate() == 1;
        }
        catch (SQLException e) {
            Confluencia.LOGGER.error("Failed to insertTempUser.", e);
        }
        return false;
    }

    @Override
    public boolean updateTempUser (String email, String username, String verificationCode) {

        try (PreparedStatement stmt = Confluencia.connection().prepareStatement(UPDATE_TEMPUSER)) {
            stmt.setString(1, verificationCode);
            stmt.setString(2, email);
            stmt.setString(3, username);

            return stmt.executeUpdate() == 1;
        }
        catch (SQLException e) {
            Confluencia.LOGGER.error("Failed to updateTempUser.", e);
        }
        return false;
    }

    @Override
    public TempUserRecord findTempUserByEmailAndUsername (String email, String username) {

        try (PreparedStatement stmt = Confluencia.connection().prepareStatement(FIND_TEMPUSER_BY_EMAIL_AND_USERNAME)) {
            stmt.setString(1, email);
            stmt.setString(2, username);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new TempUserRecord(rs);
                }
            }
        }
        catch (SQLException e) {
            Confluencia.LOGGER.error("Failed to findTempUserByEmailAndUsername.", e);
        }
        return null;
    }

    @Override
    public TempUserRecord findTempUserByEmailAndCode (String email, String code) {

        try (PreparedStatement stmt = Confluencia.connection().prepareStatement(FIND_TEMPUSER_BY_EMAIL_AND_CODE)) {
            stmt.setString(1, email);
            stmt.setString(2, code);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new TempUserRecord(rs);
                }
            }
        }
        catch (SQLException e) {
            Confluencia.LOGGER.error("Failed to findTempUserByEmailAndCode.", e);
        }
        return null;
    }

    @Override
    public boolean deleteTempUser (String email, String username) {

        try (PreparedStatement stmt = Confluencia.connection().prepareStatement(DELETE_TEMPUSER)) {
            stmt.setString(1, email);
            stmt.setString(2, username);

            return stmt.executeUpdate() == 1;
        }
        catch (SQLException e) {
            Confluencia.LOGGER.error("Failed to deleteTempUser.", e);
        }
        return false;
    }

    @Override
    public boolean deleteAllRefreshTokensByUserId (long userId) {

        try (PreparedStatement stmt = Confluencia.connection().prepareStatement(DELETE_ALL_REFRESH_TOKENS_BY_USERID)) {
            stmt.setLong(1, userId);

            return stmt.executeUpdate() >= 0;

        }
        catch (SQLException e) {
            Confluencia.LOGGER.error("Failed to deleteAllRefreshTokensByUserId.", e);
        }
        return false;
    }

    @Override
    public List<UserRoleRecord> findAllUserRolesByUserId (long userId) {

        List<UserRoleRecord> userRoles = new ArrayList<>();

        try (PreparedStatement stmt = Confluencia.connection().prepareStatement(FIND_ALL_USER_ROLES_BY_USER_ID)) {
            stmt.setLong(1, userId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    userRoles.add(new UserRoleRecord(rs));
                }
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return userRoles;
    }

    @Override
    public boolean insertPasswordReset (long userId, String code) {

        try (PreparedStatement stmt = Confluencia.connection().prepareStatement(INSERT_PASSWORD_RESET)) {

            stmt.setLong(1, userId);
            stmt.setString(2, code);

            return stmt.executeUpdate() == 1;
        }
        catch (SQLException e) {
            Confluencia.LOGGER.error("Failed to insertPasswordReset.", e);
        }
        return false;
    }

    @Override
    public boolean deletePasswordReset (long userId, String code) {

        try (PreparedStatement stmt = Confluencia.connection().prepareStatement(DELETE_PASSWORD_RESET)) {

            stmt.setLong(1, userId);
            stmt.setString(2, code);

            return stmt.executeUpdate() == 1;
        }
        catch (SQLException e) {
            Confluencia.LOGGER.error("Failed to deletePasswordReset.", e);
        }
        return false;
    }

    @Override
    public PasswordResetRecord findOnePasswordResetByEmailAndCode (String email, String code) {

        try (PreparedStatement stmt = Confluencia.connection().prepareStatement(FIND_ONE_PASSWORD_RESET_BY_EMAIL_AND_CODE)) {
            stmt.setString(1, email);
            stmt.setString(2, code);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new PasswordResetRecord(rs);
                }
            }
        }
        catch (SQLException e) {
            Confluencia.LOGGER.error("Failed to findOnePasswordResetByEmailAndCode.", e);
        }
        return null;
    }
}