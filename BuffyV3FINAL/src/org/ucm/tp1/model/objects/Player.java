package org.ucm.tp1.model.objects;
import java.util.Random;

public class Player {
	private int coins;
	private Random rand;
		
	private static final int initialCoins = 50;
	
	public Player(Random rand)	{
		this.rand = rand;
		this.coins = initialCoins;
	}
		
	public int getCoins()	{
		return this.coins;
	}
	
	public void addCoins(int value) {
		this.coins += value;
	}
	
	public void restartCoins()	{
		this.coins = initialCoins;
	}
		
	public void obtainCoins()	{		
		if(rand.nextFloat() > 0.5) {
			this.coins += 10;
		}
	}
		
	public boolean spendCoins(int value)	{
		if (this.coins >= value) {
			this.coins = this.coins - value;
			return true;
		}
		else 
			return false;
	}		
}