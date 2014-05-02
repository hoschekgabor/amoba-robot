package nxt.elte.amoba;

import lejos.nxt.Button;
import lejos.nxt.LCD;
import lejos.util.TextMenu;
import nxt.elte.amoba.exception.FatalException;
import nxt.elte.amoba.exception.PlayerSetupException;

public class Robot {

	private final static int SENSORPORT_1 = 0;
	private final static int SENSORPORT_2 = 1;
	private final static int SENSORPORT_3 = 2;
	private final static int SENSORPORT_4 = 3;
	private final static int MOTOR_A = 0;
	private final static int MOTOR_B = 1;
	private final static int MOTOR_C = 2;

	private static Robot robot;

	private boolean checked = false; 
	private Color robotColor;
	private Color humanColor;
	private final BoardController boardController = new BoardController(
			MOTOR_A, SENSORPORT_1);;
	private final Tower tower = new Tower(MOTOR_B, MOTOR_C);;
	// Pozíciókhoz tartozó board és tower pozíciók
	// TODO: A pozíciókat még meg kell adni.
	private final Position[][] positions = {
			{
					new Position(BoardPosition.BASE_POSITION,
							TowerPosition.POSITION_1),
					new Position(BoardPosition.BASE_POSITION,
							TowerPosition.POSITION_1),
					new Position(BoardPosition.BASE_POSITION,
							TowerPosition.POSITION_1) },
			{
					new Position(BoardPosition.BASE_POSITION,
							TowerPosition.POSITION_1),
					new Position(BoardPosition.BASE_POSITION,
							TowerPosition.POSITION_1),
					new Position(BoardPosition.BASE_POSITION,
							TowerPosition.POSITION_1) },
			{
					new Position(BoardPosition.BASE_POSITION,
							TowerPosition.POSITION_1),
					new Position(BoardPosition.BASE_POSITION,
							TowerPosition.POSITION_1),
					new Position(BoardPosition.BASE_POSITION,
							TowerPosition.POSITION_1) } };

	private Robot() {
	}

	public static Robot getInstance() {
		if (null == robot) {
			robot = new Robot();
		}
		return robot;
	}

	public void boardInitialPosition() {
		boardController.moveToBasePosition();
	}

	public int printMenu(String[] options, int initChoice, String Question) {
		int choice = 1;
		LCD.clear();
		TextMenu testMenu = new TextMenu(options, choice, "Choose a test type!");
		choice = testMenu.select();
		return choice;
	}

	/**
	 * Print any message to LCD of NXT Bricket.
	 * If waitForPress is true, then this method return only the user pressed any key on bricket.
	 * @param message
	 * @param waitForPress
	 */
	public void printMessage(String message, boolean waitForPress) {
		LCD.clear();
		LCD.drawString(message, 0, 0);
		if (waitForPress) {
			Button.waitForAnyPress();
		}
	}
	
	public void printMessage(String message) {
		printMessage(message, false);
	}

	public ListOfSteps getUserSteps() throws PlayerSetupException, FatalException {
		check();

		Position pos;
		PlayerEnum player;
		Color color;
		ListOfSteps listOfSteps = new ListOfSteps();

		for (int row = 0; row < 3; row++) {
			for (int col = 0; col < 0; col++) {
				pos = positions[row][col];
				boardController.moveTo(pos.getBoardPosition());
				tower.moveTo(pos.getTowerPosition());
				color = tower.readField();
				if (color == this.humanColor) {
					player = PlayerEnum.HUMAN;
				} else if (color == this.robotColor) {
					player = PlayerEnum.ROBOT;
				} else if (color == Color.OTHER) {
					player = PlayerEnum.EMPTY;
				} else {
					// ide nem szabad belekeveredni.
					throw new FatalException(
							"Can't read the color of ball on this position: "
									+ (row + 1) + ", " + (col + 1) + "!");
				}
				listOfSteps.addStep(new Step(row, col, player));
			}
		}
		return listOfSteps;
	}

	private void check() throws PlayerSetupException {
		// Ha már ellenőriztük, akkor nem ellenőrzünk megint.
		if (checked) return;
		
		if (robotColor == null) {
			throw new PlayerSetupException(
					"The color of Robot isn't set up! Use the Robot.setRobotColor(color) method!");
		}

		if (humanColor == null) {
			throw new PlayerSetupException(
					"The color of human player isn't set up! Use the Robot.setHumanColor(color) method!");
		}

		if (robotColor == humanColor) {
			throw new PlayerSetupException(
					"The color of human equal color of robot!");
		}
	}

	public void setStep(Step step) throws PlayerSetupException {
		check();
		Position pos = positions[step.getRow()][step.getColumn()];
		boardController.moveTo(pos.getBoardPosition());
		tower.moveTo(pos.getTowerPosition());
		tower.pushBall();
	}

	public Color getRobotColor() {
		return robotColor;
	}

	public void setRobotColor(Color robotColor) {
		this.robotColor = robotColor;
		checked = false;
	}

	public Color getHumanColor() {
		return humanColor;
	}

	public void setHumanColor(Color humanColor) {
		this.humanColor = humanColor;
		checked = false;
	}
}
