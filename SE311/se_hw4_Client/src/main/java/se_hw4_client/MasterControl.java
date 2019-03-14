package se_hw4_client;

import javax.swing.JFrame;

public class MasterControl {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        JavaCalculator calc = new JavaCalculator();
        calc.pack();
        calc.setLocationRelativeTo(null);
                calc.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        calc.setVisible(true);
	}

	
}
