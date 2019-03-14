import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class UpperCaseMain {
 
    public static void main(String args[]) {
    	
    	//Set up Pipe to use Filter args[0] or "TextFile.txt"
        Pipe pipeFileName = new PipeImpl();
        
        if(args.length > 0) {
        	pipeFileName.put(args[0]);
        }
        else {
        	pipeFileName.put("TextFile.txt");
        }
        
        //Setup Pipe and Filter <fileName> | Generator
        
        Pipe pipeGenerator = new PipeImpl();
 
        Generator generator = new Generator(pipeFileName,pipeGenerator);
        Thread generatorFilter = new Thread(generator);
        generatorFilter.start();
        
        //Setup Pipe and Filter Generator | UpperCaseConverter
     
        Pipe pipeUpperCaseConverter = new PipeImpl();
        
        UpperCaseConverter ucc = new UpperCaseConverter(pipeGenerator,pipeUpperCaseConverter);
        Thread uccFilter = new Thread(ucc);
        uccFilter.start();
    }
}

