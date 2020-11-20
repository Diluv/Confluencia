package com.diluv.confluencia.database;

import java.sql.Timestamp;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

import org.hibernate.Session;

import com.diluv.confluencia.database.record.TempUsersEntity;
import com.diluv.confluencia.database.record.UserChangeEmail;
import com.diluv.confluencia.database.record.UserMfaEmailEntity;
import com.diluv.confluencia.database.record.UserMfaRecoveryEntity;
import com.diluv.confluencia.database.record.UsersEntity;
import com.diluv.confluencia.utils.DatabaseUtil;

public class UserDatabase {

    public long countAll (Session session) {

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Long> q = cb.createQuery(Long.class);

        Root<UsersEntity> entity = q.from(UsersEntity.class);
        q.select(cb.count(entity));

        TypedQuery<Long> query = session.createQuery(q);
        return DatabaseUtil.findOne(query.getResultList(), 0L);
    }

    public UsersEntity findOneByUsername (Session session, String username) {

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<UsersEntity> q = cb.createQuery(UsersEntity.class);

        ParameterExpression<String> userParam = cb.parameter(String.class);

        Root<UsersEntity> entity = q.from(UsersEntity.class);
        q.select(entity);
        q.where(cb.equal(entity.get("username"), userParam));

        TypedQuery<UsersEntity> query = session.createQuery(q);
        query.setParameter(userParam, username);
        return DatabaseUtil.findOne(query.getResultList());
    }

    public UsersEntity findOneByUserId (Session session, long userId) {

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<UsersEntity> q = cb.createQuery(UsersEntity.class);

        ParameterExpression<Long> userParam = cb.parameter(Long.class);

        Root<UsersEntity> entity = q.from(UsersEntity.class);
        q.select(entity);
        q.where(cb.equal(entity.get("id"), userParam));

        TypedQuery<UsersEntity> query = session.createQuery(q);
        query.setParameter(userParam, userId);
        return DatabaseUtil.findOne(query.getResultList());

    }

    public boolean deleteUserMFARecovery (Session session, UsersEntity user) {

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaDelete<UserMfaRecoveryEntity> q = cb.createCriteriaDelete(UserMfaRecoveryEntity.class);

        Root<UserMfaRecoveryEntity> entity = q.from(UserMfaRecoveryEntity.class);
        q.where(cb.equal(entity.get("user"), user));

        Query query = session.createQuery(q);
        return query.executeUpdate() >= 0;
    }

    public List<UserMfaRecoveryEntity> findAllUserMfaRecoveryByUser (Session session, UsersEntity user) {

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<UserMfaRecoveryEntity> q = cb.createQuery(UserMfaRecoveryEntity.class);
        Root<UserMfaRecoveryEntity> entity = q.from(UserMfaRecoveryEntity.class);

        ParameterExpression<UsersEntity> s = cb.parameter(UsersEntity.class);

        q.select(entity);
        q.where(cb.equal(entity.get("user"), s));

        TypedQuery<UserMfaRecoveryEntity> query = session.createQuery(q);
        query.setParameter(s, user);
        return query.getResultList();
    }

    public boolean existsByEmail (Session session, String email) {

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<UsersEntity> q = cb.createQuery(UsersEntity.class);
        Root<UsersEntity> entity = q.from(UsersEntity.class);
        ParameterExpression<String> emailParam = cb.parameter(String.class);
        q.select(entity);
        q.where(cb.equal(entity.get("email"), emailParam));

        TypedQuery<UsersEntity> query = session.createQuery(q);
        query.setMaxResults(1);
        query.setParameter(emailParam, email);
        return DatabaseUtil.findOne(query.getResultList()) != null;
    }

    public boolean existsTempUserByEmail (Session session, String email) {

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<TempUsersEntity> q = cb.createQuery(TempUsersEntity.class);
        Root<TempUsersEntity> entity = q.from(TempUsersEntity.class);
        ParameterExpression<String> emailParam = cb.parameter(String.class);
        q.select(entity);
        q.where(cb.equal(entity.get("email"), emailParam));

        TypedQuery<TempUsersEntity> query = session.createQuery(q);
        query.setMaxResults(1);
        query.setParameter(emailParam, email);
        return DatabaseUtil.findOne(query.getResultList()) != null;
    }

    public boolean existUserChangeEmailByUser (Session session, UsersEntity user) {

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<UserChangeEmail> q = cb.createQuery(UserChangeEmail.class);
        Root<UserChangeEmail> entity = q.from(UserChangeEmail.class);
        ParameterExpression<UsersEntity> userParam = cb.parameter(UsersEntity.class);
        q.select(entity);
        q.where(cb.equal(entity.get("user"), userParam));

        TypedQuery<UserChangeEmail> query = session.createQuery(q);
        query.setMaxResults(1);
        query.setParameter(userParam, user);
        return DatabaseUtil.findOne(query.getResultList()) != null;
    }

    public boolean existUserChangeEmailByEmail (Session session, String email) {

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<UserChangeEmail> q = cb.createQuery(UserChangeEmail.class);
        Root<UserChangeEmail> entity = q.from(UserChangeEmail.class);
        ParameterExpression<String> emailParam = cb.parameter(String.class);
        q.select(entity);
        q.where(cb.equal(entity.get("email"), emailParam));

        TypedQuery<UserChangeEmail> query = session.createQuery(q);
        query.setMaxResults(1);
        query.setParameter(emailParam, email);
        return DatabaseUtil.findOne(query.getResultList()) != null;
    }

    public boolean deleteUserChangeEmail (Session session, UsersEntity user) {

        CriteriaBuilder cb = session.getCriteriaBuilder();

        CriteriaDelete<UserChangeEmail> q = cb.createCriteriaDelete(UserChangeEmail.class);

        Root<UserChangeEmail> entity = q.from(UserChangeEmail.class);
        q.where(cb.equal(entity.get("user"), user));

        Query query = session.createQuery(q);
        return query.executeUpdate() >= 0;
    }

    public UserMfaRecoveryEntity findUserMFARecovery (Session session, UsersEntity user, String code) {

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<UserMfaRecoveryEntity> q = cb.createQuery(UserMfaRecoveryEntity.class);
        Root<UserMfaRecoveryEntity> entity = q.from(UserMfaRecoveryEntity.class);
        ParameterExpression<UsersEntity> userParam = cb.parameter(UsersEntity.class);
        ParameterExpression<String> codeParam = cb.parameter(String.class);
        q.select(entity);
        q.where(cb.and(
            cb.equal(entity.get("user"), userParam),
            cb.equal(entity.get("code"), codeParam),
            cb.isTrue(entity.get("valid"))
        ));

        TypedQuery<UserMfaRecoveryEntity> query = session.createQuery(q);
        query.setMaxResults(1);
        query.setParameter(userParam, user);
        query.setParameter(codeParam, code);
        return DatabaseUtil.findOne(query.getResultList());
    }

    public UserMfaEmailEntity findUserMFAEmail (Session session, UsersEntity user, String code) {

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<UserMfaEmailEntity> q = cb.createQuery(UserMfaEmailEntity.class);
        Root<UserMfaEmailEntity> entity = q.from(UserMfaEmailEntity.class);
        ParameterExpression<UsersEntity> userParam = cb.parameter(UsersEntity.class);
        ParameterExpression<String> codeParam = cb.parameter(String.class);
        q.select(entity);
        q.where(cb.and(
            cb.equal(entity.get("user"), userParam),
            cb.equal(entity.get("code"), codeParam)
        ));

        TypedQuery<UserMfaEmailEntity> query = session.createQuery(q);
        query.setMaxResults(1);
        query.setParameter(userParam, user);
        query.setParameter(codeParam, code);
        return DatabaseUtil.findOne(query.getResultList());
    }
}