package nxt.elte.amoba;

import lejos.util.Delay;

public class UserInterface {
	static int numberOfMatches;
	static int robotWins;
	static int humanWins;
	static Match match;
	static PlayerEnum nextPlayer;
	static Robot robot = Robot.getInstance();
	
	public static void main(String[] args) {
		// J�tek kezd�se
		startGame();
		
		for(int i=0; i<numberOfMatches; i++){
			// Parti kezd�se
			startMatch();
			
			// L�p�sek
			do {
				if (nextPlayer.equals(PlayerEnum.HUMAN)) {
					// J�tekos l�p
					userStep();
				} else {
					// Robot l�p
					robotStep();
				}
			} while (match.isEndOfMatch().equals(PlayerEnum.UNDONE));
				
			endOfMatch();	
		}
		endOfGame();
	}
	
	public static void startGame() {
		// Partik sz�m�nak lek�rdez�se
		String numMatch[] = {"1", "3", "5", "10"};
		numberOfMatches = robot.printMenu(numMatch, 1, "H�ny partit szeretne j�tszani?");
		
		// Kezd� j�t�kos lek�rdez�se
		String playerDecide[] = {"J�t�kos", "Robot"};
		
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
		// Uj parti l�trehozasa, ki�r�sa, 3mp v�r�s
		match = new Match();
		robot.printMessage("Kezd�dik a parti!");
		Delay.msDelay(3000);
	}
	public static void userStep() {
		robot.printMessage("K�rem l�pjen");
		match.setUserStep(robot.getUserSteps());
		nextPlayer = PlayerEnum.ROBOT;
	}
	
	public static void robotStep() {
		Board board = match.getBoard();
		Step step = GameIntelligence.getRobotStep(board);
		robot.setStep(step);
		board.setStep(step);
		nextPlayer = PlayerEnum.HUMAN;
	}
	
	public static void endOfMatch() {
		if (match.isEndOfMatch().equals(PlayerEnum.HUMAN)) {
			humanWins++;
			robot.printMessage("Gatul�lok, megnyerte a partit!");
		}
		if (match.isEndOfMatch().equals(PlayerEnum.ROBOT)) {
			robotWins++;
			robot.printMessage("�ljen, megnyertem a partit!");
		}
		Delay.msDelay(3000);
	}
	
	public static void endOfGame() {
		if (humanWins > robotWins) {
			robot.printMessage("Gartul�luk, �n nyert!");
		} else if (humanWins < robotWins) {
			robot.printMessage("Haha, �n nyertem!!");
		} else {
			robot.printMessage("D�ntetlen!");
		}
		Delay.msDelay(5000);
	}
}
