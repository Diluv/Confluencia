package com.diluv.confluencia.database;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

import com.diluv.confluencia.Confluencia;
import com.diluv.confluencia.database.record.UsersEntity;

public class UserDatabase {

    public long countAll () {

        try {
            return Confluencia.getQuery((session, cb) -> {
                CriteriaQuery<Long> q = cb.createQuery(Long.class);

                Root<UsersEntity> entity = q.from(UsersEntity.class);
                q.select(cb.count(entity));

                TypedQuery<Long> query = session.createQuery(q);
                return query.getSingleResult();
            });
        }
        catch (Exception e) {
            return 0;
        }
    }

    public UsersEntity findOneByUsername (String username) {

        try {
            return Confluencia.getQuery((session, cb) -> {
                CriteriaQuery<UsersEntity> q = cb.createQuery(UsersEntity.class);

                ParameterExpression<String> userParam = cb.parameter(String.class);

                Root<UsersEntity> entity = q.from(UsersEntity.class);
                q.select(entity);
                q.where(cb.equal(entity.get("username"), userParam));

                TypedQuery<UsersEntity> query = session.createQuery(q);
                query.setParameter(userParam, username);
                return query.getSingleResult();
            });
        }
        catch (Exception e) {
            return null;
        }
    }

    public UsersEntity findOneByUserId (long userId) {

        try {
            return Confluencia.getQuery((session, cb) -> {
                CriteriaQuery<UsersEntity> q = cb.createQuery(UsersEntity.class);

                ParameterExpression<Long> userParam = cb.parameter(Long.class);

                Root<UsersEntity> entity = q.from(UsersEntity.class);
                q.select(entity);
                q.where(cb.equal(entity.get("id"), userParam));

                TypedQuery<UsersEntity> query = session.createQuery(q);
                query.setParameter(userParam, userId);
                return query.getSingleResult();
            });
        }
        catch (Exception e) {
            return null;
        }
    }
}