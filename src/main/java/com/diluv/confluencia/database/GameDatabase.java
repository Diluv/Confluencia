package com.diluv.confluencia.database;

import java.util.List;

import org.hibernate.Session;

import com.diluv.confluencia.database.record.FeaturedGamesEntity;
import com.diluv.confluencia.database.record.GamesEntity;
import com.diluv.confluencia.database.record.ProjectTypesEntity;
import com.diluv.confluencia.database.sort.Sort;
import com.diluv.confluencia.utils.DatabaseUtil;

public class GameDatabase {

    public long countAllBySearch (Session session, String search) {

        final String hql = "SELECT COUNT(*) FROM GamesEntity WHERE name LIKE :search";

        return DatabaseUtil.findOne(session.createQuery(hql, Long.class)
            .setParameter("search", "%" + search + "%")
            .getResultList(), 0L);
    }

    public List<GamesEntity> findAll (Session session, long page, int limit, Sort sort, String search) {

        final String hql = "FROM GamesEntity WHERE name LIKE :search " + sort.getSQL();

        return session.createQuery(hql, GamesEntity.class)
            .setParameter("search", "%" + search + "%")
            .setFirstResult((int) ((page - 1) * limit))
            .setMaxResults(limit)
            .getResultList();
    }

    public GamesEntity findOneBySlug (Session session, String slug) {

        final String hql = "FROM GamesEntity WHERE slug = :slug";

        return DatabaseUtil.findOne(session.createQuery(hql, GamesEntity.class)
            .setParameter("slug", slug)
            .setMaxResults(1)
            .getResultList());
    }

    public List<FeaturedGamesEntity> findFeaturedGames (Session session) {

        final String hql = "FROM FeaturedGamesEntity";

        return session.createQuery(hql, FeaturedGamesEntity.class)
            .getResultList();
    }

    public long countAllProjectTypes (Session session) {

        final String hql = "SELECT COUNT(*) FROM ProjectTypesEntity";

        return DatabaseUtil.findOne(session.createQuery(hql, Long.class)
            .getResultList(), 0L);
    }

    public List<ProjectTypesEntity> findAllProjectTypesByGameSlug (Session session, String gameSlug) {

        final String hql = "FROM ProjectTypesEntity WHERE game.slug = :game_slug";

        return session.createQuery(hql, ProjectTypesEntity.class)
            .setParameter("game_slug", gameSlug)
            .getResultList();
    }
}
