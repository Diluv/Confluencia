package com.diluv.confluencia.database;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

import com.diluv.confluencia.Confluencia;
import com.diluv.confluencia.database.record.PersistedGrantsEntity;

public class SecurityDatabase {

    public PersistedGrantsEntity findPersistedGrantByKeyAndType (String key, String type) {

        try {
            return Confluencia.getQuery((session, cb) -> {
                CriteriaQuery<PersistedGrantsEntity> q = cb.createQuery(PersistedGrantsEntity.class);

                ParameterExpression<String> keyParam = cb.parameter(String.class);
                ParameterExpression<String> typeParam = cb.parameter(String.class);

                Root<PersistedGrantsEntity> entity = q.from(PersistedGrantsEntity.class);
                q.select(entity);
                q.where(cb.and(cb.equal(entity.get("key"), keyParam), cb.equal(entity.get("type"), typeParam)));

                TypedQuery<PersistedGrantsEntity> query = session.createQuery(q);
                query.setParameter(keyParam, key);
                query.setParameter(typeParam, type);
                return query.getSingleResult();
            });
        }
        catch (Exception e) {
            return null;
        }
    }
}
