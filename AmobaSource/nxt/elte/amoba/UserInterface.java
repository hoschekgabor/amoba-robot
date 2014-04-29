package nxt.elte.amoba;

import lejos.util.Delay;
import lejos.util.TextMenu;

public class UserInterface {
	static int numberOfMatches;
	static int robotWins;
	static Match match;
	static PlayerEnum nextPlayer;
	
	public static void startGame() {
		// Partik szamanak lekerdezese
		String numMatch[] = {"1", "3", "5", "10"};
		numberOfMatches = Robot.printMenu(numMatch, 1, "Hány partit szeretne játszani?");
		
		// Kezdo jatekos lekrdezese
		String playerDecide[] = {"Játékos", "Robot"};
		
		switch (Robot.printMenu(playerDecide, 1, "Ki kezdje a játékot?")) {
		case 2:
			nextPlayer = PlayerEnum.ROBOT;
			break;
		default:
			nextPlayer = PlayerEnum.HUMAN;
			break;
		}
		
	}
	
	public static void startMatch() {
		// Uj parti letrehozasa, kiirasa, 3mp varas
		match = new Match();
		Robot.printMessage("Kezdõdik a parti!");
		Delay.msDelay(3000);
	}
	public static void userStep() {}
	
	public static void robotStep() {}
	
	public static void endOfMatch() {}
	
	public static void endOfGame() {}

	public static void main(String[] args) {
		// Jatek kezdese
		startGame();
		
		for(int i=0; i<numberOfMatches; i++){
			// Parti kezdese
			startMatch();
			
			// Jatekosok lepnek
			while (!match.isEndOfMatch()) {
				if (nextPlayer.equals(PlayerEnum.HUMAN)) {
					Robot.printMessage("Kérem lépjen");
				} else {
					
				}
			}
			
		}
		

	}
}
