package com.diluv.confluencia.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.diluv.confluencia.Confluencia;
import com.diluv.confluencia.database.dao.ProjectDAO;
import com.diluv.confluencia.database.record.CategoryRecord;
import com.diluv.confluencia.database.record.ProjectAuthorRecord;
import com.diluv.confluencia.database.record.ProjectRecord;
import com.diluv.confluencia.database.record.ProjectTypeRecord;
import com.diluv.confluencia.utils.Pagination;
import com.diluv.confluencia.utils.SQLHandler;

public class ProjectDatabase implements ProjectDAO {

    private static final String INSERT_PROJECT = SQLHandler.readFile("project/insertProject");
    private static final String FIND_ALL_BY_USERNAME = SQLHandler.readFile("project/findAllByUsername");
    private static final String FIND_ALL_BY_USERNAME_AUTHORIZED = SQLHandler.readFile("project/findAllByUsernameAuthorized");
    private static final String FIND_ALL_BY_GAMESLUG_AND_PROJECTYPESLUG = SQLHandler.readFile("project/findAllByGameSlugAndProjectTypeSlug");
    private static final String FIND_ONE_BY_GAMESLUG_AND_PROJECTYPESLUG_AND_PROJECTSLUG = SQLHandler.readFile("project/findOneByGameSlugAndProjectTypeSlugAndProjectSlug");
    private static final String FIND_ONE_BY_PROJECTID = SQLHandler.readFile("project/findOneByProjectId");

    private static final String FIND_ALL_BY_PROJECT_ID = SQLHandler.readFile("project_author/findAllByProjectId");

    private static final String FIND_ALL_PROJECTTYPES_BY_GAMESLUG = SQLHandler.readFile("project_types/findAllByGameSlug");
    private static final String FIND_ONE_PROJECTTYPES_BY_GAMESLUG_AND_PROJECTYPESLUG = SQLHandler.readFile("project_types/findOneByGameSlugAndProjectTypeSlug");

    private static final String FIND_ALL_CATEGORIES_BY_GAMESLUG_AND_PROJECTYPESLUG = SQLHandler.readFile("category/findAllCategoriesByGameSlugAndProjectTypeSlug");

    @Override
    public boolean insertProject (String slug, String name, String summary, String description, long userId, String gameSlug, String projectTypeSlug) {

        try (PreparedStatement stmt = Confluencia.connection().prepareStatement(INSERT_PROJECT)) {
            stmt.setString(1, slug);
            stmt.setString(2, name);
            stmt.setString(3, summary);
            stmt.setString(4, description);
            stmt.setLong(5, userId);
            stmt.setString(6, gameSlug);
            stmt.setString(7, projectTypeSlug);

            return stmt.executeUpdate() == 1;
        }
        catch (SQLException e) {
            Confluencia.LOGGER.error("Failed to run insertProject script for project {} with name {} by {}.", slug, name, userId, e);
        }
        return false;
    }

    @Override
    public ProjectRecord findOneProjectByProjectId (long id) {

        try (PreparedStatement stmt = Confluencia.connection().prepareStatement(FIND_ONE_BY_PROJECTID)) {
            stmt.setLong(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new ProjectRecord(rs);
                }
            }
        }
        catch (SQLException e) {
            Confluencia.LOGGER.error("Failed to run findOneProjectByProjectId script for id {}.", id, e);
        }
        return null;
    }

    @Override
    public ProjectRecord findOneProjectByGameSlugAndProjectTypeSlugAndProjectSlug (String gameSlug, String projectTypeSlug, String projectSlug) {

        try (PreparedStatement stmt = Confluencia.connection().prepareStatement(FIND_ONE_BY_GAMESLUG_AND_PROJECTYPESLUG_AND_PROJECTSLUG)) {
            stmt.setString(1, gameSlug);
            stmt.setString(2, projectTypeSlug);
            stmt.setString(3, projectSlug);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new ProjectRecord(rs);
                }
            }
        }
        catch (SQLException e) {
            Confluencia.LOGGER.error("Failed to run findOneProjectByGameSlugAndProjectTypeSlugAndProjectSlug script for game {}, type {}, and project {}.", gameSlug, projectTypeSlug, projectSlug, e);
        }
        return null;
    }

    @Override
    public List<ProjectRecord> findAllByUsername (String username, Pagination cursor, int limit) {

        List<ProjectRecord> projects = new ArrayList<>();
        try (PreparedStatement stmt = Confluencia.connection().prepareStatement(FIND_ALL_BY_USERNAME)) {
            stmt.setString(1, username);
            stmt.setLong(2, cursor.offset);
            stmt.setLong(3, limit);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    projects.add(new ProjectRecord(rs));
                }
            }
        }
        catch (SQLException e) {
            Confluencia.LOGGER.error("Failed to run findAllByUsername database script for user {}.", username, e);
        }
        return projects;
    }

    @Override
    public List<ProjectRecord> findAllByUsernameWhereAuthorized (String username, Pagination cursor, int limit) {

        List<ProjectRecord> projects = new ArrayList<>();
        try (PreparedStatement stmt = Confluencia.connection().prepareStatement(FIND_ALL_BY_USERNAME_AUTHORIZED)) {
            stmt.setString(1, username);
            stmt.setLong(2, cursor.offset);
            stmt.setLong(3, limit);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    projects.add(new ProjectRecord(rs));
                }
            }
        }
        catch (SQLException e) {
            Confluencia.LOGGER.error("Failed to run findAllByUsernameWhereAuthorized database script for user {}.", username, e);
        }
        return projects;
    }

    @Override
    public List<ProjectRecord> findAllProjectsByGameSlugAndProjectType (String gameSlug, String projectTypeSlug, Pagination cursor, int limit) {

        List<ProjectRecord> projects = new ArrayList<>();
        try (PreparedStatement stmt = Confluencia.connection().prepareStatement(FIND_ALL_BY_GAMESLUG_AND_PROJECTYPESLUG)) {
            stmt.setString(1, gameSlug);
            stmt.setString(2, projectTypeSlug);
            stmt.setLong(3, cursor.offset);
            stmt.setLong(4, limit);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    projects.add(new ProjectRecord(rs));
                }
            }
        }
        catch (SQLException e) {
            Confluencia.LOGGER.error("Failed to run findAllProjectsByGameSlugAndProjectType script for game {} and type {}.", gameSlug, projectTypeSlug, e);
        }
        return projects;
    }

    @Override
    public List<ProjectAuthorRecord> findAllProjectAuthorsByProjectId (long projectId) {

        List<ProjectAuthorRecord> projectAuthors = new ArrayList<>();
        try (PreparedStatement stmt = Confluencia.connection().prepareStatement(FIND_ALL_BY_PROJECT_ID)) {
            stmt.setLong(1, projectId);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    projectAuthors.add(new ProjectAuthorRecord(rs));
                }
            }
        }
        catch (SQLException e) {
            Confluencia.LOGGER.error("Failed to run findAllByProjectId script for project id {}.", projectId, e);
        }
        return projectAuthors;
    }

    @Override
    public List<ProjectTypeRecord> findAllProjectTypesByGameSlug (String gameSlug, Pagination cursor, int limit) {

        List<ProjectTypeRecord> projects = new ArrayList<>();
        try (PreparedStatement stmt = Confluencia.connection().prepareStatement(FIND_ALL_PROJECTTYPES_BY_GAMESLUG)) {
            stmt.setString(1, gameSlug);
            stmt.setLong(2, cursor.offset);
            stmt.setLong(3, limit);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    projects.add(new ProjectTypeRecord(rs));
                }
            }
        }
        catch (SQLException e) {
            Confluencia.LOGGER.error("Failed to run findAllProjectTypesByGameSlug database script for game slug {}.", gameSlug, e);
        }
        return projects;
    }

    @Override
    public ProjectTypeRecord findOneProjectTypeByGameSlugAndProjectTypeSlug (String gameSlug, String projectTypeSlug) {

        try (PreparedStatement stmt = Confluencia.connection().prepareStatement(FIND_ONE_PROJECTTYPES_BY_GAMESLUG_AND_PROJECTYPESLUG)) {
            stmt.setString(1, gameSlug);
            stmt.setString(2, projectTypeSlug);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new ProjectTypeRecord(rs);
                }
            }
        }
        catch (SQLException e) {
            Confluencia.LOGGER.error("Failed to run findOneProjectTypeByGameSlugAndProjectTypeSlug script for game {} and type {}.", gameSlug, projectTypeSlug, e);
        }
        return null;
    }

    @Override
    public List<CategoryRecord> findAllCategoriesByGameSlugAndProjectTypeSlug (String gameSlug, String projectTypeSlug, Pagination cursor, int limit) {

        List<CategoryRecord> categories = new ArrayList<>();
        try (PreparedStatement stmt = Confluencia.connection().prepareStatement(FIND_ALL_CATEGORIES_BY_GAMESLUG_AND_PROJECTYPESLUG)) {
            stmt.setString(1, gameSlug);
            stmt.setString(2, projectTypeSlug);
            stmt.setLong(3, cursor.offset);
            stmt.setLong(4, limit);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    categories.add(new CategoryRecord(rs));
                }
            }
        }
        catch (SQLException e) {
            Confluencia.LOGGER.error("Failed to run findAllProjectTypesByGameSlug database script for game slug {}.", gameSlug, e);
        }
        return categories;
    }
}
