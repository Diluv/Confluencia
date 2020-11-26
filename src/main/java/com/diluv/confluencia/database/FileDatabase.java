package com.diluv.confluencia.database;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.LockModeType;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

import org.hibernate.Session;

import com.diluv.confluencia.database.record.FileProcessingStatus;
import com.diluv.confluencia.database.record.GameVersionsEntity;
import com.diluv.confluencia.database.record.GamesEntity;
import com.diluv.confluencia.database.record.ProjectFileGameVersionsEntity;
import com.diluv.confluencia.database.record.ProjectFilesEntity;
import com.diluv.confluencia.database.record.ProjectsEntity;
import com.diluv.confluencia.database.sort.Order;
import com.diluv.confluencia.database.sort.Sort;
import com.diluv.confluencia.utils.DatabaseUtil;

public class FileDatabase {

    public boolean updateStatusById (Session session, FileProcessingStatus status, long id) {

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaUpdate<ProjectFilesEntity> q = cb.createCriteriaUpdate(ProjectFilesEntity.class);
        Root<ProjectFilesEntity> entity = q.from(ProjectFilesEntity.class);

        q.set(entity.get("processingStatus"), status.ordinal());
        q.set(entity.get("processingStatusChanged"), new Timestamp(System.currentTimeMillis()));
        q.where(cb.equal(entity.get("id"), id));

        return session.createQuery(q).executeUpdate() == 1;
    }

    public boolean updateStatusByStatus (Session session, FileProcessingStatus set, FileProcessingStatus where) {

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaUpdate<ProjectFilesEntity> q = cb.createCriteriaUpdate(ProjectFilesEntity.class);
        Root<ProjectFilesEntity> entity = q.from(ProjectFilesEntity.class);

        q.set(entity.get("processingStatus"), set.ordinal());
        q.set(entity.get("processingStatusChanged"), new Timestamp(System.currentTimeMillis()));
        q.where(cb.equal(entity.get("processingStatus"), where.ordinal()));

        session.createQuery(q).executeUpdate();
        return true;
    }

    public List<ProjectFilesEntity> findAllWhereStatusAndLimit (Session session, FileProcessingStatus status, int amount) {

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<ProjectFilesEntity> q = cb.createQuery(ProjectFilesEntity.class);
        Root<ProjectFilesEntity> entity = q.from(ProjectFilesEntity.class);

        ParameterExpression<FileProcessingStatus> s = cb.parameter(FileProcessingStatus.class);

        Subquery<ProjectsEntity> projectSubquery = q.subquery(ProjectsEntity.class);
        Root<ProjectsEntity> project = projectSubquery.from(ProjectsEntity.class);
        projectSubquery.select(project.get("id"));
        projectSubquery.where(cb.isTrue(project.get("released")));

        q.select(entity);
        q.where(cb.and(
            cb.equal(entity.get("processingStatus"), s),
            cb.isFalse(entity.get("released"))),
            cb.in(entity.get("project")).value(projectSubquery)
        );
        q.orderBy(cb.asc(entity.get("createdAt")));

        TypedQuery<ProjectFilesEntity> query = session.createQuery(q);
        query.setParameter(s, status);
        query.setMaxResults(amount);
        return query.getResultList();
    }

    public List<ProjectFilesEntity> getLatestFiles (Session session, int amount) {

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<ProjectFilesEntity> q = cb.createQuery(ProjectFilesEntity.class);
        Root<ProjectFilesEntity> entity = q.from(ProjectFilesEntity.class);

        ParameterExpression<FileProcessingStatus> s = cb.parameter(FileProcessingStatus.class);

        Subquery<ProjectsEntity> projectSubquery = q.subquery(ProjectsEntity.class);
        Root<ProjectsEntity> project = projectSubquery.from(ProjectsEntity.class);
        projectSubquery.select(project.get("id"));
        projectSubquery.where(cb.isTrue(project.get("released")));

        q.select(entity);
        q.where(cb.and(
            cb.equal(entity.get("processingStatus"), s),
            cb.in(entity.get("project")).value(projectSubquery)
        ));
        q.orderBy(cb.asc(entity.get("createdAt")));

        TypedQuery<ProjectFilesEntity> query = session.createQuery(q);
        query.setParameter(s, FileProcessingStatus.PENDING);
        query.setMaxResults(amount);
        query.setLockMode(LockModeType.PESSIMISTIC_WRITE);
        List<ProjectFilesEntity> fileQueue = query.getResultList();

        final Long[] idList = fileQueue.stream().map(ProjectFilesEntity::getId).toArray(Long[]::new);
        for (final Long id : idList) {

            CriteriaUpdate<ProjectFilesEntity> qU = cb.createCriteriaUpdate(ProjectFilesEntity.class);
            Root<ProjectFilesEntity> entityUpdate = qU.from(ProjectFilesEntity.class);

            qU.set(entityUpdate.get("processingStatus"), FileProcessingStatus.RUNNING.ordinal());
            qU.where(cb.equal(entityUpdate.get("id"), id));

            if (session.createQuery(qU).executeUpdate() != 1) {
                throw new RuntimeException("Failed to update status");
            }
        }

        return fileQueue;
    }

    public ProjectFilesEntity findOneById (Session session, long fileId) {

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<ProjectFilesEntity> q = cb.createQuery(ProjectFilesEntity.class);
        Root<ProjectFilesEntity> entity = q.from(ProjectFilesEntity.class);

        ParameterExpression<Long> projectIdParam = cb.parameter(Long.class);

        q.select(entity);
        q.where(cb.equal(entity.get("id"), projectIdParam));

        TypedQuery<ProjectFilesEntity> query = session.createQuery(q);
        query.setParameter(projectIdParam, fileId);
        return DatabaseUtil.findOne(query.getResultList());
    }

    public List<ProjectFilesEntity> findAllByProject (Session session, ProjectsEntity project, boolean authorized, long page, int limit, Sort sort, String gameVersion) {

        CriteriaBuilder cb = session.getCriteriaBuilder();

        CriteriaQuery<ProjectFilesEntity> q = cb.createQuery(ProjectFilesEntity.class);
        Root<ProjectFilesEntity> entity = q.from(ProjectFilesEntity.class);

        ParameterExpression<ProjectsEntity> projectParam = cb.parameter(ProjectsEntity.class);
        ParameterExpression<String> versionParam = cb.parameter(String.class);
        ParameterExpression<GamesEntity> gameParam = cb.parameter(GamesEntity.class);

        q.select(entity);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(cb.equal(entity.get("project"), projectParam));
        if (!authorized) {
            predicates.add(cb.isTrue(entity.get("released")));
        }

        if (gameVersion != null) {
            Root<ProjectFileGameVersionsEntity> entityProjectFileGameVersions = q.from(ProjectFileGameVersionsEntity.class);
            Root<GameVersionsEntity> entityGameVersions = q.from(GameVersionsEntity.class);
            predicates.add(cb.equal(entityProjectFileGameVersions.get("projectFile"), entity));
            predicates.add(cb.equal(entityProjectFileGameVersions.get("gameVersion"), entityGameVersions));
            predicates.add(cb.equal(entityGameVersions.get("game"), gameParam));
            predicates.add(cb.equal(entityGameVersions.get("version"), versionParam));
        }
        q.where(cb.and(predicates.toArray(new Predicate[0])));
        if (sort.getOrder() == Order.ASC) {
            q.orderBy(cb.asc(entity.get(sort.getColumn())));
        }
        else {
            q.orderBy(cb.desc(entity.get(sort.getColumn())));
        }

        TypedQuery<ProjectFilesEntity> query = session.createQuery(q);
        query.setParameter(projectParam, project);
        if (gameVersion != null) {
            query.setParameter(gameParam, project.getGame());
            query.setParameter(versionParam, gameVersion);
        }
        query.setFirstResult((int) ((page - 1) * limit));
        query.setMaxResults(limit);
        return query.getResultList();
    }

    public boolean existsByProjectIdAndVersion (Session session, long projectId, String version) {

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<ProjectFilesEntity> q = cb.createQuery(ProjectFilesEntity.class);
        Root<ProjectFilesEntity> entity = q.from(ProjectFilesEntity.class);
        ParameterExpression<ProjectsEntity> projectParam = cb.parameter(ProjectsEntity.class);
        ParameterExpression<String> versionParam = cb.parameter(String.class);
        q.select(entity);
        q.where(cb.and(cb.equal(entity.get("project"), projectParam), cb.equal(entity.get("version"), versionParam)));

        TypedQuery<ProjectFilesEntity> query = session.createQuery(q);
        query.setMaxResults(1);
        query.setParameter(projectParam, new ProjectsEntity(projectId));
        query.setParameter(versionParam, version);
        return DatabaseUtil.findOne(query.getResultList()) != null;
    }

    public int updateAllForRelease (Session session, Timestamp time) {

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaUpdate<ProjectFilesEntity> q = cb.createCriteriaUpdate(ProjectFilesEntity.class);
        Root<ProjectFilesEntity> entity = q.from(ProjectFilesEntity.class);

        q.set(entity.get("released"), true);
        q.where(cb.and(
            cb.isFalse(entity.get("released")),
            cb.equal(entity.get("processingStatus"), FileProcessingStatus.SUCCESS)),
            cb.lessThanOrEqualTo(entity.get("processingStatusChanged"), time)
        );

        return session.createQuery(q).executeUpdate();
    }

    public List<ProjectFilesEntity> findProjectFilesByHash (Session session, String projectFileHash, long page, int limit, Sort sort) {

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<ProjectFilesEntity> q = cb.createQuery(ProjectFilesEntity.class);
        Root<ProjectFilesEntity> entity = q.from(ProjectFilesEntity.class);

        ParameterExpression<String> hashParam = cb.parameter(String.class);

        q.select(entity);
        q.where(cb.and(
            cb.equal(entity.get("sha512"), hashParam),
            cb.isTrue(entity.get("released"))
        ));
        if (sort.getOrder() == Order.ASC) {
            q.orderBy(cb.asc(entity.get(sort.getColumn())));
        }
        else {
            q.orderBy(cb.desc(entity.get(sort.getColumn())));
        }

        TypedQuery<ProjectFilesEntity> query = session.createQuery(q);
        query.setParameter(hashParam, projectFileHash);
        query.setFirstResult((int) ((page - 1) * limit));
        query.setMaxResults(limit);
        return query.getResultList();
    }
}
