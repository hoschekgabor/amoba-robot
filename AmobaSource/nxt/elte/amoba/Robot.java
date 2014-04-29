package nxt.elte.amoba;

public class Robot {
	private static Robot instance = null;
	private Robot() {}
	
	public static Robot getInstance() {
		if(instance == null) {
			instance = new Robot();
		}
		return instance;
	}
	
	public void boardInitialPosition() {}
	
	public void printMenu() {}
	
	public void printMessage() {}
	
	public ListOfSteps getUserSteps() {
		ListOfSteps listOfSteps = null;
		return listOfSteps;
	}
	
	public void setStep() {}

}
