package application.model;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

//test edit for testing github pushj
//2nd test
//3rd test

//this will be tossed back and forth between graphController and funcInfoController
//
//
// can calculate for cos sin tan log sqrt square e and pi
// user input must have terms seperated by a basic operator
// and squares like (5)^(x) - VALID
// sin(5*cos(3+log(x))) - VALID
public class Function {
	
	//used to hold the user input before evaluating special chars
	//like 5(3+x) x^5 etc
	private String userInput;
	private String equation;
	//function x y interccepts
	private double xInt, yInt;
	
	//constructor
	public Function(String input){
		
		System.out.println("Function constructor called");
		
		setFunction(input);
		formatFunction();
		
		//temporary until the logic for calculating is implemented
		setXInt(10.66);
		setYInt(20.77);
	}
	
	private void formatFunction() {
		
		
		equation = userInput;
		//formats using some regex bits
		//used to handle nesting of cos ex: 5*cos(5*x+cos(5))
		//".*(?<!Math.)cos\\(([^<]*)\\).*" matches anything up to cos( NOT preceded by 'Math.'
		//stores inside of ( ) then matches closing ) followed by anything
		while (equation.matches(".*(?<!Math.)cos\\(([^<]*)\\).*")) {
			equation = equation.replaceAll("(?<!Math.)cos\\(([^<]*)\\)", "Math.cos($1)");
		}
		while (equation.matches(".*(?<!Math.)sin\\(([^<]*)\\).*")) {
			equation = equation.replaceAll("(?<!Math.)sin\\(([^<]*)\\)", "Math.sin($1)");
		}
		while (equation.matches(".*(?<!Math.)tan\\(([^<]*)\\).*")) {
			equation = equation.replaceAll("(?<!Math.)cos\\(([^<]*)\\)", "Math.tan($1)");
		}
		while (equation.matches(".*(?<!Math.)sqrt\\(([^<]*)\\).*")) {
			equation = equation.replaceAll("(?<!Math.)sqrt\\(([^<]*)\\)", "Math.sqrt($1)");
		}
		while (equation.matches(".*(?<!Math.)log\\(([^<]*)\\).*")) {
			equation = equation.replaceAll("(?<!Math.)log\\(([^<]*)\\)", "Math.log($1)");
		}
		//figure out math.pow
		while (equation.matches(".*\\(([^<]*)\\)\\^\\(([^<]*)\\).*")) {
			equation = equation.replaceAll(".*\\(([^<]*)\\)\\^\\(([^<]*)\\).*", "Math.pow($1, $2)");
		}
		
		while (equation.matches(".*pi.*")) {
			equation = equation.replaceAll("pi", "Math.PI");
		}
		while (equation.matches(".*e.*")) {
			equation = equation.replaceAll("e", "Math.E");
		}
		System.out.println(equation);
	}
	
	//to be used for user calulating manually will need to have access to the 
	//equation in terms of x and y
	//right now just double the input and returns it to be used as the other value
	//used like calcVal(userInput taken from text field, 'x' or 'y' to solve for)
	public double calculateValues(double input, char toSolveFor) throws ScriptException {
		//ScriptEngine created so eval() from javascript can be used to do the math
		ScriptEngineManager mgr = new ScriptEngineManager();
		ScriptEngine engine = mgr.getEngineByName("JavaScript");

		switch(toSolveFor) {
		case 'x':
			//gonna have to use newtons method or something
			//solve for x given y
			return 1066;
		case 'y':
			//solve for y given x
			String temp = this.getEquation();
			temp = temp.replace("x", String.valueOf(input));
			System.out.println(temp);
			String val = engine.eval(temp).toString();
			return Double.valueOf(val);
		}
		
		return -1;
	}
	
	
	public void setFunction(String input) {
		rawFunction = input;
	}
	
	public void setXInt(double input) {
		xInt = input;
	}
	
	public void setYInt(double input) {
		yInt = input;
	}
	
	public String getFunction() {
		return rawFunction;
	}
	
	public String getEquation() {
		return equation;
	}
	
	public double getXInt() {
		return xInt;
	}
	
	public double getYInt() {
		return yInt;
	}	

}
