package org.ucm.tp1.control.command;

import org.ucm.tp1.model.exceptions.CommandParseException;

public class CommandGenerator {
	public static final String unknownCommandMsg = String.format("[ERROR]: Unknown command");
	
	private static Command[] availableCommands = {
			new AddCommand(),
			new HelpCommand(),
			new ResetCommand(),
			new ExitCommand(),
			new UpdateCommand(),
			new GarlicPushCommand(),
			new LightFlashCommand(),			
			new BloodBankCommand(),
			new SuperCoinsCommand(),
			new AddVampireCommand(),
			new SaveCommand(),
			new SerializeCommand()
			};
	
	public static Command parse(String[ ] commandWords) throws CommandParseException {
		for(Command c:availableCommands) {
			Command parsedCommand = c.parse(commandWords);
			if(parsedCommand != null)
				return parsedCommand;
		}
		
		throw new CommandParseException(unknownCommandMsg);
	}
	
	public static String commandHelp() {
		String help;
		help = "Available commands:\n";
		for(Command c:availableCommands) {
			help = help + c.helpText();
		}
		return help;
	}
}
