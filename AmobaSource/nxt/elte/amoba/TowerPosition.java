package nxt.elte.amoba;

/**
 * Tower pozícióit reprezentáló enum.
 * @author fmagnucz
 *
 */
public enum TowerPosition {
	BASE(0,"Base"),
	READ_CORNER(25,"ReadCorner"),
	PUSH_CORNER(65,"PushCorner"),
	READ_MIDDLE(35,"ReadMiddle"),
	PUSH_MIDDLE(85,"PushMiddle"),
	READ_CENTER(60,"ReadCenter"),
	PUSH_CENTER(100,"PushCenter")
	;
	
	private int angle;
	private String string;
	
	private TowerPosition(int angle, String string) {
		this.angle = angle;
		this.string = string;
	}
	
	public int getAngle(){
		return angle;
	}
	
	public String toString() {
		return this.string;
	}
}
