package Model.Composite;

import Model.Visitor.IACVisitor;

public class DigitComponent implements IArithmaticComponent {

	private int digit;
	
	public DigitComponent(int _digit) {
		digit = _digit;
	}
	
	public int getDigit() {
		return digit;
	}

	public void accept(IACVisitor visitor) {
		visitor.visit(this);
	}

}
