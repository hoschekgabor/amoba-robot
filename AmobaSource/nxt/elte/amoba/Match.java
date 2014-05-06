package nxt.elte.amoba;

public class Match {
	private static Board board = new Board() ;

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