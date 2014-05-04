package nxt.elte.amoba.test;

import lejos.nxt.Button;
import lejos.nxt.LCD;
import nxt.elte.amoba.*;
import nxt.elte.amoba.exception.FatalException;

public class Tester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BoardController boardCtrl = new BoardController(Robot.MOTOR_C,
				Robot.SENSORPORT_2);
		String movement = "";

		try {
			// körbejár
			// -> Base
			movement = "-> Base";
			boardCtrl.moveToBasePosition();
			Tester.print("BasePosition");

			// Base -> Base
			movement = "Base -> Base";
			boardCtrl.moveToBasePosition();
			Tester.print("BasePosition");

			// Base -> Left
			movement = "Base -> Left";
			boardCtrl.moveToLeftSide();
			Tester.print("LeftSide");

			// Left -> Left
			movement = "Left -> Left";
			boardCtrl.moveToLeftSide();
			Tester.print("LeftSide");

			// Left -> Opposite
			movement = "Left -> Opposite";
			boardCtrl.moveToOppositeSide();
			Tester.print("OppositeSide");

			// Opposite -> Opposite
			movement = "Opposite -> Opposite";
			boardCtrl.moveToOppositeSide();
			Tester.print("OppositeSide");

			// Opposite -> Right
			movement = "Opposite -> Right";
			boardCtrl.moveToRightSide();
			Tester.print("RightSide");

			// Right -> Right
			movement = "Right -> Right";
			boardCtrl.moveToRightSide();
			Tester.print("RightSide");

			// Right -> Base
			movement = "Right -> Base";
			boardCtrl.moveToBasePosition();
			Tester.print("BasePosition");

			// körbejár vissza
			// Base -> Right movement = "Base -> Right";
			boardCtrl.moveToRightSide();
			Tester.print("RightSide");

			// Right -> Opposite
			movement = "Right -> Opposite";
			boardCtrl.moveToOppositeSide();
			Tester.print("OppositeSide");

			// Opposite -> Left
			movement = "Opposite -> Left";
			boardCtrl.moveToLeftSide();
			Tester.print("LeftSide");

			// Left -> Base
			movement = "Left -> Base";
			boardCtrl.moveToBasePosition();
			Tester.print("BasePosition");

			// Szembelévőek elérése
			// Base -> Opposite movement = "Base -> Opposite";
			boardCtrl.moveToOppositeSide();
			Tester.print("OppositeSide");

			// Opposite -> Base
			movement = "Opposite -> Base";
			boardCtrl.moveToBasePosition();
			Tester.print("BasePosition");

			// Base -> Right
			movement = "Base -> Right";
			boardCtrl.moveToRightSide();
			Tester.print("RightSide");

			// Right -> Left
			movement = "Right -> Left";
			boardCtrl.moveToLeftSide();
			Tester.print("LeftSide");

			// Left -> Right
			movement = "Left -> Right";
			boardCtrl.moveToRightSide();
			Tester.print("RightSide");

			// Vissza az alaphelyzetbe
			movement = "Back to Base.";
			boardCtrl.moveToBasePosition();
			Tester.print("End of test.");
		} catch (FatalException e) {
			LCD.clear();
			System.out.println(movement + " is not OK.\nExit.");
		}
		Button.waitForAnyPress();
	}

	private static void print(String message) {
		int result = 0;
		LCD.clear();
		LCD.drawString(message, 0, 0);
		result = Button.waitForAnyPress(5000);
		if (result == Button.ID_ESCAPE) {
			throw new FatalException("It isn't OK.");
		}
	}

}
