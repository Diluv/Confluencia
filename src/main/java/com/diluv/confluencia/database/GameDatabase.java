package com.diluv.confluencia.database;

import java.util.Collections;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

import com.diluv.confluencia.Confluencia;
import com.diluv.confluencia.database.record.FeaturedGamesEntity;
import com.diluv.confluencia.database.record.GamesEntity;
import com.diluv.confluencia.database.record.ProjectTypesEntity;
import com.diluv.confluencia.database.record.ProjectsEntity;
import com.diluv.confluencia.database.sort.Order;
import com.diluv.confluencia.database.sort.Sort;

public class GameDatabase {

    public long countAllBySearch (String search) {

        try {
            return Confluencia.getQuery((session, cb) -> {
                CriteriaQuery<Long> q = cb.createQuery(Long.class);

                ParameterExpression<String> s = cb.parameter(String.class);

                Root<GamesEntity> entity = q.from(GamesEntity.class);
                q.select(cb.count(entity));
                q.where(cb.like(entity.get("name"), s));

                TypedQuery<Long> query = session.createQuery(q);
                query.setParameter(s, "%" + search + "%");
                return query.getSingleResult();
            });
        }
        catch (Exception e) {
            return 0;
        }
    }

    public long countAllProjectsBySlug (String gameSlug) {

        try {
            return Confluencia.getQuery((session, cb) -> {
                CriteriaQuery<Long> q = cb.createQuery(Long.class);

                ParameterExpression<String> gameSlugParam = cb.parameter(String.class);

                Root<ProjectsEntity> entity = q.from(ProjectsEntity.class);
                q.select(cb.count(entity));
                q.where(cb.like(entity.get("gameSlug"), gameSlugParam));

                TypedQuery<Long> query = session.createQuery(q);
                query.setParameter(gameSlugParam, gameSlug);
                return query.getSingleResult();
            });
        }
        catch (Exception e) {
            return 0;
        }
    }

    public List<GamesEntity> findAll (long page, int limit, Sort sort, String search) {

        try {
            return Confluencia.getQuery((session, cb) -> {
                CriteriaQuery<GamesEntity> q = cb.createQuery(GamesEntity.class);
                Root<GamesEntity> entity = q.from(GamesEntity.class);

                ParameterExpression<String> s = cb.parameter(String.class);

                q.select(entity);
                q.where(cb.like(entity.get("name"), s));
                if (sort.getOrder() == Order.ASC) {
                    q.orderBy(cb.asc(entity.get(sort.getColumn())));
                }
                else {
                    q.orderBy(cb.desc(entity.get(sort.getColumn())));
                }

                TypedQuery<GamesEntity> query = session.createQuery(q);
                query.setParameter(s, "%" + search + "%");
                query.setFirstResult((int) ((page - 1) * limit));
                query.setMaxResults(limit);
                return query.getResultList();
            });
        }
        catch (Exception e) {
            return Collections.emptyList();
        }
    }

    public GamesEntity findOneBySlug (String slug) {

        try {
            return Confluencia.getQuery((session, cb) -> {
                CriteriaQuery<GamesEntity> q = cb.createQuery(GamesEntity.class);

                ParameterExpression<String> s = cb.parameter(String.class);

                Root<GamesEntity> entity = q.from(GamesEntity.class);
                q.select(entity);
                q.where(cb.like(entity.get("slug"), s));

                TypedQuery<GamesEntity> query = session.createQuery(q);
                query.setParameter(s, slug);
                return query.getSingleResult();
            });
        }
        catch (Exception e) {
            return null;
        }
    }

    public List<FeaturedGamesEntity> findFeaturedGames () {

        try {
            return Confluencia.getQuery((session, cb) -> {
                CriteriaQuery<FeaturedGamesEntity> q = cb.createQuery(FeaturedGamesEntity.class);

                Root<FeaturedGamesEntity> entity = q.from(FeaturedGamesEntity.class);

                q.select(entity);

                TypedQuery<FeaturedGamesEntity> query = session.createQuery(q);
                return query.getResultList();
            });
        }
        catch (Exception e) {
            return Collections.emptyList();
        }
    }

    public long countAllProjectTypes () {

        try {
            return Confluencia.getQuery((session, cb) -> {
                CriteriaQuery<Long> q = cb.createQuery(Long.class);

                Root<ProjectTypesEntity> entity = q.from(ProjectTypesEntity.class);
                q.select(cb.count(entity));

                TypedQuery<Long> query = session.createQuery(q);
                return query.getSingleResult();
            });
        }
        catch (Exception e) {
            return 0;
        }
    }
}
