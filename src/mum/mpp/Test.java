package mum.mpp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Test extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = (Parent)FXMLLoader.load(getClass().getResource("views/AddLibrarian.fxml"));
		
		Scene scene = new Scene(root);
		
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
