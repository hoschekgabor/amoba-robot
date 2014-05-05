package nxt.elte.amoba;

import nxt.elte.amoba.exception.FatalException;
import lejos.nxt.Button;
import lejos.nxt.ColorSensor;
import lejos.nxt.Motor;
import lejos.nxt.NXTRegulatedMotor;
import lejos.nxt.SensorPort;

/**
 * Ez az osztály reprezentálja azt a szerkezeti egységet, ami a színérzékelést
 * végzi, valamint a robot golyóit elhelyezi a táblán.
 * 
 * @author fmagnucz
 * 
 */
public class Tower {

	private final NXTRegulatedMotor movingMotor;
	private final NXTRegulatedMotor ballPusherMotor;
	private final ColorSensor colorSensor;
	
	private TowerPosition position = null;
	private int movingCounter = 0;
	
	private static Tower me = null;

	private Tower(int movingMotorPort, int ballPusherMotorPort,
			int colorSensorPort) {
		movingMotor = Motor.getInstance(movingMotorPort);
		ballPusherMotor = Motor.getInstance(ballPusherMotorPort);
		colorSensor = new ColorSensor(SensorPort.getInstance(colorSensorPort));
		movingMotor.setSpeed(80);
		movingMotor.setStallThreshold(10, 100);
		ballPusherMotor.setSpeed(100);
		
		initTowerPosition();
	}

	/**
	 * 
	 */
	private void initTowerPosition() {
		// Search the base position
		long timeStop = System.currentTimeMillis() + 2000;
		movingMotor.rotate(-359,true);
		while (System.currentTimeMillis() < timeStop) {
			if(movingMotor.isStalled()){
				movingMotor.stop();
				break;
			}
		}
		if (System.currentTimeMillis() >= timeStop) {
			throw new FatalException("Tower moving problem. I can't find the base position.");
		}
		movingMotor.rotate(40);
		movingMotor.resetTachoCount();
		
		position = TowerPosition.BASE;
		movingCounter = 0;
	}
	
	public static Tower getInstance(int movingMotorPort, int ballPusherMotorPort,
			int colorSensorPort) {
		if (me == null) {
			me = new Tower(movingMotorPort, ballPusherMotorPort, colorSensorPort);
		}
		return me;
	}

	public void moveTo(TowerPosition newPosition) {
		// TODO: Túl nagy a holtjéték. Ezzel valamit kezdeni kell. Egyik lehetőség, hogy minden mozgatást
		//       az alaphelyzetből indítunk. Ez lassab olvasást eredményez, de legalább működik.
		int diffAngle = 0;
		/*
		if (movingCounter >= 5) {
			this.initTowerPosition();
		}
		*/
		if (this.position != newPosition) {
			diffAngle = newPosition.getAngle() - this.position.getAngle();
			//diffAngle = newPosition.getAngle() - movingMotor.getTachoCount();
			movingMotor.rotate(diffAngle);
			if(movingMotor.isStalled())	throw new FatalException("Tower is stalled.");
			this.position = newPosition;
		}
		
		//movingCounter++;
	}
	
	public void moveToBasePosition() {
		this.moveTo(TowerPosition.BASE);
	}

	public Color readField() {
		Color color = Color.OTHER;
		// TODO: Le kell tesztelni.
		switch (colorSensor.getColorID()) {
		case lejos.robotics.Color.RED:
			color = Color.RED;
			break;
		
		case lejos.robotics.Color.GREEN:
			//TODO Tesztelés miatt a zöld is most piros. :-)
			//color = Color.GREEN;
			color = Color.RED;
			break;

		case lejos.robotics.Color.YELLOW:
			color = Color.YELLOW;
			break;

		default:
			break;
		}
		return color;
	}

	public void pushBall() {
		// TODO: Meg kell még írni.
		ballPusherMotor.rotate(360);
	}
}
