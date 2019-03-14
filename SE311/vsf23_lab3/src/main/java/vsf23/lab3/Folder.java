package vsf23.lab3;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Folder extends FSComponent {
	
	List<FSComponent> components = new ArrayList<FSComponent>();

	public Folder(String _name, Date _dateCreated) {
		super(_name, _dateCreated);
	}
	
	public void addComponent(FSComponent fsc) {
		fsc.updateNameWith(name + '/');
		components.add(fsc);
	}
	
	public List<FSComponent> getComponents() {
		return components;
	}

	public void accept(FSVisitor visitor) {
		visitor.Visit(this);
	}
	
}
