/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mum.mpp.tay.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author 984761
 */
@Entity
public class BookCopy implements Serializable {

    public BookCopy(boolean borrowed, long copyNumber, Book book) {
        this.borrowed = borrowed;
        this.copyNumber = copyNumber;
        this.book = book;
    }

    public BookCopy() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private boolean borrowed;
    private long copyNumber;
    @ManyToOne
    private Book book;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isBorrowed() {
        return borrowed;
    }

    public void setBorrowed(boolean borrowed) {
        this.borrowed = borrowed;
    }

    public long getCopyNumber() {
        return copyNumber;
    }

    public void setCopyNumber(long copyNumber) {
        this.copyNumber = copyNumber;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

}
