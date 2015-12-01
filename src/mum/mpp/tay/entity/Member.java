/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mum.mpp.tay.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author 984761
 */
@Entity
@Table(name = "member_table")
public class Member extends Person implements Serializable {

    public Member() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long uniqueMemberNumber;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "member")
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
