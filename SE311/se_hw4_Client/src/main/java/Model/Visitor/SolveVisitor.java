package Model.Visitor;

import Model.Composite.DigitComponent;
import Model.Composite.EquationComponent;

public class SolveVisitor implements ACVisitor {
	
	private int result = 0;
	
	public int getResult() {
		return result;
	}

	public void visit(DigitComponent digit) {
		result += digit.getDigit();
	}

	public void visit(EquationComponent equation) {
		equation.accept(this);
	}

}
