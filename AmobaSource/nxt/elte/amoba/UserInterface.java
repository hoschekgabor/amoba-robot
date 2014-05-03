package nxt.elte.amoba;

import nxt.elte.amoba.exception.FatalException;
import nxt.elte.amoba.exception.PlayerSetupException;
import lejos.util.Delay;

public class UserInterface {
	static int numberOfMatches;
	static int robotWins;
	static int humanWins;
	static Match match;
	static PlayerEnum nextPlayer;
	static Robot robot = Robot.getInstance();
	
	public static void main(String[] args) {
		// Játek kezdése
		startGame();
		
		for(int i=0; i<numberOfMatches; i++){
			// Parti kezdése
			startMatch();
			
			// L�p�sek
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
		numberOfMatches = robot.printMenu(numMatch, 1, "Hány partit szeretne játszani?");
		
		// Kezdő játékos lekérdezése
		String playerDecide[] = {"Játékos", "Robot"};
		
		switch (robot.printMenu(playerDecide, 1, "Ki kezdje a j�t�kot?")) {
		case 2:
			nextPlayer = PlayerEnum.ROBOT;
			break;
		default:
			nextPlayer = PlayerEnum.HUMAN;
			break;
		}
		
	}
	
	public static void startMatch() {
		// Új parti létrehozasa, kiírása, 3mp várás
		match = new Match();
		robot.printMessage("Kezdődik a parti!");
		Delay.msDelay(3000);
	}
	public static void userStep() {
		robot.printMessage("Kérem lépjen");
		try {
			match.setUserStep(robot.getUserSteps());
		} catch (FatalException e) {
			// TODO Exception kezelést megcsinálni
			e.printStackTrace();
		} catch (PlayerSetupException e) {
			// TODO Exception kezelést megcsinálni
			e.printStackTrace();
		}
		nextPlayer = PlayerEnum.ROBOT;
	}
	
	public static void robotStep() {
		//TODO Exception kezelést megcsinálni
		Board board = match.getBoard();
		Step step = GameIntelligence.getRobotStep(board);
		try {
			robot.setStep(step);
		} catch (PlayerSetupException e) {
			//TODO Exception kezelést megcsinálni
			e.printStackTrace();
		}
		board.setStep(step);
		nextPlayer = PlayerEnum.HUMAN;
	}
	
	public static void endOfMatch() {
		if (match.isEndOfMatch().equals(PlayerEnum.HUMAN)) {
			humanWins++;
			robot.printMessage("Gatulálok, megnyerte a partit!");
		}
		if (match.isEndOfMatch().equals(PlayerEnum.ROBOT)) {
			robotWins++;
			robot.printMessage("Éljen, megnyertem a partit!");
		}
		Delay.msDelay(3000);
	}
	
	public static void endOfGame() {
		if (humanWins > robotWins) {
			robot.printMessage("Gartuláluk, Ön nyert!");
		} else if (humanWins < robotWins) {
			robot.printMessage("Haha, Én nyertem!!");
		} else {
			robot.printMessage("Döntetlen!");
		}
		Delay.msDelay(5000);
	}
}
