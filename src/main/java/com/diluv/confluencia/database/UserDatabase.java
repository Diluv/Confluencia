package com.diluv.confluencia.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import com.diluv.confluencia.Confluencia;
import com.diluv.confluencia.database.dao.UserDAO;
import com.diluv.confluencia.database.record.APITokenRecord;
import com.diluv.confluencia.database.record.RefreshTokenRecord;
import com.diluv.confluencia.database.record.TempUserRecord;
import com.diluv.confluencia.database.record.UserRecord;
import com.diluv.confluencia.utils.SQLHandler;

public class UserDatabase implements UserDAO {

    private static final String EXISTS_USER_BY_EMAIL = SQLHandler.readFile("user/existsUserByEmail");
    private static final String EXISTS_USER_BY_USERNAME = SQLHandler.readFile("user/existsUserByUsername");
    private static final String FIND_USER_BY_USERNAME = SQLHandler.readFile("user/findUserByUsername");
    private static final String INSERT_USER = SQLHandler.readFile("user/insertUser");

    private static final String EXISTS_TEMPUSER_BY_EMAIL = SQLHandler.readFile("temp_user/existsTempUserByEmail");
    private static final String EXISTS_TEMPUSER_BY_USERNAME = SQLHandler.readFile("temp_user/existsTempUserByUsername");
    private static final String INSERT_TEMPUSER = SQLHandler.readFile("temp_user/insertTempUser");
    private static final String UPDATE_TEMPUSER = SQLHandler.readFile("temp_user/updateTempUser");
    private static final String FIND_TEMPUSER_BY_EMAIL_AND_USERNAME = SQLHandler.readFile("temp_user/findTempUserByEmailAndUsername");
    private static final String FIND_TEMPUSER_BY_EMAIL_AND_CODE = SQLHandler.readFile("temp_user/findTempUserByEmailAndCode");
    private static final String DELETE_TEMPUSER = SQLHandler.readFile("temp_user/deleteTempUser");

    private static final String INSERT_REFRESH_TOKEN = SQLHandler.readFile("refresh_token/insertRefreshToken");
    private static final String FIND_REFRESH_TOKEN_BY_USERID_AND_CODE = SQLHandler.readFile("refresh_token/findRefreshTokenByUserIdAndCode");
    private static final String DELETE_REFRESH_TOKEN_BY_USERID_AND_CODE = SQLHandler.readFile("refresh_token/deleteRefreshTokenByUserIdAndCode");

    private static final String INSERT_API_TOKEN = SQLHandler.readFile("api_token/insertAPIToken");
    private static final String INSERT_API_TOKEN_PERMISSIONS = SQLHandler.readFile("api_token/insertAPITokenPermission");
    private static final String FIND_API_TOKEN_BY_USERID_AND_CODE = SQLHandler.readFile("api_token/findAPITokenByUserIdAndCode");
    private static final String DELETE_API_TOKEN_BY_USERID_AND_CODE = SQLHandler.readFile("api_token/deleteAPITokenByUserIdAndCode");

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
    public boolean insertUser (String email, String username, String displayName, String password, String passwordType, Timestamp createdAt) {

        try (PreparedStatement stmt = Confluencia.connection().prepareStatement(INSERT_USER)) {
            stmt.setString(1, email);
            stmt.setString(2, username);
            stmt.setString(3,displayName);
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
    public boolean insertRefreshToken (long userId, String code, Timestamp time) {

        try (PreparedStatement stmt = Confluencia.connection().prepareStatement(INSERT_REFRESH_TOKEN)) {
            stmt.setLong(1, userId);
            stmt.setString(2, code);
            stmt.setTimestamp(3, time);

            return stmt.executeUpdate() == 1;
        }
        catch (SQLException e) {
            Confluencia.LOGGER.error("Failed to insertRefreshToken.", e);
        }
        return false;
    }

    @Override
    public RefreshTokenRecord findRefreshTokenByUserIdAndCode (long userId, String code) {

        try (PreparedStatement stmt = Confluencia.connection().prepareStatement(FIND_REFRESH_TOKEN_BY_USERID_AND_CODE)) {
            stmt.setLong(1, userId);
            stmt.setString(2, code);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new RefreshTokenRecord(rs);
                }
            }
        }
        catch (SQLException e) {
            Confluencia.LOGGER.error("Failed to findRefreshTokenByUserIdAndCode.", e);
        }
        return null;
    }

    @Override
    public boolean deleteRefreshTokenByUserIdAndCode (long userId, String code) {

        try (PreparedStatement stmt = Confluencia.connection().prepareStatement(DELETE_REFRESH_TOKEN_BY_USERID_AND_CODE)) {
            stmt.setLong(1, userId);
            stmt.setString(2, code);

            return stmt.executeUpdate() == 1;
        }
        catch (SQLException e) {
            Confluencia.LOGGER.error("Failed to deleteRefreshTokenByUserIdAndCode for user {}", userId, e);
        }
        return false;
    }

    @Override
    public boolean insertAPITokens (long userId, String code, String name) {

        try (PreparedStatement stmt = Confluencia.connection().prepareStatement(INSERT_API_TOKEN)) {
            stmt.setLong(1, userId);
            stmt.setString(2, code);
            stmt.setString(3, name);

            return stmt.executeUpdate() == 1;
        }
        catch (SQLException e) {
            Confluencia.LOGGER.error("Failed to insertAPIToken.", e);
        }
        return false;
    }

    @Override
    public boolean insertAPITokenPermissions (long userId, String code, List<String> permissions) {

        try (PreparedStatement stmt = Confluencia.connection().prepareStatement(INSERT_API_TOKEN_PERMISSIONS)) {

            for (String permission : permissions) {
                stmt.setLong(1, userId);
                stmt.setString(2, code);
                stmt.setString(3, permission);
                stmt.addBatch();
            }
            stmt.executeLargeBatch();
            return true;
        }
        catch (SQLException e) {
            Confluencia.LOGGER.error("Failed to insertAPIToken.", e);
        }
        return false;
    }

    @Override
    public APITokenRecord findAPITokenByUserIdAndCode (long userId, String code) {

        try (PreparedStatement stmt = Confluencia.connection().prepareStatement(FIND_API_TOKEN_BY_USERID_AND_CODE)) {
            stmt.setLong(1, userId);
            stmt.setString(2, code);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new APITokenRecord(rs);
                }
            }
        }
        catch (SQLException e) {
            Confluencia.LOGGER.error("Failed to findAPITokenByUserIdAndCode.", e);
        }
        return null;
    }

    @Override
    public boolean deleteAPITokenByUserIdAndCode (long userId, String code) {

        try (PreparedStatement stmt = Confluencia.connection().prepareStatement(DELETE_API_TOKEN_BY_USERID_AND_CODE)) {
            stmt.setLong(1, userId);
            stmt.setString(2, code);

            return stmt.executeUpdate() == 1;
        }
        catch (SQLException e) {
            Confluencia.LOGGER.error("Failed to deleteAPITokenByUserIdAndCode for user {}", userId, e);
        }
        return false;
    }
}