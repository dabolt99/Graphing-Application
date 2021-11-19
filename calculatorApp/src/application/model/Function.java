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
public class Function {
	
	//used to hold the user input before evaluating special chars
	//like 5(3+x) x^5 etc
	private String rawFunction;
	private String equation;
	//function x y interccepts
	private double xInt, yInt;
	
	//constructor
	public Function(String input){
		
		System.out.println("Function constructor called");
		
		setFunction(input);
		formatFunction(input);
		
		//temporary until the logic for calculating is implemented
		setXInt(10.66);
		setYInt(20.77);
	}
	
	private void formatFunction(String input) {
		
		
		//formats using some regex bits
		equation = rawFunction.replace("sin", "Math.sin").
			    replace("tan", "Math.tan").
			    replace("sqrt", "Math.sqrt").
			    replace("sqr", "Math.pow").
			    replace("pi", "Math.PI").
			    replace("e", "Math.E").
			    replace("pi", "Math.PI").
			    replace("pi", "Math.PI").
			    replace("pi", "Math.PI").
			    replace("log", "Math.log");
		
		//used to handle nesting of cos ex: 5*cos(5*x+cos(5))
		//".*(?<!Math.)cos\\(([^<]*)\\).*" matches anything up to cos( NOT preceded by 'Math.'
		//stores inside of ( ) then matches closing ) followed by anything
		while (equation.matches(".*(?<!Math.)cos\\(([^<]*)\\).*")) {
			equation = equation.replaceAll("(?<!Math.)cos\\(([^<]*)\\)", "Math.cos($1)");
		}
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
