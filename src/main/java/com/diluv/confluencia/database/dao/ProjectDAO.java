package com.diluv.confluencia.database.dao;

import com.diluv.confluencia.database.record.*;
import com.diluv.confluencia.database.sort.ProjectSort;

import java.util.List;

public interface ProjectDAO {

    long countAll ();

    long countAllByGameSlug (String gameSlug);

    boolean insertProject (String slug, String name, String summary, String description, long userId, String gameSlug, String projectTypeSlug);

    ProjectRecord findOneProjectByProjectId (long id);

    ProjectRecord findOneProjectByGameSlugAndProjectTypeSlugAndProjectSlug (String gameSlug, String projectTypeSlug, String projectSlug);

    List<ProjectRecord> findAllByUsername (String username, boolean authorized, long page, int limit, ProjectSort sort);

    List<ProjectRecord> findAllProjectsByProjectIds (long[] projectIds);

    List<ProjectRecord> findAllProjectsByGameSlugAndProjectType (String gameSlug, String projectTypeSlug, long page, int limit, ProjectSort sort);

    List<ProjectRecord> findAllProjectsByGameSlugAndProjectTypeAndVersion (String gameSlug, String projectTypeSlug, long page, int limit, ProjectSort sort, String version);

    List<ProjectRecord> findFeaturedProjects ();

    List<ProjectAuthorRecord> findAllProjectAuthorsByProjectId (long projectId);

    List<ProjectTypeRecord> findAllProjectTypesByGameSlug (String gameSlug);

    ProjectTypeRecord findOneProjectTypeByGameSlugAndProjectTypeSlug (String gameSlug, String projectTypeSlug);

    List<TagRecord> findAllTagsByGameSlugAndProjectTypeSlug (String gameSlug, String projectTypeSlug);

    List<TagRecord> findAllTagsByProjectId (long projectId);

    List<ProjectLinkRecord> findAllLinksByProjectId (long id);
}
