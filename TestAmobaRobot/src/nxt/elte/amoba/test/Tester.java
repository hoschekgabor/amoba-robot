package nxt.elte.amoba.test;

import java.util.Iterator;

import lejos.nxt.Button;
import lejos.nxt.LCD;
import nxt.elte.amoba.*;
import nxt.elte.amoba.exception.FatalException;
import nxt.elte.amoba.exception.PlayerSetupException;

public class Tester {
	private static BoardController boardCtrl = BoardController.getInstance(Robot.MOTOR_C, Robot.SENSORPORT_2);
	private static Tower tower = Tower.getInstance(Robot.MOTOR_B, Robot.MOTOR_A,
			Robot.SENSORPORT_1);
	private static BoardPosition[] boardPositionArray = BoardPosition.values();
	private static TowerPosition[] towerPositionArray = TowerPosition.values();
	private static String movement = "";

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int result = 0;
		
		
		try {
			//testBoardController();
			//testTower();
			//testBallPushing();
			
			//Robot test
			
			Robot robot = Robot.getInstance();
			robot.setHumanColor(Color.WHITE);
			robot.setRobotColor(Color.RED);
			
			for (int i = 1; i <= 3; i++) {
				for (int j = 1; j <= 3; j++) {
						robot.setStep(new Step(i, j, PlayerEnum.ROBOT));
						System.out.println(i+","+j);
						result = Button.waitForAnyPress();
						if (result == Button.ID_ESCAPE) {
							throw new FatalException("It isn't OK.");
						}
				}
			}
			Button.waitForAnyPress();
			
			//read table
			ListOfSteps table = null;
			table = robot.getUserSteps();
			System.out.println("Table is readed.");
			/*for (int i = 1; i <= 3; i++) {
				for (int j = 1; j <= 3; j++) {
					//LCD.clear();
					System.out.println(i + "," + j + ": " + table.getStep(i, j));
					Button.waitForAnyPress();
				}
			}*/
		} catch (Exception e ) { //PlayerSetupException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		Button.waitForAnyPress();
	}

	private static void testBallPushing() {
		// sarokba
		boardCtrl.moveTo(BoardPosition.LEFT_OPPOSITE_POSITION);
		tower.moveTo(TowerPosition.PUSH_CORNER);
		tower.pushBall();
		tower.moveTo(TowerPosition.BASE);
		boardCtrl.moveTo(BoardPosition.BASE_POSITION);
		
		// középre
		boardCtrl.moveTo(BoardPosition.OPPOSITE_SIDE_POSITION);
		tower.moveTo(TowerPosition.PUSH_MIDDLE);
		tower.pushBall();
		tower.moveTo(TowerPosition.BASE);
		boardCtrl.moveTo(BoardPosition.BASE_POSITION);
		
		// center
		// Ide nem tud rakni a robot
		/*
		boardCtrl.moveTo(BoardPosition.BASE_LEFT_POSITION);
		tower.moveTo(TowerPosition.PUSH_CENTER);
		tower.pushBall();
		tower.moveToBasePosition();
		boardCtrl.moveToBasePosition();
		*/
	}

	private static void testTower() {
		int k = Math.round(towerPositionArray.length/2);
		
		try {
			for (int i = 0; i < k; i++) {
				testTowerMoving(i, i-1);
				for (int j = i; j < towerPositionArray.length; j++) {					
					testTowerMoving(j, i);
					if (i!=j) testTowerMoving(i, j);
				}
			}
			
			for (int i = k-1; i > -1; i++) {
				testTowerMoving(i, i-1);
				for (int j = i; j < towerPositionArray.length; j++) {					
					testTowerMoving(j, i);
					if (i!=j) testTowerMoving(i, j);
				}
			}
			System.out.println("Movings of tower are OK.");
		} catch (FatalException e) {
			LCD.clear();
			System.out.println(movement + " is not OK.");
			System.out.println(e.getMessage());
			Button.waitForAnyPress();
		}
		
		// Vissza az alaphelyzetbe
		movement = "Tower: Back to Base.";
		tower.moveTo(towerPositionArray[0]);
	}

	/**
	 * This method test the BoardController class.
	 */
	private static void testBoardController() {
		int k = Math.round(boardPositionArray.length/2);
		
		try {
			for (int i = 0; i < k; i++) {
				testBoardMoving(i, i-1);
				for (int j = i; j < boardPositionArray.length; j++) {
					testBoardMoving(j, i);
					if (i!=j) testBoardMoving(i, j);
				}
			}
			System.out.println("Movings of board are OK.");
		} catch (FatalException e) {
			LCD.clear();
			System.out.println(movement + " is not OK.");
			System.out.println(e.getMessage());
			Button.waitForAnyPress();
		}
		
		// Vissza az alaphelyzetbe
		movement = "Board: Back to Base.";
		boardCtrl.moveTo(boardPositionArray[0]);
		Tester.print("End of test.");
	}

	/**
	 * @param i
	 * @param j
	 * @return
	 */
	private static void testBoardMoving(int i, int j) throws FatalException {
		if (j == -1) {
			movement = "-> " + boardPositionArray[i];
		} else {
			movement = boardPositionArray[j] + " -> " + boardPositionArray[i];
		}
		boardCtrl.moveTo(boardPositionArray[i]);
		Tester.print(boardPositionArray[i].toString());
	}
	
	/**
	 * @param i
	 * @param j
	 * @return
	 */
	private static void testTowerMoving(int i, int j) throws FatalException {
		Color color = null;
		
		if (j == -1) {
			movement = "-> " + towerPositionArray[i];
		} else {
			movement = towerPositionArray[j] + " -> " + towerPositionArray[i];
		}
		
		//Board megfelelő pozícióba állítása.
		if (towerPositionArray[i].equals(TowerPosition.READ_CORNER) || towerPositionArray[i].equals(TowerPosition.PUSH_CORNER)) {
			boardCtrl.moveTo(BoardPosition.BASE_LEFT_POSITION);
		} else {
			boardCtrl.moveTo(BoardPosition.BASE_POSITION);
		}

		tower.moveTo(towerPositionArray[i]);

		if (towerPositionArray[i].equals(TowerPosition.READ_CORNER) || towerPositionArray[i].equals(TowerPosition.READ_MIDDLE)
				|| towerPositionArray[i].equals(TowerPosition.READ_CENTER)) {
			color = tower.readField();
			Tester.print(towerPositionArray[i].toString() + "(" + color + ")");
		} else {
			Tester.print(towerPositionArray[i].toString());
		}
	}

	private static void print(String message) throws FatalException {
		int result = 0;
		LCD.clear();
		LCD.drawString(message, 0, 0);
		result = Button.waitForAnyPress(5000);
		if (result == Button.ID_ESCAPE) {
			throw new FatalException("It isn't OK.");
		}
	}

}
