package mum.mpp.utils;

import java.util.HashMap;
import java.util.Map;

import mum.mpp.AddLibrarian;

public class MapStringToView {
	static Map<String,String> viewMap = new HashMap<>();
	static {
		viewMap.put("New Librarian", "AddLibrarian.fxml");
	}
	
	public static String map(String menuItem) {
		String s = null;
		
		if(viewMap.containsKey(menuItem))
			s = viewMap.get(menuItem);
		
		return s;
	}
}
