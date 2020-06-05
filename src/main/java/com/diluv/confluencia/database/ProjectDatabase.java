package com.diluv.confluencia.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

import com.diluv.confluencia.Confluencia;
import com.diluv.confluencia.database.record.ProjectAuthorRecord;
import com.diluv.confluencia.database.record.ProjectLinkRecord;
import com.diluv.confluencia.database.record.ProjectRecord;
import com.diluv.confluencia.database.record.ProjectTypeRecord;
import com.diluv.confluencia.database.record.TagRecord;
import com.diluv.confluencia.database.sort.Sort;
import com.diluv.confluencia.utils.SQLHandler;

public class ProjectDatabase {

    private static final String COUNT_ALL = SQLHandler.readFile("project/countAll");
    private static final String COUNT_ALL_BY_GAME_SLUG = SQLHandler.readFile("project/countAllByGameSlug");
    private static final String INSERT_PROJECT = SQLHandler.readFile("project/insertProject");
    private static final String FIND_ALL_BY_USERNAME = SQLHandler.readFile("project/findAllByUsername");
    private static final String FIND_ALL_BY_PROJECT_IDS = SQLHandler.readFile("project/findAllByProjectIds");
    private static final String FIND_ALL_BY_GAMESLUG_AND_PROJECTYPESLUG = SQLHandler.readFile("project/findAllByGameSlugAndProjectTypeSlug");
    private static final String FIND_ALL_BY_GAMESLUG_AND_PROJECTYPESLUG_AND_VERSION = SQLHandler.readFile("project/findAllByGameSlugAndProjectTypeSlugAndVersion");
    private static final String FIND_ONE_BY_GAMESLUG_AND_PROJECTYPESLUG_AND_PROJECTSLUG = SQLHandler.readFile("project/findOneByGameSlugAndProjectTypeSlugAndProjectSlug");
    private static final String FIND_ONE_BY_PROJECTID = SQLHandler.readFile("project/findOneByProjectId");
    private static final String FIND_ALL_LINKS_BY_PROJECTID = SQLHandler.readFile("project/findAllLinksByProjectId");

    private static final String FIND_FEATURED_PROJECTS = SQLHandler.readFile("project/findFeaturedProjects");

    private static final String FIND_ALL_PROJECT_AUTHORS_BY_PROJECT_ID = SQLHandler.readFile("project_author/findAllByProjectId");

    private static final String FIND_ALL_PROJECTTYPES_BY_GAMESLUG = SQLHandler.readFile("project_types/findAllByGameSlug");
    private static final String FIND_ONE_PROJECTTYPES_BY_GAMESLUG_AND_PROJECTYPESLUG = SQLHandler.readFile("project_types/findOneByGameSlugAndProjectTypeSlug");
    private static final String FIND_DEFAULT_PROJECTTYPES_BY_GAMESLUG_AND_PROJECTYPESLUG = SQLHandler.readFile("project_types/findDefaultProjectTypesByGameSlug");
    private static final String FIND_ALL_TAGS_BY_GAMESLUG_AND_PROJECTYPESLUG = SQLHandler.readFile("tags/findAllTagsByGameSlugAndProjectTypeSlug");
    private static final String FIND_ALL_TAGS_BY_PROJECT_ID = SQLHandler.readFile("tags/findAllTagsByProjectId");

    public long countAll () {

        try (PreparedStatement stmt = Confluencia.connection().prepareStatement(COUNT_ALL)) {
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getLong(1);
                }
            }
        }
        catch (SQLException e) {
            Confluencia.LOGGER.error("Failed to run countAll.", e);
        }
        return 0;
    }

    public long countAllByGameSlug (String gameSlug) {

        try (PreparedStatement stmt = Confluencia.connection().prepareStatement(COUNT_ALL_BY_GAME_SLUG)) {
            stmt.setString(1, gameSlug);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getLong(1);
                }
            }
        }
        catch (SQLException e) {
            Confluencia.LOGGER.error("Failed to run countAllByGameSlug.", e);
        }
        return 0;
    }

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

    public List<ProjectRecord> findAllByUsername (String username, boolean authorized, long page, int limit, Sort sort) {

        List<ProjectRecord> projects = new ArrayList<>();
        try (PreparedStatement stmt = sort.getQuery(FIND_ALL_BY_USERNAME)) {
            stmt.setString(1, username);
            stmt.setBoolean(2, authorized);
            stmt.setLong(3, (page - 1) * limit);
            stmt.setLong(4, limit);
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

    public List<ProjectRecord> findAllProjectsByProjectIds (long[] projectIds) {

        StringJoiner b = new StringJoiner(",");
        for (int i = 0; i < projectIds.length; i++) {
            b.add("?");
        }
        List<ProjectRecord> projects = new ArrayList<>();
        try (PreparedStatement stmt = Confluencia.connection().prepareStatement(FIND_ALL_BY_PROJECT_IDS.replace("(?)", "(" + b.toString() + ")"))) {

            for (int i = 0; i < projectIds.length; i++) {
                stmt.setLong(i + 1, projectIds[i]);
            }
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    projects.add(new ProjectRecord(rs));
                }
            }
        }
        catch (SQLException e) {
            Confluencia.LOGGER.error("Failed to run findGameVersionsByGameSlugAndVersions games database script.", e);
        }
        return projects;
    }

    public List<ProjectRecord> findAllProjectsByGameSlugAndProjectType (String gameSlug,
                                                                        String projectTypeSlug,
                                                                        String search,
                                                                        long page,
                                                                        int limit,
                                                                        Sort sort) {

        List<ProjectRecord> projects = new ArrayList<>();

        try (PreparedStatement stmt = sort.getQuery(FIND_ALL_BY_GAMESLUG_AND_PROJECTYPESLUG)) {
            stmt.setString(1, gameSlug);
            stmt.setString(2, projectTypeSlug);
            stmt.setString(3, search);
            stmt.setLong(4, (page - 1) * limit);
            stmt.setLong(5, limit);
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

    public List<ProjectRecord> findAllProjectsByGameSlugAndProjectTypeAndVersion (String gameSlug,
                                                                                  String projectTypeSlug,
                                                                                  String search,
                                                                                  long page,
                                                                                  int limit,
                                                                                  Sort sort,
                                                                                  String version) {

        List<ProjectRecord> projects = new ArrayList<>();

        try (PreparedStatement stmt = sort.getQuery(FIND_ALL_BY_GAMESLUG_AND_PROJECTYPESLUG_AND_VERSION)) {
            stmt.setString(1, gameSlug);
            stmt.setString(2, projectTypeSlug);
            stmt.setString(3, version);
            stmt.setString(4, search);
            stmt.setLong(5, (page - 1) * limit);
            stmt.setLong(6, limit);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    projects.add(new ProjectRecord(rs));
                }
            }
        }
        catch (SQLException e) {
            Confluencia.LOGGER.error("Failed to run findAllProjectsByGameSlugAndProjectTypeAndVersion script for game {} and type {}.", gameSlug, projectTypeSlug, e);
        }
        return projects;
    }

    public List<ProjectRecord> findFeaturedProjects () {

        List<ProjectRecord> projects = new ArrayList<>();
        try (PreparedStatement stmt = Confluencia.connection().prepareStatement(FIND_FEATURED_PROJECTS)) {
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    projects.add(new ProjectRecord(rs));
                }
            }
        }
        catch (SQLException e) {
            Confluencia.LOGGER.error("Failed to run findFeaturedProjects script.", e);
        }
        return projects;
    }

    public List<ProjectAuthorRecord> findAllProjectAuthorsByProjectId (long projectId) {

        List<ProjectAuthorRecord> projectAuthors = new ArrayList<>();
        try (PreparedStatement stmt = Confluencia.connection().prepareStatement(FIND_ALL_PROJECT_AUTHORS_BY_PROJECT_ID)) {
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

    public List<ProjectTypeRecord> findAllProjectTypesByGameSlug (String gameSlug) {

        List<ProjectTypeRecord> projects = new ArrayList<>();
        try (PreparedStatement stmt = Confluencia.connection().prepareStatement(FIND_ALL_PROJECTTYPES_BY_GAMESLUG)) {
            stmt.setString(1, gameSlug);
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

    public String findDefaultProjectTypesByGameSlug (String gameSlug) {

        try (PreparedStatement stmt = Confluencia.connection().prepareStatement(FIND_DEFAULT_PROJECTTYPES_BY_GAMESLUG_AND_PROJECTYPESLUG)) {
            stmt.setString(1, gameSlug);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getString(1);
                }
            }
        }
        catch (SQLException e) {
            Confluencia.LOGGER.error("Failed to run findDefaultProjectTypesByGameSlug script for game {} .", gameSlug, e);
        }
        return null;
    }

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

    public List<TagRecord> findAllTagsByGameSlugAndProjectTypeSlug (String gameSlug, String projectTypeSlug) {

        List<TagRecord> tags = new ArrayList<>();
        try (PreparedStatement stmt = Confluencia.connection().prepareStatement(FIND_ALL_TAGS_BY_GAMESLUG_AND_PROJECTYPESLUG)) {
            stmt.setString(1, gameSlug);
            stmt.setString(2, projectTypeSlug);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    tags.add(new TagRecord(rs));
                }
            }
        }
        catch (SQLException e) {
            Confluencia.LOGGER.error("Failed to run findAllTagsByGameSlugAndProjectTypeSlug database script for game slug {}.", gameSlug, e);
        }
        return tags;
    }

    public List<TagRecord> findAllTagsByProjectId (long projectId) {

        List<TagRecord> tags = new ArrayList<>();
        try (PreparedStatement stmt = Confluencia.connection().prepareStatement(FIND_ALL_TAGS_BY_PROJECT_ID)) {
            stmt.setLong(1, projectId);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    tags.add(new TagRecord(rs));
                }
            }
        }
        catch (SQLException e) {
            Confluencia.LOGGER.error("Failed to run findAllTagsByProjectId database script for project id {}.", projectId, e);
        }
        return tags;
    }

    public List<ProjectLinkRecord> findAllLinksByProjectId (long id) {

        List<ProjectLinkRecord> projectLinks = new ArrayList<>();
        try (PreparedStatement stmt = Confluencia.connection().prepareStatement(FIND_ALL_LINKS_BY_PROJECTID)) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    projectLinks.add(new ProjectLinkRecord(rs));
                }
            }
        }
        catch (SQLException e) {
            Confluencia.LOGGER.error("Failed to run findAllLinksByProjectId database script for projectId {}.", id, e);
        }
        return projectLinks;
    }
}
