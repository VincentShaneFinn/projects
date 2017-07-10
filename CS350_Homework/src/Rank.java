import java.util.Arrays;
import java.util.Enumeration;
import java.util.Vector;

public class Rank extends Matching{
	
	public Rank(){
		
	}
	public Rank(String p, Vector<String> a){
		super.setPrompt(p);
		super.setListA(a);
	}
	public void setListAItem(int n,Vector<String> a){
		super.setListA(a);
	}
	public void setListA(Vector<String> a){
		super.setListA(a);
	}
	public Vector<String> getListA(){
		return super.getListA();
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
		System.out.println("Do you wish to modify a choice?");
		System.out.println("1) Yes");
		System.out.println("2) No");
		n = menus.inputI();
		if (n == 1){
			System.out.println("Which choice do you want to modify");
			System.out.printf(this.getChoicesDisplay());
			n = menus.inputI();
			System.out.println("Choice selected, type new value");
			String s = menus.inputS();
			super.setListAItem(n-1,s);
			System.out.println("Choice updated");
		}
	}
	public void enterResponse(){
		System.out.println("Enter your response");
		String s = menus.inputS().toLowerCase();
		Vector<String> vs = new Vector<String>(Arrays.asList(s.split(" "))); // different from matching and rank, may need to handle input with this late
		super.setResponse(vs);
	}
	public void askQuestion(){
		
	}
	public String getQuestion(){
		String temp;
		temp = super.getPrompt() + "\n(Please Answer the question in this format: 1 2 3 )\n";
		temp = temp + this.getChoicesDisplay() + "\n";
		return temp;
	}
	public String getChoicesDisplay(){
		String temp = "";
		Enumeration vEnum = super.getListA().elements();
		int i = 1;
		while(vEnum.hasMoreElements())
		{
			temp = temp + i + ") " + vEnum.nextElement()+ "\n";
			i++;
		}
		return  temp;
	}
}
