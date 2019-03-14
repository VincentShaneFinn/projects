package Model.State;

import Controller.CalculatorController;

public interface ICalculatorState {
	public void digitEntered(CalculatorController context, String digit);
	public void operatorEntered(CalculatorController context, String operator);
	public void equalsEntered(CalculatorController context);
	public void clearEntered(CalculatorController context);
}
