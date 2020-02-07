package com.diluv.confluencia.database.record;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProjectFileQueueRecord extends BaseProjectFileRecord {

    private final String status;
    private final long statusChangeTime;

    public ProjectFileQueueRecord (ResultSet rs) throws SQLException {

        super(rs);
        this.status = rs.getString("status");
        this.statusChangeTime = rs.getTimestamp("status_change_time").getTime();
    }

    public String getStatus () {

        return this.status;
    }

    public long getStatusChangeTime () {

        return this.statusChangeTime;
    }
}
