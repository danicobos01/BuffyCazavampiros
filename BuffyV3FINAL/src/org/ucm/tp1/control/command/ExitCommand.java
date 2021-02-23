package org.ucm.tp1.control.command;

import org.ucm.tp1.model.Game;
import org.ucm.tp1.model.exceptions.CommandParseException;

public class ExitCommand extends Command{
	public ExitCommand() {
		super("Exit", "e", "[e]", "xit: exit game");
	}

	public boolean execute(Game game) {
		game.doExit();
		return false;
	}

	public Command parse(String[] commandWords) throws CommandParseException {
		return parseNoParamsCommand(commandWords);
	}
}
