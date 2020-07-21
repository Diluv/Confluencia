package com.diluv.confluencia.database;

import java.util.Collections;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

import com.diluv.confluencia.Confluencia;
import com.diluv.confluencia.database.record.NewsEntity;
import com.diluv.confluencia.database.sort.Order;
import com.diluv.confluencia.database.sort.Sort;

public class NewsDatabase {

    public List<NewsEntity> findAll (long page, int limit, Sort sort) {

        try {
            return Confluencia.getQuery((session, cb) -> {
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
            });
        }
        catch (Exception e) {
            return Collections.emptyList();
        }
    }

    public NewsEntity findOneByNewsSlug (String slug) {

        try {
            return Confluencia.getQuery((session, cb) -> {

                CriteriaQuery<NewsEntity> q = cb.createQuery(NewsEntity.class);
                Root<NewsEntity> entity = q.from(NewsEntity.class);

                ParameterExpression<String> newsSlugParam = cb.parameter(String.class);
                q.select(entity);
                q.where(cb.equal(entity.get("slug"), newsSlugParam));

                TypedQuery<NewsEntity> query = session.createQuery(q);
                query.setParameter(newsSlugParam, slug);
                return query.getSingleResult();
            });
        }
        catch (Exception e) {
            return null;
        }
    }
}
