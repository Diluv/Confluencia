package com.diluv.confluencia.database.dao;

import java.sql.SQLException;
import java.util.List;

import com.diluv.confluencia.database.record.ProjectFileQueueRecord;
import com.diluv.confluencia.database.record.ProjectFileRecord;

public interface FileDAO {
    List<ProjectFileQueueRecord> findAllWherePending (int amount);

    boolean updateFileQueueStatusById (long id) throws SQLException;

    List<ProjectFileQueueRecord> getLatestFileQueueRecord (int amount) throws SQLException;

    List<ProjectFileRecord> findAllProjectFilesByGameSlugAndProjectType (String gameSlug, String projectTypeSlug, String projectSlug);

    Long insertProjectFileQueue (String name, long size, String changelog, long projectId, long userId);
}
