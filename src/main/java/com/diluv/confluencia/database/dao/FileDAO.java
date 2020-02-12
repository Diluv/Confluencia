package com.diluv.confluencia.database.dao;

import java.sql.SQLException;
import java.util.List;

import com.diluv.confluencia.database.record.ProjectFileRecord;

public interface FileDAO {

    boolean updateFileQueueStatusById (long id) throws SQLException;

    List<ProjectFileRecord> findAllWherePending (int amount);

    List<ProjectFileRecord> getLatestFileQueueRecord (int amount) throws SQLException;

    Long insertProjectFile (String name, long size, String changelog, long projectId, long userId);

    ProjectFileRecord findOneProjectFileQueueByFileId (long fileId);

    List<ProjectFileRecord> findAllProjectFilesByGameSlugAndProjectTypeAndProjectSlug (String gameSlug, String projectTypeSlug, String projectSlug);

    List<ProjectFileRecord> findAllProjectFilesByGameSlugAndProjectTypeAndProjectSlugAuthorized (String gameSlug, String projectTypeSlug, String projectSlug);
}