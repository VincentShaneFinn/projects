
public class UpperCaseConverter extends Filter {

	public UpperCaseConverter(Pipe input_, Pipe output_) {
		super(input_, output_);
		// TODO Auto-generated constructor stub
	}
	
	public void transform() {
		String line;
		try {
			while((line = (String) input_.get()) != null) {
				printUpperLine(line);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void printUpperLine(String line) {
		System.out.println(line.toUpperCase());
	}
	

}
