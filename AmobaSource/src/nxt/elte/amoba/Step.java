package nxt.elte.amoba;

public class Step {
	int row;
	int column;
	PlayerEnum player;
	
	public Step(int row, int column, PlayerEnum player) {
		this.row = row;
		this.column = column;
		this.player = player;
	}
	
	public int comparePosition(Step step) {
		return 0;
	}
	

}
