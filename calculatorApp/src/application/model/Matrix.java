package application.model;

import java.util.ArrayList;

import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class Matrix {

	private TextField A11, A12, A13, A21, A22, A23, A31, A32, A33;
	private TextField B11, B12, B13, B21, B22, B23, B31, B32, B33;
	
	public Matrix(TextField A11,TextField A12,TextField A13,TextField A21,TextField A22,TextField A23,TextField A31,TextField A32,TextField A33,
			TextField B11,TextField B12,TextField B13,TextField B21,TextField B22,TextField B23,TextField B31,TextField B32,TextField B33) {
		this.A11 = A11; this.A12 = A12; this.A13 = A13;
		this.A21 = A21; this.A22 = A22; this.A23 = A23;
		this.A31 = A31; this.A32 = A32; this.A33 = A33;
		
		this.B11 = B11; this.B12 = B12; this.B13 = B13;
		this.B21 = B21; this.B22 = B22; this.B23 = B23;
		this.B31 = B31; this.B32 = B32; this.B33 = B33;
	}
	
	public void validateInput(TextField tf) {
		Alert a = new Alert(AlertType.NONE);
		String regex = "^[0-9]+$";
		if(tf.getText().matches(regex)){
			//CHANCE STYLE TEXTBOX HAS INTEGER
			tf.setStyle("-fx-text-fill: black; -fx-font-size: 16px; -fx-border-color: BLACK; -fx-background-color: #D3D3D3");
	    } else if (tf.getText().equals("")) {
	    	tf.setStyle("-fx-text-fill: black; -fx-font-size: 16px; -fx-border-color: BLACK; -fx-background-color: white");
	    } else { //INVALID
	        a.setAlertType(AlertType.ERROR);
    		//a.setContentText("Your entry at " + tfToString(tf.getId()) + " is invalid.\nPlease input valid character.\nBlank spaces are not checked.");
    		a.showAndWait();
	    }
	}	
	
	public int checkSize(TextField x, TextField y, TextField z) {
		if(x.getText().isEmpty() == false && y.getText().isEmpty() && z.getText().isEmpty()) {
			// ONLY X FILLED
			return 1;
		} else if (x.getText().isEmpty() == false && y.getText().isEmpty() == false && z.getText().isEmpty()) {
			// X and Y FILLED
			return 2;
		} else if (x.getText().isEmpty() == false && y.getText().isEmpty() == false && z.getText().isEmpty() == false){
			// X and Y and Z FILLED
			return 3;
		} 
		// ALL BLANK
		return 0;
    }
	
	public String addMatrices(int a, int b, int sign) {
		int one=0, two=0, three=0, four=0, five=0, six=0, seven=0, eight=0, nine=0;
		String answer = "";
		// a = rows   while   b = cols
		if(a >= 1) {
			if(b >= 1) {
				one = Integer.parseInt(A11.getText()) +  sign*Integer.parseInt(B11.getText());
				answer += one + "\t";
			}
			if(b>=2) {
				two = Integer.parseInt(A12.getText()) +  sign*Integer.parseInt(B12.getText());
				answer += two + "\t";
			}
			if(b==3) {
				three = Integer.parseInt(A13.getText()) +  sign*Integer.parseInt(B13.getText());
				answer += three + "\t";
			}
			answer += "\n";
		}
		if(a >= 2) {
			if(b >= 1) {
				four = Integer.parseInt(A21.getText()) +  sign*Integer.parseInt(B21.getText());
				answer += four + "\t";
			}
			if(b>=2) {
				five = Integer.parseInt(A22.getText()) +  sign*Integer.parseInt(B22.getText());
				answer += five + "\t";
			}
			if(b>=3) {
				six = Integer.parseInt(A23.getText()) +  sign*Integer.parseInt(B23.getText());
				answer += six;
			}	
			answer += "\n";
		}
		if(a >= 3) {
			if(b >= 1) {
				seven = Integer.parseInt(A31.getText()) +  sign*Integer.parseInt(B31.getText());
				answer += seven + "\t";
			}
			if(b>=2) {
				eight = Integer.parseInt(A32.getText()) +  sign*Integer.parseInt(B32.getText());
				answer += eight + "\t";
			}
			if(b>=3) {
				nine = Integer.parseInt(A33.getText()) +  sign*Integer.parseInt(B33.getText());
				answer += nine;
			}	
		}
		return answer;
	}
	
	public String multiply(int aRows, int aCols,int bRows,int bCols) {
		ArrayList<ArrayList<Integer> > matrixA = new ArrayList<ArrayList<Integer> >();
		ArrayList<ArrayList<Integer> > matrixB = new ArrayList<ArrayList<Integer> >();
		
		//GET ELEMENTS FOR MATRIX A
		ArrayList<Integer> aRow1 = new ArrayList<Integer>();
		if(aRows >= 1) {
			if(aCols >= 1) {
				aRow1.add(Integer.parseInt(A11.getText()));
			}
			if(aCols >=2) {
				aRow1.add(Integer.parseInt(A12.getText()));
			}
			if(aCols ==3) {
				aRow1.add(Integer.parseInt(A13.getText()));
			}
			matrixA.add(aRow1);
		}
		
		ArrayList<Integer> aRow2 = new ArrayList<Integer>();
		if(aRows >= 2) {
			if(aCols >= 1) {
				aRow2.add(Integer.parseInt(A21.getText()));
			}
			if(aCols >=2) {
				aRow2.add(Integer.parseInt(A22.getText()));
			}
			if(aCols ==3) {
				aRow2.add(Integer.parseInt(A23.getText()));
			}
			matrixA.add(aRow2);
		}
		
		ArrayList<Integer> aRow3 = new ArrayList<Integer>();
		if(aRows >= 3) {
			if(aCols >= 1) {
					aRow3.add(Integer.parseInt(A31.getText()));
			}
			if(aCols >= 2) {
				aRow3.add(Integer.parseInt(A32.getText()));
			}
			if(aCols == 3) {
				aRow3.add(Integer.parseInt(A33.getText()));
			}
			matrixA.add(aRow3);
		}
		
		//GET ELEMENTS FOR MATRIX B
		ArrayList<Integer> bRow1 = new ArrayList<Integer>();
		if(bRows >= 1) {
			if(bCols >= 1) {
				bRow1.add(Integer.parseInt(B11.getText()));
			}
			if(bCols >=2) {
				bRow1.add(Integer.parseInt(B12.getText()));
			}
			if(bCols ==3) {
				bRow1.add(Integer.parseInt(B13.getText()));
			}
			matrixB.add(bRow1);
		}
		
		ArrayList<Integer> bRow2 = new ArrayList<Integer>();
		if(bRows >= 2) {
			if(bCols >= 1) {
				bRow2.add(Integer.parseInt(B21.getText()));
			}
			if(bCols >=2) {
				bRow2.add(Integer.parseInt(B22.getText()));
			}
			if(bCols ==3) {
				bRow2.add(Integer.parseInt(B23.getText()));
			}
			matrixB.add(bRow2);
		}
		
		ArrayList<Integer> bRow3 = new ArrayList<Integer>();
		if(bRows >= 3) {
			if(bCols >= 1) {
				bRow3.add(Integer.parseInt(B31.getText()));
			}
			if(bCols >= 2) {
				bRow3.add(Integer.parseInt(B32.getText()));
			}
			if(bCols == 3) {
				bRow3.add(Integer.parseInt(B33.getText()));
			}
			matrixB.add(bRow3);
		}		
		
		int[][] result = new int[matrixA.size()][matrixB.get(0).size()];
		for (int row = 0; row < result.length; row++) {
			for(int col = 0; col < result[row].length; col++) {
				result[row][col] = cellValue(matrixA, matrixB, row, col);
			}	
		}
		String answer = "";
		for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[i].length; j++) {
                answer += result[i][j] + "\t";
            }
            answer += "\n";
        }
		return answer;	
	}
	
	
	public int cellValue(ArrayList<ArrayList<Integer> > matrixA, ArrayList<ArrayList<Integer> > matrixB, int row, int col) {
		int value = 0;
		for(int i = 0; i < matrixB.size(); i++) {
			value += matrixA.get(row).get(i) * matrixB.get(i).get(col);
		}
		return value;
	}

}
