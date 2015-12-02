/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mum.mpp.tay.backendinterface;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import mum.mpp.tay.controller.StaffJpaController;
import mum.mpp.tay.entity.AuthorizationLevel;
import mum.mpp.tay.entity.Staff;

/**
 *
 * @author 984761
 */
public class InterfaceFactory {

    public static Object createAnInterface(String id, String password) throws ServiceException {
        try {
            long longId = Long.valueOf(id.trim());

            StaffJpaController staffController = new StaffJpaController(Persistence.createEntityManagerFactory("ProjectPU"));

            Staff staff = staffController.findStaff(longId);

            if (staff == null || !staff.getPassword().equals(password)) {
                throw new ServiceException("Wrong login details");
            }

            AuthorizationLevel role = staff.getRole();

            if (role.equals(AuthorizationLevel.FULLACCESS)) {
                return new AllAccessIMP(staff);
            } else if (role.equals(AuthorizationLevel.ADMIN)) {
                return new AdminIMP(staff);
            } else if (role.equals(AuthorizationLevel.LIBRARIAN)) {
                return new LibrarianIMP(staff);
            }

            throw new ServiceException("Wrong login details");
        } catch (Exception e) {
            throw new ServiceException("Something is wrong!");
        }
    }

}
