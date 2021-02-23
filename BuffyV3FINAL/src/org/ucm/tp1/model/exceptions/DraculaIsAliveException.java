package org.ucm.tp1.model.exceptions;

public class DraculaIsAliveException extends CommandExecuteException{
	public DraculaIsAliveException(String message) {
		super(message);
	}

	private static final long serialVersionUID = 1L;
}