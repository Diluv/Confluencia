package com.diluv.confluencia.database;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

import org.hibernate.Session;

import com.diluv.confluencia.database.record.FeaturedGamesEntity;
import com.diluv.confluencia.database.record.GamesEntity;
import com.diluv.confluencia.database.record.ProjectTypesEntity;
import com.diluv.confluencia.database.sort.Order;
import com.diluv.confluencia.database.sort.Sort;
import com.diluv.confluencia.utils.DatabaseUtil;

public class GameDatabase {

    public long countAllBySearch (Session session, String search) {

        CriteriaBuilder cb = session.getCriteriaBuilder();

        CriteriaQuery<Long> q = cb.createQuery(Long.class);

        ParameterExpression<String> s = cb.parameter(String.class);

        Root<GamesEntity> entity = q.from(GamesEntity.class);
        q.select(cb.count(entity));
        q.where(cb.like(entity.get("name"), s));

        TypedQuery<Long> query = session.createQuery(q);
        query.setParameter(s, "%" + search + "%");
        return DatabaseUtil.findOne(query.getResultList(), 0L);
    }

    public List<GamesEntity> findAll (Session session, long page, int limit, Sort sort, String search) {

        CriteriaBuilder cb = session.getCriteriaBuilder();

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
    }

    public GamesEntity findOneBySlug (Session session, String slug) {

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<GamesEntity> q = cb.createQuery(GamesEntity.class);

        ParameterExpression<String> s = cb.parameter(String.class);

        Root<GamesEntity> entity = q.from(GamesEntity.class);
        q.select(entity);
        q.where(cb.like(entity.get("slug"), s));

        TypedQuery<GamesEntity> query = session.createQuery(q);
        query.setParameter(s, slug);
        return DatabaseUtil.findOne(query.getResultList());
    }

    public List<FeaturedGamesEntity> findFeaturedGames (Session session) {

        CriteriaBuilder cb = session.getCriteriaBuilder();

        CriteriaQuery<FeaturedGamesEntity> q = cb.createQuery(FeaturedGamesEntity.class);

        Root<FeaturedGamesEntity> entity = q.from(FeaturedGamesEntity.class);

        q.select(entity);

        TypedQuery<FeaturedGamesEntity> query = session.createQuery(q);
        return query.getResultList();
    }

    public long countAllProjectTypes (Session session) {

        CriteriaBuilder cb = session.getCriteriaBuilder();

        CriteriaQuery<Long> q = cb.createQuery(Long.class);

        Root<ProjectTypesEntity> entity = q.from(ProjectTypesEntity.class);
        q.select(cb.count(entity));

        TypedQuery<Long> query = session.createQuery(q);
        return DatabaseUtil.findOne(query.getResultList(), 0L);
    }

    public List<ProjectTypesEntity> findAllProjectTypesByGameSlug (Session session, String gameSlug) {

        CriteriaBuilder cb = session.getCriteriaBuilder();

        CriteriaQuery<ProjectTypesEntity> q = cb.createQuery(ProjectTypesEntity.class);
        Root<ProjectTypesEntity> entity = q.from(ProjectTypesEntity.class);
        ParameterExpression<GamesEntity> gameParam = cb.parameter(GamesEntity.class);

        q.select(entity);
        q.where(cb.equal(entity.get("game"), gameParam));

        TypedQuery<ProjectTypesEntity> query = session.createQuery(q);
        query.setParameter(gameParam, new GamesEntity(gameSlug));
        return query.getResultList();
    }
}
