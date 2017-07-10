import java.util.Arrays;
import java.util.Enumeration;
import java.util.Vector;

public class Matching extends Question{

	private Vector<String> listA;
	private Vector<String> listB;
	
	public Matching(){
		
	}
	public Matching(String p, Vector<String> a, Vector<String> b){
		super.setPrompt(p);
		listA = a;
		listB = b;
	}
	public void setListA(Vector<String> a){
		listA = a;
	}
	public void setListAItem(int n, String p){
		listA.setElementAt(p,n);
	}
	public Vector<String> getListA(){
		return listA;
	}
	public void setListB(Vector<String> b){
		listB = b;
	}
	public void setListBItem(int n, String p){
		listB.setElementAt(p,n);
	}
	public Vector<String> getListB(){
		return listB;
	}
	public void modifyQuestion(){
		System.out.println("Do you wish to modify the prompt?");
		System.out.println("1) Yes");
		System.out.println("2) No");
		int n = menus.inputI();
		if (n == 1){
			System.out.println("Enter a new prompt:");
			super.setPrompt(menus.inputS());
		}
		System.out.println();
		System.out.println("Do you wish to modify a choice from list A?");
		System.out.println("1) Yes");
		System.out.println("2) No");
		n = menus.inputI();
		if (n == 1){
			System.out.println("Which choice do you want to modify");
			System.out.printf(this.getChoicesADisplay());
			n = menus.inputI();
			System.out.println("Choice selected, type new value");
			String s = menus.inputS();
			this.setListAItem(n-1,s);
			System.out.println("Choice updated");
		}
		System.out.println();
		System.out.println("Do you wish to modify a choice from list B?");
		System.out.println("1) Yes");
		System.out.println("2) No");
		n = menus.inputI();
		if (n == 1){
			System.out.println("Which choice do you want to modify");
			System.out.printf(this.getChoicesBDisplayNumbered());
			n = menus.inputI();
			System.out.println("Choice selected, type new value");
			String s = menus.inputS();
			this.setListBItem(n-1,s);
			System.out.println("Choice updated");
		}
	}
	public void enterResponse(){
		System.out.println("Enter your response");
		String s = menus.inputS().toLowerCase();
		Vector<String> vs = new Vector<String>(Arrays.asList(s.split(" "))); 
		super.setResponse(vs);
	}
	public void askQuestion(){
		
	}

	public String getQuestion(){
		String temp;
		temp = super.getPrompt() + "\n(Please Answer the question in this format: 1,x 2,x 3,x # where x is the letter you think matches that number)\n";
		temp = temp + this.getChoicesADisplay() + "\n";
		temp = temp + this.getChoicesBDisplay() + "\n";
		return temp;
	}
	public String getChoicesADisplay(){
		String temp = "";
		Enumeration vEnum = this.getListA().elements();
		int i = 1;
		while(vEnum.hasMoreElements()){
			temp = temp + i + ") " + vEnum.nextElement()+ "\n";
			i++;
		}
		return  temp;
	}
	public String getChoicesBDisplay(){
		String temp = "";
		char c = 'a';
		Enumeration vEnumB = listB.elements();
		while(vEnumB.hasMoreElements()){
			temp = temp + c + ") " + vEnumB.nextElement()+ "\n";
			c++;
		}
		return  temp;
	}
	public String getChoicesBDisplayNumbered(){
		String temp = "";
		int c = 1;
		Enumeration vEnumB = listB.elements();
		while(vEnumB.hasMoreElements()){
			temp = temp + c + ") " + vEnumB.nextElement()+ "\n";
			c++;
		}
		return  temp;
	}
}
		