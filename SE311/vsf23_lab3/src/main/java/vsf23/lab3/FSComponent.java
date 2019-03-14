package vsf23.lab3;

import java.util.Date;

public abstract class FSComponent {

	protected String name;
	protected Date dateCreated;
	
	public FSComponent(String _name, Date _dateCreated) {
		name = _name;
		dateCreated = _dateCreated;
	}
	
	public abstract void accept(FSVisitor visitor);
	
	public String getName() {
		return name;
	}
	
	public Date getDate() {
		return dateCreated;
	}
	
	public void updateNameWith(String path) {
		name = path + name;
	}
	
}
