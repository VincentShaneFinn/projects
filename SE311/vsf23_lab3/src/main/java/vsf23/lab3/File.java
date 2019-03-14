package vsf23.lab3;

import java.util.Date;

public class File extends FSComponent {

	int bytes = 0;
	
	public File(String _name,  Date _dateCreated) {
		super(_name, _dateCreated);
	}
	
	public File(String _name,  Date _dateCreated, int _bytes) {
		super(_name, _dateCreated);
		bytes = _bytes;
	}
	
	public int getBytes() {
		return bytes;
	}

	public void accept(FSVisitor visitor) {
		visitor.Visit(this);
	}
	
}
