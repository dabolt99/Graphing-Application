package application;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		
		//code to double check we are all on the same version of java 8 and its included java fx
		//
		final String javaRuntime = "1.8.0_311"
				   , jFxRuntime = "8.0.311";
		
		final String myJavaRuntime = System.getProperty("java.runtime.version");
		final String myjFxRuntime = System.getProperty("javafx.runtime.version");
		
		if(!myJavaRuntime.contains(javaRuntime))
			System.out.println("Warning: Java runtime does not match latest version of Java 8 (1.8.0_311)");
		if(!myjFxRuntime.contains(jFxRuntime))
			System.out.println("Warning: jFx runtime does not match latest version of Java 8 jFx (8.0_311)");
		
		
		try {
			
			Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("view/introScreen.fxml"));
			primaryStage.setScene(new Scene(root));
			primaryStage.setTitle("Graphing Calculator");
			primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/appIcon.png")));
			primaryStage.show();

		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
