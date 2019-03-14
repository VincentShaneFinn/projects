package Controller;

import javax.swing.JFrame;

import View.CalculatorView;

public class CalculatorController {

	CalculatorView view;
	
	public void presentView() {
        view = new CalculatorView();
        view.pack();
        view.setLocationRelativeTo(null);
        view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        view.setVisible(true);
	}
	
}
