package Model.Composite;

import Model.Operator;
import Model.Visitor.IACVisitor;

public class EquationComponent implements IArithmaticComponent {

	private IArithmaticComponent left;
	private IArithmaticComponent right;
	private Operator operator;
	public int result;
	
	public EquationComponent(IArithmaticComponent _left, Operator _operator, IArithmaticComponent _right) {
		left = _left;
		operator = _operator;
		right = _right;
	}
	
	public IArithmaticComponent getLeft() {
		return left;
	}
	
	public IArithmaticComponent getRight() {
		return right;
	}

	public Operator getOperator() {
		return operator;
	}

	public void accept(IACVisitor visitor) {
		visitor.visit(this);
	}

}
