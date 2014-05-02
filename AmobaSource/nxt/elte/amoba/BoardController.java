package nxt.elte.amoba;

import lejos.nxt.Motor;
import lejos.nxt.NXTRegulatedMotor;
import lejos.nxt.SensorPort;
import lejos.nxt.TouchSensor;
import lejos.util.Delay;

public class BoardController {
	// Constant variables	
	private final int MOTOR_SPEED = 100;
	private final NXTRegulatedMotor boardMotor;
	private TouchSensor touchSensor;
	

	// private variables and classes
	private BoardPosition position;

	// constructor
	public BoardController(int motorPort, int touchSensorPort) {
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
		boardMotor.rotate(13);
		
		position = BoardPosition.BASE_POSITION;
	}

	// private methods

	// public methods
	public void moveToBasePosition() {
		moveTo(BoardPosition.BASE_POSITION);
	}
	
	public void moveToOppositeSide() {
		moveTo(BoardPosition.OPPOSITE_SIDE);
	}
	
	public void moveToLeftSide() {
		moveTo(BoardPosition.LEFT_SIDE);
	}
	
	public void moveToRightSide() {
		moveTo(BoardPosition.RIGHT_SIDE);
	}
	
	public void moveTo(BoardPosition position) {
		int moving = (this.position.getIntValue() + position.getIntValue()) % 4;
		
		boardMotor.rotate(-90 * moving);
		this.position = position;
	}
}
