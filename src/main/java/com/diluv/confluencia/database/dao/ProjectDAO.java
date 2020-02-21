package com.diluv.confluencia.database.dao;

import java.util.List;

import com.diluv.confluencia.database.record.ProjectAuthorRecord;
import com.diluv.confluencia.database.record.ProjectRecord;
import com.diluv.confluencia.database.record.ProjectTypeRecord;

public interface ProjectDAO {

    boolean insertProject (String slug, String name, String summary, String description, long userId, String gameSlug, String projectTypeSlug);

    ProjectRecord findOneProjectByProjectId (long id);

    ProjectRecord findOneProjectByGameSlugAndProjectTypeSlugAndProjectSlug (String gameSlug, String projectTypeSlug, String projectSlug);

    List<ProjectRecord> findAllByUsername (String username);

    List<ProjectRecord> findAllByUsernameWhereAuthorized (String username);

    List<ProjectRecord> findAllProjectsByGameSlugAndProjectType (String gameSlug, String projectTypeSlug);

    List<ProjectTypeRecord> findAllProjectTypesByGameSlug (String gameSlug);

    ProjectTypeRecord findOneProjectTypeByGameSlugAndProjectTypeSlug (String gameSlug, String projectTypeSlug);

    List<ProjectAuthorRecord> findAllProjectAuthorsByProjectId (long projectId);
}
