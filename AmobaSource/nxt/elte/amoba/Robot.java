package nxt.elte.amoba;

public class Robot {

	private final static int SENSORPORT_1 = 0;
	private final static int SENSORPORT_2 = 1;
	private final static int SENSORPORT_3 = 2;
	private final static int SENSORPORT_4 = 3;
	private final static int MOTOR_A = 0;
	private final static int MOTOR_B = 1;
	private final static int MOTOR_C = 2;

	private static Robot robot;

	private Color robotColor;
	private Color humanColor;
	private final BoardController boardController = new BoardController(MOTOR_A, SENSORPORT_1);;
	private final Tower tower = new Tower(MOTOR_B, MOTOR_C);;
	// Pozíciókhoz tartozó board és tower pozíciók
	// TODO: A pozíciókat még meg kell adni.
	private final Position[][] positions = {
			{
					new Position(BoardPosition.BASE_POSITION, TowerPosition.POSITION_1),
					new Position(BoardPosition.BASE_POSITION, TowerPosition.POSITION_1),
					new Position(BoardPosition.BASE_POSITION, TowerPosition.POSITION_1) },
			{
					new Position(BoardPosition.BASE_POSITION, TowerPosition.POSITION_1),
					new Position(BoardPosition.BASE_POSITION, TowerPosition.POSITION_1),
					new Position(BoardPosition.BASE_POSITION, TowerPosition.POSITION_1) },
			{
					new Position(BoardPosition.BASE_POSITION, TowerPosition.POSITION_1),
					new Position(BoardPosition.BASE_POSITION, TowerPosition.POSITION_1),
					new Position(BoardPosition.BASE_POSITION, TowerPosition.POSITION_1) }
	};

	private Robot() {}

	public static Robot getInstance() {
		if (null == robot) {
			robot = new Robot();
		}
		return robot;
	}

	protected void boardInitialPosition() {
		boardController.moveToBasePosition();
	}

	protected int printMenu(String[] options, int initChoice, String Question) {
		int choice = 1;
		//TODO: Meg kell írni.
		return choice;
	}

	protected void printMessage(String message) {
	}

	protected ListOfSteps getUserSteps() {
		//TODO: Meg kell írni.
		ListOfSteps listOfSteps = null;
		
		return listOfSteps;
	}

	protected static void setStep(Step step) {
		// TODO: Meg kell írni
	}

	public Color getRobotColor() {
		return robotColor;
	}

	public void setRobotColor(Color robotColor) {
		this.robotColor = robotColor;
	}

	public Color getHumanColor() {
		return humanColor;
	}

	public void setHumanColor(Color humanColor) {
		this.humanColor = humanColor;
	}
}
