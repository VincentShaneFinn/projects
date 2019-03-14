package Controller;

import javax.swing.JFrame;

import Model.Visitor.DisplayVisitor;
import View.CalculatorView;

public class MasterControl {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		DisplayVisitor dv = new DisplayVisitor();
		dv.visit(new Model.Composite.EquationComponent(new Model.Composite.DigitComponent(2), Model.Operator.Plus, new Model.Composite.DigitComponent(3)));
		dv.print();
		
		CalculatorController controller =  new CalculatorController();
		controller.presentView();
	}

	
}
