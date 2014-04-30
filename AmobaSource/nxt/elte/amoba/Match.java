package nxt.elte.amoba;

public class Match {
	private static Board board = new Board() ;
	
	public PlayerEnum isEndOfMatch() {
		ListOfSteps listOfSteps = board.getSteps();
		
		for (int i = 0; i < 3; i++) {
			// Horizontális ellenõrzés
			if ((!listOfSteps.getStep(i, 0).equals(PlayerEnum.EMPTY)) &&
				(listOfSteps.getStep(i, 0).equals(listOfSteps.getStep(i, 1))) &&
			    (listOfSteps.getStep(i, 1).equals(listOfSteps.getStep(i, 2)))) {
				return listOfSteps.getStep(i, 0);
			}
			// Vertikális ellenõrzés
			if ((!listOfSteps.getStep(0, i).equals(PlayerEnum.EMPTY)) &&
				(listOfSteps.getStep(0, i).equals(listOfSteps.getStep(1, i))) &&
				(listOfSteps.getStep(1, i).equals(listOfSteps.getStep(2, i)))) {
				return listOfSteps.getStep(0, i);
			}
		}
		// Diagonális ellenõrzés 1.
		if ((!listOfSteps.getStep(0, 0).equals(PlayerEnum.EMPTY)) &&
			(listOfSteps.getStep(0, 0).equals(listOfSteps.getStep(1, 1))) && 
			(listOfSteps.getStep(1, 1).equals(listOfSteps.getStep(2, 2)))) {
			return listOfSteps.getStep(0, 0);
		}
		// Diagonális ellenõrzés 2.
		if ((!listOfSteps.getStep(0, 2).equals(PlayerEnum.EMPTY)) &&
			(listOfSteps.getStep(0, 2).equals(listOfSteps.getStep(1, 1))) && 
			(listOfSteps.getStep(1, 1).equals(listOfSteps.getStep(2, 0)))) {
			return listOfSteps.getStep(0, 2);
		}
		// Döntetlen ellenõrzése
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (listOfSteps.getStep(i, j) == PlayerEnum.EMPTY) {
					return PlayerEnum.UNDONE;
				}
			}
		}
		// Ha nincs senkinek sem három egy vonalban, és egyik mezõ sem EMPTY, akkor döntetlen
		return PlayerEnum.DRAW;
	}

	public Board getBoard() {
		return board;
	}
	
	public boolean setUserStep(ListOfSteps userSteps) {
		boolean result;
		result = board.compare(userSteps) == 1;
		
		if (result) {
			board.setListOfSteps(userSteps);
		}
		return result;
	}

}