package com.diluv.confluencia.database.dao;

import java.sql.SQLException;
import java.util.List;

import com.diluv.confluencia.database.record.FileProcessingStatus;
import com.diluv.confluencia.database.record.GameVersionRecord;
import com.diluv.confluencia.database.record.ProjectFileRecord;
import com.diluv.confluencia.utils.Pagination;

public interface FileDAO {

    boolean updateStatusById (FileProcessingStatus status, long id) throws SQLException;

    boolean updateStatusByStatus (FileProcessingStatus set, FileProcessingStatus where);

    List<ProjectFileRecord> findAllWhereStatusAndLimit (FileProcessingStatus status, int amount);

    List<ProjectFileRecord> getLatestFiles (int amount) throws SQLException;

    Long insertProjectFile (String name, long size, String changelog, String sha512, String releaseType, String classifier, long projectId, long userId);

    ProjectFileRecord findOneProjectFileQueueByFileId (long fileId);

    List<ProjectFileRecord> findAllByGameSlugAndProjectTypeAndProjectSlug (String gameSlug, String projectTypeSlug, String projectSlug, Pagination cursor, int limit);

    List<ProjectFileRecord> findAllByGameSlugAndProjectTypeAndProjectSlugAuthorized (String gameSlug, String projectTypeSlug, String projectSlug, Pagination cursor, int limit);

    boolean insertProjectFileAntivirus (long projectFileId, String malware);

    List<GameVersionRecord> findAllGameVersionsByProjectFile (long projectFileId);
}