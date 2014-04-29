package nxt.elte.amoba;

public class Robot {
	private static Robot robot;
	private Robot() {}
	
	public static Robot getInstance() {
		if (null == robot) {
			robot = new Robot();
		}
		return robot;
	}
	
	protected static void boardInitialPosition() {}
	
	protected static int printMenu(String[] options, int initChoice, String Question) {
		int choice = 1;
		return choice;
	}
	
	protected static void printMessage(String message) {}
	
	protected static ListOfSteps getUserSteps() {
		ListOfSteps listOfSteps = null;
		return listOfSteps;
	}
	
	protected static void setStep() {}

}
