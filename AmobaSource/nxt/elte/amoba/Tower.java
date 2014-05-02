package nxt.elte.amoba;

import lejos.nxt.Motor;
import lejos.nxt.NXTRegulatedMotor;

/**
 * Ez az osztály reprezentálja azt a szerkezeti egységet,
 * ami a színérzékelést végzi, valamint a robot golyóit
 * elhelyezi a táblán.
 * @author fmagnucz
 *
 */
public class Tower {
	
	private final NXTRegulatedMotor movingMotor;
	private final NXTRegulatedMotor ballPusherMotor;
	
	public Tower(int movingMotorPort, int ballPusherMotorPort) {
		movingMotor = Motor.getInstance(movingMotorPort);
		ballPusherMotor = Motor.getInstance(ballPusherMotorPort);
		movingMotor.setSpeed(100);
		ballPusherMotor.setSpeed(100);
	}

	public void moveTo(TowerPosition position) {
		//TODO: Meg kell írni.
	}
	
	public Color readField() {
		Color color = Color.OTHER;
		//TODO: Meg kell írni.
		
		return color;
	}
	
	public void pushBall() {
		//TODO: Meg kell még írni.
	}
}
