package nxt.elte.amoba;


public class Match {
	Board board;
	
	public PlayerEnum isEndOfMatch() {
		ListOfSteps listOfSteps = board.getSteps();
		
		for (int i = 0; i < 3; i++) {
			// Horizontális ellenõrzés
			if ((listOfSteps.getStep(i, 1).equals(listOfSteps.getStep(i, 2))) &&
			    (listOfSteps.getStep(i, 2).equals(listOfSteps.getStep(i, 3)))) {
				return listOfSteps.getStep(i, 1);
			}
			// Vertikális ellenõrzés
			if ((listOfSteps.getStep(1, i).equals(listOfSteps.getStep(2, i))) &&
				    (listOfSteps.getStep(2, i).equals(listOfSteps.getStep(3, i)))) {
				return listOfSteps.getStep(1, i);
			}
		}
		// Diagonális ellenõrzés 1.
		if ((listOfSteps.getStep(1, 1).equals(listOfSteps.getStep(2, 2))) && 
			(listOfSteps.getStep(2, 2).equals(listOfSteps.getStep(3, 3)))) {
			return listOfSteps.getStep(1, 1);
		}
		// Diagonális ellenõrzés 2.
		if ((listOfSteps.getStep(1, 3).equals(listOfSteps.getStep(2, 2))) && 
			(listOfSteps.getStep(2, 2).equals(listOfSteps.getStep(3, 1)))) {
			return listOfSteps.getStep(1, 3);
		}
		// Döntettlen ellenõrzése
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (listOfSteps.getStep(i, j) == null) {
					return PlayerEnum.UNDONE;
				}
			}
		}
		// Ha nincs senkinek sem három egy vonalban, és egyik mezõ sem null, akkor döntetlen
		return PlayerEnum.DRAW;
	}

	public Board getBoard() {
		return board;
	}
	
	public boolean setUserStep(ListOfSteps userSteps) {
		int difference = 0;
		boolean result;
		ListOfSteps oldSteps = board.getSteps();
		userSteps.goToBegin();
		oldSteps.goToBegin();
		
		for (int i = 0; i < 9; i++) {
			if (oldSteps.getNextStep().equals(userSteps.getNextStep())) {
				difference++;
			}
		}
		
		result = ((difference % 2) != 1);
		
		if (result) {
			board.setListOfSteps(userSteps);
		}
		return result;
	}

}
