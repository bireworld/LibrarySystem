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
public class Member extends Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long uniqueMemberNumber;
    private List<CheckoutRecord> records;

    public long getUniqueMemberNumber() {
        return uniqueMemberNumber;
    }

    public void setUniqueMemberNumber(long uniqueMemberNumber) {
        this.uniqueMemberNumber = uniqueMemberNumber;
    }

    public List<CheckoutRecord> getRecords() {
        return records;
    }

    public void setRecords(List<CheckoutRecord> records) {
        this.records = records;
    }

}
