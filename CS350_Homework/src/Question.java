import java.io.Serializable;
import java.util.*;

public class Question implements Serializable {

	private String prompt;
	private Response response; // each question will hold the user response
	public IOandMenus menus = new IOandMenus(); // learned a very valuable lesson here, when you add a new object, the old serialized files no longer load, need to create a new survey. Wonder how to use old serialized files when adding objects later?
	public Question(){
		
	}
	public void setPrompt(String p){
		prompt = p;
	}
	public String getPrompt(){
		return prompt;
	}
	public void modifyQuestion(){
		System.out.println("Do you wish to modify the prompt?");
		System.out.println("1) Yes");
		System.out.println("2) No");
		int n = menus.inputI();
		if (n == 1){
			System.out.println("Enter a new prompt:");
			prompt = menus.inputS();
		}
	}
	public void enterResponse(){
		System.out.println("Enter your response");
		Vector<String> vs = new Vector<String>();
		vs.addElement(menus.inputS().toLowerCase());
		response = new Response(vs);
	}
	public void setResponse(Vector<String> vs){
		response = new Response(vs);
	}
	public Response getResponse(){
		return response;
	}
	public void askQuestion(){
		
	}
	public String getQuestion(){
		return prompt + "\n\n";
	}
	
}
