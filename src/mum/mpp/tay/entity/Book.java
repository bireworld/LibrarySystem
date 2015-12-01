/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mum.mpp.tay.entity;

import java.util.List;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author 984761
 */
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String ISBNNumber;
    private String title;
    private List<Author> authors;
    private int maximumCheckoutDurationInDays;
    private List<BookCopy> copies;

    public String getISBNNumber() {
        return ISBNNumber;
    }

    public void setISBNNumber(String ISBNNumber) {
        this.ISBNNumber = ISBNNumber;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public int getMaximumCheckoutDurationInDays() {
        return maximumCheckoutDurationInDays;
    }

    public void setMaximumCheckoutDurationInDays(int maximumCheckoutDurationInDays) {
        this.maximumCheckoutDurationInDays = maximumCheckoutDurationInDays;
    }

    public List<BookCopy> getCopies() {
        return copies;
    }

    public void setCopies(List<BookCopy> copies) {
        this.copies = copies;
    }

}
