package nxt.elte.amoba;

/**
 * Játéktábla pozícióit reprezentáló enum.
 * @author fmagnucz
 *
 */
public enum BoardPosition {
	BASE_POSITION(0),
	LEFT_SIDE(1),
	OPPOSITE_SIDE(2),
	RIGHT_SIDE(3);
	
	private final int value;
	
	private BoardPosition(int value) {
		this.value = value;
	}
	
	public int getIntValue() {
		return value;
	}
}
