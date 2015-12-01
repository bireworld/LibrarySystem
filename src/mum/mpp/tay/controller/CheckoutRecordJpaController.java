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
import mum.mpp.tay.entity.Member;
import mum.mpp.tay.entity.Fine;

/**
 *
 * @author 984761
 */
public class CheckoutRecordJpaController implements Serializable {

    public CheckoutRecordJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(CheckoutRecord checkoutRecord) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Member member = checkoutRecord.getMember();
            if (member != null) {
                member = em.getReference(member.getClass(), member.getUniqueMemberNumber());
                checkoutRecord.setMember(member);
            }
            Fine fine = checkoutRecord.getFine();
            if (fine != null) {
                fine = em.getReference(fine.getClass(), fine.getId());
                checkoutRecord.setFine(fine);
            }
            em.persist(checkoutRecord);
            if (member != null) {
                member.getRecords().add(checkoutRecord);
                member = em.merge(member);
            }
            if (fine != null) {
                CheckoutRecord oldRecordOfFine = fine.getRecord();
                if (oldRecordOfFine != null) {
                    oldRecordOfFine.setFine(null);
                    oldRecordOfFine = em.merge(oldRecordOfFine);
                }
                fine.setRecord(checkoutRecord);
                fine = em.merge(fine);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(CheckoutRecord checkoutRecord) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CheckoutRecord persistentCheckoutRecord = em.find(CheckoutRecord.class, checkoutRecord.getId());
            Member memberOld = persistentCheckoutRecord.getMember();
            Member memberNew = checkoutRecord.getMember();
            Fine fineOld = persistentCheckoutRecord.getFine();
            Fine fineNew = checkoutRecord.getFine();
            if (memberNew != null) {
                memberNew = em.getReference(memberNew.getClass(), memberNew.getUniqueMemberNumber());
                checkoutRecord.setMember(memberNew);
            }
            if (fineNew != null) {
                fineNew = em.getReference(fineNew.getClass(), fineNew.getId());
                checkoutRecord.setFine(fineNew);
            }
            checkoutRecord = em.merge(checkoutRecord);
            if (memberOld != null && !memberOld.equals(memberNew)) {
                memberOld.getRecords().remove(checkoutRecord);
                memberOld = em.merge(memberOld);
            }
            if (memberNew != null && !memberNew.equals(memberOld)) {
                memberNew.getRecords().add(checkoutRecord);
                memberNew = em.merge(memberNew);
            }
            if (fineOld != null && !fineOld.equals(fineNew)) {
                fineOld.setRecord(null);
                fineOld = em.merge(fineOld);
            }
            if (fineNew != null && !fineNew.equals(fineOld)) {
                CheckoutRecord oldRecordOfFine = fineNew.getRecord();
                if (oldRecordOfFine != null) {
                    oldRecordOfFine.setFine(null);
                    oldRecordOfFine = em.merge(oldRecordOfFine);
                }
                fineNew.setRecord(checkoutRecord);
                fineNew = em.merge(fineNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                long id = checkoutRecord.getId();
                if (findCheckoutRecord(id) == null) {
                    throw new NonexistentEntityException("The checkoutRecord with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CheckoutRecord checkoutRecord;
            try {
                checkoutRecord = em.getReference(CheckoutRecord.class, id);
                checkoutRecord.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The checkoutRecord with id " + id + " no longer exists.", enfe);
            }
            Member member = checkoutRecord.getMember();
            if (member != null) {
                member.getRecords().remove(checkoutRecord);
                member = em.merge(member);
            }
            Fine fine = checkoutRecord.getFine();
            if (fine != null) {
                fine.setRecord(null);
                fine = em.merge(fine);
            }
            em.remove(checkoutRecord);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<CheckoutRecord> findCheckoutRecordEntities() {
        return findCheckoutRecordEntities(true, -1, -1);
    }

    public List<CheckoutRecord> findCheckoutRecordEntities(int maxResults, int firstResult) {
        return findCheckoutRecordEntities(false, maxResults, firstResult);
    }

    private List<CheckoutRecord> findCheckoutRecordEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(CheckoutRecord.class));
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

    public CheckoutRecord findCheckoutRecord(long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CheckoutRecord.class, id);
        } finally {
            em.close();
        }
    }

    public int getCheckoutRecordCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<CheckoutRecord> rt = cq.from(CheckoutRecord.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
