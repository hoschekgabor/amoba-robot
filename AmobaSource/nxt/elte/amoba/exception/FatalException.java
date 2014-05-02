package nxt.elte.amoba.exception;

/**
 * Ha valami nem várt hiba lép fel, ami miatt a programot nem lehet folytatni,
 * akkor ezt az exceptiont dobjuk.
 * @author fmagnucz
 *
 */
public class FatalException extends RuntimeException {

	public FatalException(String message) {
		super(message);
	}

	public FatalException(String message, Throwable cause) {
		super(message, cause);
	}

}
