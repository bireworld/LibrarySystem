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
import mum.mpp.tay.entity.Book;
import mum.mpp.tay.entity.Librarian;

/**
 *
 * @author 984761
 */
public class LibrarianJpaController implements Serializable {

    public LibrarianJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Librarian librarian) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(librarian);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Librarian librarian) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            librarian = em.merge(librarian);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                long id = librarian.getUniqueStaffId();
                if (findLibrarian(id) == null) {
                    throw new NonexistentEntityException("The librarian with id " + id + " no longer exists.");
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
            Librarian librarian;
            try {
                librarian = em.getReference(Librarian.class, id);
                librarian.getUniqueStaffId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The librarian with id " + id + " no longer exists.", enfe);
            }
            em.remove(librarian);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Librarian> findLibrarianEntities() {
        return findLibrarianEntities(true, -1, -1);
    }

    public List<Librarian> findLibrarianEntities(int maxResults, int firstResult) {
        return findLibrarianEntities(false, maxResults, firstResult);
    }

    private List<Librarian> findLibrarianEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Librarian.class));
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

    public Librarian findLibrarian(long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Librarian.class, id);
        } finally {
            em.close();
        }
    }

    public List<Librarian> findByName(String name) {
        EntityManager em = null;
        try {
            name = name.trim();
            String[] words = name.split(" ");
            if (words.length == 0) {
                return null;
            }

            em = getEntityManager();
            TypedQuery<Librarian> query = em.createNamedQuery("Librarian.findByName", Librarian.class);

            query.setParameter("fname", "%" + words[0] + "%");
            query.setParameter("lname", "%" + words[words.length - 1] + "%");
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public int getLibrarianCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Librarian> rt = cq.from(Librarian.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
