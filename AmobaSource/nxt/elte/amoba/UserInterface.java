package nxt.elte.amoba;

import lejos.util.Delay;

public class UserInterface {
	static int numberOfMatches;
	static int robotWins;
	static Match match;
	static PlayerEnum nextPlayer;
	
	public static void main(String[] args) {
		// Jatek kezdese
		startGame();
		
		for(int i=0; i<numberOfMatches; i++){
			// Parti kezdese
			startMatch();
			
			while (!match.isEndOfMatch()) {
				if (nextPlayer.equals(PlayerEnum.HUMAN)) {
					// Jatekos lep
					userStep();
				} else {
					// Robot lep
					robotStep();
				}
			}
			endOfMatch();	
		}
		endOfGame();
	}
	
	public static void startGame() {
		// Partik szamanak lekerdezese
		String numMatch[] = {"1", "3", "5", "10"};
		numberOfMatches = Robot.printMenu(numMatch, 1, "H�ny partit szeretne j�tszani?");
		
		// Kezdo jatekos lekrdezese
		String playerDecide[] = {"J�t�kos", "Robot"};
		
		switch (Robot.printMenu(playerDecide, 1, "Ki kezdje a j�t�kot?")) {
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
		Robot.printMessage("Kezd�dik a parti!");
		Delay.msDelay(3000);
	}
	public static void userStep() {
		do {
			Robot.printMessage("K�rem l�pjen");
			//TODO v�rni kell, hogy l�pett-e... Robot met�dus
		} while (!match.setUserStep(Robot.getUserSteps()));
	}
	
	public static void robotStep() {}
	
	public static void endOfMatch() {}
	
	public static void endOfGame() {}
}
