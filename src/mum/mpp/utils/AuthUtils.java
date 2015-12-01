package mum.mpp.utils;

import java.util.HashMap;
import java.util.Map;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mum.mpp.tay.entity.AuthorizationLevel;

public class AuthUtils {
	static Map<String,AuthorizationLevel> authMap = new HashMap<>();
	static {
		authMap.put("ADMIN", AuthorizationLevel.ADMIN);
		authMap.put("LIBRARIAN", AuthorizationLevel.LIBRARIAN);
		authMap.put("FULLACCESS", AuthorizationLevel.FULLACCESS);
	}
	public static AuthorizationLevel getAuthLevelFromString(String authLevelString) {
		if(authMap.containsKey(authLevelString))
			return authMap.get(authLevelString);
		return null;
	}
	
	public static ObservableList<String> getAuthLevelStrings() {
		return FXCollections.observableArrayList("ADMIN", "LIBRARIAN", "FULLACCESS");
	}
}
