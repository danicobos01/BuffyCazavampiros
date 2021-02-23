package org.ucm.tp1.model.exceptions;

public class GameException extends Exception {
	private static final long serialVersionUID = 1L;
	public static final String unknownCommandMsg = String.format("[ERROR]: Unknown command");
	
	public GameException() { 
		super(); 
	}
	
	public GameException(String message) {
		super(message);
	}
	
	public GameException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public GameException(Throwable cause) {
		super(cause);
	}
}
