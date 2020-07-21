package com.diluv.confluencia.database;

import com.diluv.confluencia.Confluencia;
import com.diluv.confluencia.database.record.*;
import com.diluv.confluencia.database.sort.Order;
import com.diluv.confluencia.database.sort.Sort;

import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class ProjectDatabase {

    public long countAllByGameSlug (String gameSlug) {

        try {
            return Confluencia.getQuery((session, cb) -> {

                CriteriaQuery<Long> q = cb.createQuery(Long.class);
                ParameterExpression<String> gameSlugParam = cb.parameter(String.class);

                Root<ProjectsEntity> entity = q.from(ProjectsEntity.class);
                Root<GamesEntity> entityGame = q.from(GamesEntity.class);

                q.select(cb.count(entity));

                q.where(cb.like(entityGame.get("slug"), gameSlugParam));

                TypedQuery<Long> query = session.createQuery(q);
                query.setParameter(gameSlugParam, "%" + gameSlug + "%");
                return query.getSingleResult();
            });
        }
        catch (Exception e) {
            return 0;
        }
    }

    public long countAllByGameSlugAndProjectTypeSlug (String gameSlug, String projectTypeSlug) {

        try {
            return Confluencia.getQuery((session, cb) -> {
                CriteriaQuery<Long> q = cb.createQuery(Long.class);

                ParameterExpression<ProjectTypesEntity> projectTypeSlugParam = cb.parameter(ProjectTypesEntity.class);

                Root<ProjectsEntity> entity = q.from(ProjectsEntity.class);
                q.select(cb.count(entity));
                q.where(cb.equal(entity.get("projectType"), projectTypeSlugParam));

                TypedQuery<Long> query = session.createQuery(q);
                query.setParameter(projectTypeSlugParam, new ProjectTypesEntity(new GamesEntity(gameSlug), projectTypeSlug));
                return query.getSingleResult();
            });
        }
        catch (Exception e) {
            return 0;
        }
    }

    public ProjectsEntity findOneProjectByProjectId (long id) {

        try {
            return Confluencia.getQuery((session, cb) -> {
                CriteriaQuery<ProjectsEntity> q = cb.createQuery(ProjectsEntity.class);

                ParameterExpression<Long> idParam = cb.parameter(Long.class);

                Root<ProjectsEntity> entity = q.from(ProjectsEntity.class);
                q.select(entity);
                q.where(cb.equal(entity.get("id"), idParam));

                TypedQuery<ProjectsEntity> query = session.createQuery(q);
                query.setParameter(idParam, id);
                return query.getSingleResult();
            });
        }
        catch (Exception e) {
            return null;
        }
    }

    public ProjectsEntity findOneProjectByGameSlugAndProjectTypeSlugAndProjectSlug (String gameSlug, String projectTypeSlug, String projectSlug) {

        try {
            return Confluencia.getQuery((session, cb) -> {
                CriteriaQuery<ProjectsEntity> q = cb.createQuery(ProjectsEntity.class);

                ParameterExpression<ProjectTypesEntity> projectTypeSlugParam = cb.parameter(ProjectTypesEntity.class);
                ParameterExpression<String> projectSlugParam = cb.parameter(String.class);

                Root<ProjectsEntity> entity = q.from(ProjectsEntity.class);
                q.select(entity);
                q.where(cb.and(cb.equal(entity.get("projectType"), projectTypeSlugParam),
                    cb.equal(entity.get("slug"), projectSlugParam)));

                TypedQuery<ProjectsEntity> query = session.createQuery(q);
                GamesEntity game = new GamesEntity(gameSlug);
                query.setParameter(projectTypeSlugParam, new ProjectTypesEntity(game, projectTypeSlug));
                query.setParameter(projectSlugParam, projectSlug);
                return query.getSingleResult();
            });
        }
        catch (Exception e) {
            return null;
        }
    }

    public ProjectTypesEntity findOneProjectTypeByGameSlugAndProjectTypeSlug (String gameSlug, String projectTypeSlug) {

        try {
            return Confluencia.getQuery((session, cb) -> {
                CriteriaQuery<ProjectTypesEntity> q = cb.createQuery(ProjectTypesEntity.class);

                ParameterExpression<GamesEntity> gameSlugParam = cb.parameter(GamesEntity.class);
                ParameterExpression<String> projectTypeSlugParam = cb.parameter(String.class);

                Root<ProjectTypesEntity> entity = q.from(ProjectTypesEntity.class);
                q.select(entity);
                q.where(cb.and(cb.equal(entity.get("game"), gameSlugParam),
                    cb.equal(entity.get("slug"), projectTypeSlugParam)));

                TypedQuery<ProjectTypesEntity> query = session.createQuery(q);
                query.setParameter(gameSlugParam, new GamesEntity(gameSlug));
                query.setParameter(projectTypeSlugParam, projectTypeSlug);
                return query.getSingleResult();
            });
        }
        catch (Exception e) {
            return null;
        }
    }

    public long countAllByUsername (String username, boolean authorized) {

        try {
            return Confluencia.getQuery((session, cb) -> {
                CriteriaQuery<Long> q = cb.createQuery(Long.class);
                Root<ProjectsEntity> entity = q.from(ProjectsEntity.class);

                ParameterExpression<String> usernameParam = cb.parameter(String.class);
                List<Predicate> predicates = new ArrayList<>();

                q.select(cb.count(entity));

                Subquery<UsersEntity> usersSubQuery = q.subquery(UsersEntity.class);
                Root<UsersEntity> entityUsers = usersSubQuery.from(UsersEntity.class);
                usersSubQuery.select(entityUsers.get("id"));
                usersSubQuery.where(cb.equal(entityUsers.get("username"), usernameParam));

                Subquery<ProjectsEntity> projectAuthorsSubQuery = q.subquery(ProjectsEntity.class);
                Root<ProjectAuthorsEntity> entityProjectAuthors = projectAuthorsSubQuery.from(ProjectAuthorsEntity.class);
                projectAuthorsSubQuery.select(entityProjectAuthors.get("project"));
                projectAuthorsSubQuery.where(cb.in(entityProjectAuthors.get("user")).value(usersSubQuery));

                predicates.add(
                    cb.or(
                        cb.and(cb.in(entity.get("user")).value(usersSubQuery)),
                        cb.and(cb.in(entity).value(projectAuthorsSubQuery))
                    )
                );
                if (!authorized) {
                    predicates.add(cb.isTrue(entity.get("released")));
                }

                q.where(cb.and(predicates.toArray(new Predicate[0])));

                TypedQuery<Long> query = session.createQuery(q);
                query.setParameter(usernameParam, username);
                return query.getSingleResult();
            });
        }
        catch (Exception e) {
            return 0;
        }
    }

    public List<ProjectsEntity> findAllByUsername (String username, boolean authorized, long page, int limit, Sort sort) {

        try {
            return Confluencia.getQuery((session, cb) -> {
                CriteriaQuery<ProjectsEntity> q = cb.createQuery(ProjectsEntity.class);
                Root<ProjectsEntity> entity = q.from(ProjectsEntity.class);

                ParameterExpression<String> usernameParam = cb.parameter(String.class);

                List<Predicate> predicates = new ArrayList<>();

                q.select(entity);
                Subquery<UsersEntity> usersSubQuery = q.subquery(UsersEntity.class);
                Root<UsersEntity> entityUsers = usersSubQuery.from(UsersEntity.class);
                usersSubQuery.select(entityUsers.get("id"));
                usersSubQuery.where(cb.equal(entityUsers.get("username"), usernameParam));

                Subquery<ProjectsEntity> projectAuthorsSubQuery = q.subquery(ProjectsEntity.class);
                Root<ProjectAuthorsEntity> entityProjectAuthors = projectAuthorsSubQuery.from(ProjectAuthorsEntity.class);
                projectAuthorsSubQuery.select(entityProjectAuthors.get("project"));
                projectAuthorsSubQuery.where(cb.in(entityProjectAuthors.get("user")).value(usersSubQuery));

                predicates.add(
                    cb.or(
                        cb.and(cb.in(entity.get("user")).value(usersSubQuery)),
                        cb.and(cb.in(entity).value(projectAuthorsSubQuery))
                    )
                );
                if (!authorized) {
                    predicates.add(cb.isTrue(entity.get("released")));
                }

                q.where(cb.and(predicates.toArray(new Predicate[0])));

                if (sort.getOrder() == Order.ASC) {
                    q.orderBy(cb.asc(entity.get(sort.getColumn())));
                }
                else {
                    q.orderBy(cb.desc(entity.get(sort.getColumn())));
                }

                TypedQuery<ProjectsEntity> query = session.createQuery(q);
                query.setParameter(usernameParam, username);
                query.setFirstResult((int) ((page - 1) * limit));
                query.setMaxResults(limit);
                return query.getResultList();
            });
        }
        catch (Exception e) {
            return Collections.emptyList();
        }
    }

    public List<ProjectsEntity> findAllProjectsByProjectIds (long[] projectIds) {

        try {
            return Confluencia.getQuery((session, cb) -> {
                CriteriaQuery<ProjectsEntity> q = cb.createQuery(ProjectsEntity.class);

                Root<ProjectsEntity> entity = q.from(ProjectsEntity.class);
                q.select(entity);

                List<ParameterExpression<Long>> params = new LinkedList<>();
                List<Predicate> idk = new LinkedList<>();
                for (int i = 0; i < projectIds.length; i++) {
                    ParameterExpression<Long> param = cb.parameter(Long.class);
                    params.add(param);
                    idk.add(cb.equal(entity.get("id"), param));
                }
                q.where(cb.or(idk.toArray(new Predicate[0])));

                TypedQuery<ProjectsEntity> query = session.createQuery(q);
                for (int i = 0; i < projectIds.length; i++) {
                    long id = projectIds[i];
                    ParameterExpression<Long> param = params.get(i);
                    query.setParameter(param, id);
                }
                return query.getResultList();
            });
        }
        catch (Exception e) {
            return Collections.emptyList();
        }
    }

    public List<ProjectsEntity> findAllByGameAndProjectType (String gameSlug, String projectTypeSlug,
                                                             String search, long page, int limit, Sort sort) {

        return this.findAllByGameAndProjectType(gameSlug, projectTypeSlug, search, page, limit, sort, null, null);
    }

    public List<ProjectsEntity> findAllByGameAndProjectType (String gameSlug, String projectTypeSlug,
                                                             String search, long page, int limit,
                                                             Sort sort, String gameVersion, String[] tags) {

        try {
            return Confluencia.getQuery((session, cb) -> {
                CriteriaQuery<ProjectsEntity> q = cb.createQuery(ProjectsEntity.class);
                Root<ProjectsEntity> entity = q.from(ProjectsEntity.class);

                ParameterExpression<ProjectTypesEntity> projectTypeSlugParam = cb.parameter(ProjectTypesEntity.class);
                ParameterExpression<String> nameParam = cb.parameter(String.class);
                ParameterExpression<String> gameVersionParam = cb.parameter(String.class);
                List<ParameterExpression<String>> tagParams = new LinkedList<>();
                ParameterExpression<Long> tagLengthParam = cb.parameter(Long.class);

                q.select(entity);
                List<Predicate> predicates = new ArrayList<>();
                predicates.add(cb.like(entity.get("name"), nameParam));
                predicates.add(cb.equal(entity.get("projectType"), projectTypeSlugParam));

                if (tags != null && tags.length > 0) {

                    Subquery<ProjectsEntity> projectTagsSubQuery = q.subquery(ProjectsEntity.class);
                    Root<ProjectTagsEntity> entityProjectTags = projectTagsSubQuery.from(ProjectTagsEntity.class);
                    Root<TagsEntity> entityTag = projectTagsSubQuery.from(TagsEntity.class);

                    CriteriaBuilder.In<Object> in = cb.in(entityTag.get("slug"));
                    for (int i = 0; i < tags.length; i++) {
                        ParameterExpression<String> tagParam = cb.parameter(String.class);
                        tagParams.add(tagParam);
                        in.value(tagParam);
                    }

                    projectTagsSubQuery.select(entityProjectTags.get("project"));
                    projectTagsSubQuery.where(cb.and(
                        cb.equal(entityProjectTags.get("tag"), entityTag),
                        in,
                        cb.equal(entityTag.get("projectType"), projectTypeSlugParam))
                    );
                    projectTagsSubQuery.groupBy(entityProjectTags.get("project"));
                    projectTagsSubQuery.having(cb.equal(cb.count(entityProjectTags.get("project")), tagLengthParam));
                    predicates.add(cb.in(entity).value(projectTagsSubQuery));
                }
                if (gameVersion != null) {
                    Subquery<ProjectsEntity> projectSubQuery = q.subquery(ProjectsEntity.class);
                    Root<ProjectFilesEntity> entityProjectFiles = projectSubQuery.from(ProjectFilesEntity.class);
                    Root<ProjectFileGameVersionsEntity> entityProjectFilesGameVersion = projectSubQuery.from(ProjectFileGameVersionsEntity.class);
                    Root<GameVersionsEntity> entityGameVersion = projectSubQuery.from(GameVersionsEntity.class);

                    projectSubQuery.select(entityProjectFiles.get("project"));
                    projectSubQuery.where(cb.and(
                        cb.equal(entityProjectFiles, entityProjectFilesGameVersion.get("projectFile"))),
                        cb.equal(entityProjectFilesGameVersion.get("gameVersion"), entityGameVersion),
                        cb.equal(entityGameVersion.get("version"), gameVersionParam)
                    );
                    predicates.add(cb.in(entity).value(projectSubQuery));
                }

                q.where(cb.and(predicates.toArray(new Predicate[0])));
                if (sort.getOrder() == Order.ASC) {
                    q.orderBy(cb.asc(entity.get(sort.getColumn())));
                }
                else {
                    q.orderBy(cb.desc(entity.get(sort.getColumn())));
                }

                TypedQuery<ProjectsEntity> query = session.createQuery(q);
                query.setParameter(projectTypeSlugParam, new ProjectTypesEntity(new GamesEntity(gameSlug), projectTypeSlug));
                query.setParameter(nameParam, "%" + search + "%");
                if (tags != null && tags.length > 0) {
                    for (int i = 0; i < tags.length; i++) {
                        ParameterExpression<String> tagParam = tagParams.get(i);
                        query.setParameter(tagParam, tags[i]);
                    }
                    query.setParameter(tagLengthParam, (long) tags.length);
                }
                if (gameVersion != null) {
                    query.setParameter(gameVersionParam, gameVersion);
                }
                query.setFirstResult((int) ((page - 1) * limit));
                query.setMaxResults(limit);
                return query.getResultList();
            });
        }
        catch (Exception e) {
            return Collections.emptyList();
        }
    }

    public List<FeaturedProjectsEntity> findFeaturedProjects () {

        try {
            return Confluencia.getQuery((session, cb) -> {
                CriteriaQuery<FeaturedProjectsEntity> q = cb.createQuery(FeaturedProjectsEntity.class);

                q.select(q.from(FeaturedProjectsEntity.class));

                TypedQuery<FeaturedProjectsEntity> query = session.createQuery(q);
                query.setMaxResults(4);
                return query.getResultList();
            });
        }
        catch (Exception e) {
            return Collections.emptyList();
        }
    }

    public boolean insertProject (ProjectsEntity projectsEntity) {

        Transaction transaction = null;
        try (Session session = Confluencia.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(projectsEntity);
            transaction.commit();
            return true;
        }
        catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }

        return false;
    }

    public boolean updateProject (ProjectsEntity project) {


        Transaction transaction = null;
        try (Session session = Confluencia.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(project);
            transaction.commit();
            return true;
        }
        catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

        return false;
    }
}
