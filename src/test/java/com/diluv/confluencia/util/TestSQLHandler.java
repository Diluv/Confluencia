package com.diluv.confluencia.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.commons.io.FileUtils;

import com.diluv.confluencia.Confluencia;
import com.diluv.confluencia.utils.SQLHandler;

import org.apache.commons.io.IOUtils;

public final class TestSQLHandler {

    private TestSQLHandler () {

    }

    public static String readFile (String filename) {

        try {
            InputStream stream = SQLHandler.class.getClassLoader().getResourceAsStream("db/insert/" + filename + ".sql");
            if (stream == null)
                throw new IOException();
            return IOUtils.toString(stream, Charset.defaultCharset());
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
