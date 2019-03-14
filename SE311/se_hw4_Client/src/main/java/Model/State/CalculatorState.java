package Model.State;

import Controller.CalculatorController;
import Model.Operator;

public interface CalculatorState {
	public void digitEntered(CalculatorController controller, int digit);
	public void operatorEntered(CalculatorController controller, Operator operator);
}
