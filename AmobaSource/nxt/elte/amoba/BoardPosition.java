package nxt.elte.amoba;

/**
 * Játéktábla pozícióit reprezentáló enum.
 * 
 * @author fmagnucz
 * 
 */
public enum BoardPosition {
	BASE_POSITION(0, "BaseSide"),
	BASE_LEFT_POSITION(1, "BaseLeft"),
	LEFT_SIDE_POSITION(2, "LeftSide"),
	LEFT_OPPOSITE_POSITION(3, "LeftOpposite"),
	OPPOSITE_SIDE_POSITION(4, "OppositeSide"),
	OPPOSITE_RIGHT_POSITION(5, "OppositeRight"),
	RIGHT_SIDE_POSITION(6, "RightSide"),
	RIGHT_BASE_POSITION(7, "RightBase");

	private final int value;
	private final String string;

	private BoardPosition(int value, String string) {
		this.value = value;
		this.string = string;
	}

	public int getIntValue() {
		return this.value;
	}

	public String toString() {
		return this.string;
	}
}
