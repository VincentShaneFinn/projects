import java.io.*;
import java.util.*;

//much of these classes make the necessary changes from the survey class
public class Test extends Survey {

	private Vector<Response> correctAnswers;
	private double score;
	
	public Test(){
		
	}
	public int displayMenu2(){
		while(0==0)
		{
			System.out.println(" ");
			System.out.println("1) Create a new Test");
			System.out.println("2) Display a Test");
			System.out.println("3) Load a Test");
			System.out.println("4) Save a Test");
			System.out.println("5) Modify an Existing Test");
			System.out.println("6) Take a Test");
			System.out.println("7) Tabulate a Test");
			System.out.println("8) Grade a Test");
			System.out.println("9) Quit");
		    int c = menus.inputI();
		    if (c == 1){
		    	this.create();
		    }
		    else if (c == 2){
		    	System.out.println(" ");
		    	this.displayMenu();
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
		    	return 6;
		    }
		    else if (c == 9){
		    	return 7;
		    }
		}
	}
	// updated create to add the create correct answer prompt
	public void create(){
		correctAnswers = new Vector<Response>();
		super.setQuestions(new Vector<Question>());
		int c = super.createMainDisplay();
		super.createQuestion(c);
		this.createCorrectAnswer(c);
		while (c!=7)
		{
			c = super.createMainDisplay();
			super.createQuestion(c);
			this.createCorrectAnswer(c);
		}
	}
	// each if statement adds to the vector of responses
	public void createCorrectAnswer(int c){
		Vector<String> vs;
		int n;
		if (c == 1){ // always true or false, just use numbers to handle when user doesn't type 1 for true or 2 for false 
			System.out.println("Enter correct choice, 1 for True or 2 for False:");
			n = menus.inputI();
			if(n != 1 && n != 2){
				System.out.println("Please type 1 for True or 2 for False");
				createCorrectAnswer(c);
			}
			vs = new Vector<String>();
			vs.addElement(Integer.toString(n));
			Response torfCA = new Response(vs);
			correctAnswers.addElement(torfCA);
				
		}
		if (c == 2){
			System.out.println("Enter correct choice:");
			String s = menus.inputS().toLowerCase();
			vs = new Vector<String>(Arrays.asList(s.split(" "))); // different from matching and rank, may need to handle input with this later
			Response mcCA = new Response(vs);
			correctAnswers.addElement(mcCA);
				
		}
		if (c == 3){
			System.out.println("Enter correct answer:");
			String s = menus.inputS().toLowerCase();
			vs = new Vector<String>();
			vs.addElement(s.toLowerCase());
			Response saCA = new Response(vs);
			correctAnswers.addElement(saCA);
				
		}
		if (c == 4){
			String s = "Essay question";
			vs = new Vector<String>();
			vs.addElement(s);
			Response torfCA = new Response(vs);
			correctAnswers.addElement(torfCA);
				
		}
		if (c == 5){ // rank correct answer format 1 2 3
			System.out.println("Enter correct order of choices");
			String s = menus.inputS().toLowerCase();
			vs = new Vector<String>(Arrays.asList(s.split(" "))); // different from matching and rank, may need to handle input with this later
			Response mcCA = new Response(vs);
			correctAnswers.addElement(mcCA);
				
		}
		if (c == 6){ // matching correct answer format 1,a 2,b 3,c
			System.out.println("Enter correct order of choices");
			String s = menus.inputS().toLowerCase();
			vs = new Vector<String>(Arrays.asList(s.split(" "))); // different from matching and rank, may need to handle input with this later
			Response mcCA = new Response(vs);
			correctAnswers.addElement(mcCA);
				
		}
	}
	// chose to display with or without correct answers
	public void displayMenu(){
		while (0 == 0){
			System.out.println("1) Display without correct answers");
			System.out.println("2) Display with correct answers");
			int c;
			c = menus.inputI();
			if (c==1){
				this.displayConsoleOrVoice();
				return;
			}
			else if(c==2){
				this.displayCAConsoleOrVoice();
				return;
			}
		}
		
	}
	public void displayConsoleOrVoice(){
		out.toConsole("\nHow do you wish to display the test?");
		out.toConsole("1) print to console");
		out.toConsole("2) have a voice read off the entire test");
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
		int n = super.getQuestions().size();
		for (int i = 0; i < n;i++){
			out.toConsole(i+1 + ". " + super.getQuestions().get(i).getQuestion());
		}
		}
		catch(NullPointerException npe){
			out.toConsole("\nBlank Survey");
		}
	}
	public void playVoice(){
		try{
		int n = super.getQuestions().size();
		for (int i = 0; i < n;i++){
			out.toVoice(" ");
			out.toVoice(i+1 + ". " + super.getQuestions().get(i).getQuestion());
		}
		}
		catch(NullPointerException npe){
			out.toVoice("\nBlank Survey");
		}
	}
	public void displayCAConsoleOrVoice(){
		
			out.toConsole("\nHow do you wish to display the test?");
			out.toConsole("1) print to console");
			out.toConsole("2) have a voice read off the entire test");
			int c = menus.inputI();
			if(c == 1){
				this.displayWithCorrectAnswers();
			}
			if(c == 2){
				this.playVoiceWithCorrectAnswers();
			
		}
	}
	public void displayWithCorrectAnswers(){
		try{
		int n = super.getQuestions().size();
		for (int i = 0; i < n;i++){
			out.toConsole(i+1 + ". " + super.getQuestions().get(i).getQuestion());
			out.toConsole("Correct Answer: " + this.getCorrectAnswer(i).display()+"\n");
		}
		}
		catch(NullPointerException npe){
			out.toConsole("\nBlank Survey");
		}
	}
	public void playVoiceWithCorrectAnswers(){
		try{
		int n = super.getQuestions().size();
		for (int i = 0; i < n;i++){
			out.toVoice(i+1 + ". " + super.getQuestions().get(i).getQuestion());
			out.toVoice("Correct Answer: " + this.getCorrectAnswer(i).display()+"\n");
		}
		}
		catch(NullPointerException npe){
			out.toVoice("\nBlank Survey");
		}
	}
	// have to change the modify from questions to add the display of the correct answer
	public void modify(){
		try{
		if (super.getQuestions().size() == 0){
			return;
		}
		System.out.println("Enter the number of the question you wish to modify");
		super.setQuestionNumber(menus.inputI()-1);
		System.out.printf(super.getQuestionNumber() + 1 + ". " + super.getQuestions().get(super.getQuestionNumber()).getQuestion());
		System.out.printf("Correct Answer: " + this.getCorrectAnswer(super.getQuestionNumber()).display()+"\n\n");
		super.getQuestion(super.getQuestionNumber()).modifyQuestion();
		}
		catch(NullPointerException npe){
			System.out.println("\nBlank Survey\n");
		}
		int n = super.getQuestionNumber();
		try{
		if (correctAnswers.size() == 0){
			return;
		}
		System.out.println("Do you wish to modify the correctAnswer?");
		System.out.println("1) Yes");
		System.out.println("2) No");
		int c = menus.inputI();
		if(c == 1){
			System.out.println("Correct Answer: " + this.getCorrectAnswer(n).display());
			correctAnswers.get(super.getQuestionNumber()).setResponse(this.modifyCorrectAnswer());			
		}
		}
		catch(NullPointerException npe){
		}
	}
	//I'm not sure how to check question type from response but that would be a more efficient way to do things if possible
	public Vector<String> modifyCorrectAnswer(){
		int n;
		Vector<String> vs = new Vector<String>();
		if(super.getQuestion(super.getQuestionNumber()) instanceof TorF){
			System.out.println("Enter your choice, 1 for True or 2 for False:");
			n = menus.inputI();
			if(n != 1 && n != 2){
				System.out.println("Please type 1 for True or 2 for False");
				this.modifyCorrectAnswer();
			}
			vs.addElement(Integer.toString(n));
			return vs;
		}
		else if(super.getQuestion(super.getQuestionNumber()) instanceof MultipleChoice){
			System.out.println("Enter your choice(s):");
			String s = menus.inputS().toLowerCase();
			vs = new Vector<String>(Arrays.asList(s.split(" "))); // different from matching and rank, may need to handle input with this later
			return vs;
		}
		else if(super.getQuestion(super.getQuestionNumber()) instanceof ShortAnswer){
			System.out.println("Enter your answer");
			String s = menus.inputS().toLowerCase();
			vs.addElement(s);
			return vs;
		}
		else if(super.getQuestion(super.getQuestionNumber()) instanceof Essay){
			String s = "Essay question";
			vs.addElement(s);
			return vs;
		}
		else if(super.getQuestion(super.getQuestionNumber()) instanceof Rank){
			System.out.println("Enter correct order of choices");
			String s = menus.inputS().toLowerCase();
			vs = new Vector<String>(Arrays.asList(s.split(" ")));
			return vs;
		}
		else if(super.getQuestion(super.getQuestionNumber()) instanceof Matching){
			System.out.println("Enter correct order of choices");
			String s = menus.inputS().toLowerCase();
			vs = new Vector<String>(Arrays.asList(s.split(" ")));
			return vs;
		}
		return vs;
	}
	public double grade(){
		try{
		int n = super.getQuestions().size();
		int numberOfQuestions = n; // subtract for each essay question
		score = 0;
		for (int i = 0; i < n;i++){
			Response user = super.getQuestion(i).getResponse();
			Response ca = correctAnswers.get(i);
			//check equals then subtract points from total number of question percentage
			if (super.getQuestion(i) instanceof Essay && !(super.getQuestion(i) instanceof ShortAnswer)){
				numberOfQuestions = numberOfQuestions - 1;
			}
			else if (user.equals(ca)){
				score = score + 1;
			}
			
		}
		if (numberOfQuestions == 0){
			return -2;
		}
		return (score / numberOfQuestions) * 100;
		}
		catch(NullPointerException npe){
			System.out.println("\nBlank Survey\n");
			return -1;
		}
		
		
	}
	public void setCorrectAnswer(int n){
		
	}
	public Response getCorrectAnswer(int n){
		return correctAnswers.get(n);
	}
	
}
