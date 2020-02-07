package com.diluv.confluencia.database.record;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProjectFileRecord extends BaseProjectFileRecord {

    /**
     * A SHA 512 hash of the file.
     */
    private final String sha512;

    /**
     * The date the file was last modified.
     */
    private final long updatedAt;

    /**
     * Whether or not the file has been released.
     */
    private final boolean released;

    public ProjectFileRecord (ResultSet rs) throws SQLException {

        super(rs);
        this.sha512 = rs.getString("sha512");
        this.updatedAt = rs.getTimestamp("updated_at").getTime();
        this.released = rs.getBoolean("released");
    }

    public String getSha512 () {

        return this.sha512;
    }

    public long getUpdatedAt () {

        return this.updatedAt;
    }

    public boolean isReleased () {

        return this.released;
    }

}
