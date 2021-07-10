package com.diluv.confluencia.database;

import java.time.Instant;
import java.util.List;

import org.hibernate.Session;

import com.diluv.confluencia.database.record.RegistrationCodesEntity;
import com.diluv.confluencia.utils.DatabaseUtil;

public class MiscDatabase {

    public boolean existsImagesForRelease (Session session) {

        final String hql = "SELECT id FROM ImagesEntity WHERE released = FALSE";

        return DatabaseUtil.findOne(session.createQuery(hql, String.class)
            .setMaxResults(1)
            .getResultList()) != null;
    }

    public int updateAllImagesForRelease (Session session, Instant createdAt) {

        final String hql = "UPDATE ImagesEntity SET released = TRUE WHERE released = FALSE AND released = FALSE AND createdAt <= :created_at";

        return session.createQuery(hql)
            .setParameter("created_at", createdAt)
            .executeUpdate();
    }

    public List<RegistrationCodesEntity> findAllRegistrationCodesByUser (Session session, long userId) {

        final String hql = "FROM RegistrationCodesEntity WHERE valid = TRUE AND user.id = :user_id";

        return session.createQuery(hql, RegistrationCodesEntity.class)
            .setParameter("user_id", userId)
            .getResultList();
    }
}
