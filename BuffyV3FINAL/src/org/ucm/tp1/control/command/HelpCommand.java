package org.ucm.tp1.control.command;

import org.ucm.tp1.model.Game;
import org.ucm.tp1.model.exceptions.CommandParseException;

public class HelpCommand extends Command {
	public HelpCommand() {
		super("Help", "h", "[h]", "elp: show this help");
	}

	public boolean execute(Game game) {
		String help = CommandGenerator.commandHelp();
		System.out.println(help);
		return false;
	}

	public Command parse(String[] commandWords) throws CommandParseException {
		return parseNoParamsCommand(commandWords);
	}
}
