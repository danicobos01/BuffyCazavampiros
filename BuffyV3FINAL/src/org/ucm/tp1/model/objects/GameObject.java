package org.ucm.tp1.model.objects;

import org.ucm.tp1.model.Game;

public abstract class GameObject implements IAttack {
	private int posX;
	private int posY;
	private int health;
	
	public GameObject(Game game, int posX, int posY, int health) {
		this.posX = posX;
		this.posY = posY;
		this.health = health;
	}
	
	public abstract void deadObject();
	
	public abstract void objectAdvance();

	public abstract void attack();
	
	public abstract String typePrinter();
	
	public String serializeObject() {
		return typePrinter() + ";" + getPosX() + ";" + getPosY() + ";" + getHealth();
	}
	
	public String toString() {
		return typePrinter() + " [" + getHealth() + "]";
	}
	
	public int getPosX() {
		return posX;
	}
	

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public boolean inPosition(int posX, int posY) {
		return (this.posX == posX && this.posY == posY);
	}
	
	public void getsBitten(int damage) {
		this.health -= damage;
		if (objectDeath()) {
			this.deadObject();			
		}
	}
	
	public boolean objectDeath()
	{
		return this.health <= 0;
	}
	
	/*
	public static int getOnTheBoard() {	
		return Vampire.vampireOnTheBoard;
	}

	/*
	public static int getdeadObjects() {		
		return Vampire.deadVampires;
	}
	*/
    
	/*	
	public static void setOnTheBoard(int objectOnTheBoard) {
		Vampire.setOnTheBoard(objectOnTheBoard);
	}	
	*/
	public boolean reachEnd() {
		return false;
	}
	/*
	public static void restartObjectInfo()	{
		Dracula.DraculaisAlive = false;
		setOnTheBoard(0);
		Vampire.setDeadVampires(0);
	}
	*/
}