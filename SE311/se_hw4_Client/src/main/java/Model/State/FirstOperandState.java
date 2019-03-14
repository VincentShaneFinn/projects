package Model.State;

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
		
	}

	public void equalsEntered(CalculatorController context) {
		//TODO: Display error
	}

	public void clearEntered(CalculatorController context) {
		context.setDisplayText("");
		context.setState(new InitialState());
	}


}
