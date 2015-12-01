package mum.mpp;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
<<<<<<< HEAD
import mum.mpp.tay.backendinterface.AdminInterface;
import mum.mpp.views.AdminController;
=======
>>>>>>> origin/master

public class Admin extends Application {
	
	@Override
	public void start(Stage primaryStage) throws Exception {
<<<<<<< HEAD
		FXMLLoader loader = new FXMLLoader(getClass().getResource("views/Admin.fxml"));
		BorderPane parent = loader.load();
				
=======
		BorderPane parent = (BorderPane)FXMLLoader.load(getClass().getResource("views/Admin.fxml"));
		
		loadMenuBar(parent);
		
>>>>>>> origin/master
		Scene scene = new Scene(parent);
		primaryStage.setScene(scene);
		
		primaryStage.show();
		
		AdminController adminController = loader.getController();
		adminController.setPrimaryStage(primaryStage);
		adminController.setScene(scene);
	}
	
	private void loadMenuBar(BorderPane parent) throws IOException {
		AnchorPane menuBar = (AnchorPane)FXMLLoader.load(getClass().getResource("views/MenuBar.fxml"));
		parent.setTop(menuBar);
	}
	
	
}
