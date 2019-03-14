package Model.State;

import Controller.CalculatorController;

public class NextOperandState implements ICalculatorState {

	private String operator;
	private int precedingValue;
	private String nextValue;
	
	public NextOperandState(String _operator, int _precedingValue, String _nextValue) {
		operator = _operator;
		precedingValue = _precedingValue;
		nextValue = _nextValue;
	}

	public void digitEntered(CalculatorController context, String digit) {
		// TODO Auto-generated method stub
		
	}

	public void operatorEntered(CalculatorController context, String operator) {
		// TODO Auto-generated method stub
		
	}

	public void equalsEntered(CalculatorController context) {
		//GOTO calculate state
	}

	public void clearEntered(CalculatorController context) {
		context.clear();
	}

	
}
