package org.ucm.tp1.control.command;

import org.ucm.tp1.model.Game;
import org.ucm.tp1.model.exceptions.CommandExecuteException;
import org.ucm.tp1.model.exceptions.CommandParseException;

public class AddCommand extends Command{
	public static final String incorrectNumberType = String.format("[ERROR]: Unvalid argument for add slayer command, number expected: [a]dd <x> <y>");	
	
	private int posX;
	private int posY;
	public AddCommand() {
		super("Add", "a", "[a]", "dd <x> <y>: add a slayer in position x, y");
	}

	public boolean execute(Game game) throws CommandExecuteException  {
		try {
			if(game.addSlayer(posX, posY)) {
		    	return true;			
			}
		}
		catch (CommandExecuteException e) {
			throw new CommandExecuteException(e.getMessage());
			
		}
    	return false;
	}

	public Command parse(String[] commandWords) throws CommandParseException {
		if(matchCommandName(commandWords[0])) {
			if(commandWords.length == 3) {
				try {
					posX = Integer.parseInt(commandWords[1]);
					posY = Integer.parseInt(commandWords[2]);
				}
				catch (NumberFormatException e){
					throw new CommandParseException(incorrectNumberType);
				} 
			}
			else {
				throw new CommandParseException(incorrectNumberType);
			}
		}

		return parseParamsCommand(commandWords);
	}
}
