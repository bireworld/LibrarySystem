package mum.mpp;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import mum.mpp.views.LoginController;


public class Main extends Application {
	private Stage primaryStage;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			this.primaryStage = primaryStage;
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("./views/Login.fxml"));
//			Parent root = FXMLLoader.load(getClass().getResource("./views/Login.fxml"));
//			scene.getStylesheets().add(getClass().getResource("../css/application.css").toExternalForm());
			Parent root = loader.load();
			Scene scene = new Scene(root);
			
            // Give the controller access to the main app.
			LoginController controller = loader.getController();
            controller.setMainApp(this);
            
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}

	public void showLibrarianOperationDialog() {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("./views/LibrarianOprationDetail.fxml"));
            Parent page = loader.load();
            Scene scene = new Scene(page);

            this.primaryStage.setScene(scene);

        } catch (IOException e) {
            e.printStackTrace();
            
        }
		
	}
}
