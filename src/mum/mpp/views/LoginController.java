package mum.mpp.views;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import mum.mpp.Main;

public class LoginController {
	@FXML
	private TextField txtfUsername;
	
	@FXML
	private PasswordField pswfPassword;
	
	@FXML
	private Button btnLogin;
	
    // Reference to the main application.
    private Main mainApp;
    
    public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
    }
    
	@FXML
	public void btnLogin_click() {
//		Stage secondWindow=new Stage();
//		Parent root;
//		try {
//			root = FXMLLoader.load(getClass().getResource("./Admin.fxml"));
//			Scene scene=new Scene(root,300,275);
//			secondWindow.setTitle("secondWindow");
//			secondWindow.setScene(scene);
//			secondWindow.show();
//			
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
//        //表格面板 
//        TablePane tp = new TablePane(); 
//        //加到场景中 
//        Scene tpScene = new Scene(tp,500,500); 
//        //切换舞台场景为表格面板 
//        primaryStage.setScene(tpScene); 
		
		//TODO call Amir login API here
        
        mainApp.showLibrarianOperationDialog();

	}


}
