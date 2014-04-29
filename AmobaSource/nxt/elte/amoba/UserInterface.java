package nxt.elte.amoba;

import lejos.util.TextMenu;

public class UserInterface {
	static int numberOfMatches;
	static int robotWins;
	static Match match;
	static PlayerEnum nextPlayer;
	
	public static void startGame() {
		// Partik szamanak lekerdezese
		String numMatch[] = {"1", "3", "5", "10"};
		TextMenu numMatchMenu = new TextMenu(numMatch, 1, "Hány partit szeretne játszani?");
		numberOfMatches = numMatchMenu.select();
		
		// Kezdo jatekos lekrdezese
		String playerDecide[] = {"Játékos", "Robot"};
		TextMenu playerDecideMenu = new TextMenu(playerDecide, 1, "Ki kezdje a játékot?");
		
		switch (playerDecideMenu.select()) {
		case 2:
			nextPlayer = PlayerEnum.ROBOT;
			break;
		default:
			nextPlayer = PlayerEnum.HUMAN;
			break;
		}
		
	}
	
	public static void startMatch() {}
	
	public static void userStep() {}
	
	public static void robotStep() {}
	
	public static void endOfMatch() {}
	
	public static void endOfGame() {}

	public static void main(String[] args) {
		startGame();
		

	}
}
