/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mum.mpp.tay.backendinterface;

/**
 *
 * @author asus
 */
public class ServiceException extends Exception {

    public ServiceException(String msg) {
        super(msg);
    }

    @Override
    public String toString() {
        return "ServiceException: " + getMessage();
    }

}
