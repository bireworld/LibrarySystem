/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mum.mpp.tay.controller;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import mum.mpp.tay.entity.CheckoutRecord;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import mum.mpp.tay.controller.exceptions.NonexistentEntityException;
import mum.mpp.tay.entity.Member;

/**
 *
 * @author 984761
 */
public class MemberJpaController implements Serializable {

    public MemberJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Member member) {
        if (member.getRecords() == null) {
            member.setRecords(new ArrayList<CheckoutRecord>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<CheckoutRecord> attachedRecords = new ArrayList<CheckoutRecord>();
            for (CheckoutRecord recordsCheckoutRecordToAttach : member.getRecords()) {
                recordsCheckoutRecordToAttach = em.getReference(recordsCheckoutRecordToAttach.getClass(), recordsCheckoutRecordToAttach.getId());
                attachedRecords.add(recordsCheckoutRecordToAttach);
            }
            member.setRecords(attachedRecords);
            em.persist(member);
            for (CheckoutRecord recordsCheckoutRecord : member.getRecords()) {
                Member oldMemberOfRecordsCheckoutRecord = recordsCheckoutRecord.getMember();
                recordsCheckoutRecord.setMember(member);
                recordsCheckoutRecord = em.merge(recordsCheckoutRecord);
                if (oldMemberOfRecordsCheckoutRecord != null) {
                    oldMemberOfRecordsCheckoutRecord.getRecords().remove(recordsCheckoutRecord);
                    oldMemberOfRecordsCheckoutRecord = em.merge(oldMemberOfRecordsCheckoutRecord);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Member member) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Member persistentMember = em.find(Member.class, member.getUniqueMemberNumber());
            List<CheckoutRecord> recordsOld = persistentMember.getRecords();
            List<CheckoutRecord> recordsNew = member.getRecords();
            List<CheckoutRecord> attachedRecordsNew = new ArrayList<CheckoutRecord>();
            for (CheckoutRecord recordsNewCheckoutRecordToAttach : recordsNew) {
                recordsNewCheckoutRecordToAttach = em.getReference(recordsNewCheckoutRecordToAttach.getClass(), recordsNewCheckoutRecordToAttach.getId());
                attachedRecordsNew.add(recordsNewCheckoutRecordToAttach);
            }
            recordsNew = attachedRecordsNew;
            member.setRecords(recordsNew);
            member = em.merge(member);
            for (CheckoutRecord recordsOldCheckoutRecord : recordsOld) {
                if (!recordsNew.contains(recordsOldCheckoutRecord)) {
                    recordsOldCheckoutRecord.setMember(null);
                    recordsOldCheckoutRecord = em.merge(recordsOldCheckoutRecord);
                }
            }
            for (CheckoutRecord recordsNewCheckoutRecord : recordsNew) {
                if (!recordsOld.contains(recordsNewCheckoutRecord)) {
                    Member oldMemberOfRecordsNewCheckoutRecord = recordsNewCheckoutRecord.getMember();
                    recordsNewCheckoutRecord.setMember(member);
                    recordsNewCheckoutRecord = em.merge(recordsNewCheckoutRecord);
                    if (oldMemberOfRecordsNewCheckoutRecord != null && !oldMemberOfRecordsNewCheckoutRecord.equals(member)) {
                        oldMemberOfRecordsNewCheckoutRecord.getRecords().remove(recordsNewCheckoutRecord);
                        oldMemberOfRecordsNewCheckoutRecord = em.merge(oldMemberOfRecordsNewCheckoutRecord);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                long id = member.getUniqueMemberNumber();
                if (findMember(id) == null) {
                    throw new NonexistentEntityException("The member with id " + id + " no longer exists.");
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
            Member member;
            try {
                member = em.getReference(Member.class, id);
                member.getUniqueMemberNumber();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The member with id " + id + " no longer exists.", enfe);
            }
            List<CheckoutRecord> records = member.getRecords();
            for (CheckoutRecord recordsCheckoutRecord : records) {
                recordsCheckoutRecord.setMember(null);
                recordsCheckoutRecord = em.merge(recordsCheckoutRecord);
            }
            em.remove(member);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Member> findMemberEntities() {
        return findMemberEntities(true, -1, -1);
    }

    public List<Member> findMemberEntities(int maxResults, int firstResult) {
        return findMemberEntities(false, maxResults, firstResult);
    }

    private List<Member> findMemberEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Member.class));
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

    public Member findMember(long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Member.class, id);
        } finally {
            em.close();
        }
    }

    public int getMemberCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Member> rt = cq.from(Member.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
