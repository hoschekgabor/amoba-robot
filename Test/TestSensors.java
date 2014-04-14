package nxt.test;

import lejos.nxt.Button;
import lejos.nxt.ColorSensor;
import lejos.nxt.LCD;
import lejos.nxt.Motor;
import lejos.nxt.ColorSensor.Color;
import lejos.nxt.SensorPort;
import lejos.nxt.TouchSensor;
import lejos.nxt.UltrasonicSensor;
import lejos.util.Delay;
import lejos.util.TextMenu;

public class TestSensors {

	final static int colorSensorPort = 2;
	final static int motorPort = 2;
	final static int touchSensorPort = 0;
	final static int usoundSensorPort = 3;

	public static void main(String[] args) {
		boolean exit = false;
		String types[] = { "Color Test", "Motor Test", "Touch Test",
				"Ultrasound test", "Exit" };

		while (!exit) {
			LCD.clear();
			TextMenu testMenu = new TextMenu(types, 1, "Choose a test type!");
			int testType = testMenu.select();

			switch (testType) {
			case 0:
				colorTest();
				break;
			case 1:
				motorTest();
				break;
			case 2:
				touchTest();
				break;
			case 3:
				ultrasoundTest();
				break;
			default:
				exit = true;
				break;
			}
		}
	}

	private static void ultrasoundTest() {
		UltrasonicSensor us = new UltrasonicSensor(
				SensorPort.getInstance(usoundSensorPort));
		LCD.clear();
		while (!Button.ESCAPE.isDown()) {
			LCD.drawString("Distance:", 0, 0);
			LCD.clear(10, 0, 5);
			LCD.drawInt(us.getDistance(), 10, 0);
			Delay.msDelay(1000);
		}
		// csak akkor lépünk ki, ha már elengedték a buttont
		while (Button.ESCAPE.isDown())
			;
	}

	private static void touchTest() {
		TouchSensor ts = new TouchSensor(
				SensorPort.getInstance(touchSensorPort));
		boolean wasPressed = false;
		LCD.clear();
		while (!Button.ESCAPE.isDown()) {
			if (ts.isPressed()) {
				if (!wasPressed) {
					wasPressed = true;
					LCD.clear();
				}
				LCD.drawString("Pressed", 0, 0);
			} else {
				if (wasPressed) {
					wasPressed = false;
					LCD.clear();
				}
				LCD.drawString("Not pressed!", 0, 0);
			}
		}

		// csak akkor lépünk ki, ha már elengedték a buttont
		while (Button.ESCAPE.isDown())
			;
	}

	private static void motorTest() {
		String degrees[] = { "30", "60", "90", "120", "180", "360" };
		LCD.clear();
		TextMenu motorMenu = new TextMenu(degrees, 1, "Choose rotation degree!");
		int degreeAmount = 0;

		switch (motorMenu.select()) {
		case 0:
			degreeAmount = 30;
			break;
		case 1:
			degreeAmount = 60;
			break;
		case 2:
			degreeAmount = 90;
			break;
		case 3:
			degreeAmount = 120;
			break;
		case 4:
			degreeAmount = 180;
			break;
		case 5:
			degreeAmount = 360;
			break;
		default:
			degreeAmount = 360;
			break;
		}

		Motor.getInstance(motorPort).setSpeed(200);
		for (int i = degreeAmount; i <= 360; i += degreeAmount) {
			Motor.getInstance(motorPort).rotate(degreeAmount);
			Delay.msDelay(1000);
		}
	}

	private static void colorTest() {
		String colorNames[] = { "None", "Red", "Green", "Blue", "Yellow",
				"Megenta", "Orange", "White", "Black", "Pink", "Grey",
				"Light Grey", "Dark Grey", "Cyan" };
		ColorSensor cs = new ColorSensor(
				SensorPort.getInstance(colorSensorPort));
		cs.setFloodlight(Color.WHITE);
		LCD.clear();
		while (!Button.ESCAPE.isDown()) {
			ColorSensor.Color vals = cs.getColor();
			LCD.drawString("Color:          ", 0, 0);
			LCD.drawString(colorNames[vals.getColor() + 1], 7, 0);
		}

		// csak akkor lépünk ki, ha már elengedték a buttont
		while (Button.ESCAPE.isDown())
			;
	}
}
