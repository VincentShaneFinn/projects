package se_hw1;

public class Main {

	public static void main(String[] args) {
		MasterControl masterControl = new MasterControl();
		if(args.length > 0) {
			masterControl.programLoop(args[0]);
		}
		else {
			masterControl.programLoop();
		}
	}

}
