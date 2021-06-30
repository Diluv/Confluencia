package com.diluv.confluencia.database;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.LockModeType;

import org.hibernate.Session;

import com.diluv.confluencia.database.record.FileProcessingStatus;
import com.diluv.confluencia.database.record.ProjectFilesEntity;
import com.diluv.confluencia.database.record.ProjectsEntity;
import com.diluv.confluencia.database.sort.Sort;
import com.diluv.confluencia.utils.DatabaseUtil;

public class FileDatabase {

    public boolean updateStatusById (Session session, FileProcessingStatus status, long id) {

        final String hql = "UPDATE ProjectFilesEntity SET processingStatus = :status, processingStatusChanged = :processing_time WHERE id = :id";

        return session.createQuery(hql)
            .setParameter("status", status)
            .setParameter("processing_time", new Timestamp(System.currentTimeMillis()))
            .setParameter("id", id)
            .executeUpdate() == 1;
    }

    public boolean updateStatusByStatus (Session session, FileProcessingStatus set, FileProcessingStatus where) {

        final String hql = "UPDATE ProjectFilesEntity SET processingStatus = :status, processingStatusChanged = :processing_time WHERE processingStatus = :where_status";

        return session.createQuery(hql)
            .setParameter("status", set)
            .setParameter("processing_time", new Timestamp(System.currentTimeMillis()))
            .setParameter("where_status", where)
            .executeUpdate() >= 0;
    }

    public List<ProjectFilesEntity> findAllWhereStatusAndLimit (Session session, FileProcessingStatus status, int amount) {

        final String hql = "FROM ProjectFilesEntity pf WHERE pf.processingStatus = :status AND released = FALSE AND pf.project.id IN (SELECT p.id FROM ProjectsEntity p WHERE p.released = TRUE) ORDER BY createdAt";

        return session.createQuery(hql, ProjectFilesEntity.class)
            .setParameter("status", status)
            .setMaxResults(amount)
            .getResultList();
    }

    public List<ProjectFilesEntity> getLatestFiles (Session session, int amount) {

        final String hql = "FROM ProjectFilesEntity pf WHERE pf.processingStatus = :status AND pf.project.id IN (SELECT p.id FROM ProjectsEntity p WHERE released = TRUE) ORDER BY createdAt ASC";

        List<ProjectFilesEntity> fileQueue = session.createQuery(hql, ProjectFilesEntity.class)
            .setParameter("status", FileProcessingStatus.PENDING)
            .setMaxResults(amount)
            .setLockMode(LockModeType.PESSIMISTIC_WRITE)
            .getResultList();

        final Long[] idList = fileQueue.stream().map(ProjectFilesEntity::getId).toArray(Long[]::new);
        for (final Long id : idList) {

            if (session.createQuery("UPDATE ProjectFilesEntity SET processingStatus = :status WHERE id = :id")
                .setParameter("status", FileProcessingStatus.PENDING)
                .setParameter("id", id)
                .executeUpdate() != 1) {
                throw new RuntimeException("Failed to update status");
            }
        }

        return fileQueue;
    }

    public ProjectFilesEntity findOneById (Session session, long id) {

        final String hql = "FROM ProjectFilesEntity where id = :id";

        return DatabaseUtil.findOne(session.createQuery(hql, ProjectFilesEntity.class)
            .setParameter("id", id)
            .getResultList());
    }

    public List<ProjectFilesEntity> findAllByProject (Session session, long projectId, boolean authorized, long page, int limit, Sort sort, String gameVersion, String search) {

        final String hql = "FROM ProjectFilesEntity pf\n" +
            "WHERE pf.project.id = :project_id\n" +
            "AND pf.name LIKE :search\n" +
            "AND (:authorized = TRUE OR pf.released = TRUE)\n" +
            "AND (:skip_version = TRUE OR pf.id IN (SELECT pfgv.projectFile.id FROM ProjectFileGameVersionsEntity pfgv WHERE pfgv.gameVersion.version = :game_version AND pfgv.projectFile.project.id = :project_id))";

        return session.createQuery(hql, ProjectFilesEntity.class)
            .setParameter("project_id", projectId)
            .setParameter("search", "%" + search + "%")
            .setParameter("authorized", authorized)
            .setParameter("skip_version", gameVersion == null)
            .setParameter("game_version", gameVersion)
            .setFirstResult((int) ((page - 1) * limit))
            .setMaxResults(limit)
            .getResultList();
    }

    public boolean existsByProjectIdAndVersion (Session session, long projectId, String version) {

        final String hql = "SELECT id FROM ProjectFilesEntity WHERE project.id = :project_id AND version = :version";

        return DatabaseUtil.findOne(session.createQuery(hql, Long.class)
            .setParameter("project_id", projectId)
            .setParameter("version", version)
            .setMaxResults(1)
            .getResultList()) != null;
    }

    public int updateAllForRelease (Session session, Timestamp time) {

        final String hql = "UPDATE ProjectFilesEntity SET released = TRUE WHERE released = FALSE and processingStatus = :status AND processingStatusChanged <= :processing_time";

        return session.createQuery(hql)
            .setParameter("status", FileProcessingStatus.SUCCESS)
            .setParameter("processing_time", time)
            .executeUpdate();
    }

    public List<ProjectFilesEntity> findProjectFilesByHash (Session session, String sha512, long page, int limit, Sort sort) {

        final String hql = "FROM ProjectFilesEntity where sha512 = :sha512 AND released = TRUE ORDER BY :order_column " + sort.getOrder().name;

        return session.createQuery(hql, ProjectFilesEntity.class)
            .setParameter("sha512", sha512)
            .setParameter("order_column", sort.getColumn())
            .setFirstResult((int) ((page - 1) * limit))
            .setMaxResults(limit)
            .getResultList();
    }

    public long countByProjectParams (Session session, long projecetId, boolean authorized, String gameVersion, String search) {

        final String hql = "SELECT COUNT(*) FROM ProjectFilesEntity pf\n" +
            "WHERE pf.project.id = :project_id\n" +
            "AND pf.name LIKE :search\n" +
            "AND (:authorized = TRUE OR pf.released = TRUE)\n" +
            "AND (:skip_version = TRUE OR pf.id IN (SELECT pfgv.projectFile.id FROM ProjectFileGameVersionsEntity pfgv WHERE pfgv.gameVersion.version = :game_version AND pfgv.projectFile.project.id = :project_id))";

        return DatabaseUtil.findOne(session.createQuery(hql, Long.class)
            .setParameter("project_id", projecetId)
            .setParameter("search", "%" + search + "%")
            .setParameter("authorized", authorized)
            .setParameter("skip_version", gameVersion == null)
            .setParameter("game_version", gameVersion)
            .getResultList(), 0L);
    }

    public long countAllFileSize (Session session) {

        final String hql = "SELECT SUM(pf.size) FROM ProjectFilesEntity pf";

        return DatabaseUtil.findOne(session.createQuery(hql, Long.class)
            .getResultList(), 0L);
    }
}
