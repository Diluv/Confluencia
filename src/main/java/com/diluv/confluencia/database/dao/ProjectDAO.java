package com.diluv.confluencia.database.dao;

import java.util.List;

import com.diluv.confluencia.database.record.ProjectRecord;
import com.diluv.confluencia.database.record.ProjectTypeRecord;

public interface ProjectDAO {

    List<ProjectRecord> findAllByUsername (String username);

    List<ProjectTypeRecord> findAllProjectTypesByGameSlug (String gameSlug);

    ProjectTypeRecord findOneProjectTypeByGameSlugAndProjectTypeSlug (String gameSlug, String projectTypeSlug);

    List<ProjectRecord> findAllProjectsByGameSlugAndProjectType (String gameSlug, String projectTypeSlug);

    ProjectRecord findOneProjectByGameSlugAndProjectTypeSlugAndProjectSlug (String gameSlug, String projectTypeSlug, String projectSlug);

    boolean insertProject (String slug, String name, String summary, String description, long userId, String gameSlug, String projectTypeSlug);
}
