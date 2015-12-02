/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mum.mpp.tay.backendinterface;

import java.util.List;
import mum.mpp.tay.entity.Book;
import mum.mpp.tay.entity.BookCopy;
import mum.mpp.tay.entity.Member;
import mum.mpp.tay.entity.Staff;

/**
 *
 * @author 984761
 */
public interface AdminInterface {

    Staff getThisStaffObject();

    Book addNewBook(Book book) throws ServiceException;

    Book editBook(Book book) throws ServiceException;

    BookCopy addBookCopy(BookCopy copy) throws ServiceException;

    Member addMember(Member member) throws ServiceException;

    Member editMember(Member member) throws ServiceException;

    Staff addStaff(Staff staff) throws ServiceException;

    Staff editStaff(Staff staff) throws ServiceException;

    Staff getStaff(long id) throws ServiceException;

    List<Staff> searchStaffByName(String name) throws ServiceException;

    List<Staff> getAllStaff() throws ServiceException;

    Member getMember(long id) throws ServiceException;

    List<Member> searchMemberByName(String name) throws ServiceException;

    List<Member> getAllMembers() throws ServiceException;

}
