package org.ucm.tp1.control.command;

import org.ucm.tp1.model.Game;
import org.ucm.tp1.model.exceptions.CommandExecuteException;
import org.ucm.tp1.model.exceptions.CommandParseException;

public class GarlicPushCommand extends Command {
	
	public GarlicPushCommand() {
		super("Garlic", "g", "[g]", "arlic : pushes back vampires");
	}
	
	public boolean execute(Game game) throws CommandExecuteException {
		return game.garlicPush();
	}

	public Command parse(String[] commandWords) throws CommandParseException {
		return parseNoParamsCommand(commandWords);
	}
}

