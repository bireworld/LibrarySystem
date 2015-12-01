/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mum.mpp.tay.backendinterface;

/**
 *
 * @author 984761
 */
public class InterfaceFactory {
    
    public static Object createAnInterface(String username,String password){
    	if(username.equals("apollo") && password.equals("123"))
    		return new Object();
    	return null;
    }
    
}
