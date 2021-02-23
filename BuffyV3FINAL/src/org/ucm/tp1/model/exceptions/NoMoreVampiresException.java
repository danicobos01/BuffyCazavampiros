package org.ucm.tp1.model.exceptions;

public class NoMoreVampiresException extends CommandExecuteException{
	public NoMoreVampiresException(String message) {
		super(message);
	}

	private static final long serialVersionUID = 1L;
}