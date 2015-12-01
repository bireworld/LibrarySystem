package mum.mpp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AddLibrarian extends Application {
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = (Parent)FXMLLoader.load(getClass().getResource("views/AddLibrarian.fxml"));
		
		Scene scene = new Scene(root);
		
		primaryStage.setScene(scene);
		primaryStage.show();
	}	
}
