package nxt.elte.amoba;

import nxt.elte.amoba.exception.FatalException;

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
		if((row < 1 || row > 3) && (column < 1 || column > 3)) throw new FatalException("Invalid row and column numbers!");
		else if (row < 1 || row > 3) throw new FatalException("Invalid row number!");
		else if (column < 1 || column > 3) throw new FatalException("Invalid column number!");
		
		int stepNo = ((row-1) * 3) + column-1;
		PlayerEnum player = steps[stepNo].getPlayer();
		return player;
	}
	
	public Step stepFromList(int row, int column) {
		int stepNo = ((row - 1)  * 3) + (column - 1);
		Step step = steps[stepNo];
		return step;
	}
	
	public void addStep(Step step) {
		int stepNo = ((step.getRow() - 1) * 3) + (step.getColumn()-1);
		steps[stepNo].setPlayer(step.getPlayer()); 
	}
}
