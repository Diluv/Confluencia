package com.diluv.confluencia.database;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

import org.hibernate.Session;

import com.diluv.confluencia.database.record.NodeCDNCommitsEntity;
import com.diluv.confluencia.database.record.PersistedGrantsEntity;
import com.diluv.confluencia.utils.DatabaseUtil;

public class SecurityDatabase {

    public PersistedGrantsEntity findPersistedGrantByKeyAndType (Session session, String key, String type) {

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<PersistedGrantsEntity> q = cb.createQuery(PersistedGrantsEntity.class);

        ParameterExpression<String> keyParam = cb.parameter(String.class);
        ParameterExpression<String> typeParam = cb.parameter(String.class);

        Root<PersistedGrantsEntity> entity = q.from(PersistedGrantsEntity.class);
        q.select(entity);
        q.where(cb.and(cb.equal(entity.get("key"), keyParam), cb.equal(entity.get("type"), typeParam)));

        TypedQuery<PersistedGrantsEntity> query = session.createQuery(q);
        query.setParameter(keyParam, key);
        query.setParameter(typeParam, type);
        return DatabaseUtil.findOne(query.getResultList());
    }

    public NodeCDNCommitsEntity findOneNodeCDNCommitsByHash (Session session, String hash) {

        CriteriaBuilder cb = session.getCriteriaBuilder();

        CriteriaQuery<NodeCDNCommitsEntity> q = cb.createQuery(NodeCDNCommitsEntity.class);

        ParameterExpression<String> hashParam = cb.parameter(String.class);

        Root<NodeCDNCommitsEntity> entity = q.from(NodeCDNCommitsEntity.class);
        q.select(entity);
        q.where(cb.and(cb.isFalse(entity.get("completed")), cb.equal(entity.get("hash"), hashParam)));

        TypedQuery<NodeCDNCommitsEntity> query = session.createQuery(q);
        query.setParameter(hashParam, hash);
        query.setMaxResults(1);
        return DatabaseUtil.findOne(query.getResultList());
    }

    public NodeCDNCommitsEntity findLatestNodeCDNCommits (Session session) {

        CriteriaBuilder cb = session.getCriteriaBuilder();

        CriteriaQuery<NodeCDNCommitsEntity> q = cb.createQuery(NodeCDNCommitsEntity.class);

        Root<NodeCDNCommitsEntity> entity = q.from(NodeCDNCommitsEntity.class);
        q.select(entity);
        q.where(cb.isFalse(entity.get("completed")));

        TypedQuery<NodeCDNCommitsEntity> query = session.createQuery(q);
        query.setMaxResults(1);
        return DatabaseUtil.findOne(query.getResultList());
    }
}
