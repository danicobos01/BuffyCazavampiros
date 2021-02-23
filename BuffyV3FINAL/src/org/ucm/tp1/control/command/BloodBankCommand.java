package org.ucm.tp1.control.command;

import org.ucm.tp1.model.Game;
import org.ucm.tp1.model.exceptions.CommandExecuteException;
import org.ucm.tp1.model.exceptions.CommandParseException;

public class BloodBankCommand extends Command {
	public static final String incorrectNumberType = String.format("[ERROR]: Unvalid argument for add blood bank command, number expected: [b]ank <x> <y> <z>");
	private int posX;
	private int posY;
	private int z;

	public BloodBankCommand() {
		super("Bank", "b", "[b]", "ank <x> <y> <z>: add a blood bank with cost z in position x, y");
	}
	
	public boolean execute(Game game) throws CommandExecuteException {
		if(game.addBloodBank(posX, posY, z))
			return true;
		
		return false;
	}
	
	protected Command parseParamsCommand(String[] words) {
		if (matchCommandName(words[0])) {
			if (words.length != 4) {
				return null;
			}
			return this;
		}
		return null;
	}
	
	public Command parse(String[] commandWords) throws CommandParseException {
		if(commandWords.length == 4 && matchCommandName(commandWords[0])) {
			try {
				posX =  Integer.parseInt(commandWords[1]);
				posY =  Integer.parseInt(commandWords[2]);
				z = Integer.parseInt(commandWords[3]);	
			}
			catch (NumberFormatException e){
				throw new CommandParseException(incorrectNumberType);
			} 
					
		}
		return parseParamsCommand(commandWords);
	}
}
