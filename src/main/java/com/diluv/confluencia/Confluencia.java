package com.diluv.confluencia;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.flywaydb.core.Flyway;

import com.zaxxer.hikari.HikariDataSource;

public class Confluencia {

    public static final Logger LOGGER = LogManager.getLogger("Confluencia");

    private static HikariDataSource ds;
    private static Connection connection;

    public static void init (String url, String username, String password, boolean clean) {

        ds = new HikariDataSource();
        ds.setJdbcUrl(url + "?useLegacyDatetimeCode=false&serverTimezone=UTC");
        ds.setMaxLifetime(500000); // 500 seconds < 600 second (wait timeout)
        ds.setUsername(username);
        ds.setPassword(password);
        ds.addDataSourceProperty("rewriteBatchedStatements", "true");
        Flyway flyway = Flyway.configure().dataSource(ds).load();
        if (clean) {
            flyway.clean();
        }
        flyway.migrate();
    }

    public static Connection connection () throws SQLException {

        if (connection == null || connection.isClosed())
            connection = ds.getConnection();
        return connection;
    }
}