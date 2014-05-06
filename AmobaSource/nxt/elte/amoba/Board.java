package nxt.elte.amoba;

public class Board{
	private ListOfSteps listOfSteps = new ListOfSteps();
	
	public void setStep(Step step) {
		listOfSteps.addStep(step);
	}

	public ListOfSteps getSteps() {
		return listOfSteps;
	}

	public void setListOfSteps(ListOfSteps listOfSteps) {
		this.listOfSteps = listOfSteps;
	}

	public int compare(ListOfSteps userSteps) {
		int difference = 0;
		for (int i = 1; i < 4; i++) {
			for (int j = 1; j < 4; j++) {
				if (!listOfSteps.getStep(i, j).equals(userSteps.getStep(i, j))) {
					difference++;
				}
			}
		}
		return difference;
	}
	public void removeStep(int row, int column){
		listOfSteps.stepFromList(row, column).setPlayer(PlayerEnum.EMPTY);
	}
	
	public PlayerEnum isEndOfMatch() {
		
		for (int i = 1; i < 4; i++) {
			// Horizontális ellenőrzés
			if ((!listOfSteps.getStep(i, 1).equals(PlayerEnum.EMPTY)) &&
				(listOfSteps.getStep(i, 1).equals(listOfSteps.getStep(i, 2))) &&
			    (listOfSteps.getStep(i, 2).equals(listOfSteps.getStep(i, 3)))) {
				return listOfSteps.getStep(i, 1);
			}
			// Vertikális ellenőrzés
			if ((!listOfSteps.getStep(1, i).equals(PlayerEnum.EMPTY)) &&
				(listOfSteps.getStep(1, i).equals(listOfSteps.getStep(2, i))) &&
				(listOfSteps.getStep(2, i).equals(listOfSteps.getStep(3, i)))) {
				return listOfSteps.getStep(1, i);
			}
		}
		// Diagonális ellenőrzés 1.
		if ((!listOfSteps.getStep(1, 1).equals(PlayerEnum.EMPTY)) &&
			(listOfSteps.getStep(1, 1).equals(listOfSteps.getStep(2, 2))) && 
			(listOfSteps.getStep(2, 2).equals(listOfSteps.getStep(3, 3)))) {
			return listOfSteps.getStep(1, 1);
		}
		// Diagonális ellenőrzés 2.
		if ((!listOfSteps.getStep(1, 3).equals(PlayerEnum.EMPTY)) &&
			(listOfSteps.getStep(1, 3).equals(listOfSteps.getStep(2, 2))) && 
			(listOfSteps.getStep(2, 2).equals(listOfSteps.getStep(3, 1)))) {
			return listOfSteps.getStep(1, 3);
		}
		// Döntetlen ellenőrzése
		for (int i = 1; i < 4; i++) {
			for (int j = 1; j < 4; j++) {
				if (listOfSteps.getStep(i, j) == PlayerEnum.EMPTY) {
					return PlayerEnum.UNDONE;
				}
			}
		}
		// Ha nincs senkinek sem három egy vonalban, és egyik mező sem EMPTY, akkor döntetlen
		return PlayerEnum.DRAW;
	}
	public void printOut() {
		for (int i = 1; i < 4; i++) {
			for (int j = 1; j < 4; j++) {
				System.out.print(listOfSteps.getStep(i, j) + ", ");
			}
			System.out.println();
		}
	}
}