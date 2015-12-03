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
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import mum.mpp.tay.controller.exceptions.NonexistentEntityException;
import mum.mpp.tay.entity.Address;
import mum.mpp.tay.entity.AuthorizationLevel;
import mum.mpp.tay.entity.Staff;

/**
 *
 * @author 984761
 */
public class StaffJpaController implements Serializable {

    public StaffJpaController(EntityManagerFactory emf) {
        this.emf = emf;

        if ( findStaffEntities().isEmpty()) {

            EntityManager em = getEntityManager();

            em.getTransaction().begin();
            Staff def = new Staff();
            def.setFirstName("Default");
            def.setLastName("Staff");
            def.setRole(AuthorizationLevel.FULLACCESS);
            def.setPassword("123");
            def.setUniqueStaffId(10000);
            Address address = new  Address();
            def.setAddress(address);
            create(def);
            em.getTransaction().commit();
        }

    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Staff Staff) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(Staff);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Staff Staff) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Staff = em.merge(Staff);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                long id = Staff.getUniqueStaffId();
                if (findStaff(id) == null) {
                    throw new NonexistentEntityException("The Staff with id " + id + " no longer exists.");
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
            Staff Staff;
            try {
                Staff = em.getReference(Staff.class, id);
                Staff.getUniqueStaffId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The Staff with id " + id + " no longer exists.", enfe);
            }
            em.remove(Staff);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Staff> findStaffEntities() {
        return findStaffEntities(true, -1, -1);
    }

    public List<Staff> findStaffEntities(int maxResults, int firstResult) {
        return findStaffEntities(false, maxResults, firstResult);
    }

    private List<Staff> findStaffEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Staff.class));
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

    public Staff findStaff(long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Staff.class, id);
        } finally {
            em.close();
        }
    }

    public List<Staff> findByName(String name) {
        EntityManager em = null;
        try {
            name = name.trim();
            String[] words = name.split(" ");
            if (words.length == 0) {
                return null;
            }

            em = getEntityManager();
            TypedQuery<Staff> query = em.createNamedQuery("Staff.findByName", Staff.class);

            query.setParameter("fname", "%" + words[0] + "%");
            query.setParameter("lname", "%" + words[words.length - 1] + "%");
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public int getStaffCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Staff> rt = cq.from(Staff.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
