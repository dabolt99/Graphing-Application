package application;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

	public class introScreenController {

		@FXML
		private Button btnEnter;

		@FXML
		void switchCalculator(MouseEvent event) throws IOException {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("view/regCalc.fxml"));
	    	Parent root = loader.load();
	    	
	        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
	        stage.setScene(new Scene(root));
	        stage.show();
		}

	}