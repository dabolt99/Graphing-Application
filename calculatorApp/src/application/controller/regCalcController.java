package application.controller;

import java.io.IOException;

import application.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class regCalcController {

	@FXML
	private Label result;
	@FXML
	private long number1 = 0;
	@FXML
	private String operator = "";
	@FXML
	private boolean start = true;
	@FXML
	private Model model = new Model();
	
    @FXML
    private Button btnGotoGraphCalc;

    @FXML
    private Button btnGotoIntro;
    
    @FXML
    private Button btnGotoMatrixCalc;
    
    @FXML
    void switchMatrixCalc(MouseEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("view/Matrix.fxml"));
    	Parent root = loader.load();
    	
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    void switchGraphCalc(MouseEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("view/graph.fxml"));
    	Parent root = loader.load();
    	
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    void switchHome(MouseEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("view/introScreen.fxml"));
    	Parent root = loader.load();
    	
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
    
    @FXML
    public void processNumbers(ActionEvent event) {
    	if (start) {
    		result.setText("");
    		start = false;
    	}
    	String value = ((Button)event.getSource()).getText();
    	result.setText(result.getText() + value);
    }
    
    @FXML
    public void processOperators(ActionEvent event) {
    	String value = ((Button)event.getSource()).getText();
    	
    	if (!value.equals("=")) {
    		if (!operator.isEmpty())
    			return;
    		
    		operator = value;
    		number1 = Long.parseLong(result.getText());
    		result.setText("");
    	} else {
    		if (operator.isEmpty())
    			return;
    		
    		Long number2 = Long.parseLong(result.getText());
    		float output = model.calculate(number1, number2, operator);
    		result.setText(String.valueOf(output));
    		operator = "";
    		start = true;
    	}
    }  
}

