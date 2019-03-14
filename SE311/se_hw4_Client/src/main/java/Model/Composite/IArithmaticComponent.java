package Model.Composite;

import Model.Visitor.IACVisitor;

public interface IArithmaticComponent {
	public void accept(IACVisitor visitor);
}
