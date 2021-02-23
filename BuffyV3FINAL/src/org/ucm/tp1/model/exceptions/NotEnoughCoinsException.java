package org.ucm.tp1.model.exceptions;

public class NotEnoughCoinsException extends CommandExecuteException {
	public NotEnoughCoinsException(String message) {
		super(message);
	}

	private static final long serialVersionUID = 1L;
}