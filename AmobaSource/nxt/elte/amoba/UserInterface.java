package nxt.elte.amoba;

import lejos.util.Delay;

public class UserInterface {
	static int numberOfMatches;
	static int robotWins;
	static int humanWins;
	static Match match;
	static PlayerEnum nextPlayer;
	
	public static void main(String[] args) {
		// Játek kezdése
		startGame();
		
		for(int i=0; i<numberOfMatches; i++){
			// Parti kezdése
			startMatch();
			
			// Lépések
			do {
				if (nextPlayer.equals(PlayerEnum.HUMAN)) {
					// Játekos lép
					userStep();
				} else {
					// Robot lép
					robotStep();
				}
			} while (match.isEndOfMatch().equals(PlayerEnum.UNDONE));
				
			endOfMatch();	
		}
		endOfGame();
	}
	
	public static void startGame() {
		// Partik számának lekérdezése
		String numMatch[] = {"1", "3", "5", "10"};
		numberOfMatches = Robot.printMenu(numMatch, 1, "Hány partit szeretne játszani?");
		
		// Kezdõ játékos lekérdezése
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
		// Uj parti létrehozasa, kiírása, 3mp várás
		match = new Match();
		Robot.printMessage("Kezdõdik a parti!");
		Delay.msDelay(3000);
	}
	public static void userStep() {
		Robot.printMessage("Kérem lépjen");
		match.setUserStep(Robot.getUserSteps());
		nextPlayer = PlayerEnum.ROBOT;
	}
	
	public static void robotStep() {
		Board board = match.getBoard();
		Step step = GameIntelligence.getRobotStep(board);
		Robot.setStep(step);
		board.setStep(step);
		nextPlayer = PlayerEnum.HUMAN;
	}
	
	public static void endOfMatch() {
		if (match.isEndOfMatch().equals(PlayerEnum.HUMAN)) {
			humanWins++;
			Robot.printMessage("Gatulálok, megnyerte a partit!");
		}
		if (match.isEndOfMatch().equals(PlayerEnum.ROBOT)) {
			robotWins++;
			Robot.printMessage("Éljen, megnyertem a partit!");
		}
		Delay.msDelay(3000);
	}
	
	public static void endOfGame() {
		if (humanWins > robotWins) {
			Robot.printMessage("Gartuláluk, Ön nyert!");
		} else if (humanWins < robotWins) {
			Robot.printMessage("Haha, én nyertem!!");
		} else {
			Robot.printMessage("Döntetlen!");
		}
		Delay.msDelay(5000);
	}
}
