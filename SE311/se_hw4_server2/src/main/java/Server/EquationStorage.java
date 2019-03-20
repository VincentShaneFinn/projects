package Server;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class EquationStorage {

	private List<String> equations = new ArrayList<String>();
	
	public void addEquation(String equation) {
		equations.add(equation);
	}
	
	public void printList() {
		equations.forEach(new Consumer<String>() {
			public void accept(String e) {
				System.out.println(equations.indexOf(e) + ": " + e);
			} 
		});
	}
	
	public void printCount() {
		System.out.println(equations.size() + " recorded equations");
	}
}
