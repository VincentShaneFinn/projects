package vsf23.lab3;

public interface FSVisitor {

	void Visit(File file);
	void Visit(Folder folder);
	
}
