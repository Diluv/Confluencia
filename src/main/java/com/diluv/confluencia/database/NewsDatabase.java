package com.diluv.confluencia.database;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

import org.hibernate.Session;

import com.diluv.confluencia.database.record.NewsEntity;
import com.diluv.confluencia.database.sort.Order;
import com.diluv.confluencia.database.sort.Sort;
import com.diluv.confluencia.utils.DatabaseUtil;

public class NewsDatabase {

    public List<NewsEntity> findAll (Session session, long page, int limit, Sort sort) {

        CriteriaBuilder cb = session.getCriteriaBuilder();

        CriteriaQuery<NewsEntity> q = cb.createQuery(NewsEntity.class);
        Root<NewsEntity> entity = q.from(NewsEntity.class);

        q.select(entity);

        if (sort.getOrder() == Order.ASC) {
            q.orderBy(cb.asc(entity.get(sort.getColumn())));
        }
        else {
            q.orderBy(cb.desc(entity.get(sort.getColumn())));
        }

        TypedQuery<NewsEntity> query = session.createQuery(q);
        query.setFirstResult((int) ((page - 1) * limit));
        query.setMaxResults(limit);
        return query.getResultList();
    }

    public NewsEntity findOneByNewsSlug (Session session, String slug) {

        CriteriaBuilder cb = session.getCriteriaBuilder();

        CriteriaQuery<NewsEntity> q = cb.createQuery(NewsEntity.class);
        Root<NewsEntity> entity = q.from(NewsEntity.class);

        ParameterExpression<String> newsSlugParam = cb.parameter(String.class);
        q.select(entity);
        q.where(cb.equal(entity.get("slug"), newsSlugParam));

        TypedQuery<NewsEntity> query = session.createQuery(q);
        query.setParameter(newsSlugParam, slug);
        return DatabaseUtil.findOne(query.getResultList());
    }
}
