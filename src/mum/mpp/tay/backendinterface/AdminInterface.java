/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mum.mpp.tay.backendinterface;

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

    Book addNewBook(Book book);

    Book editBook(Book book);

    BookCopy addBookCopy(BookCopy copy);

    Librarian addLibrarian(Librarian librarian);

    Librarian editLibrarian(Librarian librarian);

    Admin addAdmin(Admin admin);

    Member addMember(Member member);

    Member editMember(Member member);
}
