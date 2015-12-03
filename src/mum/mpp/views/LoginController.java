package mum.mpp.views;

import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import mum.mpp.Admin;
import mum.mpp.Main;
import mum.mpp.tay.backendinterface.AdminInterface;
import mum.mpp.tay.backendinterface.AllAccessInterface;
import mum.mpp.tay.backendinterface.InterfaceFactory;
import mum.mpp.tay.backendinterface.LibrarianInterface;
import mum.mpp.tay.backendinterface.ServiceException;
import mum.mpp.tay.entity.AuthorizationLevel;
import mum.mpp.utils.DialogUtil;

public class LoginController {
	@FXML
	private TextField txtfUsername;

	@FXML
	private PasswordField pswfPassword;

	@FXML
	private Button btnLogin;

	public static Object interfaceObj;
	public static AuthorizationLevel role;

	// Reference to the main application.
	private Main mainApp;

	public void setMainApp(Main mainApp) {
		this.mainApp = mainApp;
	}

	@FXML
	public void btnLogin_click() {
		// Stage secondWindow=new Stage();
		// Parent root;
		// try {
		// root = FXMLLoader.load(getClass().getResource("./Admin.fxml"));
		// Scene scene=new Scene(root,300,275);
		// secondWindow.setTitle("secondWindow");
		// secondWindow.setScene(scene);
		// secondWindow.show();
		//
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }

		// //表格面板
		// TablePane tp = new TablePane();
		// //加到场景中
		// Scene tpScene = new Scene(tp,500,500);
		// //切换舞台场景为表格面板
		// primaryStage.setScene(tpScene);

		// TODO call Amir login API here
		// Object user = InterfaceFactory.createAnInterface(username, password);
		// user
		// if(is a libIn){
		// LibrarianInterface librarianIMP = (LibrarianInterface)user;
		// mainApp.showLibrarianOperationDialog(librarianIMP);
		// }
		try {
			interfaceObj = InterfaceFactory.createAnInterface(txtfUsername.getText(),
					pswfPassword.getText().isEmpty() ? null : pswfPassword.getText());
			if (interfaceObj instanceof AllAccessInterface) {
				role = AuthorizationLevel.FULLACCESS;
				Stage stage = new Stage();
				Admin admin = new Admin();
				admin.setAdminInterface((AdminInterface) interfaceObj);
				try {
					admin.start(stage);
				} catch (Exception e) {
					e.printStackTrace();
				}
			
				mainApp.showLibrarianOperationDialog((LibrarianInterface) interfaceObj);
			} else if (interfaceObj instanceof AdminInterface) {
				role = AuthorizationLevel.ADMIN;
				Stage stage = new Stage();
				Admin admin = new Admin();
				admin.setAdminInterface((AdminInterface) interfaceObj);
				try {
					admin.start(stage);
					mainApp.hide();
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if (interfaceObj instanceof LibrarianInterface) {
				role = AuthorizationLevel.LIBRARIAN;
				mainApp.showLibrarianOperationDialog((LibrarianInterface) interfaceObj);
			}
		} catch (ServiceException e1) {
			DialogUtil.showDialog("Login Error!", "Username/Password doesn't match.", "Please try signin again.", AlertType.ERROR);
		}
	}
	
	@FXML
	public void onHitEnter(KeyEvent e) {
		if(e.getCode()==KeyCode.ENTER)
			btnLogin_click();
	}

}
