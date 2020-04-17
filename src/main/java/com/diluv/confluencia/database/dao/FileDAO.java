package com.diluv.confluencia.database.dao;

import java.sql.SQLException;
import java.util.List;

import com.diluv.confluencia.database.record.FileProcessingStatus;
import com.diluv.confluencia.database.record.GameVersionRecord;
import com.diluv.confluencia.database.record.ProjectFileRecord;
import com.diluv.confluencia.database.sort.ProjectFileSort;

public interface FileDAO {

    boolean updateStatusById (FileProcessingStatus status, long id) throws SQLException;

    boolean updateStatusByStatus (FileProcessingStatus set, FileProcessingStatus where);

    List<ProjectFileRecord> findAllWhereStatusAndLimit (FileProcessingStatus status, int amount);

    List<ProjectFileRecord> getLatestFiles (int amount) throws SQLException;

    Long insertProjectFile (String name, String version, long size, String changelog, String sha512, String releaseType, String classifier, long projectId, long userId);

    ProjectFileRecord findOneProjectFileQueueByFileId (long fileId);

    List<ProjectFileRecord> findAllByGameSlugAndProjectTypeAndProjectSlug (String gameSlug, String projectTypeSlug, String projectSlug, boolean authorized, long page, int limit, ProjectFileSort sort);

    List<ProjectFileRecord> findAllByGameSlugAndProjectTypeAndProjectSlugWhereVersion (String gameSlug, String projectTypeSlug, String projectSlug, boolean authorized, long page, int limit, ProjectFileSort sort, String version);

    boolean insertProjectFileAntivirus (long projectFileId, String malware);

    List<GameVersionRecord> findAllGameVersionsByProjectFile (long projectFileId);

    boolean insertProjectFileGameVersions (long projectFileId, List<Long> versionIds);

    boolean existsByProjectIdAndVersion(long projectId, String version);
}