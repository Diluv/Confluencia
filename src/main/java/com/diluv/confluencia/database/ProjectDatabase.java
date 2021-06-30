package com.diluv.confluencia.database;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;

import com.diluv.confluencia.database.record.FeaturedProjectsEntity;
import com.diluv.confluencia.database.record.ProjectTypesEntity;
import com.diluv.confluencia.database.record.ProjectsEntity;
import com.diluv.confluencia.database.record.TagsEntity;
import com.diluv.confluencia.database.sort.Sort;
import com.diluv.confluencia.utils.DatabaseUtil;

public class ProjectDatabase {

    public long countAll (Session session, boolean released) {

        final String hql = "SELECT COUNT(*) FROM ProjectsEntity WHERE released = :released";

        return DatabaseUtil.findOne(session.createQuery(hql, Long.class)
            .setParameter("released", released)
            .getResultList(), 0L);
    }

    public long countAllByGameSlug (Session session, String gameSlug) {

        final String hql = "SELECT COUNT(*) FROM ProjectsEntity WHERE released = TRUE AND game.slug = :game_slug";

        return DatabaseUtil.findOne(session.createQuery(hql, Long.class)
            .setParameter("game_slug", gameSlug)
            .getResultList(), 0L);
    }

    public long countAllByGameSlugAndProjectTypeSlug (Session session, String gameSlug, String projectTypeSlug) {

        final String hql = "SELECT COUNT(*) FROM ProjectsEntity WHERE game.slug = :game_slug AND projectType.slug = :project_type_slug";

        return DatabaseUtil.findOne(session.createQuery(hql, Long.class)
            .setParameter("game_slug", gameSlug)
            .setParameter("project_type_slug", projectTypeSlug)
            .getResultList(), 0L);
    }

    public ProjectsEntity findOneProjectByProjectId (Session session, long id) {

        final String hql = "FROM ProjectsEntity WHERE id = :id";

        return DatabaseUtil.findOne(session.createQuery(hql, ProjectsEntity.class)
            .setParameter("id", id)
            .getResultList());
    }

    public ProjectsEntity findOneProject (Session session, String gameSlug, String projectTypeSlug, String projectSlug) {

        final String hql = "FROM ProjectsEntity WHERE game.slug = :game_slug AND projectType.slug = :project_type_slug AND slug = :project_slug";

        return DatabaseUtil.findOne(session.createQuery(hql, ProjectsEntity.class)
            .setParameter("game_slug", gameSlug)
            .setParameter("project_type_slug", projectTypeSlug)
            .setParameter("project_slug", projectSlug)
            .getResultList());
    }

    public ProjectTypesEntity findOneProjectTypeByGameSlugAndProjectTypeSlug (Session session, String gameSlug, String projectTypeSlug) {

        final String hql = "FROM ProjectTypesEntity WHERE game.slug = :game_slug AND slug = :project_type_slug";

        return DatabaseUtil.findOne(session.createQuery(hql, ProjectTypesEntity.class)
            .setParameter("game_slug", gameSlug)
            .setParameter("project_type_slug", projectTypeSlug)
            .getResultList());
    }

    public long countAllByUserId (Session session, long userId, boolean authorized) {

        final String hql = "SELECT COUNT(*) FROM ProjectsEntity p WHERE (:authorized = TRUE OR p.released = TRUE) AND (p.user.id = :user_id OR p.id IN (SELECT pa.project.id FROM ProjectAuthorsEntity pa WHERE pa.user.id = :user_id AND pa.project.released = TRUE))";

        return DatabaseUtil.findOne(session.createQuery(hql, Long.class)
            .setParameter("user_id", userId)
            .setParameter("authorized", authorized)
            .getResultList(), 0L);
    }

    public List<ProjectsEntity> findAllByUserId (Session session, long userId, boolean authorized, long page, int limit, Sort sort) {

        final String hql = "FROM ProjectsEntity p WHERE (:authorized = TRUE OR p.released = TRUE) AND (p.user.id = :user_id OR p.id IN (SELECT pa.project.id FROM ProjectAuthorsEntity pa WHERE pa.user.id = :user_id AND pa.project.released = TRUE)) ORDER BY :order_column " + sort.getOrder().name;

        return session.createQuery(hql, ProjectsEntity.class)
            .setParameter("authorized", authorized)
            .setParameter("user_id", userId)
            .setParameter("order_column", sort.getColumn())
            .setFirstResult((int) ((page - 1) * limit))
            .setMaxResults(limit)
            .getResultList();
    }

    public List<Long> findAllProjectsByProjectIds (Session session, Set<Long> projectIds) {

        final String hql = "SELECT id FROM ProjectsEntity WHERE id IN (:project_ids)";

        return session.createQuery(hql, Long.class)
            .setParameter("project_ids", projectIds)
            .getResultList();
    }

    public List<ProjectsEntity> findAllByGameAndProjectType (Session session, String gameSlug, String projectTypeSlug,
                                                             String search, long page, int limit, Sort sort) {

        return this.findAllByGameAndProjectType(session, gameSlug, projectTypeSlug, search, page, limit, sort, null, Collections.emptySet(), Collections.emptySet());
    }

    public List<ProjectsEntity> findAllByGameAndProjectType (Session session, String gameSlug, String projectTypeSlug,
                                                             String search, long page, int limit,
                                                             Sort sort, String gameVersion, Set<String> tags, Set<String> loaders) {

        final String hql = "FROM ProjectsEntity p WHERE p.released = TRUE AND p.name LIKE :search AND p.game.slug = :game_slug AND p.projectType.slug = :project_type_slug\n" +
            "AND (:skip_tags = TRUE OR p.id IN (SELECT pt.project.id FROM ProjectTagsEntity pt WHERE pt.project.game.slug = :game_slug AND pt.project.projectType.slug = :project_type_slug AND pt.tag.slug IN (:tags) GROUP BY (pt.project.id) HAVING COUNT(pt.project.id) = :tag_count))\n" +
            "AND (:skip_loaders = TRUE OR p.id IN (SELECT pfl.projectFile.project.id FROM ProjectFileLoadersEntity pfl WHERE pfl.projectFile.project.game.slug = :game_slug AND pfl.projectFile.project.projectType.slug = :project_type_slug AND pfl.loader.slug IN (:loaders) GROUP BY (pfl.projectFile.project.id) HAVING COUNT(pfl.projectFile.project.id) = :loader_count))\n" +
            "AND (:skip_version = TRUE OR p.id IN (SELECT pfgv.projectFile.project.id FROM ProjectFileGameVersionsEntity pfgv WHERE pfgv.gameVersion.version = :game_version AND pfgv.projectFile.project.id = p.id))\n" +
            "ORDER BY :order_column " + sort.getOrder().name;

        return session.createQuery(hql, ProjectsEntity.class)
            .setParameter("game_slug", gameSlug)
            .setParameter("project_type_slug", projectTypeSlug)
            .setParameter("search", "%" + search + "%")

            .setParameter("skip_tags", tags.size() == 0)
            .setParameter("tags", tags)
            .setParameter("tag_count", (long) tags.size())

            .setParameter("skip_loaders", loaders.size() == 0)
            .setParameter("loaders", loaders)
            .setParameter("loader_count", (long) loaders.size())

            .setParameter("skip_version", gameVersion == null)
            .setParameter("game_version", gameVersion)
            .setParameter("order_column", sort.getColumn())
            .setFirstResult((int) ((page - 1) * limit))
            .setMaxResults(limit)
            .getResultList();
    }

    public List<FeaturedProjectsEntity> findFeaturedProjects (Session session) {

        final String hql = "FROM FeaturedProjectsEntity";

        return session.createQuery(hql, FeaturedProjectsEntity.class)
            .setMaxResults(4)
            .getResultList();
    }

    public List<TagsEntity> findAllTagsByGameSlugAndProjectTypeSlug (Session session, String gameSlug, String projectTypeSlug) {

        final String hql = "FROM TagsEntity WHERE game.slug = :game_slug AND projectType.slug = :project_type_slug";

        return session.createQuery(hql, TagsEntity.class)
            .setParameter("game_slug", gameSlug)
            .setParameter("project_type_slug", projectTypeSlug)
            .getResultList();
    }

    public List<ProjectsEntity> findAllByReview (Session session, long page, int limit) {

        final String hql = "FROM ProjectsEntity p WHERE p.review = TRUE AND (p.released = TRUE OR (SELECT COUNT(*) FROM ProjectFilesEntity pf WHERE p.id = pf.project.id) > 0) ORDER BY p.createdAt ASC";

        return session.createQuery(hql, ProjectsEntity.class)
            .setFirstResult((int) ((page - 1) * limit))
            .setMaxResults(limit)
            .getResultList();
    }
}
