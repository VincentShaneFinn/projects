package Model.Visitor;

import Model.Composite.*;

public interface ACVisitor {
	public void visit(DigitComponent digit);
	public void visit(EquationComponent equation);
}
