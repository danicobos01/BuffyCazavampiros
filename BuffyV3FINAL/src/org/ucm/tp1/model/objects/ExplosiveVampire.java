package org.ucm.tp1.model.objects;

import org.ucm.tp1.model.Game;

public class ExplosiveVampire extends Vampire {
	private Game game;
	
	public ExplosiveVampire(Game game, int posX, int posY) {
		super(game, posX, posY);
		this.game = game;
		setPosX(posX);
		setPosY(posY);	
	}
	
	public String typePrinter() {
		return "EV";
	}
	
	public void deadObject() {
		IAttack other = null;
		setOnTheBoard(Vampire.vampireOnTheBoard - 1);
		setDeadVampires(Vampire.deadVampires + 1);
	
		if (getPosX() < game.getNumCols()) {
			for(int i = getPosX() - 1; i <= getPosX() + 1; i++) {
				for(int j = getPosY() - 1; j <= getPosY() + 1; j++) {
					other = game.getAttackableInPosition(i, j);		
					if(other != null && (getPosX() != i || getPosY() != j) && other.receiveSlayerAttack(0)) {
						other.receiveSlayerAttack(1);
					}
				}
			}		
		}

	}
}