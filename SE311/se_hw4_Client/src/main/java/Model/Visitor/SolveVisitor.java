package Model.Visitor;

import Model.Composite.DigitComponent;
import Model.Composite.EquationComponent;

public class SolveVisitor implements IACVisitor {
	
	private int currentEvaluation = 0;
	private int currentDigit = 0;
	
	public int getResult() {
		return currentEvaluation;
	}

	public void visit(DigitComponent digit) {
		currentDigit = digit.getDigit();
	}

	public void visit(EquationComponent equation) {
		equation.getLeft().accept(this);
		int left = currentDigit;
		equation.getRight().accept(this);
		int right = currentDigit;
		
		if(equation.getOperator().equals("+")) {
			currentEvaluation = left + right;
		}
		if(equation.getOperator().equals("-")) {
			currentEvaluation = left - right;
		}
		if(equation.getOperator().equals("*")) {
			currentEvaluation = left * right;
		}
		if(equation.getOperator().equals("/")) {
			currentEvaluation = left / right;
		}
	}

}
