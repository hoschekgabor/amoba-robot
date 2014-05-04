package nxt.elte.amoba;

/**
 * Játéktábla pozícióit reprezentáló enum.
 * @author fmagnucz
 *
 */
public enum BoardPosition {
	BASE_POSITION(0),
	BASE_LEFT_POSIITION(1),
	LEFT_SIDE(2),
	LEFT_OPPOSITE(3),
	OPPOSITE_SIDE(4),
	OPPOSITE_RIGHT(5),
	RIGHT_SIDE(6),
	RIGHT_BASE(7);
	
	private final int value;
	
	private BoardPosition(int value) {
		this.value = value;
	}
	
	public int getIntValue() {
		return value;
	}
}
