package com.diluv.confluencia.utils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

import javax.sql.DataSource;

import org.flywaydb.core.Flyway;
import org.hibernate.HibernateException;
import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;
import org.hibernate.hikaricp.internal.HikariCPConnectionProvider;
import org.hibernate.hikaricp.internal.HikariConfigurationUtil;
import org.hibernate.service.UnknownUnwrapTypeException;
import org.hibernate.service.spi.Configurable;
import org.hibernate.service.spi.Stoppable;
import org.jboss.logging.Logger;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class FlywayConnectionProvider implements ConnectionProvider, Configurable, Stoppable {

    private static final Logger LOGGER = Logger.getLogger(HikariCPConnectionProvider.class);

    private HikariConfig hcfg = null;

    private HikariDataSource hds = null;

    @SuppressWarnings("rawtypes")
    @Override
    public void configure (Map props) throws HibernateException {

        try {
            LOGGER.debug("Configuring HikariCP");

            hcfg = HikariConfigurationUtil.loadConfiguration(props);
            hds = new HikariDataSource(hcfg);

            Flyway flyway = Flyway.configure().dataSource(hds).load();
            flyway.migrate();
        }
        catch (Exception e) {
            throw new HibernateException(e);
        }

        LOGGER.debug("HikariCP Configured");
    }

    @Override
    public Connection getConnection () throws SQLException {

        Connection conn = null;
        if (hds != null) {
            conn = hds.getConnection();
        }

        return conn;
    }

    @Override
    public void closeConnection (Connection conn) throws SQLException {

        conn.close();
    }

    @Override
    public boolean supportsAggressiveRelease () {

        return false;
    }

    @Override
    @SuppressWarnings("rawtypes")
    public boolean isUnwrappableAs (Class unwrapType) {

        return ConnectionProvider.class.equals(unwrapType)
            || HikariCPConnectionProvider.class.isAssignableFrom(unwrapType)
            || DataSource.class.isAssignableFrom(unwrapType);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T unwrap (Class<T> unwrapType) {

        if (ConnectionProvider.class.equals(unwrapType) ||
            HikariCPConnectionProvider.class.isAssignableFrom(unwrapType)) {
            return (T) this;
        }
        else if (DataSource.class.isAssignableFrom(unwrapType)) {
            return (T) hds;
        }
        else {
            throw new UnknownUnwrapTypeException(unwrapType);
        }
    }

    @Override
    public void stop () {

        hds.close();
    }
}
