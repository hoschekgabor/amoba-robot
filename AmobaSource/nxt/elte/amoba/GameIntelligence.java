package nxt.elte.amoba;

public class GameIntelligence {
	private static GameIntelligence intelligence;
	private GameIntelligence() {}
	
	public static GameIntelligence getInstance() {
		if (null == intelligence) {
			intelligence = new GameIntelligence();
		}
		return intelligence;
	}
	
	protected static Step getRobotStep(Board board) {
		ListOfSteps listOfSteps = board.getSteps();
		Step step;
		Step drawStep = null;
		int moves = 0;		

		// megszámoljuk hány lépés van már
		for (int i = 1; i < 4; i++) {
			for (int j = 1; j < 4; j++) {
				if (!listOfSteps.getStep(i, j).equals(PlayerEnum.EMPTY)) {
					moves++;
				}
			}
		}

		switch (moves) {
		case 0:
			return (step = new Step(2,2,PlayerEnum.ROBOT));
		case 1:
			if (listOfSteps.getStep(2, 2).equals(PlayerEnum.EMPTY)) {
				return (step = new Step(2,2,PlayerEnum.ROBOT));
			} else return (step = new Step(1,1,PlayerEnum.ROBOT));
		default:
			for (int i = 1; i < 4; i++) {
				for (int j = 1; j < 4; j++) {
					if (listOfSteps.getStep(i, j).equals(PlayerEnum.EMPTY)) {
						step = new Step(i, j, PlayerEnum.ROBOT);
						board.setStep(step);
						PlayerEnum result = calculateHeuristicValue(board, PlayerEnum.ROBOT);
						board.removeStep(i, j);
						//board.printOut();
						switch (result) {
						case ROBOT:
							return step;
						case DRAW:
							drawStep = step;
							break;
						default:
							// Itt nem csinálunk semmit, kell, hogy legyen jobb lépés
							break;
						}
					}
				}
			}
		}
		return drawStep;
	}
	private static PlayerEnum calculateHeuristicValue(Board board, PlayerEnum currentPlayer) {
		PlayerEnum nextPlayer;
		PlayerEnum result = board.isEndOfMatch();
		PlayerEnum bestResult = null;

		// Ha falevél, akkor csak visszaadjuk az eredményt
		if (!result.equals(PlayerEnum.UNDONE)) {
			return result;
		}

		if (currentPlayer.equals(PlayerEnum.HUMAN)) {
			nextPlayer = PlayerEnum.ROBOT;
		} else { nextPlayer = PlayerEnum.HUMAN; }


		// Ha nem falevél, akkor gyerek és lejjebb megyünk
		for (int i = 1; i < 4; i++) {
			for (int j = 1; j < 4; j++) {
				if (board.getSteps().getStep(i, j).equals(PlayerEnum.EMPTY)) {
					// kipróbáljuk  a lépést
					board.setStep(new Step(i, j, nextPlayer));
					result = calculateHeuristicValue(board, nextPlayer);
					board.removeStep(i, j);

					// Az eredményt a játékos függvényében felfelé áramoltatjuk
					// HUMAN esetében a legrosszabb lehetőséget
					// ROBOT esetében a legjobbat
					switch (result) {
					case HUMAN:
						if (nextPlayer.equals(PlayerEnum.HUMAN)) {
							return result;
						} else {
							if (bestResult == null) {
								bestResult = result;
							}
						}
						break;
					case DRAW:
						bestResult = PlayerEnum.DRAW;
						break;
					case ROBOT:
						if (nextPlayer.equals(PlayerEnum.HUMAN)) {
							if (bestResult == null) {
								bestResult = result;
							}
						} else {
							return result;
						}
						break;
					default:
						// Nem kell csinálni semmit, kell, hogy legyen jobb lépés
						break;
					}				
				}	
			}
		}
		return bestResult;
	}
}
