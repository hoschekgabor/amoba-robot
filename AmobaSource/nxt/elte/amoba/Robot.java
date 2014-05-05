package nxt.elte.amoba;

import lejos.nxt.Button;
import lejos.nxt.LCD;
import lejos.nxt.SensorPort;
import lejos.nxt.UltrasonicSensor;
import lejos.util.Delay;
import lejos.util.TextMenu;
import nxt.elte.amoba.exception.FatalException;
import nxt.elte.amoba.exception.PlayerSetupException;

public class Robot {

	public final static int SENSORPORT_1 = 0;
	public final static int SENSORPORT_2 = 1;
	public final static int SENSORPORT_3 = 2;
	public final static int SENSORPORT_4 = 3;
	public final static int MOTOR_A = 0;
	public final static int MOTOR_B = 1;
	public final static int MOTOR_C = 2;

	private static Robot robot;

	private boolean checked = false; 
	private Color robotColor;
	private Color humanColor;
	private final BoardController boardController = BoardController.getInstance(
			MOTOR_C, SENSORPORT_2);
	private final UltrasonicSensor us = new UltrasonicSensor(SensorPort.getInstance(SENSORPORT_3));
	private final Tower tower = Tower.getInstance(MOTOR_B, MOTOR_A, SENSORPORT_1);
	// Pozíciókhoz tartozó board és tower pozíciók
	private final Position[][] positions = {
			{
					//0 0
					new Position(BoardPosition.BASE_LEFT_POSITION,
							TowerPosition.READ_CORNER,
							TowerPosition.PUSH_CORNER),
					//0 1
					new Position(BoardPosition.BASE_POSITION,
							TowerPosition.READ_MIDDLE,
							TowerPosition.PUSH_MIDDLE),
					//0 2
					new Position(BoardPosition.RIGHT_BASE_POSITION,
							TowerPosition.READ_CORNER,
							TowerPosition.PUSH_CORNER) },
			{
					//1 0
					new Position(BoardPosition.LEFT_SIDE_POSITION,
							TowerPosition.READ_MIDDLE,
							TowerPosition.PUSH_MIDDLE),
					//1 1
					new Position(BoardPosition.RIGHT_BASE_POSITION,
							TowerPosition.READ_CENTER,
							TowerPosition.PUSH_CENTER),
					//1 2
					new Position(BoardPosition.RIGHT_SIDE_POSITION,
							TowerPosition.READ_MIDDLE,
							TowerPosition.PUSH_MIDDLE) },
			{
					//2 0
					new Position(BoardPosition.LEFT_OPPOSITE_POSITION,
							TowerPosition.READ_CORNER,
							TowerPosition.PUSH_CORNER),
					//2 1
					new Position(BoardPosition.OPPOSITE_SIDE_POSITION,
							TowerPosition.READ_MIDDLE,
							TowerPosition.PUSH_MIDDLE),
					//2 2
					new Position(BoardPosition.OPPOSITE_RIGHT_POSITION,
							TowerPosition.READ_CORNER,
							TowerPosition.PUSH_CORNER) } };

	private Robot() {
	}

	public static Robot getInstance() {
		if (null == robot) {
			robot = new Robot();
		}
		return robot;
	}

	public void boardInitialPosition() {
		boardController.moveTo(BoardPosition.BASE_POSITION);
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
		boolean humanHand = false;
		
		// várunk, amíg az emberi játékos nem rakott.
		while ( !humanHand ){
			humanHand = (us.getDistance() <= 10);
		}
		
		// valaki itt olálkodott, ezért várunk fél másodpercet
		Delay.msDelay(500);
		
		// megvárjuk, míg elveszi a kezét
		while (humanHand) {
			humanHand = (us.getDistance() <= 11);
		}

		// elvette a kezét, mehet az olvasás
		for (int row = 0; row < 3; row++) {
			for (int col = 0; col < 3; col++) {
				pos = positions[row][col];
				boardController.moveTo(pos.getBoardPosition());
				tower.moveTo(pos.getTowerPositionRead());
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
				listOfSteps.addStep(new Step(row+1, col+1, player));
				
				// TODO Ezeket majd ki kell kommnetezni, ha már jól működik
				System.out.println((row+1) + "," + (col+1) + ": " + player);
				Button.waitForAnyPress();
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

	public void setStep(Step step) throws PlayerSetupException, FatalException {
		check();
		if (step.getRow() == 2 && step.getColumn() == 2) {
			throw new FatalException("I can't push the ball into the center field!");
		}
		Position pos = positions[step.getRow()-1][step.getColumn()-1];
		boardController.moveTo(pos.getBoardPosition());
		tower.moveTo(pos.getTowerPositionPush());
		tower.pushBall();
		tower.moveToBasePosition();
		boardController.moveToBasePosition();
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
