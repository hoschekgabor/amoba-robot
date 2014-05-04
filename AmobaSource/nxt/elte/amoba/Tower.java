package nxt.elte.amoba;

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

	public Tower(int movingMotorPort, int ballPusherMotorPort,
			int colorSensorPort) {
		movingMotor = Motor.getInstance(movingMotorPort);
		ballPusherMotor = Motor.getInstance(ballPusherMotorPort);
		colorSensor = new ColorSensor(SensorPort.getInstance(colorSensorPort));
		movingMotor.setSpeed(100);
		ballPusherMotor.setSpeed(100);
		movingMotor.setStallThreshold(2, 100);
	}

	public void moveTo(TowerPosition position) {
		// TODO: Meg kell írni.
		long timeStop = System.currentTimeMillis() + 5000;
		
		if (TowerPosition.BASE == position) {
			movingMotor.rotate(-359,true);
			while (System.currentTimeMillis() < timeStop) {
				if(movingMotor.isStalled()){
					System.out.println("stalled");
					movingMotor.stop();
					break;
				}
			}
			movingMotor.rotate(100);			
		}
	}

	public Color readField() {
		Color color = Color.OTHER;
		// TODO: Le kell tesztelni.
		switch (colorSensor.getColorID()) {
		case lejos.robotics.Color.RED:
			color = Color.RED;
			break;
		
		case lejos.robotics.Color.GREEN:
			color = Color.GREEN;
			break;

		default:
			break;
		}
		return color;
	}

	public void pushBall() {
		// TODO: Meg kell még írni.
	}
}
