package nxt.elte.amoba;

public class ListOfSteps {
	Step[] steps = {new Step(1, 1, PlayerEnum.EMPTY),
					new Step(1, 2, PlayerEnum.EMPTY),
					new Step(1, 3, PlayerEnum.EMPTY),
					new Step(2, 1, PlayerEnum.EMPTY),
					new Step(2, 2, PlayerEnum.EMPTY),
					new Step(2, 3, PlayerEnum.EMPTY),
					new Step(3, 1, PlayerEnum.EMPTY),
					new Step(3, 2, PlayerEnum.EMPTY),
					new Step(3, 3, PlayerEnum.EMPTY),};
	
	public PlayerEnum getStep(int row, int column) {
		int stepNo = (row * 3) + column;
		PlayerEnum player = steps[stepNo].getPlayer();
		return player;
	}
	
	public void addStep(Step step) {
		int stepNo = ((step.getRow() - 1) * 3) + (step.getColumn()-1);
		steps[stepNo].setPlayer(step.getPlayer()); 
	}
}
