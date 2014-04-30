package nxt.elte.amoba;

public class GameIntelligence {
	private static GameIntelligence intelligence;
	private GameIntelligence() {}
	
	public static GameIntelligence getInstance() {
		if (null == intelligence) {
			intelligence = new GameIntelligence();
		}
		return intelligence;
	}
	
	protected static Step getRobotStep(Board board) {
		ListOfSteps listOfSteps = board.getSteps();
		Step step = null;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (listOfSteps.getStep(i, j).equals(PlayerEnum.EMPTY)) {
					return step = new Step(i+1, j+1, PlayerEnum.ROBOT);
				}
			}
		}
		return step;
	}

}
