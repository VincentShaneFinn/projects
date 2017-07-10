import java.util.*;
import java.io.*;

public class Tabulate {
	private Vector<Survey> surveys;
	
	public Tabulate(){
		
	}
	public Tabulate(Vector<Survey> vs){
		surveys = vs;
	}
	public void setSurveys(Vector<Survey> vs){
		surveys = vs;
	}
	public Vector<Survey> getSurveys(){
		return surveys;
	}
	public void doTabulation(){
		Vector<Question> questions = new Vector<Question>();
		Survey baseSurvey = surveys.get(0);
		questions = baseSurvey.getQuestions();
		for (int i = 0; i < questions.size(); i++){
			Boolean TorFTest = false;
			Vector<Response> replies = new Vector<Response>();
			for (int j = 0; j < surveys.size(); j++){
				replies.addElement(surveys.get(j).getQuestion(i).getResponse());
			}
			System.out.printf(i+1 + ") " + baseSurvey.getQuestion(i).getQuestion());
			System.out.println("Replies:");
			if (baseSurvey.getQuestion(i) instanceof TorF){
				for( int t = 0; t < replies.size(); t++){
					if(replies.get(t).display().contains("1"))
						System.out.println("True");
					else
						System.out.println("False");
					TorFTest = true;
				}
			}
			else {
				for (int r = 0; r < replies.size(); r++){
					System.out.println(replies.get(r).display());
				}	
			}
			System.out.println("\nTabulation:");
			this.tabulateVector(replies, TorFTest);
			
		}
	}
	public void tabulateVector(Vector<Response> vr, Boolean TorFTest){
		HashMap Tabulation = new HashMap();
		Vector<String> test = new Vector<String>();
		for (int i = 0; i < vr.size(); i++){
			Tabulation.put(vr.get(i).display(), 0);			
		}
		Set set = Tabulation.entrySet();
		Iterator i = set.iterator();
		while(i.hasNext()){
			Map.Entry me = (Map.Entry)i.next();
			int count = 0;
			for (int j = 0; j < vr.size(); j++){
				if (vr.get(j).display().equals(me.getKey())){
					count = count + 1;
					
				}
				
			}
			if(TorFTest){
				if(me.getKey().toString().contains("1")){
					System.out.println(count + ")");
					System.out.println("True\n");
				}
				else{
					System.out.println(count + ")");
					System.out.println("False\n");
				}
					
			}
			else{
				System.out.println(count + ")");
				System.out.println(me.getKey() + "\n");
			}
		}
	}
}
