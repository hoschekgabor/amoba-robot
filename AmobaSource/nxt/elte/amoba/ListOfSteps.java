package nxt.elte.amoba;

public class ListOfSteps {
	Step[] steps = new Step[9];
	
	public void goToBegin() {
		//TODO itt �gy kellene be�llni a lista elej�re,
		//hogy a getNextStep els� h�v�skor az els� elemet adja vissza!!!!
	}
	
	public PlayerEnum getStep(int row, int column) {
		PlayerEnum player = null;
		return player;
	}
	
	public Step getNextStep() {
		Step nextStep = null;
		return nextStep;
	}
	
	public void addStep(Step step) {}
}
