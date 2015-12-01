/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mum.mpp.tay.controller;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import mum.mpp.tay.controller.exceptions.NonexistentEntityException;
import mum.mpp.tay.entity.CheckoutRecord;
import mum.mpp.tay.entity.Fine;

/**
 *
 * @author 984761
 */
public class FineJpaController implements Serializable {

    public FineJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Fine fine) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CheckoutRecord record = fine.getRecord();
            if (record != null) {
                record = em.getReference(record.getClass(), record.getId());
                fine.setRecord(record);
            }
            em.persist(fine);
            if (record != null) {
                Fine oldFineOfRecord = record.getFine();
                if (oldFineOfRecord != null) {
                    oldFineOfRecord.setRecord(null);
                    oldFineOfRecord = em.merge(oldFineOfRecord);
                }
                record.setFine(fine);
                record = em.merge(record);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Fine fine) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Fine persistentFine = em.find(Fine.class, fine.getId());
            CheckoutRecord recordOld = persistentFine.getRecord();
            CheckoutRecord recordNew = fine.getRecord();
            if (recordNew != null) {
                recordNew = em.getReference(recordNew.getClass(), recordNew.getId());
                fine.setRecord(recordNew);
            }
            fine = em.merge(fine);
            if (recordOld != null && !recordOld.equals(recordNew)) {
                recordOld.setFine(null);
                recordOld = em.merge(recordOld);
            }
            if (recordNew != null && !recordNew.equals(recordOld)) {
                Fine oldFineOfRecord = recordNew.getFine();
                if (oldFineOfRecord != null) {
                    oldFineOfRecord.setRecord(null);
                    oldFineOfRecord = em.merge(oldFineOfRecord);
                }
                recordNew.setFine(fine);
                recordNew = em.merge(recordNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = fine.getId();
                if (findFine(id) == null) {
                    throw new NonexistentEntityException("The fine with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(int id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Fine fine;
            try {
                fine = em.getReference(Fine.class, id);
                fine.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The fine with id " + id + " no longer exists.", enfe);
            }
            CheckoutRecord record = fine.getRecord();
            if (record != null) {
                record.setFine(null);
                record = em.merge(record);
            }
            em.remove(fine);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Fine> findFineEntities() {
        return findFineEntities(true, -1, -1);
    }

    public List<Fine> findFineEntities(int maxResults, int firstResult) {
        return findFineEntities(false, maxResults, firstResult);
    }

    private List<Fine> findFineEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Fine.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Fine findFine(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Fine.class, id);
        } finally {
            em.close();
        }
    }

    public int getFineCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Fine> rt = cq.from(Fine.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
