package Model.Visitor;

import Model.Composite.DigitComponent;
import Model.Composite.EquationComponent;

public class SolveVisitor implements IACVisitor {
	
	private int currentEvaluation = 0;
	
	public int getResult() {
		return currentEvaluation;
	}

	public void visit(DigitComponent digit) {
		currentEvaluation = digit.getDigit();
	}

	public void visit(EquationComponent equation) {
		equation.getLeft().accept(this);
		int left = currentEvaluation;
		equation.getRight().accept(this);
		int right = currentEvaluation;
		
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
