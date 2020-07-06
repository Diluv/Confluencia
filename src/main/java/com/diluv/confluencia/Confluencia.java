package com.diluv.confluencia;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.flywaydb.core.Flyway;

import com.zaxxer.hikari.HikariDataSource;

import static java.util.concurrent.TimeUnit.SECONDS;

public class Confluencia {

    public static final Logger LOGGER = LogManager.getLogger("Confluencia");

    private static HikariDataSource ds;
    private static Connection connection;

    public static void init (String url, String username, String password) {

        ds = new HikariDataSource();
        ds.setJdbcUrl(url + "?useLegacyDatetimeCode=false&serverTimezone=UTC");
        ds.setIdleTimeout(SECONDS.toMillis(300));
        ds.setMaxLifetime(SECONDS.toMillis(300));
        ds.setUsername(username);
        ds.setPassword(password);
        ds.addDataSourceProperty("rewriteBatchedStatements", "true");
        Flyway flyway = Flyway.configure().dataSource(ds).load();
        flyway.migrate();
    }

    public static Connection connection () throws SQLException {

        if (connection != null) {
            connection.close();
        }
        connection = ds.getConnection();
        return connection;
    }
}