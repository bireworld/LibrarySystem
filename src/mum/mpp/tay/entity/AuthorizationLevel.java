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
public enum AuthorizationLevel {

	LIBRARIAN("Librarian"), ADMIN("Admin"), FULLACCESS("Super");

	private String value;

	private AuthorizationLevel(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return value;
	}

}
