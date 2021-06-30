package com.diluv.confluencia.database;

import java.sql.Timestamp;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.hibernate.Session;

import com.diluv.confluencia.database.record.TempUsersEntity;
import com.diluv.confluencia.database.record.UserMfaEmailEntity;
import com.diluv.confluencia.database.record.UserMfaRecoveryEntity;
import com.diluv.confluencia.database.record.UsersEntity;
import com.diluv.confluencia.utils.DatabaseUtil;

public class UserDatabase {

    public long countAll (Session session) {

        final String hql = "SELECT COUNT(*) FROM UsersEntity";

        return DatabaseUtil.findOne(session.createQuery(hql, Long.class)
            .getResultList(), 0L);
    }

    public long countAllTempUsers (Session session) {

        final String hql = "SELECT COUNT(*) FROM TempUsersEntity";

        return DatabaseUtil.findOne(session.createQuery(hql, Long.class)
            .getResultList(), 0L);
    }

    public UsersEntity findOneByEmail (Session session, String email) {

        final String hql = "FROM UsersEntity WHERE email = :email";

        return DatabaseUtil.findOne(session.createQuery(hql, UsersEntity.class)
            .setParameter("email", email)
            .getResultList());
    }

    public UsersEntity findOneByUsername (Session session, String username) {

        final String hql = "FROM UsersEntity WHERE username = :username";

        return DatabaseUtil.findOne(session.createQuery(hql, UsersEntity.class)
            .setParameter("username", username)
            .getResultList());
    }

    public UsersEntity findOneByUserId (Session session, long userId) {

        final String hql = "FROM UsersEntity WHERE id = :user_id";

        return DatabaseUtil.findOne(session.createQuery(hql, UsersEntity.class)
            .setParameter("user_id", userId)
            .getResultList());
    }

    public boolean deleteUserMFARecovery (Session session, long userId) {

        final String hql = "DELETE UserMfaRecoveryEntity WHERE user.id = :user_id";

        return session.createQuery(hql)
            .setParameter("user_id", userId)
            .executeUpdate() >= 0;
    }

    public List<UserMfaRecoveryEntity> findAllUserMfaRecoveryByUser (Session session, long userId) {

        final String hql = "FROM UserMfaRecoveryEntity WHERE user.id = :user_id";

        return session.createQuery(hql, UserMfaRecoveryEntity.class)
            .setParameter("user_id", userId)
            .getResultList();
    }

    public boolean existsByEmail (Session session, String email) {

        final String hql = "SELECT id FROM UsersEntity WHERE email = :email";

        return DatabaseUtil.findOne(session.createQuery(hql, Long.class)
            .setParameter("email", email)
            .getResultList()) != null;
    }

    public boolean existsTempUserByEmail (Session session, String email) {

        final String hql = "SELECT id FROM TempUsersEntity WHERE email = :email";

        return DatabaseUtil.findOne(session.createQuery(hql, Long.class)
            .setParameter("email", email)
            .getResultList()) != null;
    }

    public boolean existUserChangeEmailByUser (Session session, long userId) {

        final String hql = "SELECT user.id FROM UserChangeEmail WHERE user.id = :user_id";

        return DatabaseUtil.findOne(session.createQuery(hql, Long.class)
            .setParameter("user_id", userId)
            .getResultList()) != null;
    }

    public boolean existUserChangeEmailByEmail (Session session, String email) {

        final String hql = "SELECT user.id FROM UserChangeEmail WHERE email = :email";

        return DatabaseUtil.findOne(session.createQuery(hql, Long.class)
            .setParameter("email", email)
            .getResultList()) != null;
    }

    public boolean deleteUserChangeEmail (Session session, long userId) {

        final String hql = "DELETE UserChangeEmail WHERE user.id = :user_id";

        return session.createQuery(hql)
            .setParameter("user_id", userId)
            .executeUpdate() >= 0;
    }

    public UserMfaRecoveryEntity findUserMFARecovery (Session session, long userId, String code) {

        final String hql = "FROM UserMfaRecoveryEntity WHERE user.id = :user_id AND code = :code AND valid = TRUE";

        return DatabaseUtil.findOne(session.createQuery(hql, UserMfaRecoveryEntity.class)
            .setParameter("user_id", userId)
            .setParameter("code", code)
            .getResultList());
    }


    public UserMfaEmailEntity findUserMFAEmail (Session session, long userId) {

        final String hql = "FROM UserMfaEmailEntity WHERE user.id = :user_id AND createdAt >= :created_at";

        return DatabaseUtil.findOne(session.createQuery(hql, UserMfaEmailEntity.class)
            .setParameter("user_id", userId)
            .setParameter("created_at", new Timestamp(System.currentTimeMillis() - TimeUnit.MINUTES.toMillis(10)))
            .getResultList());
    }

    public UserMfaEmailEntity findUserMFAEmail (Session session, long userId, String code) {

        final String hql = "FROM UserMfaEmailEntity WHERE user.id = :user_id AND code= :code";

        return DatabaseUtil.findOne(session.createQuery(hql, UserMfaEmailEntity.class)
            .setParameter("user_id", userId)
            .setParameter("code", code)
            .getResultList());
    }

    public TempUsersEntity findTempUserByUsername (Session session, String username) {

        final String hql = "FROM TempUsersEntity WHERE username = :username";

        return DatabaseUtil.findOne(session.createQuery(hql, TempUsersEntity.class)
            .setParameter("username", username)
            .getResultList());
    }

    public TempUsersEntity findTempUserByEmail (Session session, String email) {

        final String hql = "FROM TempUsersEntity WHERE email = :email";

        return DatabaseUtil.findOne(session.createQuery(hql, TempUsersEntity.class)
            .setParameter("email", email)
            .getResultList());
    }

    public List<UsersEntity> findLimitBySearch (Session session, String search) {

        final String hql = "FROM UsersEntity WHERE username LIKE :username";

        return session.createQuery(hql, UsersEntity.class)
            .setParameter("username", "%" + search + "%")
            .setMaxResults(5)
            .list();
    }
}