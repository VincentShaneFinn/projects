//package Controller;
//
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
//import javax.swing.JTextField;
//
//public abstract class textFieldListener {
//	
//	private JTextField textField;
//	
//	public textFieldListener(JTextField _textField) {
//		textField = _textField;
//	}
//	
//	protected void setTextField(String text) {
//		textField.setText(text);
//	}
//}
//
//public class DigitNumberListener extends textFieldListener implements ActionListener {
//	
//	private int digit;
//	
//	public DigitNumberListener(int _digit, JTextField textField) {
//		super(textField);
//		digit = _digit;
//	}
//
//	public void actionPerformed(ActionEvent e) {
//		setTextField(Integer.toString(digit));
//	}
//	
//}
//
//public class EqualsListener extends textFieldListener implements ActionListener {
//
//	public EqualsListener(JTextField _textField) {
//		super(_textField);
//	}
//
//	public void actionPerformed(ActionEvent e) {
//		setTextField("=");
//	}
//	
//}
//
//public class ClearListener extends textFieldListener implements ActionListener {
//
//	public ClearListener(JTextField _textField) {
//		super(_textField);
//	}
//
//	public void actionPerformed(ActionEvent e) {
//		setTextField("C");
//	}
//	
//}
//
//public class AddListener extends textFieldListener implements ActionListener {
//
//	public AddListener(JTextField _textField) {
//		super(_textField);
//	}
//
//	public void actionPerformed(ActionEvent e) {
//		setTextField("+");
//	}
//	
//}
//
//public class SubtractListener extends textFieldListener implements ActionListener {
//
//	public SubtractListener(JTextField _textField) {
//		super(_textField);
//	}
//
//	public void actionPerformed(ActionEvent e) {
//		setTextField("-");
//	}
//	
//}
//
//public class MultiplyListener extends textFieldListener implements ActionListener {
//
//	public MultiplyListener(JTextField _textField) {
//		super(_textField);
//	}
//
//	public void actionPerformed(ActionEvent e) {
//		setTextField("*");
//	}
//	
//}
//
//public class DivideListener extends textFieldListener implements ActionListener {
//
//	public DivideListener(JTextField _textField) {
//		super(_textField);
//	}
//
//	public void actionPerformed(ActionEvent e) {
//		setTextField("/");
//	}
//	
//}