package mum.mpp;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import mum.mpp.tay.backendinterface.AdminIMP;
import mum.mpp.tay.backendinterface.AdminInterface;
import mum.mpp.views.AdminController;

public class Admin extends Application {
	
	private AdminInterface adminInterface = new AdminIMP();
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("views/Admin.fxml"));
		BorderPane parent = loader.load();
				
		Scene scene = new Scene(parent);
		primaryStage.setScene(scene);
		
		primaryStage.show();
		
		AdminController adminController = loader.getController();
		adminController.setPrimaryStage(primaryStage);
		adminController.setScene(scene);
		adminController.setAdminInterface(adminInterface);
	}
	
	private void loadMenuBar(BorderPane parent) throws IOException {
		AnchorPane menuBar = (AnchorPane)FXMLLoader.load(getClass().getResource("views/MenuBar.fxml"));
		parent.setTop(menuBar);
	}
	
	public void run() throws Exception {
		Stage stage = new Stage();
		start(stage);
	}

	public AdminInterface getAdminInterface() {
		return adminInterface;
	}

	public void setAdminInterface(AdminInterface adminInterface) {
		this.adminInterface = adminInterface;
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
