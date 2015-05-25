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
				System.out.println("How many runs would you like to make?");
				n = scanner.nextInt();
				g.setMaxCount(n);
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
