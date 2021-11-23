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
	private double[] xInt = {0, 0, 0};
	
	//constructor
	public Function(String input) throws ScriptException{
		
		System.out.println("Function constructor called");
		
		setFunction(input);
		formatFunction();
		
		//temporary until the logic for calculating is implemented
		setXInt(calculateValues(0, 'x'));

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
			System.out.println("mathed pow");
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
		
		//double

		switch(toSolveFor) {
		case 'x':
			double yOffset = input;
			//gonna have to use newtons method or something
			//solve for x given y
			//use random value
			//find slope fo func at random value
			//go to where that slope has x intercept
			//use that as neww guess
			//repeat  until guess is > 0.00000 accuracy to 0.0
			double randGuess = Math.random()* 10;
			double fGuess = calculateValues(randGuess, 'y');
			//used to approximate slope at guess
			double guessP1 = calculateValues(randGuess + .01, 'y');
			double guessP2 = calculateValues(randGuess - .01, 'y');
			//slope = rise / run = (guessP1 - guessP2) / (.02)
			double slope = (guessP1 - guessP2) / (.02);
			//System.out.println("Slope: " + slope);
			//System.out.println("x(randGuess): " + randGuess);
			//System.out.println("y(fGuess): " + fGuess/slope);
			double x2nd = randGuess - ((fGuess - yOffset)/slope);
			
			//double scale = Math.pow(10, 6);
		    //x2nd = Math.round(x2nd * scale) / scale;
			
			System.out.println("x: " + randGuess + "\nY: " + fGuess );
			//if (calculateValues(x2nd, 'y') >= 0 && 
			//		calculateValues(x2nd, 'y') <= .000001) {
			//	return x2nd;
			//}
			double x1 = x2nd;
			int runs  = 0;
			double scale = Math.pow(10, 5);
			while(!(calculateValues(x1, 'y') >= 0 + yOffset && 
					calculateValues(x1, 'y') <= .0000001 + yOffset) && runs < 25) {
				//keep doing newt meth
				double y0 = calculateValues(x1, 'y');
				//used to approximate slope at guess
				double y1 = calculateValues(x1 + .01, 'y');
				double y2 = calculateValues(x1 - .01, 'y');
				//slope = rise / run = (guessP1 - guessP2) / (.02)
				slope = (y1 - y2) / (.02);
				//System.out.println("Slope: " + slope);
				//System.out.println("x(randGuess): " + randGuess);
				//System.out.println("y(fGuess): " + fGuess/slope);
				x1 = x1 - ((y0 - yOffset)/slope);
				System.out.println("\nYoff(Slope): " + yOffset + "(" + slope + ")" + "\nRuns: " + runs + "\nReturn: " + x1);
				//System.out.println("Yoff: " + yOffset);
				runs++;
			}

		    x1 = Math.round(x1 * scale) / scale;
			System.out.println("\nYoff: " + yOffset + "\nRuns: " + runs + "\nReturn: " + x1);
			return x1;
			
			
			
			
			//return 1066;
		case 'y':
			//solve for y given x
			String temp = this.getEquation();
			temp = temp.replace("x", String.valueOf(input));
			//System.out.println(temp);
			String val = engine.eval(temp).toString();
			return Double.valueOf(val);
		}
		
		return -1;
	}
	
	
	public void setFunction(String input) {
		userInput = input;
	}
	
	public void setXInt(double input) {
		xInt[0] = input;
	}
	
	
	public String getFunction() {
		return userInput;
	}
	
	public String getEquation() {
		return equation;
	}
	
	public double getXInt() {
		return xInt[0];
	}
	

}
