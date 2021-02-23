package org.ucm.tp1.model.objects;

import org.ucm.tp1.model.Game;

public class Dracula extends Vampire {
	
	public static boolean DraculaisAlive = false;
	private Game game;
	
	public Dracula(Game game, int posX, int posY) {
		super(game, posX, posY);
		this.game = game;
		setPosX(posX);
		setPosY(posY);			
		DraculaisAlive = true;
	}
	
	public String typePrinter() {
		return "D";
	}
	
	public void attack() {
		IAttack other = game.getAttackableInPosition(getPosX()-1, getPosY());
		if(other != null ) {		
			other.receiveDraculaAttack();
		}
	}

	public void deadObject() {
		super.deadObject();
		DraculaisAlive = false;
	}
	
	public boolean receiveLightFlash() {
		return false;
	}
}