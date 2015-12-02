/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mum.mpp.tay.backendinterface;

import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import mum.mpp.tay.controller.AdminJpaController;
import mum.mpp.tay.controller.BookCopyJpaController;
import mum.mpp.tay.controller.BookJpaController;
import mum.mpp.tay.controller.LibrarianJpaController;
import mum.mpp.tay.controller.MemberJpaController;
import mum.mpp.tay.entity.Admin;
import mum.mpp.tay.entity.Book;
import mum.mpp.tay.entity.BookCopy;
import mum.mpp.tay.entity.Librarian;
import mum.mpp.tay.entity.Member;

/**
 *
 * @author asus
 */
public class AdminIMP implements AdminInterface {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProjectPU");

    @Override
    public Book addNewBook(Book book) throws ServiceException {
        try {
            BookJpaController bC = new BookJpaController(emf);
            bC.create(book);
            return book;
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public Book editBook(Book book) throws ServiceException {
        try {
            BookJpaController bC = new BookJpaController(emf);
            bC.edit(book);
            return book;
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public BookCopy addBookCopy(BookCopy copy) throws ServiceException {
        try {
            BookCopyJpaController bcC = new BookCopyJpaController(emf);
            bcC.create(copy);
            return copy;
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public Librarian addLibrarian(Librarian librarian) throws ServiceException {
        try {
            LibrarianJpaController lC = new LibrarianJpaController(emf);
            lC.create(librarian);
            return librarian;
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public Librarian editLibrarian(Librarian librarian) throws ServiceException {
        try {
            LibrarianJpaController lC = new LibrarianJpaController(emf);
            lC.edit(librarian);
            return librarian;
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public Admin addAdmin(Admin admin) throws ServiceException {
        try {
            AdminJpaController aC = new AdminJpaController(emf);
            aC.create(admin);
            return admin;
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }

    }

    @Override
    public Admin editAdmin(Admin admin) throws ServiceException {
        try {
            AdminJpaController aC = new AdminJpaController(emf);
            aC.edit(admin);
            return admin;
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public Member addMember(Member member) throws ServiceException {
        try {
            MemberJpaController mC = new MemberJpaController(emf);
            mC.create(member);
            return member;
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public Member editMember(Member member) throws ServiceException {
        try {
            MemberJpaController mC = new MemberJpaController(emf);
            mC.edit(member);
            return member;
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public Librarian getLibrarian(long id) throws ServiceException {
        try {
            LibrarianJpaController lC = new LibrarianJpaController(emf);
            return lC.findLibrarian(id);
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public Librarian searchLibrarianByName(String name) throws ServiceException {
        try {
            LibrarianJpaController lC = new LibrarianJpaController(emf);
            return lC.findByName(name);
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public List<Librarian> getAllLibrarian() throws ServiceException {
        try {
            LibrarianJpaController lC = new LibrarianJpaController(emf);
            return lC.findLibrarianEntities();
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public Admin getAdmin(long id) throws ServiceException {
        try {
            AdminJpaController lC = new AdminJpaController(emf);
            return lC.findAdmin(id);
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public Admin searchAdminByName(String name) throws ServiceException {
        try {
            AdminJpaController lC = new AdminJpaController(emf);
            return lC.findByName(name);
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public List<Admin> getAllAdmins() throws ServiceException {
        try {
            AdminJpaController lC = new AdminJpaController(emf);
            return lC.findAdminEntities();
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public Member getMember(long id) throws ServiceException {
        try {
            MemberJpaController lC = new MemberJpaController(emf);
            return lC.findMember(id);
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public Member searchMemberByName(String name) throws ServiceException {
        try {
            MemberJpaController lC = new MemberJpaController(emf);
            return lC.findByName(name);
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public List<Member> getAllMembers() throws ServiceException {
        try {
            MemberJpaController lC = new MemberJpaController(emf);
            return lC.findMemberEntities();
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }

}
