package com.diluv.confluencia.database;

import com.diluv.confluencia.database.record.ImagesEntity;
import com.diluv.confluencia.database.record.RegistrationCodesEntity;
import com.diluv.confluencia.database.record.UsersEntity;
import com.diluv.confluencia.utils.DatabaseUtil;

import org.hibernate.Session;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;

import java.sql.Timestamp;
import java.util.List;

public class MiscDatabase {

    public boolean existsImagesForRelease (Session session) {

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<ImagesEntity> q = cb.createQuery(ImagesEntity.class);
        Root<ImagesEntity> entity = q.from(ImagesEntity.class);

        q.where(cb.isFalse(entity.get("released")));

        TypedQuery<ImagesEntity> query = session.createQuery(q);
        query.setMaxResults(1);
        return DatabaseUtil.findOne(query.getResultList()) != null;
    }

    public int updateAllImagesForRelease (Session session, Timestamp time) {

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaUpdate<ImagesEntity> q = cb.createCriteriaUpdate(ImagesEntity.class);
        Root<ImagesEntity> entity = q.from(ImagesEntity.class);

        q.set(entity.get("released"), true);
        q.where(
            cb.isFalse(entity.get("released")),
            cb.lessThanOrEqualTo(entity.get("createdAt"), time)
        );

        return session.createQuery(q).executeUpdate();
    }

    public List<RegistrationCodesEntity> findAllRegistrationCodesByUser (Session session, long userId) {

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<RegistrationCodesEntity> q = cb.createQuery(RegistrationCodesEntity.class);
        Root<RegistrationCodesEntity> entity = q.from(RegistrationCodesEntity.class);

        q.select(entity);
        q.where(cb.and(
            cb.isTrue(entity.get("valid")),
            cb.equal(entity.get("user"), new UsersEntity(userId))
        ));

        TypedQuery<RegistrationCodesEntity> query = session.createQuery(q);
        return query.getResultList();
    }
}
