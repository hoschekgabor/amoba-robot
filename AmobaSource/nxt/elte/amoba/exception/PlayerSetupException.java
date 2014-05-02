package nxt.elte.amoba.exception;

/**
 * Ha a játékosok beállításaival probléma van, akkor ezt az exceptiont dobjuk
 * @author fmagnucz
 *
 */
public class PlayerSetupException extends Exception {

	/**
	 * @param message
	 */
	public PlayerSetupException(String message) {
		super(message);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public PlayerSetupException(String message, Throwable cause) {
		super(message, cause);
	}

}
