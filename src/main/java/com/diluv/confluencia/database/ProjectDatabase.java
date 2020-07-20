package com.diluv.confluencia.database;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.diluv.confluencia.Confluencia;
import com.diluv.confluencia.database.record.FeaturedProjectsEntity;
import com.diluv.confluencia.database.record.GamesEntity;
import com.diluv.confluencia.database.record.ProjectTypesEntity;
import com.diluv.confluencia.database.record.ProjectsEntity;
import com.diluv.confluencia.database.sort.Order;
import com.diluv.confluencia.database.sort.Sort;

public class ProjectDatabase {

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

                ParameterExpression<String> usernameParam = cb.parameter(String.class);

                Root<ProjectsEntity> entity = q.from(ProjectsEntity.class);
                q.select(cb.count(entity));
                if (!authorized) {
                    q.where(cb.isTrue(entity.get("released")));
                }
                q.where(cb.like(entity.get("user_id").get("username"), usernameParam));

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

                q.select(entity);
                q.where(cb.equal(entity.get("user_id").get("username"), usernameParam));
                if (!authorized) {
                    q.where(cb.isTrue(entity.get("released")));
                }
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
                                                             Sort sort, String version, String[] tags) {

        try {
            return Confluencia.getQuery((session, cb) -> {
                CriteriaQuery<ProjectsEntity> q = cb.createQuery(ProjectsEntity.class);
                Root<ProjectsEntity> entity = q.from(ProjectsEntity.class);

                ParameterExpression<ProjectTypesEntity> projectTypeSlugParam = cb.parameter(ProjectTypesEntity.class);
                ParameterExpression<String> nameParam = cb.parameter(String.class);
                ParameterExpression<String> versionParam = cb.parameter(String.class);
                List<ParameterExpression<String>> tagParams = new LinkedList<>();

                q.select(entity);
                q.where();
                q.where(cb.and(cb.like(entity.get("name"), nameParam), cb.equal(entity.get("projectType"), projectTypeSlugParam)));
//                if (tags != null && tags.length > 0) {
//                    CriteriaBuilder.In<String> inTags = cb.in(entity.get("tag"));
//                    for (int i = 0; i < tags.length; i++) {
//                        ParameterExpression<String> tagParam = cb.parameter(String.class);
//                        inTags.value(tagParam);
//                        tagParams.add(tagParam);
//                    }
//                    q.where(inTags);
//                }
//                if (version != null) {
//                    q.where(cb.equal(entity.get("version"), versionParam));
//                }
                if (sort.getOrder() == Order.ASC) {
                    q.orderBy(cb.asc(entity.get(sort.getColumn())));
                }
                else {
                    q.orderBy(cb.desc(entity.get(sort.getColumn())));
                }

                TypedQuery<ProjectsEntity> query = session.createQuery(q);
                query.setParameter(projectTypeSlugParam, new ProjectTypesEntity(new GamesEntity(gameSlug), projectTypeSlug));
                query.setParameter(nameParam, "%" + search + "%");
//                if (tags != null && tags.length > 0) {
//                    for (int i = 0; i < tags.length; i++) {
//                        ParameterExpression<String> tagParam = tagParams.get(i);
//                        query.setParameter(tagParam, tags[i]);
//                    }
//                }
//                if (version != null) {
//                    query.setParameter(versionParam, version);
//                }
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
            e.printStackTrace();
        }

        return false;
    }
}
