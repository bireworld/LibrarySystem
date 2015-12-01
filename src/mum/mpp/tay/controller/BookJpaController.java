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
import mum.mpp.tay.entity.BookCopy;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import mum.mpp.tay.controller.exceptions.NonexistentEntityException;
import mum.mpp.tay.controller.exceptions.PreexistingEntityException;
import mum.mpp.tay.entity.Book;

/**
 *
 * @author 984761
 */
public class BookJpaController implements Serializable {

    public BookJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Book book) throws PreexistingEntityException, Exception {
        if (book.getCopies() == null) {
            book.setCopies(new ArrayList<BookCopy>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();

            em.getTransaction().begin();
            em.persist(book);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findBook(book.getiSBNNumber()) != null) {
                throw new PreexistingEntityException("Book " + book + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Book book) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Book persistentBook = em.find(Book.class, book.getiSBNNumber());
            List<BookCopy> copiesOld = persistentBook.getCopies();
            List<BookCopy> copiesNew = book.getCopies();
            List<BookCopy> attachedCopiesNew = new ArrayList<BookCopy>();
            for (BookCopy copiesNewBookCopyToAttach : copiesNew) {
                copiesNewBookCopyToAttach = em.getReference(copiesNewBookCopyToAttach.getClass(), copiesNewBookCopyToAttach.getId());
                attachedCopiesNew.add(copiesNewBookCopyToAttach);
            }
            copiesNew = attachedCopiesNew;
            book.setCopies(copiesNew);
            book = em.merge(book);
            for (BookCopy copiesOldBookCopy : copiesOld) {
                if (!copiesNew.contains(copiesOldBookCopy)) {
                    copiesOldBookCopy.setBook(null);
                    copiesOldBookCopy = em.merge(copiesOldBookCopy);
                }
            }
            for (BookCopy copiesNewBookCopy : copiesNew) {
                if (!copiesOld.contains(copiesNewBookCopy)) {
                    Book oldBookOfCopiesNewBookCopy = copiesNewBookCopy.getBook();
                    copiesNewBookCopy.setBook(book);
                    copiesNewBookCopy = em.merge(copiesNewBookCopy);
                    if (oldBookOfCopiesNewBookCopy != null && !oldBookOfCopiesNewBookCopy.equals(book)) {
                        oldBookOfCopiesNewBookCopy.getCopies().remove(copiesNewBookCopy);
                        oldBookOfCopiesNewBookCopy = em.merge(oldBookOfCopiesNewBookCopy);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = book.getiSBNNumber();
                if (findBook(id) == null) {
                    throw new NonexistentEntityException("The book with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public Book findByTitle(String title) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            TypedQuery<Book> query = em.createNamedQuery("findByTitle", Book.class);
            query.setParameter("title", "%" + title + "%");
            Book book = query.getSingleResult();
            return book;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void destroy(String id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Book book;
            try {
                book = em.getReference(Book.class, id);
                book.getiSBNNumber();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The book with id " + id + " no longer exists.", enfe);
            }
            List<BookCopy> copies = book.getCopies();
            for (BookCopy copiesBookCopy : copies) {
                copiesBookCopy.setBook(null);
                copiesBookCopy = em.merge(copiesBookCopy);
            }
            em.remove(book);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Book> findBookEntities() {
        return findBookEntities(true, -1, -1);
    }

    public List<Book> findBookEntities(int maxResults, int firstResult) {
        return findBookEntities(false, maxResults, firstResult);
    }

    private List<Book> findBookEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Book.class));
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

    public Book findBook(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Book.class, id);
        } finally {
            em.close();
        }
    }

    public int getBookCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Book> rt = cq.from(Book.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
