package org.ucm.tp1.model;
import org.ucm.tp1.model.lists.*;
import org.ucm.tp1.model.objects.GameObject;
import org.ucm.tp1.model.objects.IAttack;
import java.util.Random;

public class GameObjectBoard {
	private GameObjectList gameObjectList;
	
	public GameObjectBoard(Game game, Random rand, Level level) {
		gameObjectList = new GameObjectList(game, level);
	}	
	
	public void addObjectBoard(GameObject gameObject) {
	  gameObjectList.addObjectList(gameObject);
    }
	
	public void attackObject() {
		gameObjectList.attackObject();
	}
	
	public String serializeObjectBoard() {
		return gameObjectList.serializeObjectList();
	}
	
	public void objectAdvance() {
		gameObjectList.objectListAdvance();
	}
	
	public boolean noPositionObject(int posX, int posY) {
		return gameObjectList.noPositionObjectList(posX, posY);
	}
	
	public boolean ObjectListReachEnd(){
		return gameObjectList.ObjectListReachEnd();		
	}
	
	/*
	public int getRemainingObjects() {
		return gameObjectList.getRemainingObjects();
	}
	*/
	/*
	public int getNumObjects() {
		return gameObjectList.getNumObjects();
	}
	/*
	public boolean noMoreObjects() {
		return gameObjectList.noMoreObjects();
	}
	*/

	public IAttack getAttackableInPosition (int posX, int posY) {
		return gameObjectList.getAttackableInPosition(posX, posY);
	}
	
	public void reset() {
		gameObjectList.reset();
	}
	
	public void lightFlashBoard() {
		gameObjectList.lightFlashList();
	}
	
	public void garlicPushBoard() {
		gameObjectList.garlicPushList();
	}
}