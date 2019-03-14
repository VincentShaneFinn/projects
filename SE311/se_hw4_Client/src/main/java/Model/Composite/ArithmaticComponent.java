package Model.Composite;

import Model.Visitor.ACVisitor;

public interface ArithmaticComponent {
	public void accept(ACVisitor visitor);
}
