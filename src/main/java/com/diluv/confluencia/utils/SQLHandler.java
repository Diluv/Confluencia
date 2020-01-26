package com.diluv.confluencia.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

import javax.annotation.Nullable;

import org.apache.commons.io.IOUtils;

import com.diluv.confluencia.Confluencia;

public final class SQLHandler {
    private SQLHandler () {

    }

    public static String readFile (String filename) {

        try {
            InputStream stream = SQLHandler.class.getClassLoader().getResourceAsStream("db/query/" + filename + ".sql");
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
            Confluencia.LOGGER.error("Failed to read sql script file {}.", filename, e);
        }
        return null;
    }
}