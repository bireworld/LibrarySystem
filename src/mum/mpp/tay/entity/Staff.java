/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mum.mpp.tay.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author 984761
 */
@Entity
@NamedQueries({
    @NamedQuery(
            name = "Staff.findByName",
            query = "SELECT l FROM Staff l WHERE l.firstName LIKE :fname OR l.lastName LIKE :lname"
    )
})
public class Staff extends Person implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected long uniqueStaffId;
    @Enumerated(EnumType.STRING)
    @Column(name = "staff_role")
    protected AuthorizationLevel role;
    protected String password;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Staff{" + "uniqueStaffId=" + uniqueStaffId + ", role=" + role + '}' + super.toString();
    }

}
