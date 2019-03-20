package Server;

import java.util.Scanner;

public class InputThread extends Thread {

	private ServerMain main;
	
    public InputThread(ServerMain main) {
		this.main = main;
	}

	public void run() {
        Scanner sc = new Scanner(System.in);
        while(sc.hasNextLine()) {
            String line = sc.nextLine();
            if (line.equals("count")){
            	main.storage.printCount();
            }
            else if (line.equals("list")) {
            	main.storage.printList();
            }
        }
    }
}
