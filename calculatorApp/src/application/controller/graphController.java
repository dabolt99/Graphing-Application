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
import javafx.scene.chart.Axis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
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
    private TextField txtMinX;
    
    @FXML
    private TextField txtMaxX;
    
    //@FXML
   // private NumberAxis xAxis ;

    //@FXML
    //private NumberAxis yAxis ;
    
    @FXML
	private LineChart<Number, Number> lineGraph;
    
    Function func;
    //just a prototype for getting the users input function and then doing sum with it
    @FXML
    void updateGraph(MouseEvent event) throws ScriptException {
 
    	func = new Function(txtFuncF.getText());
    	//temporary just to display what the graph is actually calculating on
       	txtDeleteMe.setText(func.getEquation());
    	//empty the graph
    	lineGraph.getData().clear();
    	
    	
    	int minX, maxX;
    	if(txtMinX.getText().isEmpty()) {
    		//if coming back from function info set values manually
    		//will cause error otherwise
    		minX = -10;
        	maxX = 10;
    	} else {
        	minX = Integer.valueOf(txtMinX.getText());
        	maxX = Integer.valueOf(txtMaxX.getText());
    	}

    	//setting the x axis bounds
    	NumberAxis axis = (NumberAxis) lineGraph.getXAxis();
    	axis.setLowerBound(minX);
    	axis.setUpperBound(maxX);

    	//setting how many data points to calc
    	//very intensive as far as time to render
    	double incVal = .2;
    	double y;
    	XYChart.Series<Number, Number> series = new XYChart.Series<Number, Number>();
    	for (double x = minX - incVal; x <= maxX + incVal; x = x + incVal) {
    		
    		y = func.calculateValues(x, 'y');
    		//plotting a Nan Value or infinite value breaks the graph scaling
    		if(!Double.isNaN(y) && Double.isFinite(y)) {
    			series.getData().add(new XYChart.Data<Number, Number>(x, y));
    		}
    	}
    	//dropping the coords into the graph
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
