package vsf23.lab3;

import java.util.Date;

public class Main 
{
    public static void main( String[] args )
    {
    	
    	//Mark: Setup
    	
		Folder root = new Folder("Root", new Date(0,0,1));
    	
    	Folder documents = new Folder("Documents", new Date(10,0,1));
    	Folder desktop = new Folder("Desktop", new Date(20,0,1));
    	Folder photos = new Folder("Photos", new Date(30,0,1));
    	root.addComponent(documents);
    	root.addComponent(desktop);
    	root.addComponent(photos);
    	
    	Folder wordDocuments = new Folder("WordDocuments", new Date(10,0,1));
    	documents.addComponent(wordDocuments);
    	
    	File word1 = new File("word1.txt", new Date(10,1,1), 4);
    	File word2 = new File("word2.txt", new Date(10,2,1), 8);
    	wordDocuments.addComponent(word1);
    	wordDocuments.addComponent(word2);
    
    	Folder homeworks = new Folder("Homeworks", new Date(10,0,2));
    	documents.addComponent(homeworks);
    	
    	File recycle = new File("Recycle Bin.exe", new Date(0,1,1), 1000);
    	File ie = new File("Internet Explorer.exe", new Date(50,5,5), 30);
    	File eclipse = new File("Eclipse.exe", new Date(50,4,3), 200);
    	File steam = new File("Steam.exe", new Date(50,7,2), 100);
    	File chrome = new File("Chrome.exe", new Date(50,3,22), 40);
    	desktop.addComponent(recycle);
    	desktop.addComponent(ie);
    	desktop.addComponent(eclipse);
    	desktop.addComponent(steam);
    	desktop.addComponent(chrome);
    	
    	File photo1 = new File("photo1.png", new Date(10,0,3), 70);
    	File photo2 = new File("photo2.png", new Date(10,0,3), 100);
    	File photo3 = new File("photo3.png", new Date(10,0,3), 60);
    	photos.addComponent(photo1);
    	photos.addComponent(photo2);
    	photos.addComponent(photo3);
    	
    	//Mark: Printing Examples
    	
    	System.out.println("Printing All Files");
    	System.out.println("");
    	
    	root.accept(new PrintingVisitor());
    	
    	//Mark: Byte Count Examples
    	
    	System.out.println("");
    	System.out.println("Printing File Count, Inclused all Folders and Files");
    	System.out.println("");
    	
    	CountVisitor visitor = new CountVisitor();
    	root.accept(visitor);
    	System.out.println(visitor.getTotalFiles());
    	
    	System.out.println("");
    	System.out.println("Printing Total Bytes");
    	System.out.println("");
    	
    	System.out.println(visitor.getTotalBytes());
    	
    	//Mark: get Earliest Example
    	
    	System.out.println("");
    	System.out.println("Sorted By Earliest");
    	System.out.println("");
    	
    	SortVisitor sort = new SortVisitor();
    	root.accept(sort);
    	sort.printList();
    	
    
    }
}
