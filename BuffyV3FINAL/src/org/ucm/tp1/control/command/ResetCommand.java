package org.ucm.tp1.control.command;

import org.ucm.tp1.model.Game;
import org.ucm.tp1.model.exceptions.CommandParseException;

public class ResetCommand extends Command{

	public ResetCommand() {
		super("Reset", "r", "[r]", "eset: reset game");
	}

	public boolean execute(Game game) {
		return 	game.reset();
	}

	public Command parse(String[] commandWords) throws CommandParseException {
		return parseNoParamsCommand(commandWords);
	}
}
