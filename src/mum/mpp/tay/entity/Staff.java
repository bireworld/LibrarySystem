/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mum.mpp.tay.entity;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 *
 * @author 984761
 */
@MappedSuperclass
public abstract class Staff extends Person{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected long uniqueStaffId;
    @Enumerated(EnumType.STRING)
    @Column(name = "staff_role")
    protected AuthorizationLevel role;

    public long getUniqueStaffId() {
        return uniqueStaffId;
    }

    public void setUniqueStaffId(long uniqueStaffId) {
        this.uniqueStaffId = uniqueStaffId;
    }

    public AuthorizationLevel getRole() {
        return role;
    }

    public void setRole(AuthorizationLevel role) {
        this.role = role;
    }

}
