/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mum.mpp.tay.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author 984761
 */
@Entity
@Table(name = "admin_staff")
@NamedQueries({
    @NamedQuery(
            name = "Admin.findByName",
            query = "SELECT l FROM Admin l WHERE l.firstName LIKE :fname OR l.lastName LIKE :lname"
    )
})
public class Admin extends Staff implements Serializable {

    public Admin() {
    }

}
