package org.ucm.tp1.model;

import org.ucm.tp1.model.exceptions.CommandExecuteException;
import org.ucm.tp1.model.exceptions.DraculaIsAliveException;
import org.ucm.tp1.model.exceptions.InvalidPositionException;
import org.ucm.tp1.model.exceptions.NoMoreVampiresException;
import org.ucm.tp1.model.exceptions.NotEnoughCoinsException;
import org.ucm.tp1.model.objects.BloodBank;
import org.ucm.tp1.model.objects.Dracula;
import org.ucm.tp1.model.objects.ExplosiveVampire;
import org.ucm.tp1.model.objects.IAttack;
import org.ucm.tp1.model.objects.Player;
import org.ucm.tp1.model.objects.Slayer;
import org.ucm.tp1.model.objects.Vampire;
import org.ucm.tp1.view.GamePrinter;
import org.ucm.tp1.view.IPrintable;

import java.util.Random;

public class Game implements IPrintable {
	
	public static final String notEnoughCoinsMsg = String.format("[ERROR]: Defender cost is 50: Not enough coins");
	public static final String notEnoughCoinsGarlic = String.format("[ERROR]: Defender cost is 10: Not enough coins");
	public static final String notEnoughCoinsLight = String.format("[ERROR]: Light Flash cost is 50: Not enough coins");
	public static final String failedAddSlayer = String.format("[ERROR]: Failed to add slayer");
	public static final String failedAddBank = String.format("[ERROR]: Failed to add blood bank");
	public static final String failedAddLightFlash = String.format("[ERROR]: Failed to light flash");
	public static final String failedAddGarlicPush = String.format("[ERROR]: Failed to garlic push");
	public static final String failedAddVampire = String.format("[ERROR]: Failed to add this vampire");	
	public static final String noMoreVampires = String.format("[ERROR]: No more remaining vampires left");
	public static final String draculaAlreadyAlive = String.format("[ERROR]: Dracula is already on board");
	public static final String invalidPositionMsg = String.format("[ERROR]: position");
	
	public static final String numberCycles = String.format("Number of cycles: ");
	public static final String numberCoins = String.format("Coins: ");
	public static final String vampiresRemaining = String.format("Remaining vampires: ");
	public static final String vampiresOnBoard = String.format("Vampires on the board: ");
	public static final String resetMsg = String.format("Reset succesful");	
	public static final String draculaAlive = String.format("Dracula is alive");
	
	public static final String vampireWins = String.format("[GAME OVER] Vampires win!");
	public static final String playerWins = String.format("[GAME OVER] Player wins");
	public static final String exitMsg = String.format("[GAME OVER] Nobody wins...");	
	
	public static final int valueSuperCoins = 1000;
	public static final int coinsGame = 50;
	public static final int coinsGarlic = 10;
	private Level level;
	private Player player;
	private int numCycles = 0;
	private GameObjectBoard gameObjectBoard;
	private Random rand;
	private boolean userExit;
	private GamePrinter printer;
	
	public Game(long seed, Level level)	{
		rand = new Random(seed);
		this.level = level;
		player = new Player(rand);
		gameObjectBoard = new GameObjectBoard(this, this.rand, this.level);
		printer = new GamePrinter(this, level.getNumColumns(), level.getNumRows());
		userExit = false;
	}
	
	public void doExit() {
		userExit = true;
	}
	
	public boolean reset() {
		numCycles = 0;
		player.restartCoins();
		gameObjectBoard.reset();
		Vampire.initialInfo();
		return true;
	}
		
	public String getGameOverMsg() {
		if(userExit)
			return exitMsg;
		return null;
	}
	
	/*
	public int getRemainingObjects() {
		return gameObjectBoard.getRemainingObjects();
	}
	*/
	
	/*
	public int getNumObjects() {
		return gameObjectBoard.getNumObjects();
	}
	*/

	public void setCoins(int coins) {
		this.player.addCoins(coins);
	}
	
	public int getRemainingObjects() {
		return level.getNumVampires() - Vampire.vampireOnTheBoard - Vampire.deadVampires;
	}

	
	public int getNumCols() {
		return level.getNumColumns();
	}
	
	public boolean isFinished()	{
		if(gameObjectBoard.ObjectListReachEnd()) {
			System.out.println(vampireWins);
			return true;
		}
		else if (noMoreObjects()) {
			System.out.println(playerWins);
			return true;
		}
		else if (userExit) {
			System.out.println(getGameOverMsg());
			return true;
		}
		else {
			return false;		
		}
	}
	
	public String invalidPosMsg(int posX, int posY)
	{
		return invalidPositionMsg + " (" + posX + "," + posY + "): Unvalid Position";
	}
	
	public boolean addSlayer(int posX, int posY) throws CommandExecuteException {
		String errorMsg = invalidPosMsg(posX, posY);
		try {
			if(posX >= level.getNumColumns() - 1 ||
			   posY > level.getNumRows() ||
			   posX < 0 || posY < 0)
				throw new InvalidPositionException(errorMsg);
			
			else if(!occupyGameObject(posX, posY)) {
				throw new InvalidPositionException(errorMsg);
			}
			else {		
				if (player.spendCoins(coinsGame)) {
					gameObjectBoard.addObjectBoard(new Slayer(this, posX, posY, Slayer.initialHealth));
				    updates();
				    return true;
				}
	
				else
					throw new NotEnoughCoinsException(notEnoughCoinsMsg);
			}
		}
		catch (InvalidPositionException e){
			throw new CommandExecuteException(e.getMessage() + "\n" + failedAddSlayer);
		}
		catch (NotEnoughCoinsException e2){
			throw new CommandExecuteException(e2.getMessage() + "\n" + failedAddSlayer);
		}
	}	
	
	public boolean addBloodBank(int posX, int posY, int z) throws CommandExecuteException {
		String errorMsg = invalidPosMsg(posX, posY);
		try {	
			if(posX > level.getNumColumns() - 1 ||
			   posY > level.getNumRows() - 1 ||
			   posX < 0 || posY < 0) 
				throw new InvalidPositionException(errorMsg);
			else if(!occupyGameObject(posX, posY))
				throw new InvalidPositionException(errorMsg);
			else {		
				if (player.spendCoins(z)) {
					gameObjectBoard.addObjectBoard(new BloodBank(this, posX, posY, z, BloodBank.initialHealth));
				    updates();
				    return true;
				}
				else
					throw new NotEnoughCoinsException(notEnoughCoinsMsg);
			}
		}
		catch (InvalidPositionException e){
			throw new CommandExecuteException(e.getMessage() + "\n" + failedAddSlayer);
		}
		catch (NotEnoughCoinsException e2){

			throw new CommandExecuteException(e2.getMessage() + "\n" + failedAddSlayer);
		}
	}
	
	public boolean addVampire(int posX, int posY) throws CommandExecuteException
	{
		String errorMsg = invalidPosMsg(posX, posY);
		try {
			if(posX > level.getNumColumns() - 1 || posY > level.getNumRows() - 1 ||
			   posX < 0 || posY < 0) 
				throw new InvalidPositionException(errorMsg);
			else if(!occupyGameObject(posX, posY))
				throw new InvalidPositionException(errorMsg);
			else {		
				if(getRemainingObjects() > 0) {
					gameObjectBoard.addObjectBoard(new Vampire(this, posX, posY));	
				    return true;
				}
				else {
					throw new NoMoreVampiresException(noMoreVampires);
				}
			}
		}
		catch (InvalidPositionException e){
			throw new CommandExecuteException(e.getMessage() + "\n" + failedAddVampire);
		}catch (NoMoreVampiresException e){
			throw new CommandExecuteException(e.getMessage() + "\n" + failedAddVampire);
		}
	}
	
	
	public boolean addExplosiveVampire(int posX, int posY) throws CommandExecuteException
	{
		String errorMsg = invalidPosMsg(posX, posY);
		try {
			if(posX > level.getNumColumns() - 1 || posY > level.getNumRows() - 1 ||
			   posX < 0 || posY < 0) 
				throw new InvalidPositionException(errorMsg);
			else if(!occupyGameObject(posX, posY))
				throw new InvalidPositionException(errorMsg);
			else {		
				if(getRemainingObjects() > 0) {
					gameObjectBoard.addObjectBoard(new ExplosiveVampire(this, posX, posY));	
				    return true;
				}
				else {
					throw new NoMoreVampiresException(noMoreVampires);
				}
			}
		}
		catch (InvalidPositionException e){
			throw new CommandExecuteException(e.getMessage() + "\n" + failedAddVampire);
		}catch (NoMoreVampiresException e){
			throw new CommandExecuteException(e.getMessage() + "\n" + failedAddVampire);
		}
	}
	
	public boolean addDracula(int posX, int posY) throws CommandExecuteException
	{
		String errorMsg = invalidPosMsg(posX, posY);
		try {
			if(posX > level.getNumColumns() - 1 || posY > level.getNumRows() - 1 ||
			   posX < 0 || posY < 0) 
				throw new InvalidPositionException(errorMsg);
			else if(!occupyGameObject(posX, posY))
				throw new InvalidPositionException(errorMsg);
			else {		
				if(getRemainingObjects() > 0) {
					if(!Dracula.DraculaisAlive) {
						gameObjectBoard.addObjectBoard(new Dracula(this, posX, posY));	
					    return true;
					}
					else {
						throw new DraculaIsAliveException(draculaAlreadyAlive);
					}
				}
				else {
					throw new NoMoreVampiresException(noMoreVampires);
				}
			}
		}
		catch (InvalidPositionException e){
			throw new CommandExecuteException(e.getMessage() + "\n" + failedAddVampire);
		}catch (DraculaIsAliveException e){
			throw new CommandExecuteException(e.getMessage() + "\n" + failedAddVampire);
		}catch (NoMoreVampiresException e){
			throw new CommandExecuteException(e.getMessage() + "\n" + failedAddVampire);
		}
	}
	
	public int getNumObjects()
	{
		return Vampire.vampireOnTheBoard + Vampire.deadVampires;
	}
	
	public void maybeAddObject() {
		if(getRemainingObjects() > 0) {
			if(getNumObjects() < level.getNumVampires()) {
				if(rand.nextDouble() < level.getFrecuency()) {
					int x = this.level.getNumColumns() - 1;
					int y = rand.nextInt(level.getNumRows());
					if(occupyGameObject(x, y))
						gameObjectBoard.addObjectBoard(new Vampire(this, x, y));		
				}
			}
			if(getNumObjects() < level.getNumVampires()) {
				if(!Dracula.DraculaisAlive) {
					if(rand.nextDouble() < level.getFrecuency()) {
						int x = this.level.getNumColumns() - 1;
						int y = rand.nextInt(level.getNumRows());
						if(occupyGameObject(x, y))
							gameObjectBoard.addObjectBoard(new Dracula(this, x, y));
					}
				}
			}
			if(getNumObjects() < level.getNumVampires()) {
				if(rand.nextDouble() < level.getFrecuency()) {
					int x = this.level.getNumColumns() - 1;
					int y = rand.nextInt(level.getNumRows());
					if(occupyGameObject(x, y))
						gameObjectBoard.addObjectBoard(new ExplosiveVampire(this, x, y));		
				}
			}
		}
		
	}
	
	public void updates() {
		player.obtainCoins();
		gameObjectBoard.objectAdvance();
		gameObjectBoard.attackObject();
     	maybeAddObject();		
     	if (!gameObjectBoard.ObjectListReachEnd() && !noMoreObjects()) {
     		numCycles++;
     	}  
	}
	
	public boolean noMoreObjects()
	{
		return Vampire.vampireOnTheBoard == 0 && Vampire.deadVampires == level.getNumVampires();
	}
	
	public boolean superCoins() {
		player.addCoins(valueSuperCoins);
		return true;
	}
		
	public String getSerializeInfo() {
		String info;
		info = "Cycles: " + Integer.toString(numCycles)+"\n";
		info = info + "Coins: " + Integer.toString(player.getCoins())+"\n";
		info = info + "Level: " + level.getName().toUpperCase()+"\n";
		info = info + "Remaining Vampires: " + Integer.toString(getRemainingObjects())+"\n";
		info = info + "Vampires on Board: " + Integer.toString(getNumObjects() - Vampire.deadVampires) + "\n" + "\n";	
		info = info + "Game Object List: \n";
		info = info + gameObjectBoard.serializeObjectBoard() + "\n";
		return info;
	}
	
	public boolean occupyGameObject(int posX, int posY) {	
		return gameObjectBoard.noPositionObject(posX, posY);
	}

	public IAttack getAttackableInPosition (int posX, int posY) {
		return gameObjectBoard.getAttackableInPosition(posX, posY);
	}
	
	public String toString() {
		return printer.toString();
	}

	public String getPositionToString(int x, int y) {
		IAttack aux = gameObjectBoard.getAttackableInPosition(x, y);
		if (aux != null) {
			return aux.toString();
		}
		return "";
	}
	
	public String getInfo() {
		String info;
		info = numberCycles + Integer.toString(numCycles)+"\n";
		info = info + numberCoins + Integer.toString(player.getCoins())+"\n";
		info = info + vampiresRemaining + Integer.toString(getRemainingObjects())+"\n";
		info = info + vampiresOnBoard + Integer.toString(getNumObjects() - Vampire.deadVampires);
		if(Dracula.DraculaisAlive) {
			info = info + "\n" + draculaAlive;
		}
		return info;
	}
	
	public boolean lightFlash() throws CommandExecuteException {
		try {
			if(player.spendCoins(coinsGame)) {
				gameObjectBoard.lightFlashBoard();
				updates();	
				return true;
			}
			else {
				throw new NotEnoughCoinsException(notEnoughCoinsLight);
			}
		}
		catch (NotEnoughCoinsException e){
			throw new CommandExecuteException(e.getMessage() + "\n" + failedAddLightFlash);
		}
	}
	
	public boolean garlicPush() throws CommandExecuteException {
		//No usamos el método update porque el garlicPush no tiene que ejecutar 
		//el avance de lo vampiros.
		try {
			if(player.spendCoins(coinsGarlic)) {
				gameObjectBoard.garlicPushBoard();
				player.obtainCoins();
				gameObjectBoard.attackObject();
		     	maybeAddObject();		
		     	if (!gameObjectBoard.ObjectListReachEnd() && !noMoreObjects()) {
		     		numCycles++;
		     	}  
			}
			else
				throw new NotEnoughCoinsException(notEnoughCoinsGarlic);
		}
		catch (NotEnoughCoinsException e){
			throw new CommandExecuteException(e.getMessage() + "\n" + failedAddGarlicPush);
		}
		return true;
	}
}