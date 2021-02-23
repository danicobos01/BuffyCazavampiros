package org.ucm.tp1.control;

import java.util.Scanner;
import org.ucm.tp1.control.command.Command;
import org.ucm.tp1.control.command.CommandGenerator;
import org.ucm.tp1.model.Game;
import org.ucm.tp1.model.exceptions.CommandParseException;
import org.ucm.tp1.model.exceptions.GameException;

public class Controller {
	
	public final String prompt = "Command > ";

    private Game game;
    private Scanner scanner;
    
    public Controller(Game game, Scanner scanner) {
	    this.game = game;
	    this.scanner = scanner;
    }
    
    public void printGame() {
   	 System.out.println(game); 
   }
    
    public void run() {    	
    	boolean refreshDisplay = true;
    	printGame();
    	while (!game.isFinished()){
    		refreshDisplay = false;
    		System.out.println(prompt);
    		String s = scanner.nextLine();
    		String[] parameters = s.toLowerCase().trim().split(" ");
			System.out.println("[DEBUG] Executing: " + s);
			Command command;
			try {
				command = CommandGenerator.parse(parameters);
    			try {
					refreshDisplay = command.execute(game);
				} catch (GameException e) {
					System.out.format(e.getMessage() + " %n %n");
				}
			} catch (CommandParseException e1) {
				System.out.format(e1.getMessage() + " %n %n");
			}
    		if (refreshDisplay) 
    			printGame();
    	}
    }
 }