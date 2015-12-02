package mum.mpp.utils;

import java.util.HashMap;
import java.util.Map;

import mum.mpp.ActionType;

public class MapStringToView {
	static Map<String,String> viewMap = new HashMap<>();
	static {
		viewMap.put("New Administrator", "AddAdministrator.fxml");
		viewMap.put("Edit Administrator", "EditAdministrator.fxml");
		viewMap.put("New Librarian", "AddLibrarian.fxml");
		viewMap.put("Edit Librarian", "EditLibrarian.fxml");
		viewMap.put("New Member", "AddMember.fxml");
		viewMap.put("Edit Member", "EditMember.fxml");
	}
	
	static Map<String,ActionType> actionTypeMap = new HashMap<>();
	static {
		actionTypeMap.put("New Librarian", ActionType.ADD_LIBRARIAN);
		actionTypeMap.put("Edit Librarian", ActionType.EDIT_LIBRARIAN);
	}
	
	public static String mapView(String menuItem) {
		String s = null;
		
		if(viewMap.containsKey(menuItem))
			s = viewMap.get(menuItem);
		
		return s;
	}
	
	public static ActionType mapAction(String menuItem) {
		ActionType s = null;
		
		if(actionTypeMap.containsKey(menuItem))
			s = actionTypeMap.get(menuItem);
		
		return s;
	}
}
