package com.diluv.confluencia.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.diluv.confluencia.Confluencia;
import com.diluv.confluencia.database.dao.EmailDAO;
import com.diluv.confluencia.utils.SQLHandler;

public class EmailDatabase implements EmailDAO {
    private static final String INSERT_DOMAIN_BLACKLIST = SQLHandler.readFile("email/insertDomainBlacklist");
    private static final String EXISTS_BLACKLIST = SQLHandler.readFile("email/existsBlacklist");

    @Override
    public boolean insertDomainBlacklist (String[] domains) {

        try (PreparedStatement stmt = Confluencia.connection().prepareStatement(INSERT_DOMAIN_BLACKLIST)) {
            for (String domain : domains) {
                stmt.setString(1, domain);
                stmt.addBatch();
            }
            stmt.executeLargeBatch();
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
}
