import java.util.Vector;

public class TorF extends MultipleChoice{

	public TorF(){
		
	}
	public TorF(String p){
		super.setPrompt(p);
		Vector<String> c = new Vector();
		c.addElement("True");
		c.addElement("False");
		super.setChoices(c);
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
	}
	public void enterResponse(){
		System.out.println("Enter your Response, 1 for True or 2 for False:");
		int n = menus.inputI();
		if(n != 1 && n != 2){
			System.out.println("Please type 1 for True or 2 for False");
			this.enterResponse();
		}
		Vector<String> vs = new Vector<String>();
		vs.addElement(Integer.toString(n));
		super.setResponse(vs);
	}
	public void askQuestion(){
		
	}
}
