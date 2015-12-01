package mum.mpp.utils;

import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class ValidationUtils {
	public static boolean isEmpty(PasswordField field) {
		if(field.getText()==null || field.getText().trim().length()==0)
			return true;
		return false;
	}
	
	public static boolean isOfLength(TextField control, int length) {
		if(control.getText()==null || control.getText().length()!=length)
			return false;
		return true;
	}
	
	public static boolean matchesPattern(String data, String pattern) {
		return data.matches(pattern);
	}
	
	public static boolean isEmpty(TextField...fields) {
		for(int i=0;i<fields.length;i++) {
			if(fields[i].getText()==null || fields[i].getText().trim().length()==0)
				return true;
		}
		return false;
	}
}
