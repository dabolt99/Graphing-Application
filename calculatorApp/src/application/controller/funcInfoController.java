package application.controller;

import java.io.IOException;

import javax.script.ScriptException;

import application.model.Function;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class funcInfoController {
	
	@FXML
    private Button btnSolveVal;

    @FXML
    private Button btnGotoGraph;

    @FXML
    private Label lblXInter;

    @FXML
    private Label lblYInter;
    
    @FXML
    private Text txtFunction;

    @FXML
    private TextField txtXEquals;

    @FXML
    private TextField txtYEquals;
    
    Function func;
    
    void initFunc(Function input) throws ScriptException {
    	System.out.println("funcInfoController manual initialize");
    	func = input;
    	
    	//not needed
    	//txtXEquals.setText(String.valueOf(func.getXInt()));
    	//txtYEquals.setText(String.valueOf(func.getYInt()));
    	//func.setXInt(func.calculateValues(, ));
    	
    	txtFunction.setText("f(x) = " + func.getFunction());
    	func.setXInt(func.calculateValues(0, 'x'));
    	lblXInter.setText(String.valueOf(func.getXInt()));
    	//lblYInter.setText(String.valueOf(func.getYInt()));
    }
    
    //this will handle solving for x given y or y given x
    //calls Function.calculateValues which right now just 
    //returns double its input
    @FXML
    void solveVal(MouseEvent event) throws ScriptException {
    	double x, y;
    	//x is blank solve for it given y
    	if(txtXEquals.getText().equals("")) {
    		y = Double.valueOf(txtYEquals.getText());
    		x = func.calculateValues(y, 'x');
    		txtXEquals.setText(String.valueOf(x));
    	} else { //y is blank solve for it given x
    		x = Double.valueOf(txtXEquals.getText());
    		y = func.calculateValues(x, 'y');
    		txtYEquals.setText(String.valueOf(y));
    	}
    	
    }

    @FXML
    void switchGraph(MouseEvent event) throws IOException, ScriptException {
    	FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("view/graph.fxml"));
    	Parent root = loader.load();
    	
    	//calling the manual initialization and giving it Function obj
    	graphController graphController = loader.getController();
    	graphController.initGraph(func);
    	
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
    
    @FXML
    public void initialize() {
        System.out.println("funcInfoController sys initialize");
    }

}
