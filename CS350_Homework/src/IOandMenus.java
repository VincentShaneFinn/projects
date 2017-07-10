import java.util.*;
import java.io.*;

public class IOandMenus implements Serializable{

	private Survey survey;
	private int menu1Choice;
	private int menu2Choice;
	private String loadedFile;
	
	public IOandMenus(){
		
	}
	//menu to pick survey or test
	public void displayMenu1(){
		while(0==0)
		{
			System.out.println("1) Survey");
			System.out.println("2) Test");
		    menu1Choice = this.inputI();	
			if (menu1Choice == 1){
				survey = new Survey();
				this.displayMenu2();
			}
			else if (menu1Choice == 2){
				survey = new Test();
				this.displayMenu2();
			}
			else{
			}
		}
	}
	// second menu is determined by the class, returns here to save and load serialized surveys
	public void displayMenu2(){
		while(0==0)
		{
			//if user chooses load
			menu2Choice = survey.displayMenu2();
			if (menu2Choice == 1){
				this.load();
			}
			//if user chooses save
			else if (menu2Choice == 2){
				this.save();
			}
			//chooses modify
			else if (menu2Choice == 3){
				this.load();
				survey.modify();
				this.saveLoaded();
			}
			//chooses take
			else if (menu2Choice == 4){
				this.load();
				survey.take();
				if (survey instanceof Test){
					System.out.println("\nYou have completed the Test");
				}
				else{
					System.out.println("\nYou have completed the Survey");
				}
				System.out.println("\nPlease enter your name");
				String s = this.inputS();
				System.out.println(" ");
				this.saveLoadedTaken(s);
			}
			//chooses tabulate
			else if (menu2Choice == 5){
				this.loadGetSurveyName();
			}
			//chooses grade
			else if (menu2Choice == 6){
				this.loadTaken();
				Double g = survey.grade();
				if (g.equals(new Double(-1.0)))
				{
					System.out.println("No grade Available");
				}
				else if(g.equals(new Double(-2.0))){
					System.out.println("All Essay Questions");
				}
				else{
					System.out.println(g + "\n");	
				}	
			}
			//quit
			else if(menu2Choice == 7){
				System.exit(0);
			}
		}
	}
	// survey and test saved in different folders, load() displays a numbered list of files and you type a number and it load into survey
	public void load(){
	      try {
	    	  int n;
	    	  System.out.println(" ");
	    		  File folder;
		    	  if (survey instanceof Test){
		    		  folder = new File("Tests/");
		    	  }
		    	  else{
		    		  folder = new File("Surveys/");
		    	  }
	    		  File[] listOfFiles = folder.listFiles();
	    		      for (int i = 0; i < listOfFiles.length; i++) {
	    		        if (listOfFiles[i].isFile()) {
	    		          System.out.println(i+1 + ") " + listOfFiles[i].getName());
	    		        } else if (listOfFiles[i].isDirectory()) {
	    		          
	    		        }
	    		      }
	    	  if (listOfFiles.length == 0){
	    		  System.out.println("No files in directory");
	    		  return;
	    	  }
	    	  System.out.println("Type the number for the file you want to open");
	          n = this.inputI()-1;
	          String name = "";
	          try{ // catch the error if user types a number outside indexes of the filenames
		    	  if (survey instanceof Test){
		    		  if (listOfFiles[n].isFile()){
		    			  name  = "Tests/" + listOfFiles[n].getName();
		    		  }
		    	  }
		    	  else{
		    		  if (listOfFiles[n].isFile()){
		    			  name = "Surveys/" + listOfFiles[n].getName();
		    		  }
		    	  }
	          }
	          catch (ArrayIndexOutOfBoundsException e){
	        	  
	          }
	          loadedFile = name.replace(".ser", "");// save the name of the file to be saved after modification, or taking etc.
	          FileInputStream fileIn = new FileInputStream(name);
	          ObjectInputStream in = new ObjectInputStream(fileIn);
	          survey = (Survey) in.readObject();
	          in.close();
	          fileIn.close();
	          System.out.println("\nYour file succesfully loaded");
	       }catch(IOException i) {
	          System.out.println("\nFile does not exist");
	       }catch(ClassNotFoundException c) {
	          System.out.println("\nSurvey class not found");
	          c.printStackTrace();
	          return;
	       }
	}
	//if you take a test, it is put in a separate directory with other taken tests or a directory with taken surveys
	public void loadTaken(){
	      try {
	    	  int n;
	    	  System.out.println(" ");
	    		  File folder;
		    	  if (survey instanceof Test){
		    		  folder = new File("Taken_Tests/");
		    	  }
		    	  else{
		    		  folder = new File("Taken_Surveys/");
		    	  }
	    		  File[] listOfFiles = folder.listFiles();
	    		      for (int i = 0; i < listOfFiles.length; i++) {
	    		        if (listOfFiles[i].isFile()) {
	    		          System.out.println(i+1 + ") " + listOfFiles[i].getName());
	    		        } else if (listOfFiles[i].isDirectory()) {
	    		          
	    		        }
	    		      }
	    	  if (listOfFiles.length == 0){
	    		  System.out.println("No files in directory");
	    		  return;
	    	  }
	    	  System.out.println("Type the number for the file you want to see graded");
	          n = this.inputI()-1;
	          String name = "";
	          try{ // catch the error if user types a number outside indexes of the filenames
		    	  if (survey instanceof Test){
		    		  if (listOfFiles[n].isFile()){
		    			  name  = "Taken_Tests/" + listOfFiles[n].getName();
		    		  }
		    	  }
		    	  else{
		    		  if (listOfFiles[n].isFile()){
		    			  name = "Taken_Surveys/" + listOfFiles[n].getName();
		    		  }
		    	  }
	          }
	          catch (ArrayIndexOutOfBoundsException e){
	        	  
	          }
	          FileInputStream fileIn = new FileInputStream(name);
	          ObjectInputStream in = new ObjectInputStream(fileIn);
	          survey = (Survey) in.readObject();
	          in.close();
	          fileIn.close();
	          System.out.println("\nYour file succesfully loaded");
	       }catch(IOException i) {
	          System.out.println("\nFile does not exist");
	       }catch(ClassNotFoundException c) {
	          System.out.println("\nSurvey class not found");
	          c.printStackTrace();
	          return;
	       }
	}
	//this method simply updates the variable loaded file to the name of the survey you want to tabulate, so if you chose Survey1 it saves that name to be looked up in the Taken_Surveys folder
	public void loadGetSurveyName(){
	    	  int n;
	    	  System.out.println(" ");
	    		  File folder;
		    	  if (survey instanceof Test){
		    		  folder = new File("Tests/");
		    	  }
		    	  else{
		    		  folder = new File("Surveys/");
		    	  }
	    		  File[] listOfFiles = folder.listFiles();
	    		      for (int i = 0; i < listOfFiles.length; i++) {
	    		        if (listOfFiles[i].isFile()) {
	    		          System.out.println(i+1 + ") " + listOfFiles[i].getName());
	    		        } else if (listOfFiles[i].isDirectory()) {
	    		          
	    		        }
	    		      }
	    	  if (listOfFiles.length == 0){
	    		  System.out.println("No files in directory");
	    		  return;
	    	  }
	    	  System.out.println("Type the number of the file name you wish to tabulate");
	          n = this.inputI()-1;
	          String name = "";
	          try{ // catch the error if user types a number outside indexes of the filenames
		    	  if (survey instanceof Test){
		    		  if (listOfFiles[n].isFile()){
		    			  name  = "Tests/" + listOfFiles[n].getName();
		    		  }
		    	  }
		    	  else{
		    		  if (listOfFiles[n].isFile()){
		    			  name = "Surveys/" + listOfFiles[n].getName();
		    		  }
		    	  }
	          }
	          catch (ArrayIndexOutOfBoundsException e){
	        	  System.out.println("No file for that number, Please Try again");
	        	  return;
	          }
	          loadedFile = name.replace(".ser", "");
	          this.loadTabulate();
	}
	public void loadTabulate(){
		try {
	    	  System.out.println(" ");
	    		  File folder;
		    	  if (survey instanceof Test){
		    		  folder = new File("Taken_Tests/");
		    	  }
		    	  else{
		    		  folder = new File("Taken_Surveys/");
		    	  }
	    		  File[] listOfFiles = folder.listFiles();
	    		      for (int i = 0; i < listOfFiles.length; i++) {
	    		        if (listOfFiles[i].isFile()) {
	    		        } else if (listOfFiles[i].isDirectory()) {
	    		          
	    		        }
	    		      }
	    	  if (listOfFiles.length == 0){
	    		  System.out.println("No Taken Files");
	    		  return;
	    	  }
	          String name = "";
	          Vector<Survey> surveys = new Vector<Survey>();
	          try{ // catch the error if user types a number outside indexes of the filenames
	        	  for(int n = 0; n < listOfFiles.length; n++)
	        	  {
		    	  if (survey instanceof Test){
		    		  if (listOfFiles[n].isFile()){
		    			  name  = "Taken_Tests/" + listOfFiles[n].getName();
		    		  }
		    	  }
		    	  else{
		    		  if (listOfFiles[n].isFile()){
		    			  name = "Taken_Surveys/" + listOfFiles[n].getName();
		    		  }
		    	  }
		    	  if (name.contains(loadedFile)){
			    	  FileInputStream fileIn = new FileInputStream(name);
				      ObjectInputStream in = new ObjectInputStream(fileIn);
				      survey = (Survey) in.readObject();
				      surveys.addElement(survey);
				      in.close();
				      fileIn.close();
		    	  }
	        	  }
	          }
	          catch (ArrayIndexOutOfBoundsException e){
	        	  System.out.println("No one has taken this survey");
	        	  return;
	          }
	          Tabulate t = new Tabulate(surveys);
	          t.doTabulation();
	       }catch(IOException i) {
	          System.out.println("\nFile does not exist");
	       }catch(ClassNotFoundException c) {
	          System.out.println("\nSurvey class not found");
	          c.printStackTrace();
	          return;
	       }
        catch (ArrayIndexOutOfBoundsException e){
      	  System.out.println("No one has taken this survey");
      	  return;
        }
	}
	// type the name of the file and it serializes it to a test or survey folder
	public void save(){
	      try {
	    	  System.out.println("What would you like to name your file");
	    	  String name;
	    	  name = this.inputS();
	    	  if (survey instanceof Test){
	    		  name = "Tests/" + name + ".ser";
	    	  }
	    	  else{
	    		  name = "Surveys/" + name + ".ser";
	    	  }
	    	  File f = new File(name);
	    	  if(f.exists() && !f.isDirectory()){
	    		  System.out.println("Are you sure you want to overwrite the existing file?");
	    		  System.out.println("1) Yes");
	    		  System.out.println("2) No");
	    		  int yn = this.inputI();
	    		  if(yn != 1){
	    			  return;
	    		  }  
	    	  }
	          FileOutputStream fileOut =
	          new FileOutputStream(name);
	          ObjectOutputStream out = new ObjectOutputStream(fileOut);
	          out.writeObject(survey);
	          out.close();
	          fileOut.close();
	          System.out.println("\nSerialized data is saved in " + name);
	      }
	      catch(IOException i) {
	          i.printStackTrace();
	       }
	}
	public void saveLoaded(){
	      try {
	          FileOutputStream fileOut = new FileOutputStream(loadedFile + ".ser");
	          ObjectOutputStream out = new ObjectOutputStream(fileOut);
	          out.writeObject(survey);
	          out.close();
	          fileOut.close();
	          System.out.println("\nSerialized data is saved in " + loadedFile + ".ser");
	      }
	      catch(IOException i) {
	          i.printStackTrace();
	       }
	      catch(NullPointerException npe){
	    	  
	      }
	}
	public void saveLoadedTaken(String s){
	      try {
	          FileOutputStream fileOut = new FileOutputStream("Taken_" + loadedFile + "_" + s);
	          ObjectOutputStream out = new ObjectOutputStream(fileOut);
	          out.writeObject(survey);
	          out.close();
	          fileOut.close();
	          System.out.println("\nSerialized data is saved in " + "Taken_" + loadedFile + "_" + s);
	      }
	      catch(IOException i) {
	          i.printStackTrace();
	       }
	      catch(NullPointerException npe){
	    	  
	      }
	}
	// just put this function here to read in when user has to type a number like menu 1 or 2
	public int inputI(){
	    Scanner input = new Scanner(System.in);
	    int c = 0;
	    try{
	    	c = Integer.parseInt(input.next());
	    }
	    catch (NumberFormatException nfe){
	    	
	    }
	    return c;
	}
	// function used throughout survey and test to type strings like question prompts
	public String inputS(){
	    Scanner input = new Scanner(System.in);
	    String c = input.nextLine();
	    return c;
	}
	
}
