package vsf23.lab3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SortVisitor implements FSVisitor {

	List<FSComponent> sortedList = new ArrayList<FSComponent>();
	
	public void Visit(File file) {
		sortedList.add(file);
	}

	public void Visit(Folder folder) {
		for (FSComponent fsc : folder.getComponents()) {
			fsc.accept(this);
		}
		
		sortedList.add(folder);
		
        // Sort in assending order
        Collections.sort(sortedList, new Comparator<FSComponent>() {
            public int compare(FSComponent p1, FSComponent p2) {
                return Long.valueOf(p1.getDate().getTime()).compareTo(p2.getDate().getTime());
            }
        });
        
	}
	
	public void printList() {
		for (FSComponent fsc : sortedList) {
			System.out.println(fsc.getName() + " " + fsc.getDate());
		}
	}

}
