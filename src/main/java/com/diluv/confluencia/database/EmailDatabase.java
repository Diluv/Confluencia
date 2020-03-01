package com.diluv.confluencia.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.diluv.confluencia.Confluencia;
import com.diluv.confluencia.database.dao.EmailDAO;
import com.diluv.confluencia.database.record.EmailSendRecord;
import com.diluv.confluencia.utils.SQLHandler;

public class EmailDatabase implements EmailDAO {
    private static final String INSERT_DOMAIN_BLACKLIST = SQLHandler.readFile("email/insertDomainBlacklist");
    private static final String EXISTS_BLACKLIST = SQLHandler.readFile("email/existsBlacklist");

    private static final String INSERT_EMAIL_SENT = SQLHandler.readFile("email/insertEmailSent");
    private static final String EXISTS_EMAIL_SENT = SQLHandler.readFile("email/existsEmailSent");
    private static final String FIND_EMAIL_SENT_BY_EMAIL_AND_TYPE = SQLHandler.readFile("email/findEmailSentByEmailAndType");
    private static final String FIND_EMAIL_SENT_BY_EMAIL = SQLHandler.readFile("email/findEmailSentByEmail");

    @Override
    public boolean insertDomainBlacklist (String[] domains) {

        try (PreparedStatement stmt = Confluencia.connection().prepareStatement(INSERT_DOMAIN_BLACKLIST)) {
            for (String domain : domains) {
                stmt.setString(1, domain);
                stmt.addBatch();
            }
            stmt.executeBatch();
            return true;
        }
        catch (SQLException e) {
            Confluencia.LOGGER.error("Failed to insert email domain blacklist {}.", e);
        }
        return false;
    }

    @Override
    public boolean existsBlacklist (String email, String domain) {

        try (PreparedStatement stmt = Confluencia.connection().prepareStatement(EXISTS_BLACKLIST)) {
            stmt.setString(1, email);
            stmt.setString(2, domain);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        }
        catch (SQLException e) {
            Confluencia.LOGGER.error("Failed to check domain {} against database email blacklist.", e);
        }
        return false;
    }

    @Override
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

    @Override
    public boolean existsEmailSent (String messageId) {

        try (PreparedStatement stmt = Confluencia.connection().prepareStatement(EXISTS_EMAIL_SENT)) {
            stmt.setString(1, messageId);

            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }
        }
        catch (SQLException e) {
            Confluencia.LOGGER.error("Failed to check domain {} against database email blacklist.", e);
        }
        return false;
    }

    @Override
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

    @Override
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
}
