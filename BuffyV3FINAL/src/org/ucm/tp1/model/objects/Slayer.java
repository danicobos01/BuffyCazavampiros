package org.ucm.tp1.model.objects;
import org.ucm.tp1.model.Game;

public class Slayer extends GameObject {	
		public static final int initialHealth = 3;
		private Game game;
		
		public Slayer(Game game, int posX, int posY, int health) {
            super(game, posX, posY, health);
    		setPosX(posX);
    		setPosY(posY);
    		this.game = game;
		}
		
		public String typePrinter() {
			return "S";
		}
		
		public void attack() {
			int i = getPosX() + 1;
			IAttack other = null;
			boolean finish = false;
			while (i < game.getNumCols() && finish == false) {
				other = game.getAttackableInPosition(i, getPosY());	
				if(other != null && other.receiveSlayerAttack(0)) {
					other.receiveSlayerAttack(1);	
					finish = true;
				}
				i++;
			}
		}
		
		public boolean receiveVampireAttack(int damage) {
			super.getsBitten(damage);
			return true;
		}
		
		public boolean receiveDraculaAttack() {
			super.getsBitten(initialHealth);
			return true;
		}
		
		public void deadObject() {}
		public void objectAdvance() {}
		public boolean receiveLightFlash() {return false;}
		public boolean receiveGarlicPush() {return false;}
		public boolean receiveSlayerAttack(int damage) {return false;}		
}