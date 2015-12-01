package mum.mpp;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import mum.mpp.tay.backendinterface.AdminInterface;
import mum.mpp.tay.backendinterface.InterfaceFactory;

public class LoginController {
	@FXML
	private TextField txtfUsername;
	
	@FXML
	private PasswordField pswfPassword;
	
	@FXML
	private Button btnLogin;
	
	private Stage primaryStage;
	
	@FXML
	public void btnLogin_click() throws Exception {
		System.out.println("Login Button clicked");
		Object o = InterfaceFactory.createAnInterface(txtfUsername.getText(), pswfPassword.getText());
		
		if(o!=null && o instanceof AdminInterface) {
			Admin admin=new Admin();
			admin.setAdminInterface((AdminInterface)o);
			admin.run();
			primaryStage.close();
		}
	}

	Stage getPrimaryStage() {
		return primaryStage;
	}

	void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}
}
