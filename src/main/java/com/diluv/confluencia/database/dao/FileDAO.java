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

    List<ProjectFileRecord> findAllByProjectId (long projectId, boolean authorized, long page, int limit, ProjectFileSort sort);

    List<ProjectFileRecord> findAllByProjectIdWhereVersion (long projectId, boolean authorized, long page, int limit, ProjectFileSort sort, String version);

    boolean insertProjectFileAntivirus (long projectFileId, String malware);

    List<Long> findAllProjectDependenciesById (long projectFileId);

    List<GameVersionRecord> findAllGameVersionsById (long projectFileId);

    boolean insertProjectFileDependency (long projectFileId, List<Long> dependencyIds);

    boolean insertProjectFileGameVersions (long projectFileId, List<Long> versionIds);

    boolean existsByProjectIdAndVersion (long projectId, String version);
}