package application;

import java.io.IOException;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

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
    private LineChart<Double, Double> graph;
    
    Function func;
    //just a prototype for getting the users input function and then doing sum with it
    @FXML
    void updateGraph(MouseEvent event) {
    	txtDeleteMe.setText(txtFuncF.getText());
    	func.setFunction(txtFuncF.getText());
    	
    }
    
    void initFunc(Function input) {
    	System.out.println("graphController manual initialize");
    	func = input;
    	
    	//not needed
    	//txtXEquals.setText(String.valueOf(func.getXInt()));
    	//txtYEquals.setText(String.valueOf(func.getYInt()));
    	
    	txtFuncF.setText(func.getFunction());
    	//a way to call updateGraph without someone having to click on it
    	//could be useful so when the user clicks back to the graph from function info view
    	//the graph will already be there
    	this.updateGraph(null);
    	
    }

    @FXML
    void switchCalc(MouseEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("view/regCalc.fxml"));
    	Parent root = loader.load();
    	
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    void switchFuncInfo(MouseEvent event) throws IOException {
    	
    	//putting the users text into the function obj
        Function func = new Function(txtFuncF.getText());
    	// also need the x and ys to get calculated
    	
    	
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("view/funcInfo.fxml"));
    	Parent root = loader.load();
    	
    	//calling the manual initialization and giving it Function obj
    	funcInfoController funcInfoController = loader.getController();
    	funcInfoController.initFunc(func);
    	
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
        
    }
    
    @FXML
    public void initialize() throws ScriptException {
        System.out.println("graphController sys initialize");
        func = new Function("Null");
        
        //ScriptEngineManager mgr = new ScriptEngineManager();
        ///ScriptEngine engine = mgr.getEngineByName("JavaScript");
        //String infix = "3+2*(4+5)";
        //System.out.println(engine.eval(infix));
    }

}
