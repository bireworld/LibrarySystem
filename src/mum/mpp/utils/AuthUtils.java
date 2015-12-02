package mum.mpp.utils;

import java.util.HashMap;
import java.util.Map;

import mum.mpp.tay.entity.AuthorizationLevel;

public class AuthUtils {
	private static Map<String, AuthorizationLevel> authMap = new HashMap<>();
	static {
		authMap.put("ADMIN", AuthorizationLevel.ADMIN);
		authMap.put("LIBRARIAN", AuthorizationLevel.LIBRARIAN);
		authMap.put("FULLACCESS", AuthorizationLevel.FULLACCESS);
	}
	public static AuthorizationLevel getAuthLevelFromString(String authLevelStr) {
		AuthorizationLevel authLevel = null;
		
		if(authMap.containsKey(authLevelStr))
			authLevel = (AuthorizationLevel)authMap.get(authLevelStr);
		
		return authLevel;
	}
}
