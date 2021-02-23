package org.ucm.tp1.model.objects;
import org.ucm.tp1.model.Game;

public class Vampire extends GameObject{	
	private int cycles;
	protected static final int harm = 1;
	private Game game;
	private static final int initialHealth = 5;
	private static final int initialCycles = -1;
	public static int vampireOnTheBoard = 0;
	public static int deadVampires = 0; 
	
	public Vampire(Game game, int posX, int posY) {
		super(game, posX, posY, initialHealth);
		cycles = initialCycles;
		this.game = game;
		setPosX(posX);
		setPosY(posY);	
		vampireOnTheBoard++;
	}
	
	public String typePrinter() {
		return "V";
	}
	
	public String serializeObject() {
		return super.serializeObject() + ";" + cycles%2;
	}
	
	public void attack() {
		IAttack other = game.getAttackableInPosition(getPosX()-1, getPosY());
		if(other != null) {
			other.receiveVampireAttack(harm);
		}
	}
	
	public boolean receiveSlayerAttack(int damage) {
		if (getHealth() <= 0) {
			return false;
		}
		this.getsBitten(damage);
		return true;		
	}
	
	public void resetCycles() {		
		this.cycles = 0;
	}
	
	
	public void objectAdvance() {
		if (game.occupyGameObject(getPosX() - 1, getPosY()))	{
			if (this.cycles % 2 == 0) {
				setPosX(getPosX() - 1);
			}
			this.cycles++;
		}
		else {
			resetCycles();
		}
	}
	
	public boolean reachEnd() {
		return (getPosX() < 0);
	}

	public static void setOnTheBoard(int vampireOnTheBoard) {
		Vampire.vampireOnTheBoard = vampireOnTheBoard;
	}
	
	public void deadObject() {
		vampireOnTheBoard--;
		deadVampires++;
	}

	public static void setDeadVampires(int deadVampires) {
		Vampire.deadVampires = deadVampires;
	}	
	
	public static void initialInfo()
	{
		Dracula.DraculaisAlive = false;
		setDeadVampires(0);
		setOnTheBoard(0);
	}
	
	public boolean receiveLightFlash() {
		setHealth(0);
		deadObject();
		return true;
	}
	
	public boolean receiveGarlicPush() {
		if (game.occupyGameObject(getPosX() + 1, getPosY())){
			setPosX(getPosX() + 1);
			resetCycles();
			
			if(getPosX() == game.getNumCols()) {
				setHealth(0);
				deadObject();
			}
			return true;
		}
		return false;
	}
	
	// public boolean receiveVampireAttack(int damage) {return false;}
	// public boolean receiveDraculaAttack() {return false;}
}