package org.ucm.tp1.model.objects;

import org.ucm.tp1.model.Game;

public class BloodBank extends Slayer{
	private int z;
	public static final int initialHealth = 1;
	private Game game;
	
	public BloodBank(Game game, int posX, int posY, int z, int health) {
		super(game, posX, posY, initialHealth);
		this.game = game;
		this.z = z;		
	}

	public String toString() {
		return typePrinter() + " [" + z + "]";
	}
	
	public String typePrinter() {
		return "B";
	}
	
	public String serializeObject() {
		return super.serializeObject() + ";" + this.z;
	}
	
	
	public void attack() {
		int v = ( z / 10);
		game.setCoins(v);
	}
}