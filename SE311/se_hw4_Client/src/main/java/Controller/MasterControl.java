package Controller;

import javax.swing.JFrame;

import View.CalculatorView;

public class MasterControl {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.out.println(new Model.Composite.EquationComponent(new Model.Composite.DigitComponent(2), Model.Operator.Plus, new Model.Composite.DigitComponent(3)).result());
		
		CalculatorController controller =  new CalculatorController();
		controller.presentView();
	}

	
}
