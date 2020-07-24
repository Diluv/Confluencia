package com.diluv.confluencia.database;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.diluv.confluencia.Confluencia;
import com.diluv.confluencia.database.record.FileProcessingStatus;
import com.diluv.confluencia.database.record.GameVersionsEntity;
import com.diluv.confluencia.database.record.GamesEntity;
import com.diluv.confluencia.database.record.ProjectFileGameVersionsEntity;
import com.diluv.confluencia.database.record.ProjectFilesEntity;
import com.diluv.confluencia.database.record.ProjectsEntity;
import com.diluv.confluencia.database.sort.Order;
import com.diluv.confluencia.database.sort.Sort;

public class FileDatabase {

    public boolean updateStatusById (FileProcessingStatus status, long id) {

        try {
            return Confluencia.getQuery((session, cb) -> {
                CriteriaUpdate<ProjectFilesEntity> q = cb.createCriteriaUpdate(ProjectFilesEntity.class);
                Root<ProjectFilesEntity> entity = q.from(ProjectFilesEntity.class);

                q.set(entity.get("processingStatus"), status.ordinal());
                q.where(cb.equal(entity.get("id"), id));

                return session.createQuery(q).executeUpdate() == 1;
            });
        }
        catch (Exception e) {
            return false;
        }
    }

    public boolean updateStatusByStatus (FileProcessingStatus set, FileProcessingStatus where) {

        try {
            return Confluencia.getQuery((session, cb) -> {
                CriteriaUpdate<ProjectFilesEntity> q = cb.createCriteriaUpdate(ProjectFilesEntity.class);
                Root<ProjectFilesEntity> entity = q.from(ProjectFilesEntity.class);

                q.set(entity.get("processingStatus"), set.ordinal());
                q.where(cb.equal(entity.get("processingStatus"), where.ordinal()));

                session.createQuery(q).executeUpdate();
                return true;
            });
        }
        catch (Exception e) {
            return false;
        }
    }

    public List<ProjectFilesEntity> findAllWhereStatusAndLimit (FileProcessingStatus status, int amount) {

        try {
            return Confluencia.getQuery((session, cb) -> {
                CriteriaQuery<ProjectFilesEntity> q = cb.createQuery(ProjectFilesEntity.class);
                Root<ProjectFilesEntity> entity = q.from(ProjectFilesEntity.class);

                ParameterExpression<FileProcessingStatus> s = cb.parameter(FileProcessingStatus.class);

                q.select(entity);
                q.where(cb.equal(entity.get("processingStatus"), s));
                q.orderBy(cb.asc(entity.get("createdAt")));

                TypedQuery<ProjectFilesEntity> query = session.createQuery(q);
                query.setParameter(s, status);
                query.setMaxResults(amount);
                return query.getResultList();
            });
        }
        catch (Exception e) {
            return Collections.emptyList();
        }
    }

//    public List<ProjectFilesEntity> getLatestFiles (int amount) throws SQLException {
//
//        List<ProjectFilesEntity> fileQueueEntity;
//        final Connection connection = Confluencia.connection();
//        final int previousIsolationLevel = connection.getTransactionIsolation();
//        try {
//            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
//            connection.setAutoCommit(false);
//
//            fileQueueRecord = this.findAllWhereStatusAndLimit(FileProcessingStatus.PENDING, amount);
//
//            if (fileQueueRecord.isEmpty()) {
//                return fileQueueEntity;
//            }
//
//            final Long[] idList = fileQueueRecord.stream().map(ProjectFilesEntity::getId).toArray(Long[]::new);
//            for (final Long id : idList) {
//                if (!this.updateStatusById(FileProcessingStatus.RUNNING, id)) {
//                    // TODO didn't work but didnt throw an exception
//                }
//            }
//            connection.commit();
//        } finally {
//            connection.setAutoCommit(true);
//            connection.setTransactionIsolation(previousIsolationLevel);
//        }
//        return fileQueueEntity;
//    }

    public boolean insertProjectFile (ProjectFilesEntity projectFile) {

        Transaction transaction = null;
        try (Session session = Confluencia.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(projectFile);
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

    public ProjectFilesEntity findOneById (long fileId) {

        try {
            return Confluencia.getQuery((session, cb) -> {
                CriteriaQuery<ProjectFilesEntity> q = cb.createQuery(ProjectFilesEntity.class);
                Root<ProjectFilesEntity> entity = q.from(ProjectFilesEntity.class);

                ParameterExpression<Long> projectIdParam = cb.parameter(Long.class);

                q.select(entity);
                q.where(cb.equal(entity.get("id"), projectIdParam));

                TypedQuery<ProjectFilesEntity> query = session.createQuery(q);
                query.setParameter(projectIdParam, fileId);
                return query.getSingleResult();
            });
        }
        catch (Exception e) {
            return null;
        }
    }

    public List<ProjectFilesEntity> findAllByProject (ProjectsEntity project, boolean authorized, long page, int limit, Sort sort, String gameVersion) {

        try {
            return Confluencia.getQuery((session, cb) -> {
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
            });
        }
        catch (Exception e) {
            return Collections.emptyList();
        }
    }

    public boolean existsByProjectIdAndVersion (long projectId, String version) {

        try {
            return Confluencia.getQuery((session, cb) -> {

                CriteriaQuery<ProjectFilesEntity> q = cb.createQuery(ProjectFilesEntity.class);
                Root<ProjectFilesEntity> entity = q.from(ProjectFilesEntity.class);
                ParameterExpression<ProjectsEntity> projectParam = cb.parameter(ProjectsEntity.class);
                ParameterExpression<String> versionParam = cb.parameter(String.class);
                q.select(entity);
                q.where(cb.and(cb.equal(entity.get("project"), projectParam), cb.equal(entity.get("version"), versionParam)));

                TypedQuery<ProjectFilesEntity> query = session.createQuery(q);
                query.setParameter(projectParam, new ProjectsEntity(projectId));
                query.setParameter(versionParam, version);
                return query.getSingleResult() != null;
            });
        }
        catch (Exception e) {
            return false;
        }
    }
}
