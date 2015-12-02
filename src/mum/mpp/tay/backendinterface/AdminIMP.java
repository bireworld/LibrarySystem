/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mum.mpp.tay.backendinterface;

import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import mum.mpp.tay.controller.BookCopyJpaController;
import mum.mpp.tay.controller.BookJpaController;
import mum.mpp.tay.controller.MemberJpaController;
import mum.mpp.tay.controller.StaffJpaController;
import mum.mpp.tay.entity.Book;
import mum.mpp.tay.entity.BookCopy;
import mum.mpp.tay.entity.Member;
import mum.mpp.tay.entity.Staff;

/**
 *
 * @author asus
 */
public class AdminIMP implements AdminInterface {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProjectPU");
    Staff staffObject;

    AdminIMP(Staff staff) {
        this.staffObject = staff;
    }

    @Override
    public Staff getThisStaffObject() {
        return staffObject;
    }

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
    public Staff addStaff(Staff staff) throws ServiceException {
        try {
            StaffJpaController aC = new StaffJpaController(emf);
            aC.create(staff);
            return staff;
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }

    }

    @Override
    public Staff editStaff(Staff staff) throws ServiceException {
        try {
            StaffJpaController aC = new StaffJpaController(emf);
            aC.edit(staff);
            return staff;
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
    public Staff getStaff(long id) throws ServiceException {
        try {
            StaffJpaController lC = new StaffJpaController(emf);
            return lC.findStaff(id);
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public List<Staff> searchStaffByName(String name) throws ServiceException {
        try {
            StaffJpaController lC = new StaffJpaController(emf);
            return lC.findByName(name);
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public List<Staff> getAllStaff() throws ServiceException {
        try {
            StaffJpaController lC = new StaffJpaController(emf);
            return lC.findStaffEntities();
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
    public List<Member> searchMemberByName(String name) throws ServiceException {
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
