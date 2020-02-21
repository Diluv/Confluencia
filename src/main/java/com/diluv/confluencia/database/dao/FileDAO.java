package com.diluv.confluencia.database.dao;

import java.sql.SQLException;
import java.util.List;

import com.diluv.confluencia.database.record.FileStatus;
import com.diluv.confluencia.database.record.ProjectFileRecord;

public interface FileDAO {

    boolean updateStatusById (long id, FileStatus status) throws SQLException;

    List<ProjectFileRecord> findAllWhereStatusAndLimit (FileStatus status, int amount);

    List<ProjectFileRecord> getLatestFiles (int amount) throws SQLException;

    Long insertProjectFile (String name, long size, String changelog, String sha512, long projectId, long userId);

    ProjectFileRecord findOneProjectFileQueueByFileId (long fileId);

    List<ProjectFileRecord> findAllByGameSlugAndProjectTypeAndProjectSlug (String gameSlug, String projectTypeSlug, String projectSlug);

    List<ProjectFileRecord> findAllByGameSlugAndProjectTypeAndProjectSlugAuthorized (String gameSlug, String projectTypeSlug, String projectSlug);

    boolean insertProjectFileAntivirus (long projectId, String malware);
}