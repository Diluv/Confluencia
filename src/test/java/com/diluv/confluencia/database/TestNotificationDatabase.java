package com.diluv.confluencia.database;

import com.diluv.confluencia.Confluencia;
import com.diluv.confluencia.ConfluenciaTest;
import com.diluv.confluencia.database.record.NotificationProjectInvitesStatus;
import com.diluv.confluencia.database.record.NotificationType;
import com.diluv.confluencia.database.sort.NotificationSort;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestNotificationDatabase extends ConfluenciaTest {

    @Test
    public void findOneById () {

        Confluencia.getTransaction(session -> {
            Assertions.assertNotNull(Confluencia.NOTIFICATION.findOneById(session, 1));
            Assertions.assertNotNull(Confluencia.NOTIFICATION.findOneById(session, 6));
            Assertions.assertNull(Confluencia.NOTIFICATION.findOneById(session, 20));
        });
    }

    @Test
    public void countByUserId () {

        Confluencia.getTransaction(session -> {
            Assertions.assertEquals(1, Confluencia.NOTIFICATION.countByUserId(session, 1, NotificationType.GENERIC, null));
            Assertions.assertEquals(0, Confluencia.NOTIFICATION.countByUserId(session, 1, NotificationType.GENERIC, true));
            Assertions.assertEquals(1, Confluencia.NOTIFICATION.countByUserId(session, 1, NotificationType.GENERIC, false));
            Assertions.assertEquals(2, Confluencia.NOTIFICATION.countByUserId(session, 1, NotificationType.PROJECT_INVITE, null));
            Assertions.assertEquals(1, Confluencia.NOTIFICATION.countByUserId(session, 1, NotificationType.PROJECT_INVITE, true));
            Assertions.assertEquals(1, Confluencia.NOTIFICATION.countByUserId(session, 1, NotificationType.PROJECT_INVITE, false));

            Assertions.assertEquals(2, Confluencia.NOTIFICATION.countByUserId(session, 2, NotificationType.GENERIC, null));
            Assertions.assertEquals(1, Confluencia.NOTIFICATION.countByUserId(session, 2, NotificationType.GENERIC, true));
            Assertions.assertEquals(1, Confluencia.NOTIFICATION.countByUserId(session, 2, NotificationType.GENERIC, false));
            Assertions.assertEquals(4, Confluencia.NOTIFICATION.countByUserId(session, 2, NotificationType.PROJECT_INVITE, null));
            Assertions.assertEquals(0, Confluencia.NOTIFICATION.countByUserId(session, 2, NotificationType.PROJECT_INVITE, true));
            Assertions.assertEquals(4, Confluencia.NOTIFICATION.countByUserId(session, 2, NotificationType.PROJECT_INVITE, false));
        });
    }

    @Test
    public void findAllNotificationByUserId () {

        Confluencia.getTransaction(session -> {
            Assertions.assertEquals(1, Confluencia.NOTIFICATION.findAllByUserId(session, 1, 25, NotificationSort.NEW, 1, NotificationType.GENERIC, null).size());
            Assertions.assertEquals(0, Confluencia.NOTIFICATION.findAllByUserId(session, 1, 25, NotificationSort.NEW, 1, NotificationType.GENERIC, true).size());
            Assertions.assertEquals(1, Confluencia.NOTIFICATION.findAllByUserId(session, 1, 25, NotificationSort.NEW, 1, NotificationType.GENERIC, false).size());
            Assertions.assertEquals(2, Confluencia.NOTIFICATION.findAllByUserId(session, 1, 25, NotificationSort.NEW, 1, NotificationType.PROJECT_INVITE, null).size());
            Assertions.assertEquals(1, Confluencia.NOTIFICATION.findAllByUserId(session, 1, 25, NotificationSort.NEW, 1, NotificationType.PROJECT_INVITE, true).size());
            Assertions.assertEquals(1, Confluencia.NOTIFICATION.findAllByUserId(session, 1, 25, NotificationSort.NEW, 1, NotificationType.PROJECT_INVITE, false).size());

            Assertions.assertEquals(2, Confluencia.NOTIFICATION.findAllByUserId(session, 1, 25, NotificationSort.NEW, 2, NotificationType.GENERIC, null).size());
            Assertions.assertEquals(1, Confluencia.NOTIFICATION.findAllByUserId(session, 1, 25, NotificationSort.NEW, 2, NotificationType.GENERIC, true).size());
            Assertions.assertEquals(1, Confluencia.NOTIFICATION.findAllByUserId(session, 1, 25, NotificationSort.NEW, 2, NotificationType.GENERIC, false).size());
            Assertions.assertEquals(4, Confluencia.NOTIFICATION.findAllByUserId(session, 1, 25, NotificationSort.NEW, 2, NotificationType.PROJECT_INVITE, null).size());
            Assertions.assertEquals(0, Confluencia.NOTIFICATION.findAllByUserId(session, 1, 25, NotificationSort.NEW, 2, NotificationType.PROJECT_INVITE, true).size());
            Assertions.assertEquals(4, Confluencia.NOTIFICATION.findAllByUserId(session, 1, 25, NotificationSort.NEW, 2, NotificationType.PROJECT_INVITE, false).size());
        });
    }

    @Test
    public void countInvitesBySenderOrReceiver () {

        Confluencia.getTransaction(session -> {
            Assertions.assertEquals(7, Confluencia.NOTIFICATION.countInvitesBySenderOrReceiver(session, 1, null));
            Assertions.assertEquals(3, Confluencia.NOTIFICATION.countInvitesBySenderOrReceiver(session, 1, NotificationProjectInvitesStatus.PENDING));
            Assertions.assertEquals(1, Confluencia.NOTIFICATION.countInvitesBySenderOrReceiver(session, 1, NotificationProjectInvitesStatus.CANCELLED));
            Assertions.assertEquals(1, Confluencia.NOTIFICATION.countInvitesBySenderOrReceiver(session, 1, NotificationProjectInvitesStatus.ACCEPTED));
            Assertions.assertEquals(1, Confluencia.NOTIFICATION.countInvitesBySenderOrReceiver(session, 1, NotificationProjectInvitesStatus.DECLINED));
            Assertions.assertEquals(1, Confluencia.NOTIFICATION.countInvitesBySenderOrReceiver(session, 1, NotificationProjectInvitesStatus.EXPIRED));

            Assertions.assertEquals(6, Confluencia.NOTIFICATION.countInvitesBySenderOrReceiver(session, 2, null));
            Assertions.assertEquals(3, Confluencia.NOTIFICATION.countInvitesBySenderOrReceiver(session, 2, NotificationProjectInvitesStatus.PENDING));
            Assertions.assertEquals(1, Confluencia.NOTIFICATION.countInvitesBySenderOrReceiver(session, 2, NotificationProjectInvitesStatus.CANCELLED));
            Assertions.assertEquals(1, Confluencia.NOTIFICATION.countInvitesBySenderOrReceiver(session, 2, NotificationProjectInvitesStatus.ACCEPTED));
            Assertions.assertEquals(1, Confluencia.NOTIFICATION.countInvitesBySenderOrReceiver(session, 2, NotificationProjectInvitesStatus.DECLINED));
            Assertions.assertEquals(0, Confluencia.NOTIFICATION.countInvitesBySenderOrReceiver(session, 2, NotificationProjectInvitesStatus.EXPIRED));

            Assertions.assertEquals(1, Confluencia.NOTIFICATION.countInvitesBySenderOrReceiver(session, 3, null));
            Assertions.assertEquals(0, Confluencia.NOTIFICATION.countInvitesBySenderOrReceiver(session, 3, NotificationProjectInvitesStatus.PENDING));
            Assertions.assertEquals(0, Confluencia.NOTIFICATION.countInvitesBySenderOrReceiver(session, 3, NotificationProjectInvitesStatus.CANCELLED));
            Assertions.assertEquals(0, Confluencia.NOTIFICATION.countInvitesBySenderOrReceiver(session, 3, NotificationProjectInvitesStatus.ACCEPTED));
            Assertions.assertEquals(0, Confluencia.NOTIFICATION.countInvitesBySenderOrReceiver(session, 3, NotificationProjectInvitesStatus.DECLINED));
            Assertions.assertEquals(1, Confluencia.NOTIFICATION.countInvitesBySenderOrReceiver(session, 3, NotificationProjectInvitesStatus.EXPIRED));

        });
    }

    @Test
    public void findAllInvitesBySenderOrReceiver () {

        Confluencia.getTransaction(session -> {
            Assertions.assertEquals(7, Confluencia.NOTIFICATION.findAllInvitesBySenderOrReceiver(session, 1, 25, NotificationSort.NEW,1, null).size());
            Assertions.assertEquals(3, Confluencia.NOTIFICATION.findAllInvitesBySenderOrReceiver(session, 1, 25, NotificationSort.NEW,1, NotificationProjectInvitesStatus.PENDING).size());
            Assertions.assertEquals(1, Confluencia.NOTIFICATION.findAllInvitesBySenderOrReceiver(session, 1, 25, NotificationSort.NEW,1, NotificationProjectInvitesStatus.CANCELLED).size());
            Assertions.assertEquals(1, Confluencia.NOTIFICATION.findAllInvitesBySenderOrReceiver(session, 1, 25, NotificationSort.NEW,1, NotificationProjectInvitesStatus.ACCEPTED).size());
            Assertions.assertEquals(1, Confluencia.NOTIFICATION.findAllInvitesBySenderOrReceiver(session, 1, 25, NotificationSort.NEW,1, NotificationProjectInvitesStatus.DECLINED).size());
            Assertions.assertEquals(1, Confluencia.NOTIFICATION.findAllInvitesBySenderOrReceiver(session, 1, 25, NotificationSort.NEW,1, NotificationProjectInvitesStatus.EXPIRED).size());

            Assertions.assertEquals(6, Confluencia.NOTIFICATION.findAllInvitesBySenderOrReceiver(session,1, 25, NotificationSort.NEW, 2, null).size());
            Assertions.assertEquals(3, Confluencia.NOTIFICATION.findAllInvitesBySenderOrReceiver(session,1, 25, NotificationSort.NEW, 2, NotificationProjectInvitesStatus.PENDING).size());
            Assertions.assertEquals(1, Confluencia.NOTIFICATION.findAllInvitesBySenderOrReceiver(session,1, 25, NotificationSort.NEW, 2, NotificationProjectInvitesStatus.CANCELLED).size());
            Assertions.assertEquals(1, Confluencia.NOTIFICATION.findAllInvitesBySenderOrReceiver(session,1, 25, NotificationSort.NEW, 2, NotificationProjectInvitesStatus.ACCEPTED).size());
            Assertions.assertEquals(1, Confluencia.NOTIFICATION.findAllInvitesBySenderOrReceiver(session, 1, 25, NotificationSort.NEW,2, NotificationProjectInvitesStatus.DECLINED).size());
            Assertions.assertEquals(0, Confluencia.NOTIFICATION.findAllInvitesBySenderOrReceiver(session,1, 25, NotificationSort.NEW, 2, NotificationProjectInvitesStatus.EXPIRED).size());

            Assertions.assertEquals(1, Confluencia.NOTIFICATION.findAllInvitesBySenderOrReceiver(session,1, 25, NotificationSort.NEW, 3, null).size());
            Assertions.assertEquals(0, Confluencia.NOTIFICATION.findAllInvitesBySenderOrReceiver(session,1, 25, NotificationSort.NEW, 3, NotificationProjectInvitesStatus.PENDING).size());
            Assertions.assertEquals(0, Confluencia.NOTIFICATION.findAllInvitesBySenderOrReceiver(session, 1, 25, NotificationSort.NEW,3, NotificationProjectInvitesStatus.CANCELLED).size());
            Assertions.assertEquals(0, Confluencia.NOTIFICATION.findAllInvitesBySenderOrReceiver(session,1, 25, NotificationSort.NEW, 3, NotificationProjectInvitesStatus.ACCEPTED).size());
            Assertions.assertEquals(0, Confluencia.NOTIFICATION.findAllInvitesBySenderOrReceiver(session,1, 25, NotificationSort.NEW, 3, NotificationProjectInvitesStatus.DECLINED).size());
            Assertions.assertEquals(1, Confluencia.NOTIFICATION.findAllInvitesBySenderOrReceiver(session, 1, 25, NotificationSort.NEW,3, NotificationProjectInvitesStatus.EXPIRED).size());
        });
    }
}
