package mum.mpp.utils;

import java.util.HashMap;
import java.util.Map;

import mum.mpp.ActionType;

public class MapStringToView {
	static Map<String,String> viewMap = new HashMap<>();
	static {
		viewMap.put("New Staff", "AddStaff.fxml");
		viewMap.put("Edit Staff", "EditStaff.fxml");
		viewMap.put("New Member", "AddMember.fxml");
		viewMap.put("Edit Member", "EditMember.fxml");
	}
	
	static Map<String,ActionType> actionTypeMap = new HashMap<>();
	static {
		actionTypeMap.put("New Staff", ActionType.ADD_STAFF);
		actionTypeMap.put("Edit Staff", ActionType.EDIT_STAFF);
		actionTypeMap.put("New Member", ActionType.ADD_MEMBER);
		actionTypeMap.put("Edit Member", ActionType.EDIT_MEMBER);
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
