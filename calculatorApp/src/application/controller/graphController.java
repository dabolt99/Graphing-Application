package application.controller;

import java.io.IOException;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import application.model.Function;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class graphController {

    @FXML
    private Button btnGotoCalc;

    @FXML
    private Button btnGotoFInfo;
    
    @FXML
    private Button btnUpdateGraph;

    @FXML
    private Text txtDeleteMe;

    @FXML
    private TextField txtFuncF;
    
    @FXML
	private LineChart<Double, Double> lineGraph;
    
    Function func;
    //just a prototype for getting the users input function and then doing sum with it
    @FXML
    void updateGraph(MouseEvent event) throws ScriptException {
    	txtDeleteMe.setText(txtFuncF.getText());
    	func = new Function(txtFuncF.getText());
    	//empty the graph
    	double range = 25;
    	lineGraph.getData().clear();
    	
    	XYChart.Series<Double, Double> series = new XYChart.Series<Double, Double>();
    	for (double x = -range; x <= range; x = x + 0.1) {
    		series.getData().add(new XYChart.Data<Double, Double>(x, func.calculateValues(x, 'y')));
    	}
    	lineGraph.getData().add(series);
    	
    }
    
    void initGraph(Function input) throws ScriptException {
    	System.out.println("graphController manual initialize");
    	func = input;
    	
    	txtFuncF.setText(func.getFunction());
    	//a way to call updateGraph without someone having to click on it
    	//could be useful so when the user clicks back to the graph from function info view
    	//the graph will already be there
    	this.updateGraph(null);
    	
    }

    @FXML
    void switchCalc(MouseEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("view/regCalc.fxml"));
    	Parent root = loader.load();
    	
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    void switchFuncInfo(MouseEvent event) throws IOException, ScriptException {
    	
    	if(txtFuncF.getText().isEmpty()) {
    		txtFuncF.setText("Enter A Function");
    	} else {
    		//putting the users text into the function obj
            Function func = new Function(txtFuncF.getText());
        	// also need the x and ys to get calculated
        	
        	
        	FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("view/funcInfo.fxml"));
        	Parent root = loader.load();
        	
        	//calling the manual initialization and giving it Function obj
        	funcInfoController funcInfoController = loader.getController();
        	funcInfoController.initFunc(func);
        	
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
    	}        
    }
    
    @FXML
    public void initialize() throws ScriptException {

    }

}
