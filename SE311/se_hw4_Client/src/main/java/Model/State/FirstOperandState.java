package Model.State;

import javax.swing.JOptionPane;

import Controller.CalculatorController;
import Model.Operator;

public class FirstOperandState implements ICalculatorState{
	
	private String number;
	
	public FirstOperandState(String _digit) {
		number = _digit;
	}

	public void digitEntered(CalculatorController context, String digit) {
		number += digit;
		context.setDisplayText(number);
	}

	public void operatorEntered(CalculatorController context, Operator operator) {
		// TODO Auto-generated method stub
		//context.setDisplayText(operator);
	}

	public void equalsEntered(CalculatorController context) {
		
		Object[] options = {"Reset", "Discard"};
		//Custom button text
		int n = JOptionPane.showOptionDialog(context.getView(),
			    "Would you like to reset and clear the calculator or discard your last input",
			    "Ops, something went wrong",
			    JOptionPane.YES_NO_OPTION,
			    JOptionPane.QUESTION_MESSAGE,
			    null,     //do not use a custom Icon
			    options,  //the titles of buttons
			    options[1]); //default button title
		
		if(n == 0) {
			clearEntered(context);
		}
		
	}

	public void clearEntered(CalculatorController context) {
		context.setDisplayText("");
		context.setState(new InitialState());
	}


}
