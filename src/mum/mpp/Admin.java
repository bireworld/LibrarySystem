package mum.mpp;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Admin extends Application {
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		BorderPane parent = (BorderPane)FXMLLoader.load(getClass().getResource("views/Admin.fxml"));
		
		loadMenuBar(parent);
		
		Scene scene = new Scene(parent);
		primaryStage.setScene(scene);
		
		primaryStage.show();
	}
	
	private void loadMenuBar(BorderPane parent) throws IOException {
		AnchorPane menuBar = (AnchorPane)FXMLLoader.load(getClass().getResource("views/MenuBar.fxml"));
		parent.setTop(menuBar);
	}
	
	
}
