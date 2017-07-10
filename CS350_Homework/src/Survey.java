import java.io.Serializable;
import java.util.*;

public class Survey implements Serializable  {

	private Vector<Question> questions;
	public IOandMenus menus = new IOandMenus(); // Survey uses inputI and inputS from IOandMenus
	protected Output out = new Output();
	private int questionNumber;
	
	public Survey(){
		
	}
	// second menu is handled by each survey and test separately
	public int displayMenu2(){
		while(0==0)
		{
			System.out.println(" ");
			System.out.println("1) Create a new Survey");
			System.out.println("2) Display a Survey");
			System.out.println("3) Load a Survey");
			System.out.println("4) Save a Survey");
			System.out.println("5) Modify an Existing Survey");
			System.out.println("6) Take a Survey");
			System.out.println("7) Tabulate a Survey");
			System.out.println("8) Quit");
		    int c = menus.inputI();
		    if (c == 1){
		    	this.create();
		    }
		    else if (c == 2){
		    	this.displayConsoleOrVoice();
		    }
		    // save and load have to be serialized outside of this class to save the survey or test object to a file
		    else if (c == 3){ 
		    	return 1;
		    }
		    else if (c == 4){
		    	return 2;
		    }
		    else if (c == 5){
		    	return 3;
		    }
		    else if (c == 6){
		    	return 4;
		    }
		    else if (c == 7){
		    	return 5;
		    }
		    else if (c == 8) {
		    	return 7;
		    }
		}
		
	}
	// 6 choices for questions, 7 returns to menu 2
	public void create(){
		questions = new Vector<Question>(); // initialize the questions vector here 
		int c = this.createMainDisplay();
		this.createQuestion(c);
		while (c!=7)
		{
			c = this.createMainDisplay();
			this.createQuestion(c);
		}
		
	}
	//display create menu, get user input and return it
	public int createMainDisplay(){
		System.out.println(" ");
		System.out.println("1) Add a new T/F Question");
		System.out.println("2) Add a new Multiple Choice Question");
		System.out.println("3) Add a new Short Answer Question");
		System.out.println("4) Add a new Essay Question");
		System.out.println("5) Add a new Ranking Question");
		System.out.println("6) Add a new Matching Question");
		System.out.println("7) Return to Previous Menu");
		return menus.inputI();
	}
	// 6 if statements to create the individual questions
	public void createQuestion(int c){
		String s;
		if (c == 1){
			System.out.println("Enter the prompt for your True or False question:");
			s = menus.inputS();
			Question torf = new TorF(s);
			this.appendQuestion(torf);
		}
		if (c == 2){
			System.out.println("Enter the prompt for your Multiple Choice question:");
			s = menus.inputS();
			System.out.println("Enter choice, type $$ to stop adding choices.");
			String choice = "";
			Vector<String> choices = new Vector<String>();
			while (!choice.equals("$$")){
				choice = menus.inputS();
				if (!choice.equals("$$")){
					choices.addElement(choice);
				}
			}
			Question mc = new MultipleChoice(s,choices);
			this.appendQuestion(mc);
		}
		if (c == 3){
			System.out.println("Enter the prompt for your Short Answer question:");
			s = menus.inputS();
			Question sa = new ShortAnswer(s);
			this.appendQuestion(sa);
		}
		if (c == 4){
			System.out.println("Enter the prompt for your Essay question:");
			s = menus.inputS();
			Question e = new Essay(s);
			this.appendQuestion(e);
		}
		if (c == 5){
			System.out.println("Enter the prompt for your Ranking question:");
			s = menus.inputS();
			System.out.println("Enter choice, type $$ to stop adding choices.");
			String choice = "";
			Vector<String> choices = new Vector<String>();
			while (!choice.equals("$$")){
				choice = menus.inputS();
				if (!choice.equals("$$")){
					choices.addElement(choice);
				}
			}
			Question r = new Rank(s,choices);
			this.appendQuestion(r);
		}		
		if (c == 6){
			System.out.println("Enter the prompt for your Matching question:");
			s = menus.inputS();
			System.out.println("Enter List A choices, type $$ to stop adding choices.");
			String choice = "";
			Vector<String> Achoices = new Vector<String>();
			while (!choice.equals("$$")){
				choice = menus.inputS();
				if (!choice.equals("$$")){
					Achoices.addElement(choice);
				}
			}
			System.out.println("Enter List B choices, type $$ to stop adding choices.");
			choice = "";
			Vector<String> Bchoices = new Vector<String>();
			while (!choice.equals("$$")){
				choice = menus.inputS();
				if (!choice.equals("$$")){
					Bchoices.addElement(choice);
				}
			}
			Question m = new Matching(s,Achoices,Bchoices);
			this.appendQuestion(m);
		}
		
	}
	// displays the test and handles if no survey is loaded or created
	public void displayConsoleOrVoice(){
		out.toConsole("\nHow do you wish to display the survey?");
		out.toConsole("1) print to console");
		out.toConsole("2) have a voice read off the entire survey");
		int c = menus.inputI();
		if(c == 1){
			this.display();
		}
		if(c == 2){
			this.playVoice();
		}
	}
	public void display(){
		try{
		int n = questions.size();
		for (int i = 0; i < n;i++){
			out.toConsole(" ");
			out.toConsole(i+1 + ". " + questions.get(i).getQuestion());
		}
		}
		catch(NullPointerException npe){
			out.toConsole("\nBlank Survey");
		}
	}
	public void playVoice(){
		try{
		int n = questions.size();
		for (int i = 0; i < n;i++){
			out.toVoice(" ");
			out.toVoice(i+1 + ". " + questions.get(i).getQuestion());
		}
		}
		catch(NullPointerException npe){
			out.toVoice("\nBlank Survey");
		}
	}
	//modify asks for the question # you want to modify and then goes to each questions individual way of asking how you want to modify that question since some are set up differently.
	public void modify(){
		try{
		if (questions.size() == 0){
			return;
		}
		System.out.println("Enter the number of the question you wish to modify");
		questionNumber = menus.inputI();
		questionNumber = questionNumber - 1;
		System.out.printf(questionNumber + 1 + ". " + questions.get(questionNumber).getQuestion() + "\n");
		questions.get(questionNumber).modifyQuestion();
		}
		catch(NullPointerException npe){
			System.out.println("\nBlank Survey\n");
		}
	}
	public void setQuestionNumber(int n){
		questionNumber = n;
	}
	public int getQuestionNumber(){
		return questionNumber;
	}
	public void take(){
		try{
		int n = questions.size();
		for (int i = 0; i < n;i++){
			System.out.println(" ");
			System.out.printf(i+1 + ". " + questions.get(i).getQuestion());
			questions.get(i).enterResponse();
		}
		}
		catch(NullPointerException npe){
			System.out.println("\nBlank Survey");
		}
	}
	public double grade(){
		return -1;
	}
	// several helper functions
	public Vector<Question> getQuestions(){
		return questions;
	}
	public void setQuestions(Vector<Question> q){
		questions = q;
	}
	public void setQuestion(int n, Question q){
		questions.set(n, q);
		
	}
	public Question getQuestion(int n){
		return questions.get(n);
	}
	public void appendQuestion(Question q){
		questions.addElement(q);	
	}
	public void insertQuestion(int n, Question q){
		questions.insertElementAt(q,n);
	}
	public void deleteQuestion(int n){
		questions.remove(n);
	}
	public void setResponse(int n){
		this.getQuestion(n).enterResponse();
	}
	public void getResponse(int n){
		this.getQuestion(n).getResponse();
	}
	
}
