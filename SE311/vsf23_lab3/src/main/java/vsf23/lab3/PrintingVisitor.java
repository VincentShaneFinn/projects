package vsf23.lab3;

public class PrintingVisitor implements FSVisitor {

	public void Visit(File file) {
		System.out.println(file.getName());
	}

	public void Visit(Folder folder) {
		for (FSComponent fsc : folder.getComponents()) {
			fsc.accept(this);
		}
		
		System.out.println(folder.getName());
	}
		
}
