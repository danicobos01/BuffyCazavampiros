package org.ucm.tp1.model.lists;

import java.util.ArrayList;
import org.ucm.tp1.model.Game;
import org.ucm.tp1.model.Level;
import org.ucm.tp1.model.objects.GameObject;
import org.ucm.tp1.model.objects.IAttack;


public class GameObjectList {	
	ArrayList<GameObject> objectList;
	
	public GameObjectList(Game game, Level level) {
		objectList = new ArrayList<GameObject>();
	}
	
	public void addObjectList(GameObject gameObject) {
		objectList.add(gameObject);
	}
	
	public IAttack getAttackableInPosition (int posX, int posY) {
		// for (int i = 0; i < objectList.size(); i++) {
		for (GameObject obj: objectList) {
			if(obj.inPosition(posX, posY)) {
				return obj;
			}
		}
		return null;
	}

	public void attackObject() {
		for (GameObject obj : objectList) {
			if(!obj.objectDeath()) {
	 			obj.attack();		
			}
		}
		removeDeadObjects();
	}
	
	public String serializeObjectList() {
		String info = "";
		for (GameObject obj: objectList) {
			info = info + obj.serializeObject() + "\n";
		}
		return info;
	}
	
	public boolean ObjectListReachEnd() {
		for (GameObject obj: objectList)	{
			if (obj.reachEnd()) {
				return true;
			}
		}
		return false;
	}
	
	public void objectListAdvance() {
		for(GameObject obj: objectList) 
			obj.objectAdvance();	
	}
	
	public boolean noPositionObjectList(int posX, int posY) {
		for (GameObject o: objectList) {
			if (o.inPosition(posX, posY)) {
				return false;
			}
		}
		return true;
	}
	/*
	public boolean noMoreObjects() {
		return (getNumObjects() == 0 && getRemainingObjects() == 0);
	}
	
	/*
	public int getRemainingObjects() {
		return level.getNumVampires() - Vampire.vampireOnTheBoard - Vampire.deadVampires;
	}
	*/
	
	/*
	public int getNumObjects() {
		return GameObject.getOnTheBoard();
	}
	*/
	
	public void removeDeadObjects() {
		for(int i = 0; i < objectList.size(); i++) {
			if(objectList.get(i).objectDeath()) {
				objectList.remove(i);
			}
		}
	}
	
	public void reset() {
		objectList.clear();
		// GameObject.restartObjectInfo();
	}	
	
	public void lightFlashList() {
		for (int i = objectList.size() - 1; i >= 0; i--) {
			objectList.get(i).receiveLightFlash();
		}
		removeDeadObjects();
	}
	
	public void garlicPushList() {
		for(GameObject o: objectList) {
			o.receiveGarlicPush();
		}
		removeDeadObjects();
	}
}