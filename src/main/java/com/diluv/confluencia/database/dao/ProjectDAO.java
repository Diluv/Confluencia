package com.diluv.confluencia.database.dao;

import java.util.List;

import com.diluv.confluencia.database.record.CategoryRecord;
import com.diluv.confluencia.database.record.ProjectAuthorRecord;
import com.diluv.confluencia.database.record.ProjectLinkRecord;
import com.diluv.confluencia.database.record.ProjectRecord;
import com.diluv.confluencia.database.record.ProjectTypeRecord;
import com.diluv.confluencia.database.sort.ProjectSort;

public interface ProjectDAO {

    boolean insertProject (String slug, String name, String summary, String description, long userId, String gameSlug, String projectTypeSlug);

    ProjectRecord findOneProjectByProjectId (long id);

    ProjectRecord findOneProjectByGameSlugAndProjectTypeSlugAndProjectSlug (String gameSlug, String projectTypeSlug, String projectSlug);

    List<ProjectRecord> findAllByUsername (String username, boolean authorized, long page, int limit, ProjectSort sort);

    List<ProjectRecord> findAllProjectsByGameSlugAndProjectType (String gameSlug, String projectTypeSlug, long page, int limit, ProjectSort sort);

    List<ProjectRecord> findAllProjectsByGameSlugAndProjectTypeAndVersion (String gameSlug, String projectTypeSlug, long page, int limit, ProjectSort sort, String version);

    List<ProjectRecord> findFeaturedProjects ();

    List<ProjectAuthorRecord> findAllProjectAuthorsByProjectId (long projectId);

    List<ProjectTypeRecord> findAllProjectTypesByGameSlug (String gameSlug);

    ProjectTypeRecord findOneProjectTypeByGameSlugAndProjectTypeSlug (String gameSlug, String projectTypeSlug);

    List<CategoryRecord> findAllCategoriesByGameSlugAndProjectTypeSlug (String gameSlug, String projectTypeSlug);

    List<CategoryRecord> findAllCategoriesByProjectId (long projectId);

    List<ProjectLinkRecord> findAllLinksByProjectId (long id);
}
