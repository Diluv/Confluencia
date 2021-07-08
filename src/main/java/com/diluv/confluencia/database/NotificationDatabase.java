package com.diluv.confluencia.database;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.diluv.confluencia.database.record.NotificationProjectInvitesEntity;
import com.diluv.confluencia.database.record.NotificationProjectInvitesStatus;
import com.diluv.confluencia.database.record.NotificationType;
import com.diluv.confluencia.database.record.NotificationsEntity;
import com.diluv.confluencia.database.sort.Sort;
import com.diluv.confluencia.utils.DatabaseUtil;

public class NotificationDatabase {

    public NotificationsEntity findOneById (Session session, long id) {

        final String hql = "FROM NotificationsEntity WHERE id = :id";

        return DatabaseUtil.findOne(session.createQuery(hql, NotificationsEntity.class)
            .setParameter("id", id)
            .setMaxResults(1)
            .getResultList());
    }

    public long countByUserId (Session session, long userId, NotificationType type, Boolean isRead) {

        String hql = "SELECT COUNT(id) FROM NotificationsEntity WHERE user.id = :user_id\n";

        if (type != null) {
            hql += "AND type = :type\n";
        }

        if (isRead != null) {
            if (isRead) {
                hql += "AND viewedAt IS NOT NULL\n";
            }
            else {
                hql += "AND viewedAt IS NULL\n";
            }
        }

        final Query<Long> query = session.createQuery(hql, Long.class)
            .setParameter("user_id", userId);

        if (type != null) {
            query.setParameter("type", type);
        }

        return DatabaseUtil.findOne(query.getResultList(), 0L);
    }

    public List<NotificationsEntity> findAllByUserId (Session session, long page, int limit, Sort sort, long userId, NotificationType type, Boolean isRead) {

        String hql = "FROM NotificationsEntity WHERE user.id = :user_id\n";

        if (type != null) {
            hql += "AND type = :type\n";
        }

        if (isRead != null) {
            if (isRead) {
                hql += "AND viewedAt IS NOT NULL\n";
            }
            else {
                hql += "AND viewedAt IS NULL\n";
            }
        }

        hql += "ORDER BY :order_column " + sort.getOrder().name;

        final Query<NotificationsEntity> query = session.createQuery(hql, NotificationsEntity.class)
            .setParameter("user_id", userId)
            .setParameter("order_column", sort.getColumn())
            .setFirstResult((int) ((page - 1) * limit))
            .setMaxResults(limit);

        if (type != null) {
            query.setParameter("type", type);
        }

        return query.getResultList();
    }

    public boolean existsProjectInviteWhereProjectIdAndUser (Session session, long userId, long projectId) {

        final String hql = "SELECT id FROM NotificationProjectInvitesEntity WHERE user.id = :user_id AND project.id = :project_id";

        return DatabaseUtil.findOne(session.createQuery(hql, Long.class)
            .setParameter("user_id", userId)
            .setParameter("project_id", projectId)
            .setMaxResults(1)
            .getResultList()) != null;
    }

    public long countInvitesBySenderOrReceiver (Session session, long userId, NotificationProjectInvitesStatus status) {

        String hql = "SELECT COUNT(id) FROM NotificationProjectInvitesEntity WHERE (user.id = :user_id OR sender.id = :user_id)\n";

        if (status != null) {
            hql += "AND status = :status\n";
        }

        final Query<Long> query = session.createQuery(hql, Long.class)
            .setParameter("user_id", userId)
            .setMaxResults(1);

        if (status != null) {
            query.setParameter("status", status);
        }

        return DatabaseUtil.findOne(query.getResultList(), 0L);
    }

    public List<NotificationProjectInvitesEntity> findAllInvitesBySenderOrReceiver (Session session, long page, int limit, Sort sort, long userId, NotificationProjectInvitesStatus status) {

        String hql = "FROM NotificationProjectInvitesEntity WHERE (user.id = :user_id OR sender.id = :user_id)\n";

        if (status != null) {
            hql += "AND status = :status\n";
        }

        hql += "ORDER BY :order_column " + sort.getOrder().name;

        final Query<NotificationProjectInvitesEntity> query = session.createQuery(hql, NotificationProjectInvitesEntity.class)
            .setFirstResult((int) ((page - 1) * limit))
            .setMaxResults(limit)
            .setParameter("order_column", sort.getColumn())
            .setParameter("user_id", userId);

        if (status != null) {
            query.setParameter("status", status);
        }

        return query.getResultList();
    }
}
