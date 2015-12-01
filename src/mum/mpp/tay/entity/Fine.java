/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mum.mpp.tay.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author 984761
 */
@Entity
public class Fine implements Serializable {

    public Fine(double amount, Date date, CheckoutRecord record) {
        this.amount = amount;
        this.date = date;
        this.record = record;
    }

    public Fine() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private double amount;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "record_date")
    private Date date;
    @OneToOne
    private CheckoutRecord record;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public CheckoutRecord getRecord() {
        return record;
    }

    public void setRecord(CheckoutRecord record) {
        this.record = record;
    }

}
