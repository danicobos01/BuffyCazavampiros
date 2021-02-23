package org.ucm.tp1.control.command;

import org.ucm.tp1.model.Game;
import org.ucm.tp1.model.exceptions.CommandExecuteException;
import org.ucm.tp1.model.exceptions.CommandParseException;

public class LightFlashCommand extends Command {
	
	public LightFlashCommand() {
		super("Light", "l", "[l]", "ight: kills all the vampires");
	}

	public boolean execute(Game game) throws CommandExecuteException {	
		
		return game.lightFlash();
	}

	public Command parse(String[] commandWords) throws CommandParseException {
		return parseNoParamsCommand(commandWords);
	}
}	