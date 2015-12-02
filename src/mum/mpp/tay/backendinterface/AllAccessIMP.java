/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mum.mpp.tay.backendinterface;

import java.util.List;
import mum.mpp.tay.entity.Book;
import mum.mpp.tay.entity.BookCopy;
import mum.mpp.tay.entity.CheckoutRecord;
import mum.mpp.tay.entity.Member;
import mum.mpp.tay.entity.Staff;

/**
 *
 * @author asus
 */
public class AllAccessIMP implements AllAccessInterface {

    private LibrarianInterface libI;
    private AdminInterface adminI;

    AllAccessIMP(Staff staff) {
        adminI = new AdminIMP(staff);
        libI = new LibrarianIMP(staff);
    }

    @Override
    public Staff getThisStaffObject() {
        return adminI.getThisStaffObject();
    }

    @Override
    public CheckoutRecord checkout(String bookISBN, long memberId) throws ServiceException {
        return libI.checkout(bookISBN, memberId);
    }

    @Override
    public boolean isBookAvailable(String ISBN) throws ServiceException {
        return libI.isBookAvailable(ISBN);
    }

    @Override
    public List<Book> getAllBooks() throws ServiceException {
        return libI.getAllBooks();
    }

    @Override
    public Book getBookByName(String name) throws ServiceException {
        return libI.getBookByName(name);
    }

    @Override
    public Member getMemberById(long id) throws ServiceException {
        return libI.getMemberById(id);
    }

    @Override
    public List<CheckoutRecord> getMemberRecord(long memberId) throws ServiceException {
        return libI.getMemberRecord(memberId);
    }

    @Override
    public boolean checkIn(String bookISBN, long memberId) throws ServiceException {
        return libI.checkIn(bookISBN, memberId);
    }

    @Override
    public Book addNewBook(Book book) throws ServiceException {
        return adminI.addNewBook(book);
    }

    @Override
    public Book editBook(Book book) throws ServiceException {
        return adminI.editBook(book);
    }

    @Override
    public BookCopy addBookCopy(BookCopy copy) throws ServiceException {
        return adminI.addBookCopy(copy);
    }

    @Override
    public Staff addStaff(Staff staff) throws ServiceException {
        return adminI.addStaff(staff);
    }

    @Override
    public Staff editStaff(Staff staff) throws ServiceException {
        return adminI.editStaff(staff);
    }

    @Override
    public Member addMember(Member member) throws ServiceException {
        return adminI.addMember(member);
    }

    @Override
    public Member editMember(Member member) throws ServiceException {
        return adminI.editMember(member);
    }

    @Override
    public Staff getStaff(long id) throws ServiceException {
        return adminI.getStaff(id);
    }

    @Override
    public List<Staff> searchStaffByName(String name) throws ServiceException {
        return adminI.searchStaffByName(name);
    }

    @Override
    public List<Staff> getAllStaff() throws ServiceException {
        return adminI.getAllStaff();
    }

    @Override
    public Member getMember(long id) throws ServiceException {
        return adminI.getMember(id);
    }

    @Override
    public List<Member> searchMemberByName(String name) throws ServiceException {
        return adminI.searchMemberByName(name);
    }

    @Override
    public List<Member> getAllMembers() throws ServiceException {
        return adminI.getAllMembers();
    }

}
