package com.diluv.confluencia.util;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.commons.io.FileUtils;

import com.diluv.confluencia.Confluencia;
import com.diluv.confluencia.utils.SQLHandler;

public final class TestSQLHandler {

    private TestSQLHandler () {

    }

    public static String readFile (String filename) {

        try {
            final File f = new File(SQLHandler.class.getClassLoader().getResource("db/insert/" + filename + ".sql").getFile());
            return FileUtils.readFileToString(f, Charset.defaultCharset());
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
