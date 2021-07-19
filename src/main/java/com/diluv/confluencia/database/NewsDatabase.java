package com.diluv.confluencia.database;

import java.util.List;

import org.hibernate.Session;

import com.diluv.confluencia.database.record.NewsEntity;
import com.diluv.confluencia.database.sort.Sort;
import com.diluv.confluencia.utils.DatabaseUtil;

public class NewsDatabase {

    public List<NewsEntity> findAll (Session session, long page, int limit, Sort sort) {

        final String hql = "FROM NewsEntity " + sort.getSQL();

        return session.createQuery(hql, NewsEntity.class)
            .setFirstResult((int) ((page - 1) * limit))
            .setMaxResults(limit)
            .getResultList();
    }

    public NewsEntity findOneByNewsSlug (Session session, String slug) {

        final String hql = "FROM NewsEntity WHERE slug = :slug";

        return DatabaseUtil.findOne(session.createQuery(hql, NewsEntity.class)
            .setParameter("slug", slug)
            .getResultList());
    }
}
