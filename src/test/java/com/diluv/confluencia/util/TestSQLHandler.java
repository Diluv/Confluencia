package com.diluv.confluencia.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.diluv.confluencia.Confluencia;
import com.diluv.confluencia.utils.SQLHandler;

public final class TestSQLHandler {

    private TestSQLHandler () {

    }

    public static String readFile (String filename) {

        try {
            InputStream stream = SQLHandler.class.getClassLoader().getResourceAsStream("db/insert/" + filename + ".sql");
            if (stream == null)
                throw new IOException();

            ByteArrayOutputStream result = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int length;
            while ((length = stream.read(buffer)) != -1) {
                result.write(buffer, 0, length);
            }
            return result.toString("UTF-8");
        }
        catch (final IOException e) {
            e.printStackTrace();
            // TODO Throw exception(crash?)
        }
        return null;
    }

    public static void loadData (String table) {

        try (PreparedStatement stmt = Confluencia.connection().prepareStatement(readFile(table))) {
            stmt.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
