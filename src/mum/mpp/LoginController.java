package mum.mpp;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {
	@FXML
	private TextField txtfUsername;
	
	@FXML
	private PasswordField pswfPassword;
	
	@FXML
	private Button btnLogin;
	
	@FXML
	public void btnLogin_click() {
		System.out.println("Login Button clicked");
	}
}
