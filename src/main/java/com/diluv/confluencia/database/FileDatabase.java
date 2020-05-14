package com.diluv.confluencia.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.diluv.confluencia.Confluencia;
import com.diluv.confluencia.database.dao.FileDAO;
import com.diluv.confluencia.database.record.FileProcessingStatus;
import com.diluv.confluencia.database.record.GameVersionRecord;
import com.diluv.confluencia.database.record.ProjectFileRecord;
import com.diluv.confluencia.database.sort.ProjectFileSort;
import com.diluv.confluencia.utils.SQLHandler;

public class FileDatabase implements FileDAO {

    private static final String INSERT_PROJECT_FILE = SQLHandler.readFile("project_files/insertProjectFile");
    private static final String UPDATE_STATUS_BY_ID = SQLHandler.readFile("project_files/updateStatusById");
    private static final String UPDATE_STATUS_BY_STATUS = SQLHandler.readFile("project_files/updateStatusByStatus");
    private static final String FIND_ALL_WHERE_STATUS_AND_LIMIT = SQLHandler.readFile("project_files/findAllWhereStatusAndLimit");
    private static final String FIND_ONE_PROJECT_FILE_QUEUE_BY_FILE_ID = SQLHandler.readFile("project_files/findOneByFileId");
    private static final String FIND_ALL_BY_PROJECT_ID = SQLHandler.readFile("project_files/findAllByProjectId");
    private static final String FIND_ALL_BY_PROJECT_ID_AND_VERSION = SQLHandler.readFile("project_files/findAllByProjectIdAndVersion");

    private static final String INSERT_PROJECT_FILE_ANTIVIRUS = SQLHandler.readFile("project_files/insertProjectFileAntivirus");

    private static final String INSERT_PROJECT_FILE_DEPENDENCIES = SQLHandler.readFile("project_files/insertProjectFileDependencies");
    private static final String INSERT_PROJECT_FILE_GAME_VERSION = SQLHandler.readFile("project_files/insertProjectFileGameVersions");
    private static final String FIND_ALL_DEPENDENCIES_BY_ID = SQLHandler.readFile("project_files/findAllDependenciesById");
    private static final String FIND_ALL_GAME_VERSIONS_BY_ID = SQLHandler.readFile("project_files/findAllGameVersionsById");

    private static final String EXISTS_BY_PROJECT_ID_AND_VERSION = SQLHandler.readFile("project_files/existsByProjectIdAndVersion");

    @Override
    public boolean updateStatusById (FileProcessingStatus status, long id) throws SQLException {

        try (PreparedStatement stmt = Confluencia.connection().prepareStatement(UPDATE_STATUS_BY_ID)) {
            stmt.setLong(1, status.ordinal());
            stmt.setLong(2, id);
            if (stmt.executeUpdate() == 1) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean updateStatusByStatus (FileProcessingStatus set, FileProcessingStatus where) {

        try (PreparedStatement stmt = Confluencia.connection().prepareStatement(UPDATE_STATUS_BY_STATUS)) {
            stmt.setLong(1, set.ordinal());
            stmt.setLong(2, where.ordinal());
            stmt.executeUpdate();
            return true;
        }
        catch (final SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public List<ProjectFileRecord> findAllWhereStatusAndLimit (FileProcessingStatus status, int amount) {

        final List<ProjectFileRecord> fileQueueRecord = new ArrayList<>();
        try (PreparedStatement stmt = Confluencia.connection().prepareStatement(FIND_ALL_WHERE_STATUS_AND_LIMIT)) {
            stmt.setInt(1, status.ordinal());
            stmt.setInt(2, amount);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    fileQueueRecord.add(new ProjectFileRecord(rs));
                }
            }
        }
        catch (final SQLException e) {
            e.printStackTrace();
        }
        return fileQueueRecord;
    }

    @Override
    public List<ProjectFileRecord> getLatestFiles (int amount) throws SQLException {

        List<ProjectFileRecord> fileQueueRecord;
        final Connection connection = Confluencia.connection();
        final int previousIsolationLevel = connection.getTransactionIsolation();
        try {
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            connection.setAutoCommit(false);

            fileQueueRecord = this.findAllWhereStatusAndLimit(FileProcessingStatus.PENDING, amount);

            if (fileQueueRecord.isEmpty()) {
                return fileQueueRecord;
            }

            final Long[] idList = fileQueueRecord.stream().map(ProjectFileRecord::getId).toArray(Long[]::new);
            for (final Long id : idList) {
                if (!this.updateStatusById(FileProcessingStatus.RUNNING, id)) {
                    // TODO didn't work but didnt throw an exception
                }
            }
            connection.commit();
        } finally {
            connection.setAutoCommit(true);
            connection.setTransactionIsolation(previousIsolationLevel);
        }
        return fileQueueRecord;
    }

    @Override
    public Long insertProjectFile (String name, String version, long size, String changelog, String sha512, String releaseType, String classifier, long projectId, long userId) {

        try (PreparedStatement stmt = Confluencia.connection().prepareStatement(INSERT_PROJECT_FILE, new String[]{"id"})) {
            stmt.setString(1, name);
            stmt.setString(2, version);
            stmt.setLong(3, size);
            stmt.setString(4, changelog);
            stmt.setString(5, sha512);
            stmt.setString(6, releaseType);
            stmt.setString(7, classifier);
            stmt.setLong(8, projectId);
            stmt.setLong(9, userId);

            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating project file failed, no rows affected.");
            }

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getLong(1);
                }
                else {
                    throw new SQLException("Creating project file failed, no ID obtained.");
                }
            }
        }
        catch (SQLException e) {
            Confluencia.LOGGER.error("Failed to insert project file {}.", e);
        }
        return null;
    }

    @Override
    public ProjectFileRecord findOneProjectFileQueueByFileId (long fileId) {

        try (PreparedStatement stmt = Confluencia.connection().prepareStatement(FIND_ONE_PROJECT_FILE_QUEUE_BY_FILE_ID)) {
            stmt.setLong(1, fileId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new ProjectFileRecord(rs);
                }
            }
        }
        catch (SQLException e) {
            Confluencia.LOGGER.error("Failed to project file queue by id.", e);
        }
        return null;
    }

    @Override
    public List<ProjectFileRecord> findAllByProjectId (long projectId, boolean authorized, long page, int limit, ProjectFileSort sort) {

        List<ProjectFileRecord> projects = new ArrayList<>();

        try (PreparedStatement stmt = sort.getQuery(FIND_ALL_BY_PROJECT_ID)) {
            stmt.setLong(1, projectId);
            stmt.setBoolean(2, authorized);
            stmt.setLong(3, (page - 1) * limit);
            stmt.setLong(4, limit);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    projects.add(new ProjectFileRecord(rs));
                }
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return projects;
    }

    @Override
    public List<ProjectFileRecord> findAllByProjectIdWhereVersion (long projectId, boolean authorized, long page, int limit, ProjectFileSort sort, String version) {

        List<ProjectFileRecord> projects = new ArrayList<>();

        try (PreparedStatement stmt = sort.getQuery(FIND_ALL_BY_PROJECT_ID_AND_VERSION)) {
            stmt.setLong(1, projectId);
            stmt.setBoolean(2, authorized);
            stmt.setString(3, version);
            stmt.setLong(4, (page - 1) * limit);
            stmt.setLong(5, limit);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    projects.add(new ProjectFileRecord(rs));
                }
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return projects;
    }

    @Override
    public boolean insertProjectFileAntivirus (long projectFileId, String malware) {

        try (PreparedStatement stmt = Confluencia.connection().prepareStatement(INSERT_PROJECT_FILE_ANTIVIRUS)) {
            stmt.setLong(1, projectFileId);
            stmt.setString(2, malware);
            if (stmt.executeUpdate() == 1) {
                return true;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Long> findAllProjectDependenciesById (long projectFileId) {

        List<Long> projectId = new ArrayList<>();
        try (PreparedStatement stmt = Confluencia.connection().prepareStatement(FIND_ALL_DEPENDENCIES_BY_ID)) {
            stmt.setLong(1, projectFileId);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    projectId.add(rs.getLong(1));
                }
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return projectId;
    }

    @Override
    public List<GameVersionRecord> findAllGameVersionsById (long projectFileId) {

        List<GameVersionRecord> gameVersions = new ArrayList<>();
        try (PreparedStatement stmt = Confluencia.connection().prepareStatement(FIND_ALL_GAME_VERSIONS_BY_ID)) {
            stmt.setLong(1, projectFileId);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    gameVersions.add(new GameVersionRecord(rs));
                }
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return gameVersions;
    }

    @Override
    public boolean insertProjectFileDependency (long projectFileId, List<Long> dependencyIds) {

        try (PreparedStatement stmt = Confluencia.connection().prepareStatement(INSERT_PROJECT_FILE_DEPENDENCIES)) {
            for (Long dependency : dependencyIds) {
                stmt.setLong(1, projectFileId);
                stmt.setLong(2, dependency);
                stmt.addBatch();
            }
            stmt.executeBatch();
            return true;
        }
        catch (SQLException e) {
            Confluencia.LOGGER.error("Failed to insertProjectFileDependency.", e);
        }
        return false;
    }

    @Override
    public boolean insertProjectFileGameVersions (long projectFileId, List<Long> versionIds) {

        try (PreparedStatement stmt = Confluencia.connection().prepareStatement(INSERT_PROJECT_FILE_GAME_VERSION)) {
            for (Long version : versionIds) {
                stmt.setLong(1, projectFileId);
                stmt.setLong(2, version);
                stmt.addBatch();
            }
            stmt.executeBatch();
            return true;
        }
        catch (SQLException e) {
            Confluencia.LOGGER.error("Failed to insertProjectFileGameVersions.", e);
        }
        return false;
    }

    @Override
    public boolean existsByProjectIdAndVersion (long projectId, String version) {

        try (PreparedStatement stmt = Confluencia.connection().prepareStatement(EXISTS_BY_PROJECT_ID_AND_VERSION)) {
            stmt.setLong(1, projectId);
            stmt.setString(2, version);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        }
        catch (SQLException e) {
            Confluencia.LOGGER.error("Failed to existsByProjectIdAndVersion.", e);
        }
        return false;
    }
}
