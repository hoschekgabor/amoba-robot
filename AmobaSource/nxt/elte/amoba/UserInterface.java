package nxt.elte.amoba;

import nxt.elte.amoba.exception.FatalException;
import nxt.elte.amoba.exception.PlayerSetupException;
import lejos.nxt.Button;
import lejos.util.Delay;

public class UserInterface {
	static int numberOfMatches;
	static int robotWins;
	static int humanWins;
	static Match match;
	static PlayerEnum nextPlayer;
	static Robot robot = Robot.getInstance();
	
	public static void main(String[] args) {
		// Ki milyen szinnel van?
		robot.setHumanColor(Color.GREEN);
		robot.setRobotColor(Color.RED);
		
		try {
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
				} while (match.getBoard().isEndOfMatch().equals(PlayerEnum.UNDONE));
					
				endOfMatch();	
			}
			endOfGame();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			Button.waitForAnyPress();
		}
	}
	
	public static void startGame() {
		// Partik számának lekérdezése
		String numMatch[] = {"1", "3", "5", "10"};
		switch (robot.printMenu(numMatch, 1, "Hány partit szeretne játszani?")) {
		case 0:
			// 1
			numberOfMatches = 1;
			break;

		case 1:
			// 1
			numberOfMatches = 3;
			break;
			
		case 2:
			// 1
			numberOfMatches = 5;
			break;
			
		case 3:
			// 1
			numberOfMatches = 10;
			break;
			
		default:
			// Ide nem jöhetünk
			throw new FatalException("UserInterface.startGame() exception - Number of parties.");
		}
		
		// Kezdő játékos lekérdezése
		String playerDecide[] = {"Játékos", "Robot"};
		
		switch (robot.printMenu(playerDecide, 1, "Ki kezdje a játékot?")) {
		case 0:
			nextPlayer = PlayerEnum.HUMAN;;
			break;
		case 1:
			nextPlayer = PlayerEnum.ROBOT;
			break;
		default:
			// ide nem jöhetünk
			throw new FatalException("UserInterface.startGame() exception - Beginner player.");
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
		if (match.getBoard().isEndOfMatch().equals(PlayerEnum.HUMAN)) {
			humanWins++;
			robot.printMessage("Gatulálok, megnyerte a partit!");
		}
		if (match.getBoard().isEndOfMatch().equals(PlayerEnum.ROBOT)) {
			robotWins++;
			robot.printMessage("Éljen, megnyertem a partit!");
		}
		robot.printMessage("Kérem szedje le a táblát. Majd nyomjon gombot",true);
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
