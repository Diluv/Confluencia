package com.diluv.confluencia.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.diluv.confluencia.Confluencia;
import com.diluv.confluencia.database.dao.FileDAO;
import com.diluv.confluencia.database.record.FileQueueRecord;
import com.diluv.confluencia.database.record.ProjectFileRecord;
import com.diluv.confluencia.utils.SQLHandler;

public class FileDatabase implements FileDAO {

    private static final String FIND_ALL_WHERE_PENDING = SQLHandler.readFile("file_queue/findOneWherePending");
    private static final String UPDATE_STATUS_BY_ID = SQLHandler.readFile("file_queue/updateStatusById");

    private static final String FIND_ALL_PROJECTFILES_BY_GAMESLUG_AND_PROJECTYPESLUG_AND_PROJECTSLUG = SQLHandler.readFile("project_files/findAllByGameSlugAndProjectTypeAndProjectSlug");
    private static final String INSERT_PROJECT_FILE_QUEUE = SQLHandler.readFile("project_files/insertProjectFileQueue");

    @Override
    public List<FileQueueRecord> findAllWherePending (int amount) {

        final List<FileQueueRecord> fileQueueRecord = new ArrayList<>();
        try (PreparedStatement stmt = Confluencia.connection().prepareStatement(FIND_ALL_WHERE_PENDING)) {
            stmt.setInt(1, amount);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    fileQueueRecord.add(new FileQueueRecord(rs));
                }
            }
        }
        catch (final SQLException e) {
            e.printStackTrace();
        }
        return fileQueueRecord;
    }

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
    public List<FileQueueRecord> getLatestFileQueueRecord (int amount) throws SQLException {

        List<FileQueueRecord> fileQueueRecord;
        final Connection connection = Confluencia.connection();
        final int previousIsolationLevel = connection.getTransactionIsolation();
        try {
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            connection.setAutoCommit(false);

            fileQueueRecord = this.findAllWherePending(amount);

            if (fileQueueRecord.isEmpty()) {
                return fileQueueRecord;
            }

            final Long[] idList = fileQueueRecord.stream().map(FileQueueRecord::getId).toArray(Long[]::new);
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
    public List<ProjectFileRecord> findAllProjectFilesByGameSlugAndProjectType (String gameSlug, String projectTypeSlug, String projectSlug) {

        //TODO Add boolean param to include/exclude non-public files
        List<ProjectFileRecord> projects = new ArrayList<>();
        try (PreparedStatement stmt = Confluencia.connection().prepareStatement(FIND_ALL_PROJECTFILES_BY_GAMESLUG_AND_PROJECTYPESLUG_AND_PROJECTSLUG)) {
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
    public Long insertProjectFileQueue (String name, String changelog, long projectId, long userId) {

        try (PreparedStatement stmt = Confluencia.connection().prepareStatement(INSERT_PROJECT_FILE_QUEUE, new String[]{"id"})) {
            stmt.setString(1, name);
            stmt.setString(2, changelog);
            stmt.setLong(3, projectId);
            stmt.setLong(4, userId);

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
}
