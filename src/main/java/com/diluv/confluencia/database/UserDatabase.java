package com.diluv.confluencia.database;

import com.diluv.confluencia.Confluencia;
import com.diluv.confluencia.database.record.TempUsersEntity;
import com.diluv.confluencia.database.record.UserChangeEmail;
import com.diluv.confluencia.database.record.UserMfaRecoveryEntity;
import com.diluv.confluencia.database.record.UsersEntity;

import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

import java.util.Collections;
import java.util.List;

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

    public boolean insertUserMFARecovery (List<UserMfaRecoveryEntity> entities) {

        Transaction transaction = null;
        try (Session session = Confluencia.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            for (UserMfaRecoveryEntity entity : entities) {
                session.save(entity);
            }
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

    public boolean deleteUserMFARecovery (UsersEntity user) {

        try {
            return Confluencia.getQuery((session, cb) -> {
                CriteriaDelete<UserMfaRecoveryEntity> q = cb.createCriteriaDelete(UserMfaRecoveryEntity.class);

                Root<UserMfaRecoveryEntity> entity = q.from(UserMfaRecoveryEntity.class);
                q.where(cb.equal(entity.get("user"), user));

                Query query = session.createQuery(q);
                return query.executeUpdate() >= 0;
            });
        }
        catch (Exception e) {
            return false;
        }
    }

    public List<UserMfaRecoveryEntity> findAllUserMfaRecoveryByUser (UsersEntity user) {

        try {
            return Confluencia.getQuery((session, cb) -> {
                CriteriaQuery<UserMfaRecoveryEntity> q = cb.createQuery(UserMfaRecoveryEntity.class);
                Root<UserMfaRecoveryEntity> entity = q.from(UserMfaRecoveryEntity.class);

                ParameterExpression<UsersEntity> s = cb.parameter(UsersEntity.class);

                q.select(entity);
                q.where(cb.equal(entity.get("user"), s));

                TypedQuery<UserMfaRecoveryEntity> query = session.createQuery(q);
                query.setParameter(s, user);
                return query.getResultList();
            });
        }
        catch (Exception e) {
            return Collections.emptyList();
        }
    }

    public boolean existsByEmail (String email) {

        try {
            return Confluencia.getQuery((session, cb) -> {

                CriteriaQuery<UsersEntity> q = cb.createQuery(UsersEntity.class);
                Root<UsersEntity> entity = q.from(UsersEntity.class);
                ParameterExpression<String> emailParam = cb.parameter(String.class);
                q.select(entity);
                q.where(cb.equal(entity.get("email"), emailParam));

                TypedQuery<UsersEntity> query = session.createQuery(q);
                query.setMaxResults(1);
                query.setParameter(emailParam, email);
                return query.getSingleResult() != null;
            });
        }
        catch (Exception e) {
            return false;
        }
    }

    public boolean existsTempUserByEmail (String email) {

        try {
            return Confluencia.getQuery((session, cb) -> {

                CriteriaQuery<TempUsersEntity> q = cb.createQuery(TempUsersEntity.class);
                Root<TempUsersEntity> entity = q.from(TempUsersEntity.class);
                ParameterExpression<String> emailParam = cb.parameter(String.class);
                q.select(entity);
                q.where(cb.equal(entity.get("email"), emailParam));

                TypedQuery<TempUsersEntity> query = session.createQuery(q);
                query.setMaxResults(1);
                query.setParameter(emailParam, email);
                return query.getSingleResult() != null;
            });
        }
        catch (Exception e) {
            return false;
        }
    }

    public boolean existUserChangeEmailByUser (UsersEntity user) {

        try {
            return Confluencia.getQuery((session, cb) -> {

                CriteriaQuery<UserChangeEmail> q = cb.createQuery(UserChangeEmail.class);
                Root<UserChangeEmail> entity = q.from(UserChangeEmail.class);
                ParameterExpression<UsersEntity> userParam = cb.parameter(UsersEntity.class);
                q.select(entity);
                q.where(cb.equal(entity.get("user"), userParam));

                TypedQuery<UserChangeEmail> query = session.createQuery(q);
                query.setMaxResults(1);
                query.setParameter(userParam, user);
                return query.getSingleResult() != null;
            });
        }
        catch (Exception e) {
            return false;
        }
    }

    public boolean existUserChangeEmailByEmail (String email) {

        try {
            return Confluencia.getQuery((session, cb) -> {

                CriteriaQuery<UserChangeEmail> q = cb.createQuery(UserChangeEmail.class);
                Root<UserChangeEmail> entity = q.from(UserChangeEmail.class);
                ParameterExpression<String> emailParam = cb.parameter(String.class);
                q.select(entity);
                q.where(cb.equal(entity.get("email"), emailParam));

                TypedQuery<UserChangeEmail> query = session.createQuery(q);
                query.setMaxResults(1);
                query.setParameter(emailParam, email);
                return query.getSingleResult() != null;
            });
        }
        catch (Exception e) {
            return false;
        }
    }

    public boolean insertUserChangeEmail (UserChangeEmail userEmail) {

        Transaction transaction = null;
        try (Session session = Confluencia.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(userEmail);
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

    public boolean deleteUserChangeEmail (UsersEntity user) {

        try {
            return Confluencia.getQuery((session, cb) -> {
                CriteriaDelete<UserChangeEmail> q = cb.createCriteriaDelete(UserChangeEmail.class);

                Root<UserChangeEmail> entity = q.from(UserChangeEmail.class);
                q.where(cb.equal(entity.get("user"), user));

                Query query = session.createQuery(q);
                return query.executeUpdate() >= 0;
            });
        }
        catch (Exception e) {
            return false;
        }
    }
}