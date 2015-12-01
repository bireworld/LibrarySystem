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
import mum.mpp.tay.entity.Book;
import mum.mpp.tay.entity.BookCopy;

/**
 *
 * @author 984761
 */
public class BookCopyJpaController implements Serializable {

    public BookCopyJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(BookCopy bookCopy) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Book book = bookCopy.getBook();
            if (book != null) {
                book = em.getReference(book.getClass(), book.getiSBNNumber());
                bookCopy.setBook(book);
            }
            em.persist(bookCopy);
            if (book != null) {
                book.getCopies().add(bookCopy);
                book = em.merge(book);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(BookCopy bookCopy) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            BookCopy persistentBookCopy = em.find(BookCopy.class, bookCopy.getId());
            Book bookOld = persistentBookCopy.getBook();
            Book bookNew = bookCopy.getBook();
            if (bookNew != null) {
                bookNew = em.getReference(bookNew.getClass(), bookNew.getiSBNNumber());
                bookCopy.setBook(bookNew);
            }
            bookCopy = em.merge(bookCopy);
            if (bookOld != null && !bookOld.equals(bookNew)) {
                bookOld.getCopies().remove(bookCopy);
                bookOld = em.merge(bookOld);
            }
            if (bookNew != null && !bookNew.equals(bookOld)) {
                bookNew.getCopies().add(bookCopy);
                bookNew = em.merge(bookNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                long id = bookCopy.getId();
                if (findBookCopy(id) == null) {
                    throw new NonexistentEntityException("The bookCopy with id " + id + " no longer exists.");
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
            BookCopy bookCopy;
            try {
                bookCopy = em.getReference(BookCopy.class, id);
                bookCopy.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The bookCopy with id " + id + " no longer exists.", enfe);
            }
            Book book = bookCopy.getBook();
            if (book != null) {
                book.getCopies().remove(bookCopy);
                book = em.merge(book);
            }
            em.remove(bookCopy);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<BookCopy> findBookCopyEntities() {
        return findBookCopyEntities(true, -1, -1);
    }

    public List<BookCopy> findBookCopyEntities(int maxResults, int firstResult) {
        return findBookCopyEntities(false, maxResults, firstResult);
    }

    private List<BookCopy> findBookCopyEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(BookCopy.class));
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

    public BookCopy findBookCopy(long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(BookCopy.class, id);
        } finally {
            em.close();
        }
    }

    public int getBookCopyCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<BookCopy> rt = cq.from(BookCopy.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
