package PipeAndFilter;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class Generator extends Filter {

	public Generator(Pipe input_, Pipe output_) {
		super(input_, output_);
		// TODO Auto-generated constructor stub
	}

	public void transform() {
		try {
		    BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream((String) input_.get())));         

		    String line;
		    while ((line = br.readLine()) != null) {
		        output_.put(line);
		    }
		    br.close();

		} catch (IOException e) {
		    e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
