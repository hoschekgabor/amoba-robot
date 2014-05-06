package nxt.elte.amoba;

import lejos.nxt.Motor;
import lejos.nxt.NXTRegulatedMotor;
import lejos.nxt.SensorPort;
import lejos.nxt.TouchSensor;

public class BoardController {
	// Constant variables	
	private final int MOTOR_SPEED = 140;
	private final int ANGLE_TO_BASE_POSITION = 10;
	private final NXTRegulatedMotor boardMotor;
	private TouchSensor touchSensor;
	

	// private variables and classes
	private BoardPosition position;
	private int numberOfPositions = BoardPosition.values().length;
	private int angle = 360 / numberOfPositions;
	
	private static BoardController me = null;

	/**
	 * This is the BoardController constructor.
	 * @param motorPort
	 * @param touchSensorPort
	 */
	private BoardController(int motorPort, int touchSensorPort) {
		boardMotor = Motor.getInstance(motorPort);
		touchSensor = new TouchSensor(SensorPort.getInstance(touchSensorPort));
		boardMotor.setSpeed(MOTOR_SPEED);
		boardMotor.backward();
		//boardMotor.forward();
		
		// wait unless motor turn away from touchsensor.
		while (touchSensor.isPressed())
			;
		//System.out.println("Waiting for touchsensor.");
		
		// touchChanged = false;
		while (!touchSensor.isPressed())
			;
		boardMotor. stop();
		boardMotor.rotate(ANGLE_TO_BASE_POSITION);
		
		position = BoardPosition.BASE_POSITION;
	}

	// private methods

	// public methods
	public static BoardController getInstance(int motorPort, int touchSensorPort) {
		if (me == null) {
			me = new BoardController(motorPort, touchSensorPort);
		}
		return me;
	}
	
	public void moveTo(BoardPosition position) {
		int moving = (position.getIntValue() - this.position.getIntValue()) % numberOfPositions;
		if (Math.abs(moving) > numberOfPositions / 2) {
			moving = ((int) (Math.signum(moving) * -1) * (numberOfPositions - Math.abs(moving)));
		}
		boardMotor.rotate(-angle * moving);
		this.position = position;
	}
	
	public void moveToBasePosition(){
		this.moveTo(BoardPosition.BASE_POSITION);
	}
}
