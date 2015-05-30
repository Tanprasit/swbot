import java.util.Scanner;

import org.sikuli.script.FindFailed;

import com.game.swsa.Game;

public class Main {

	public static void main(String[] args) throws FindFailed {
		System.out.println("Starting summoners war bot.");
		Game g = new Game();
		System.out.println("Loaded game states.");
		
		Scanner scanner = new Scanner(System.in);
		String mode;
		String level;
		int noOfRuns = 0;
		
		System.out.println("What mode would you like to run?");
		mode = scanner.next();
		int n;
		
		switch (mode) {
			case "hoh": 
				System.out.println("What level would you like to run?");
				level = scanner.next();
				g.startHohMode(level);
				break;
			case "farm":
				System.out.println("What level would you like to max? @3600exp");
				n = scanner.nextInt();
				switch(n) {
					case 2:
						noOfRuns = 10;
						break;
					case 3:
						noOfRuns = 23;
						break;
					case 4: 
						noOfRuns = 53;
						break;
					default:
						noOfRuns = n;
						break;
				}
				g.setMaxCount(noOfRuns);
				g.startFarmMode();
				break;
			case "click":
				System.out.println("How many clicks would you like to make?");
				n = scanner.nextInt();
				g.startClick(n);
				break;
			default:
				System.out.println("Unknown mode please try again");
				main(args);
				break;
		}
		
		scanner.close();
	}
}
