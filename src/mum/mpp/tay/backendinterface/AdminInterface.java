/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mum.mpp.tay.backendinterface;

import java.util.List;
import mum.mpp.tay.entity.Admin;
import mum.mpp.tay.entity.Book;
import mum.mpp.tay.entity.BookCopy;
import mum.mpp.tay.entity.Librarian;
import mum.mpp.tay.entity.Member;

/**
 *
 * @author 984761
 */
public interface AdminInterface {

    Admin getAdminObject();
    
    Book addNewBook(Book book) throws ServiceException;

    Book editBook(Book book) throws ServiceException;

    BookCopy addBookCopy(BookCopy copy) throws ServiceException;

    Librarian addLibrarian(Librarian librarian) throws ServiceException;

    Librarian editLibrarian(Librarian librarian) throws ServiceException;

    Admin addAdmin(Admin admin) throws ServiceException;

    Admin editAdmin(Admin admin) throws ServiceException;

    Member addMember(Member member) throws ServiceException;

    Member editMember(Member member) throws ServiceException;

    Librarian getLibrarian(long id) throws ServiceException;

    List<Librarian> searchLibrarianByName(String name) throws ServiceException;

    List<Librarian> getAllLibrarian() throws ServiceException;

    Admin getAdmin(long id) throws ServiceException;

    List<Admin> searchAdminByName(String name) throws ServiceException;

    List<Admin> getAllAdmins() throws ServiceException;

    Member getMember(long id) throws ServiceException;

    List<Member> searchMemberByName(String name) throws ServiceException;

    List<Member> getAllMembers() throws ServiceException;

}
