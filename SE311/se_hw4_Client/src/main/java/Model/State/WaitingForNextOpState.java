package Model.State;

import Controller.CalculatorController;

public class WaitingForNextOpState implements ICalculatorState {
	
	private String operator;
	private int precedingValue;
	
	public WaitingForNextOpState(String _operator, int _precedingValue) {
		operator = _operator;
		precedingValue = _precedingValue;
	}

	public void digitEntered(CalculatorController context, String digit) {
		context.setDisplayText(digit);
		context.setState(new NextOperandState(operator, precedingValue, digit));
	}

	public void operatorEntered(CalculatorController context, String operator) {
		context.errorMessage();
	}

	public void equalsEntered(CalculatorController context) {
		context.errorMessage();
	}

	public void clearEntered(CalculatorController context) {
		context.clear();
	}

}
