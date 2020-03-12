package com.diluv.confluencia.database.dao;

import java.util.List;

import com.diluv.confluencia.database.record.CategoryRecord;
import com.diluv.confluencia.database.record.ProjectAuthorRecord;
import com.diluv.confluencia.database.record.ProjectRecord;
import com.diluv.confluencia.database.record.ProjectTypeRecord;

public interface ProjectDAO {

    boolean insertProject (String slug, String name, String summary, String description, long userId, String gameSlug, String projectTypeSlug);

    ProjectRecord findOneProjectByProjectId (long id);

    ProjectRecord findOneProjectByGameSlugAndProjectTypeSlugAndProjectSlug (String gameSlug, String projectTypeSlug, String projectSlug);

    List<ProjectRecord> findAllByUsername (String username, long page, int limit);

    List<ProjectRecord> findAllByUsernameWhereAuthorized (String username, long page, int limit);

    List<ProjectRecord> findAllProjectsByGameSlugAndProjectType (String gameSlug, String projectTypeSlug, long page, int limit);

    List<ProjectRecord> findFeaturedProjects ();

    List<ProjectAuthorRecord> findAllProjectAuthorsByProjectId (long projectId);

    List<ProjectTypeRecord> findAllProjectTypesByGameSlug (String gameSlug);

    ProjectTypeRecord findOneProjectTypeByGameSlugAndProjectTypeSlug (String gameSlug, String projectTypeSlug);

    List<CategoryRecord> findAllCategoriesByGameSlugAndProjectTypeSlug (String gameSlug, String projectTypeSlug);

    List<CategoryRecord> findAllCategoriesByProjectId (long projectId);
}
