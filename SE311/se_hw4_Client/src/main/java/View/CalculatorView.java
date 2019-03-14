package View;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class CalculatorView extends JFrame {

    private JTextField jtfTextField;
    
    private JButton jbNum1;
    private JButton jbNum2;
    private JButton jbNum3;
    private JButton jbNum4;
    private JButton jbNum5;
    private JButton jbNum6;
    private JButton jbNum7;
    private JButton jbNum8;
    private JButton jbNum9;
    private JButton jbNum0;
    
    private JButton jbEqual;
    private JButton jbClear;
    
    private JButton jbAdd;
    private JButton jbSubtract;
    private JButton jbMultiply;
    private JButton jbDivide;

    public CalculatorView() {

        JPanel numberPanel = new JPanel();
        numberPanel.setLayout(new GridLayout(4, 5));
        numberPanel.add(jbNum1 = new JButton("1"));
        numberPanel.add(jbNum2 = new JButton("2"));
        numberPanel.add(jbNum3 = new JButton("3"));
        numberPanel.add(new JPanel());
        numberPanel.add(jbAdd = new JButton("+"));
        
        numberPanel.add(jbNum4 = new JButton("4"));
        numberPanel.add(jbNum5 = new JButton("5"));
        numberPanel.add(jbNum6 = new JButton("6"));
        numberPanel.add(new JPanel());
        numberPanel.add(jbSubtract = new JButton("-"));
        
        numberPanel.add(jbNum7 = new JButton("7"));
        numberPanel.add(jbNum8 = new JButton("8"));
        numberPanel.add(jbNum9 = new JButton("9"));
        numberPanel.add(new JPanel());
        numberPanel.add(jbMultiply = new JButton("*"));
        
        numberPanel.add(jbNum0 = new JButton("0"));
        numberPanel.add(jbEqual = new JButton("="));
        numberPanel.add(jbClear = new JButton("C"));
        numberPanel.add(new JPanel());
        numberPanel.add(jbDivide = new JButton("/"));

        JPanel displayPanel = new JPanel();
        displayPanel.setLayout(new FlowLayout());
        displayPanel.add(jtfTextField = new JTextField(20));
        jtfTextField.setHorizontalAlignment(JTextField.RIGHT);
        jtfTextField.setEditable(false);

        JPanel mainPanel = new JPanel();
        
        mainPanel.setLayout(new GridLayout(2,1));
        mainPanel.add(displayPanel);
        mainPanel.add(numberPanel);

        add(mainPanel);

        jbNum1.addActionListener(new DigitNumberListener(1, jtfTextField));
        jbNum2.addActionListener(new DigitNumberListener(2, jtfTextField));
        jbNum3.addActionListener(new DigitNumberListener(3, jtfTextField));
        jbNum4.addActionListener(new DigitNumberListener(4, jtfTextField));
        jbNum5.addActionListener(new DigitNumberListener(5, jtfTextField));
        jbNum6.addActionListener(new DigitNumberListener(6, jtfTextField));
        jbNum7.addActionListener(new DigitNumberListener(7, jtfTextField));
        jbNum8.addActionListener(new DigitNumberListener(8, jtfTextField));
        jbNum9.addActionListener(new DigitNumberListener(9, jtfTextField));
        jbNum0.addActionListener(new DigitNumberListener(0, jtfTextField));

        jbEqual.addActionListener(new EqualsListener(jtfTextField));
        jbClear.addActionListener(new ClearListener(jtfTextField));
        
        jbAdd.addActionListener(new OperatorListener("+", jtfTextField));
        jbSubtract.addActionListener(new OperatorListener("-", jtfTextField));
        jbMultiply.addActionListener(new OperatorListener("*", jtfTextField));
        jbDivide.addActionListener(new OperatorListener("/", jtfTextField));
    }
    
    abstract class textFieldListener {
    	
    	private JTextField textField;
    	
    	public textFieldListener(JTextField _textField) {
    		textField = _textField;
    	}
    	
    	protected void setTextField(String text) {
    		textField.setText(text);
    	}
    }

	class DigitNumberListener extends textFieldListener implements ActionListener {
    	
    	private int digit;
    	
    	public DigitNumberListener(int _digit, JTextField textField) {
    		super(textField);
    		digit = _digit;
    	}

		public void actionPerformed(ActionEvent e) {
			setTextField(Integer.toString(digit));
		}
		
    }
    
    class EqualsListener extends textFieldListener implements ActionListener {

		public EqualsListener(JTextField _textField) {
			super(_textField);
		}

		public void actionPerformed(ActionEvent e) {
			setTextField("=");
		}
    	
    }
    
    class ClearListener extends textFieldListener implements ActionListener {

		public ClearListener(JTextField _textField) {
			super(_textField);
		}

		public void actionPerformed(ActionEvent e) {
			jtfTextField.setText("C");
		}
    	
    }
    
    class OperatorListener extends textFieldListener implements ActionListener {

    	private String operator;
		public OperatorListener(String _operator, JTextField _textField) {
			super(_textField);
			operator = _operator;
		}

		public void actionPerformed(ActionEvent e) {
			jtfTextField.setText(operator);
		}
    	
    }

}