package com.diluv.confluencia.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.diluv.confluencia.Confluencia;
import com.diluv.confluencia.database.record.CompromisedPasswordRecord;
import com.diluv.confluencia.database.record.EmailSendRecord;
import com.diluv.confluencia.database.record.ReferenceTokenRecord;
import com.diluv.confluencia.utils.SQLHandler;

public class SecurityDatabase {
    private static final String INSERT_DOMAIN_BLOCKLIST = SQLHandler.readFile("email/insertDomainBlocklist");
    private static final String EXISTS_BLOCKLIST = SQLHandler.readFile("email/existsBlocklist");

    private static final String INSERT_EMAIL_SENT = SQLHandler.readFile("email/insertEmailSent");
    private static final String EXISTS_EMAIL_SENT = SQLHandler.readFile("email/existsEmailSent");
    private static final String FIND_EMAIL_SENT_BY_EMAIL_AND_TYPE = SQLHandler.readFile("email/findEmailSentByEmailAndType");
    private static final String FIND_EMAIL_SENT_BY_EMAIL = SQLHandler.readFile("email/findEmailSentByEmail");

    private static final String INSERT_PASSWORD = SQLHandler.readFile("password/insertPassword");
    private static final String FIND_PASSWORD_BY_HASH = SQLHandler.readFile("password/findPasswordByHash");

    private static final String FIND_PERSISTED_GRANTS_BY_KEY_AND_TYPE = SQLHandler.readFile("persisted_grants/findPersistedGrantsByKeyAndType");

    public boolean insertDomainBlocklist (String[] domains) {

        try (PreparedStatement stmt = Confluencia.connection().prepareStatement(INSERT_DOMAIN_BLOCKLIST)) {
            for (String domain : domains) {
                stmt.setString(1, domain);
                stmt.addBatch();
            }
            stmt.executeBatch();
            return true;
        }
        catch (SQLException e) {
            Confluencia.LOGGER.error("Failed to insert email domain blocklist {}.", e);
        }
        return false;
    }

    public boolean existsBlocklist (String email, String domain) {

        try (PreparedStatement stmt = Confluencia.connection().prepareStatement(EXISTS_BLOCKLIST)) {
            stmt.setString(1, email);
            stmt.setString(2, domain);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        }
        catch (SQLException e) {
            Confluencia.LOGGER.error("Failed to check domain {} against database email blocklist.", e);
        }
        return false;
    }

    public boolean insertEmailSent (String messageId, String email, String type) {

        try (PreparedStatement stmt = Confluencia.connection().prepareStatement(INSERT_EMAIL_SENT)) {
            stmt.setString(1, messageId);
            stmt.setString(2, email);
            stmt.setString(3, type);

            return stmt.executeUpdate() == 1;
        }
        catch (SQLException e) {
            Confluencia.LOGGER.error("Failed to insert email sent.", e);
        }
        return false;
    }

    public boolean existsEmailSent (String messageId) {

        try (PreparedStatement stmt = Confluencia.connection().prepareStatement(EXISTS_EMAIL_SENT)) {
            stmt.setString(1, messageId);

            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }
        }
        catch (SQLException e) {
            Confluencia.LOGGER.error("Failed to check domain {} against database email blocklist.", e);
        }
        return false;
    }

    public EmailSendRecord findEmailSentByEmailAndType (String email, String type) {

        try (PreparedStatement stmt = Confluencia.connection().prepareStatement(FIND_EMAIL_SENT_BY_EMAIL_AND_TYPE)) {
            stmt.setString(1, email);
            stmt.setString(2, type);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new EmailSendRecord(rs);
                }
            }
        }
        catch (SQLException e) {
            Confluencia.LOGGER.error("Failed to find email sent by email and type.", e);
        }
        return null;
    }

    public List<EmailSendRecord> findEmailSentByEmail (String email) {

        List<EmailSendRecord> emailSendRecords = new ArrayList<>();

        try (PreparedStatement stmt = Confluencia.connection().prepareStatement(FIND_EMAIL_SENT_BY_EMAIL)) {
            stmt.setString(1, email);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    emailSendRecords.add(new EmailSendRecord(rs));
                }
            }
        }
        catch (SQLException e) {
            Confluencia.LOGGER.error("Failed to find email sent by email.", e);
        }
        return emailSendRecords;
    }

    public boolean insertPassword (Map<String, Long> hashOccurrences) {

        try (PreparedStatement stmt = Confluencia.connection().prepareStatement(INSERT_PASSWORD)) {
            for (String hash : hashOccurrences.keySet()) {
                stmt.setString(1, hash);
                stmt.setLong(2, hashOccurrences.get(hash));
                stmt.setLong(3, hashOccurrences.get(hash));
                stmt.addBatch();
            }
            int[] s = stmt.executeBatch();
            return true;
        }
        catch (SQLException e) {
            Confluencia.LOGGER.error("Failed to find insertOrUpdatePassword.", e);
        }
        return false;
    }

    public CompromisedPasswordRecord findOnePasswordByHash (String hash) {

        try (PreparedStatement stmt = Confluencia.connection().prepareStatement(FIND_PASSWORD_BY_HASH)) {
            stmt.setString(1, hash);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new CompromisedPasswordRecord(rs);
                }
            }
        }
        catch (SQLException e) {
            Confluencia.LOGGER.error("Failed to find compromised password.", e);
        }
        return null;
    }

    public ReferenceTokenRecord findPersistedGrantByKeyAndType (String key, String type) {

        try (PreparedStatement stmt = Confluencia.connection().prepareStatement(FIND_PERSISTED_GRANTS_BY_KEY_AND_TYPE)) {
            stmt.setString(1, key);
            stmt.setString(2, type);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new ReferenceTokenRecord(rs);
                }
            }
        }
        catch (SQLException e) {
            Confluencia.LOGGER.error("Failed to findPersistedGrantByKeyAndType.", e);
        }
        return null;
    }
}
