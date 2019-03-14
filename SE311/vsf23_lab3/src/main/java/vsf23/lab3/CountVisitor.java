package vsf23.lab3;

public class CountVisitor implements FSVisitor {
	
	private int totalBytes = 0;
	private int totalFiles = 0;

	public void Visit(File file) {
		totalBytes += file.getBytes();
		totalFiles++;
	}

	public void Visit(Folder folder) {
		for (FSComponent fsc : folder.getComponents()) {
			fsc.accept(this);
		}
		totalFiles++;
	}
	
	public int getTotalBytes() {
		return totalBytes;
	}

	public int getTotalFiles() {
		return totalFiles;
	}
}
