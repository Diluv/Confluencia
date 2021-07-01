package com.diluv.confluencia.database;

import java.util.List;

import org.hibernate.Session;

import com.diluv.confluencia.database.record.APITokensEntity;
import com.diluv.confluencia.database.record.ContainsUsernameBlockListEntity;
import com.diluv.confluencia.database.record.EmailBlockListEntity;
import com.diluv.confluencia.database.record.EmailDomainBlocklistEntity;
import com.diluv.confluencia.database.record.NodeCDNCommitsEntity;
import com.diluv.confluencia.database.record.RegistrationCodesEntity;
import com.diluv.confluencia.database.record.UserCompromisedPasswordsEntity;
import com.diluv.confluencia.database.record.UsernameBlocklistEntity;
import com.diluv.confluencia.utils.DatabaseUtil;

public class SecurityDatabase {

    public APITokensEntity findAPITokensById (Session session, long id) {

        final String hql = "FROM APITokensEntity WHERE id = :id";

        return DatabaseUtil.findOne(session.createQuery(hql, APITokensEntity.class)
            .setMaxResults(1)
            .setParameter("id", id)
            .getResultList());
    }

    public List<APITokensEntity> findAPITokensByUserId (Session session, long userId) {

        final String hql = "FROM APITokensEntity WHERE user.id = :user_id";

        return session.createQuery(hql, APITokensEntity.class)
            .setParameter("user_id", userId)
            .getResultList();
    }

    public APITokensEntity findAPITokensByToken (Session session, String token) {

        final String hql = "FROM APITokensEntity WHERE token = :token";

        return DatabaseUtil.findOne(session.createQuery(hql, APITokensEntity.class)
            .setMaxResults(1)
            .setParameter("token", token)
            .getResultList());
    }

    public NodeCDNCommitsEntity findOneNodeCDNCommitsByHash (Session session, String hash) {

        final String hql = "FROM NodeCDNCommitsEntity WHERE completed = FALSE AND hash = :hash";

        return DatabaseUtil.findOne(session.createQuery(hql, NodeCDNCommitsEntity.class)
            .setMaxResults(1)
            .setParameter("hash", hash)
            .getResultList());
    }

    public NodeCDNCommitsEntity findLatestNodeCDNCommits (Session session) {

        final String hql = "FROM NodeCDNCommitsEntity WHERE completed = FALSE";

        return DatabaseUtil.findOne(session.createQuery(hql, NodeCDNCommitsEntity.class)
            .setMaxResults(1)
            .getResultList());
    }

    public boolean existsUsernameBlockList (Session session, String username) {

        final String hql = "FROM UsernameBlocklistEntity WHERE username = :username";

        return DatabaseUtil.findOne(session.createQuery(hql, UsernameBlocklistEntity.class)
            .setMaxResults(1)
            .setParameter("username", username)
            .getResultList()) != null;
    }

    public boolean existsContainsUsernameBlockList (Session session, String username) {

        final String hql = "FROM ContainsUsernameBlockListEntity WHERE :username LIKE CONCAT('%', username, '%')";

        return DatabaseUtil.findOne(session.createQuery(hql, ContainsUsernameBlockListEntity.class)
            .setMaxResults(1)
            .setParameter("username", username)
            .getResultList()) != null;
    }

    public boolean existsEmailDomainBlockList (Session session, String domain) {

        final String hql = "FROM EmailDomainBlocklistEntity WHERE domain = :domain";

        return DatabaseUtil.findOne(session.createQuery(hql, EmailDomainBlocklistEntity.class)
            .setMaxResults(1)
            .setParameter("domain", domain)
            .getResultList()) != null;
    }

    public boolean existsEmailBlockList (Session session, String email) {

        final String hql = "FROM EmailBlockListEntity WHERE email = :email";

        return DatabaseUtil.findOne(session.createQuery(hql, EmailBlockListEntity.class)
            .setMaxResults(1)
            .setParameter("email", email)
            .getResultList()) != null;
    }

    public RegistrationCodesEntity findRegistrationCode (Session session, String code) {

        final String hql = "FROM RegistrationCodesEntity WHERE code = :code";

        return DatabaseUtil.findOne(session.createQuery(hql, RegistrationCodesEntity.class)
            .setMaxResults(1)
            .setParameter("code", code)
            .getResultList());
    }

    public List<UserCompromisedPasswordsEntity> findAllPasswordByHash (Session session, String hash) {

        final String hql = "FROM UserCompromisedPasswordsEntity WHERE passwordHash LIKE :password_hash";

        return session.createQuery(hql, UserCompromisedPasswordsEntity.class)
            .setParameter("password_hash", hash + "%")
            .getResultList();
    }
}
