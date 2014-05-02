package nxt.elte.amoba;

/**
 * A tábla minden mezőjéhez meghatározott board és tower pozíció tárolására szolgáló osztály.
 * @author fmagnucz
 *
 */
public class Position {
	public BoardPosition boardPosition;
	public TowerPosition towerPosition;

	public Position(BoardPosition bp, TowerPosition tp) {
		boardPosition = bp;
		towerPosition = tp;
	}
	
	public BoardPosition getBoardPosition() {
		return boardPosition;
	}

	public TowerPosition getTowerPosition() {
		return towerPosition;
	}	
}
