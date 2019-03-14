package Controller;

import javax.swing.JFrame;

import Model.Operator;
import Model.Visitor.*;
import Model.Composite.*;
import View.CalculatorView;

public class MasterControl {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		DisplayVisitor dv = new DisplayVisitor();
		dv.visit(new Model.Composite.EquationComponent(new DigitComponent(2), Operator.Plus, new DigitComponent(3)));
		dv.print();
		
		CalculatorController controller =  new CalculatorController();
		controller.presentView();
	}

	
}
