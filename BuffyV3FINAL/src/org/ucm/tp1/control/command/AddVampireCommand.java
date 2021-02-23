package org.ucm.tp1.control.command;

import org.ucm.tp1.model.Game;
import org.ucm.tp1.model.exceptions.CommandExecuteException;
import org.ucm.tp1.model.exceptions.CommandParseException;
import org.ucm.tp1.model.exceptions.DraculaIsAliveException;
import org.ucm.tp1.model.exceptions.InvalidPositionException;
import org.ucm.tp1.model.exceptions.NoMoreVampiresException;

public class AddVampireCommand extends Command{
	public static final String invalidType = String.format("[ERROR]: Unvalid type: [v]ampire [<type>] <x> <y>. Type = {\"\"|\"D\"|\"E\"}");
	public static final String incorrectNumberType = String.format("[ERROR]: Unvalid argument for add vampire command, number expected: [v]ampire [<type>] <x> <y>. Type = {\"\"|\"D\"|\"E\"}");	
	
	private int posX;
	private int posY;
	private String type;
	public AddVampireCommand() {
		super("Vampire", "v", "[v]", "ampire [<type>] <x> <y>. Type = {\"\"|\"D\"|\"E\"}: add a vampire in position x, y");
	}

	public boolean execute(Game game) throws NoMoreVampiresException, DraculaIsAliveException, InvalidPositionException, CommandExecuteException {
		try {
			if(type.equals("v") || type.equals("")) 
			{
		    	game.addVampire(posX, posY);	
		    	return true;
			}
			else if(type.equals("d"))
			{
				game.addDracula(posX, posY);
				return true;
			}
			else
			{
				game.addExplosiveVampire(posX, posY);
				return true;
			}
		}
		catch (CommandExecuteException e) {
			throw new CommandExecuteException(e.getMessage());
			
		}
    	// return false;			
	}

	protected Command parseParamsCommand(String[] words) {
		if (matchCommandName(words[0])) {
			if (words.length != 3 && words.length != 4) {
				return null;
			}
			return this;
		}
		return null;
	}
	
	public Command parse(String[] commandWords) throws CommandParseException {
			if(commandWords.length == 4) {
				type = commandWords[1];
				if(!type.equals("v") && !type.equals("d") && !type.equals("e")) {
					throw new CommandParseException(invalidType);
				}

				try {
					posX = Integer.parseInt(commandWords[2]);
					posY = Integer.parseInt(commandWords[3]);
				}
				catch (NumberFormatException e){
					throw new CommandParseException(incorrectNumberType);
				}
				
			}
			else if (commandWords.length == 3) {
				try {
					posX = Integer.parseInt(commandWords[1]);
					posY = Integer.parseInt(commandWords[2]);
				}
				catch (NumberFormatException e){
					throw new CommandParseException(incorrectNumberType);
				}
				type = "";
			}
			return parseParamsCommand(commandWords);
	}
}