package org.ucm.tp1.control.command;

import org.ucm.tp1.model.Game;
import org.ucm.tp1.model.exceptions.CommandParseException;

public class SuperCoinsCommand extends Command {
	public static final String incorrectNumberType = String.format("[ERROR]: Command coins :Incorrect number of arguments");
	
	public SuperCoinsCommand() {
		super("Coins", "c", "[c]", "oins: add 1000 coins");
	}
	
	public boolean execute(Game game) {
		return game.superCoins();
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