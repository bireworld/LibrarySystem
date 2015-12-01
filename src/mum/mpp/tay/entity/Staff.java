/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mum.mpp.tay.entity;

/**
 *
 * @author 984761
 */
public abstract class Staff {

    protected long uniqueStaffId;
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
