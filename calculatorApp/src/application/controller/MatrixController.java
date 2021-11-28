package application.controller;

import java.io.IOException;

import application.model.Matrix;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


public class MatrixController {
	
	@FXML
	private TextField A11;	
	@FXML
	private TextField A12;	
	@FXML
	private TextField A13;
	@FXML
	private TextField A21;
	@FXML
	private TextField A22;
	@FXML
	private TextField A23;
	@FXML
	private TextField A31;
	@FXML
	private TextField A32;
	@FXML
	private TextField A33;
	@FXML
	private TextField B11;	
	@FXML
	private TextField B12;	
	@FXML
	private TextField B13;
	@FXML
	private TextField B21;
	@FXML
	private TextField B22;
	@FXML
	private TextField B23;
	@FXML
	private TextField B31;
	@FXML
	private TextField B32;
	@FXML
	private TextField B33;
	
	@FXML
	private Button btnMultiply;	
	@FXML
	private Button btnAdd;	
	@FXML
	private Button btnSubtract;
	@FXML
	private Button btnSwitchCalc;
	
	@FXML
    void switchCalc(MouseEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("view/regCalc.fxml"));
    	Parent root = loader.load();
    	
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
	private Label answer;
	
	public void handle(ActionEvent event) throws IOException {
    	Object source = event.getSource();
    	Matrix v=new Matrix(A11, A12, A13, A21, A22, A23, A31, A32, A33,
    			B11, B12, B13, B21, B22, B23, B31, B32, B33);
    	v.validateInput(A11); v.validateInput(B11);
    	v.validateInput(A12); v.validateInput(B12);
    	v.validateInput(A13); v.validateInput(B13);
    	v.validateInput(A21); v.validateInput(B21);
    	v.validateInput(A22); v.validateInput(B22);
    	v.validateInput(A23); v.validateInput(B23);
    	v.validateInput(A31); v.validateInput(B31);
    	v.validateInput(A32); v.validateInput(B32);
    	v.validateInput(A33); v.validateInput(B33);
    	
    	int aCols = v.checkSize(A11, A12, A13);
    	int bCols = v.checkSize(B11, B12, B13);
    	int aRows = v.checkSize(A11, A21, A31);
    	int bRows = v.checkSize(B11, B21, B31);
    	
    	//System.out.println("matrix A is a " + aRows + "x" + aCols + " matrix");
    	//System.out.println("matrix B is a " + bRows + "x" + bCols + " matrix");
    	
    	if (source == btnMultiply) {
    		if(aCols == bRows) {
    			//System.out.println("Can multiply.");
    			answer.setText(v.multiply(aRows,aCols,bRows,bCols));
    		} else {
    			System.out.println("Matrix A must have the same number of columns and Matrix B has rows.");
    		}
    		
    		
    	} else if (source == btnAdd) {
    		if(aRows == bRows && aCols == bCols) {
    			answer.setText(v.addMatrices(aRows, aCols, 1));
    		} else {
    			System.out.println("Matrix A must be the same size as Matrix B.");
    		}
    		
    		
    	} else if (source == btnSubtract) {
    		if(aRows == bRows && aCols == bCols) {
    			answer.setText(v.addMatrices(aRows, aCols, -1));
    		} else {
    			System.out.println("Matrix A must be the same size as Matrix B.");
    		}
    		
    		
    	}
    }
    
    
	
}
