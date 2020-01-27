package com.diluv.confluencia.database.dao;

import java.sql.SQLException;
import java.util.List;

import com.diluv.confluencia.database.record.FileQueueRecord;
import com.diluv.confluencia.database.record.ProjectFileRecord;


public interface FileDAO {
    List<FileQueueRecord> findAllWherePending (int amount);

    boolean updateFileQueueStatusById (long id) throws SQLException;

    List<FileQueueRecord> getLatestFileQueueRecord (int amount) throws SQLException;

    List<ProjectFileRecord> findAllProjectFilesByGameSlugAndProjectType (String gameSlug, String projectTypeSlug, String projectSlug);

    Long insertProjectFileQueue (String name, String changelog, long projectId, long userId);
}
