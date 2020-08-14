package com.diluv.confluencia.database;

import com.diluv.confluencia.Confluencia;
import com.diluv.confluencia.database.record.NodeCDNCommitsEntity;
import com.diluv.confluencia.database.record.PersistedGrantsEntity;

import com.diluv.confluencia.database.record.ProjectFilesEntity;

import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

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

    public NodeCDNCommitsEntity findAllNodeCDNCommits () {

        try {
            return Confluencia.getQuery((session, cb) -> {
                CriteriaQuery<NodeCDNCommitsEntity> q = cb.createQuery(NodeCDNCommitsEntity.class);

                Root<NodeCDNCommitsEntity> entity = q.from(NodeCDNCommitsEntity.class);
                q.select(entity);
                q.where(cb.isFalse(entity.get("completed")));

                TypedQuery<NodeCDNCommitsEntity> query = session.createQuery(q);
                query.setMaxResults(1);
                return query.getSingleResult();
            });
        }
        catch (Exception e) {
            return null;
        }
    }

    public boolean insertNodeCDNCommits (NodeCDNCommitsEntity entity) {

        Transaction transaction = null;
        try (Session session = Confluencia.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(entity);
            transaction.commit();
            return true;
        }
        catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

        return false;
    }

    public boolean updateNodeCDNCommits (long id) {

        try {
            return Confluencia.getQuery((session, cb) -> {
                CriteriaUpdate<NodeCDNCommitsEntity> q = cb.createCriteriaUpdate(NodeCDNCommitsEntity.class);
                Root<NodeCDNCommitsEntity> entity = q.from(NodeCDNCommitsEntity.class);

                q.set(entity.get("completed"), true);
                q.where(cb.equal(entity.get("id"), id));

                return session.createQuery(q).executeUpdate() == 1;
            });
        }
        catch (Exception e) {
            return false;
        }
    }
}
