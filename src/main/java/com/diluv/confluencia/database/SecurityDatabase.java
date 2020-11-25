package com.diluv.confluencia.database;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

import org.hibernate.Session;

import com.diluv.confluencia.database.record.NodeCDNCommitsEntity;
import com.diluv.confluencia.database.record.APITokensEntity;
import com.diluv.confluencia.utils.DatabaseUtil;

public class SecurityDatabase {

    public APITokensEntity findAPITokensByToken (Session session, String token) {

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<APITokensEntity> q = cb.createQuery(APITokensEntity.class);

        ParameterExpression<String> tokenParam = cb.parameter(String.class);

        Root<APITokensEntity> entity = q.from(APITokensEntity.class);
        q.select(entity);
        q.where(cb.equal(entity.get("token"), tokenParam));

        TypedQuery<APITokensEntity> query = session.createQuery(q);
        query.setParameter(tokenParam, token);
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
