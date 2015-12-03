package mum.mpp.utils;

import java.util.HashMap;
import java.util.Map;

import mum.mpp.ActionType;

public class MapStringToView {
	static Map<String,String> viewMap = new HashMap<>();
	static {
		viewMap.put("Home", "HomePage.fxml");
		viewMap.put("New Staff", "AddStaff.fxml");
		viewMap.put("Edit Staff", "EditStaff.fxml");
		viewMap.put("New Member", "AddMember.fxml");
		viewMap.put("Edit Member", "EditMember.fxml");
		viewMap.put("Add Book", "AddBook.fxml");
		viewMap.put("Add Book Copy", "AddBookCopy.fxml");
	}
	
	static Map<String,ActionType> actionTypeMap = new HashMap<>();
	static {
		actionTypeMap.put("Home", ActionType.HOME);
		actionTypeMap.put("New Staff", ActionType.ADD_STAFF);
		actionTypeMap.put("Edit Staff", ActionType.EDIT_STAFF);
		actionTypeMap.put("New Member", ActionType.ADD_MEMBER);
		actionTypeMap.put("Edit Member", ActionType.EDIT_MEMBER);
		actionTypeMap.put("Add Book", ActionType.ADD_BOOK);
		actionTypeMap.put("Add Book Copy", ActionType.ADD_BOOK_COPY);
	}
	
	static Map<ActionType, String> titleMap = new HashMap<>();
	static {
		titleMap.put(ActionType.HOME, "Overall Statistics of Library");
		titleMap.put(ActionType.ADD_STAFF, "Add a new Staff");
		titleMap.put(ActionType.EDIT_STAFF, "Edit Staff Information");
		titleMap.put(ActionType.ADD_MEMBER, "Add a new Library Member");
		titleMap.put(ActionType.EDIT_MEMBER, "Edit Member Information");
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
	
	public static String mapTitle(ActionType actionType) {
		String s = null;
		
		if(titleMap.containsKey(actionType))
			s = titleMap.get(actionType);
		
		return s;
	}
}
