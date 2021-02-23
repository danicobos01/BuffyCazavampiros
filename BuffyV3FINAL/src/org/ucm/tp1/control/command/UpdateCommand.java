package org.ucm.tp1.control.command;

import org.ucm.tp1.model.Game;
import org.ucm.tp1.model.exceptions.CommandParseException;

public class UpdateCommand extends Command {
	public UpdateCommand() {
		super("None", "n", "[n]", "one | []: update");
	}

	public boolean execute(Game game) {
		game.updates();
		return true;
	}

	public Command parse(String[] commandWords) throws CommandParseException {
		if(commandWords[0].equals("")) commandWords[0] = "n";
			return parseNoParamsCommand(commandWords);
	}
}