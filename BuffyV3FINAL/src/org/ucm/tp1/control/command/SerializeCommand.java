package org.ucm.tp1.control.command;

import org.ucm.tp1.model.Game;
import org.ucm.tp1.model.exceptions.CommandParseException;

public class SerializeCommand extends Command{
	public static final String incorrectNumberType = String.format("[ERROR]: Command serialize :Incorrect number of arguments");
	
	public SerializeCommand() {
		super("Serialize", "z", "Seriali[z]", "e: Serializes the board.");
	}
	
	public boolean execute(Game game) {
		String serialize = game.getSerializeInfo();
		System.out.println(serialize);
		return false;
	}
	
	public Command parse(String[] commandWords) throws CommandParseException {
		if(matchCommandName(commandWords[0])) {
			if (commandWords.length != 1) {
				throw new CommandParseException(incorrectNumberType);
			}
		}	
		return parseNoParamsCommand(commandWords);
	}
}