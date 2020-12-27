package com.diluv.confluencia.database;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

import org.hibernate.Session;

import com.diluv.confluencia.database.record.APITokensEntity;
import com.diluv.confluencia.database.record.ContainsUsernameBlockListEntity;
import com.diluv.confluencia.database.record.EmailBlockListEntity;
import com.diluv.confluencia.database.record.EmailDomainBlocklistEntity;
import com.diluv.confluencia.database.record.NodeCDNCommitsEntity;
import com.diluv.confluencia.database.record.RegistrationCodesEntity;
import com.diluv.confluencia.database.record.UserCompromisedPasswordsEntity;
import com.diluv.confluencia.database.record.UsernameBlocklistEntity;
import com.diluv.confluencia.database.record.UsersEntity;
import com.diluv.confluencia.utils.DatabaseUtil;

public class SecurityDatabase {

    public APITokensEntity findAPITokensById (Session session, long id) {

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<APITokensEntity> q = cb.createQuery(APITokensEntity.class);

        ParameterExpression<Long> idParam = cb.parameter(Long.class);

        Root<APITokensEntity> entity = q.from(APITokensEntity.class);
        q.select(entity);
        q.where(cb.equal(entity.get("id"), idParam));

        TypedQuery<APITokensEntity> query = session.createQuery(q);
        query.setParameter(idParam, id);
        return DatabaseUtil.findOne(query.getResultList());
    }

    public List<APITokensEntity> findAPITokensByUserId (Session session, UsersEntity usersEntity) {

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<APITokensEntity> q = cb.createQuery(APITokensEntity.class);

        ParameterExpression<UsersEntity> userParam = cb.parameter(UsersEntity.class);

        Root<APITokensEntity> entity = q.from(APITokensEntity.class);
        q.select(entity);
        q.where(cb.equal(entity.get("user"), userParam));

        TypedQuery<APITokensEntity> query = session.createQuery(q);
        query.setParameter(userParam, usersEntity);
        return query.getResultList();
    }

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

    public boolean existsUsernameBlockList (Session session, String username) {

        CriteriaBuilder cb = session.getCriteriaBuilder();

        CriteriaQuery<UsernameBlocklistEntity> q = cb.createQuery(UsernameBlocklistEntity.class);
        ParameterExpression<String> userParam = cb.parameter(String.class);

        Root<UsernameBlocklistEntity> entity = q.from(UsernameBlocklistEntity.class);
        q.select(entity);
        q.where(cb.equal(entity.get("username"), userParam));

        TypedQuery<UsernameBlocklistEntity> query = session.createQuery(q);
        query.setMaxResults(1);
        query.setParameter(userParam, username);
        return DatabaseUtil.findOne(query.getResultList()) != null;
    }

    public boolean existsContainsUsernameBlockList (Session session, String username) {

        CriteriaBuilder cb = session.getCriteriaBuilder();

        CriteriaQuery<ContainsUsernameBlockListEntity> q = cb.createQuery(ContainsUsernameBlockListEntity.class);
        ParameterExpression<String> userParam = cb.parameter(String.class);

        Root<ContainsUsernameBlockListEntity> entity = q.from(ContainsUsernameBlockListEntity.class);
        q.select(entity);
        q.where(cb.like(userParam, cb.concat("%", cb.concat(entity.get("username"), "%"))));

        TypedQuery<ContainsUsernameBlockListEntity> query = session.createQuery(q);
        query.setMaxResults(1);
        query.setParameter(userParam, username);
        return DatabaseUtil.findOne(query.getResultList()) != null;
    }

    public boolean existsEmailDomainBlockList (Session session, String domain) {

        CriteriaBuilder cb = session.getCriteriaBuilder();

        CriteriaQuery<EmailDomainBlocklistEntity> q = cb.createQuery(EmailDomainBlocklistEntity.class);
        ParameterExpression<String> domainParam = cb.parameter(String.class);

        Root<EmailDomainBlocklistEntity> entity = q.from(EmailDomainBlocklistEntity.class);
        q.select(entity);
        q.where(cb.equal(entity.get("domain"), domainParam));

        TypedQuery<EmailDomainBlocklistEntity> query = session.createQuery(q);
        query.setMaxResults(1);
        query.setParameter(domainParam, domain);
        return DatabaseUtil.findOne(query.getResultList()) != null;
    }

    public boolean existsEmailBlockList (Session session, String email) {

        CriteriaBuilder cb = session.getCriteriaBuilder();

        CriteriaQuery<EmailBlockListEntity> q = cb.createQuery(EmailBlockListEntity.class);
        ParameterExpression<String> emailParam = cb.parameter(String.class);

        Root<EmailBlockListEntity> entity = q.from(EmailBlockListEntity.class);
        q.select(entity);
        q.where(cb.equal(entity.get("email"), emailParam));

        TypedQuery<EmailBlockListEntity> query = session.createQuery(q);
        query.setMaxResults(1);
        query.setParameter(emailParam, email);
        return DatabaseUtil.findOne(query.getResultList()) != null;
    }

    public RegistrationCodesEntity findRegistrationCode (Session session, String code) {

        CriteriaBuilder cb = session.getCriteriaBuilder();

        CriteriaQuery<RegistrationCodesEntity> q = cb.createQuery(RegistrationCodesEntity.class);
        ParameterExpression<String> codeParam = cb.parameter(String.class);

        Root<RegistrationCodesEntity> entity = q.from(RegistrationCodesEntity.class);
        q.select(entity);
        q.where(cb.equal(entity.get("code"), codeParam));

        TypedQuery<RegistrationCodesEntity> query = session.createQuery(q);
        query.setMaxResults(1);
        query.setParameter(codeParam, code);
        return DatabaseUtil.findOne(query.getResultList());
    }

    public List<UserCompromisedPasswordsEntity> findAllPasswordByHash (Session session, String hash) {

        CriteriaBuilder cb = session.getCriteriaBuilder();

        CriteriaQuery<UserCompromisedPasswordsEntity> q = cb.createQuery(UserCompromisedPasswordsEntity.class);
        ParameterExpression<String> hashParam = cb.parameter(String.class);

        Root<UserCompromisedPasswordsEntity> entity = q.from(UserCompromisedPasswordsEntity.class);
        q.select(entity);
        q.where(cb.equal(entity.get("password_hash"), hashParam));

        TypedQuery<UserCompromisedPasswordsEntity> query = session.createQuery(q);
        query.setMaxResults(1);
        query.setParameter(hashParam, hash + "%");
        return query.getResultList();
    }
}
