package com.diluv.confluencia.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.diluv.confluencia.Confluencia;
import com.diluv.confluencia.database.dao.FileDAO;
import com.diluv.confluencia.database.record.ProjectFileRecord;
import com.diluv.confluencia.utils.SQLHandler;

public class FileDatabase implements FileDAO {

    private static final String INSERT_PROJECT_FILE = SQLHandler.readFile("project_files/insertProjectFile");
    private static final String UPDATE_STATUS_BY_ID = SQLHandler.readFile("project_files/updateStatusById");
    private static final String FIND_X_WHERE_PENDING = SQLHandler.readFile("project_files/findXWherePending");
    private static final String FIND_ONE_PROJECT_FILE_QUEUE_BY_FILE_ID = SQLHandler.readFile("project_files/findOneByFileId");
    private static final String FIND_ALL_BY_GAMESLUG_AND_PROJECTYPESLUG_AND_PROJECTSLUG = SQLHandler.readFile("project_files/findAllByGameSlugAndProjectTypeAndProjectSlug");
    private static final String FIND_ALL_BY_GAMESLUG_AND_PROJECTYPESLUG_AND_PROJECTSLUG_AUTHORIZED = SQLHandler.readFile("project_files/findAllByGameSlugAndProjectTypeAndProjectSlugWhereAuthorized");

    private static final String INSERT_PROJECT_FILE_HASH = SQLHandler.readFile("project_files/insertProjectFileHash");

    @Override
    public boolean updateFileQueueStatusById (long id) throws SQLException {

        try (PreparedStatement stmt = Confluencia.connection().prepareStatement(UPDATE_STATUS_BY_ID)) {
            stmt.setLong(1, id);
            if (stmt.executeUpdate() == 1) {
                return true;
            }
        }
        catch (final SQLException e) {
            Confluencia.connection().rollback();
            throw e;
        }
        return false;
    }

    @Override
    public List<ProjectFileRecord> findAllWherePending (int amount) {

        final List<ProjectFileRecord> fileQueueRecord = new ArrayList<>();
        try (PreparedStatement stmt = Confluencia.connection().prepareStatement(FIND_X_WHERE_PENDING)) {
            stmt.setInt(1, amount);
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
    public List<ProjectFileRecord> getLatestFileQueueRecord (int amount) throws SQLException {

        List<ProjectFileRecord> fileQueueRecord;
        final Connection connection = Confluencia.connection();
        final int previousIsolationLevel = connection.getTransactionIsolation();
        try {
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            connection.setAutoCommit(false);

            fileQueueRecord = this.findAllWherePending(amount);

            if (fileQueueRecord.isEmpty()) {
                return fileQueueRecord;
            }

            final Long[] idList = fileQueueRecord.stream().map(ProjectFileRecord::getId).toArray(Long[]::new);
            for (final Long id : idList) {
                if (!this.updateFileQueueStatusById(id)) {
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
    public Long insertProjectFile (String name, long size, String changelog, long projectId, long userId) {

        try (PreparedStatement stmt = Confluencia.connection().prepareStatement(INSERT_PROJECT_FILE, new String[]{"id"})) {
            stmt.setString(1, name);
            stmt.setLong(2, size);
            stmt.setString(3, changelog);
            stmt.setLong(4, projectId);
            stmt.setLong(5, userId);

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
            Confluencia.LOGGER.error("Failed to insert project file queue {}.", e);
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
    public List<ProjectFileRecord> findAllProjectFilesByGameSlugAndProjectTypeAndProjectSlug (String gameSlug, String projectTypeSlug, String projectSlug) {

        //TODO Add boolean param to include/exclude non-public files
        List<ProjectFileRecord> projects = new ArrayList<>();
        try (PreparedStatement stmt = Confluencia.connection().prepareStatement(FIND_ALL_BY_GAMESLUG_AND_PROJECTYPESLUG_AND_PROJECTSLUG)) {
            stmt.setString(1, gameSlug);
            stmt.setString(2, projectTypeSlug);
            stmt.setString(3, projectSlug);

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
    public List<ProjectFileRecord> findAllProjectFilesByGameSlugAndProjectTypeAndProjectSlugAuthorized (String gameSlug, String projectTypeSlug, String projectSlug) {

        //TODO Add boolean param to include/exclude non-public files
        List<ProjectFileRecord> projects = new ArrayList<>();
        try (PreparedStatement stmt = Confluencia.connection().prepareStatement(FIND_ALL_BY_GAMESLUG_AND_PROJECTYPESLUG_AND_PROJECTSLUG_AUTHORIZED)) {
            stmt.setString(1, gameSlug);
            stmt.setString(2, projectTypeSlug);
            stmt.setString(3, projectSlug);

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
    public boolean insertProjectFileHash (long id, String hash) {

        try (PreparedStatement stmt = Confluencia.connection().prepareStatement(INSERT_PROJECT_FILE_HASH)) {
            stmt.setLong(1, id);
            stmt.setString(2, hash);
            if (stmt.executeUpdate() == 1) {
                return true;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
