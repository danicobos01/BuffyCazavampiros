package org.ucm.tp1.control.command;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import org.ucm.tp1.model.Game;
import org.ucm.tp1.model.exceptions.CommandParseException;

public class SaveCommand extends Command{
	public static final String incorrectNumberType = String.format("[ERROR]: Command save :Incorrect number of arguments");
	private String fileName;
	
	public SaveCommand() {
		super("Save", "s", "[S]", "ave <filename>: Save the state of the game to a file.");
	}

	public boolean execute(Game game) {	
		// return 	game.save(game, fileName);
		try(BufferedWriter writer = new BufferedWriter(new FileWriter(fileName + ".dat"))){
			writer.write(game.getSerializeInfo());	
			System.out.println("Game successfully saved to file " + fileName + ".dat\n");
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	protected Command parseParamsCommand(String[] words) {
		if (matchCommandName(words[0])) {
			if (words.length != 2) {
				return null;
			}
			return this;
		}
		return null;
	}
	public Command parse(String[] commandWords) throws CommandParseException {
		if (matchCommandName(commandWords[0])) {
			if (commandWords.length != 2) {
				throw new CommandParseException(incorrectNumberType);
			}
			fileName = commandWords[1];
		}	
		
		return parseParamsCommand(commandWords);
	}
}
