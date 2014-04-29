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
		Step calculatedStep = null;
		return calculatedStep;
	}

}
