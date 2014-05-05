package nxt.elte.amoba;

/**
 * A tábla minden mezőjéhez meghatározott board és tower pozíció tárolására szolgáló osztály.
 * @author fmagnucz
 *
 */
public class Position {
	private BoardPosition boardPosition;
	private TowerPosition towerPositionRead;
	private TowerPosition towerPositionPush;

	public Position(BoardPosition bp, TowerPosition tpr, TowerPosition tpp) {
		boardPosition = bp;
		towerPositionRead = tpr;
		towerPositionPush = tpp;
	}
	
	public BoardPosition getBoardPosition() {
		return boardPosition;
	}

	public TowerPosition getTowerPositionRead() {
		return towerPositionRead;
	}
	
	public TowerPosition getTowerPositionPush() {
		return towerPositionPush;
	}
}
