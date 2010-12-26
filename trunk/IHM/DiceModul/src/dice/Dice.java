package dice;

import java.util.LinkedList;

public class Dice {

	
	
	public static LinkedList<Integer> roll( int nbDice, DiceType dt){
		LinkedList<Integer> listresult= new LinkedList<Integer>();
		
		
		int lower = 1;
		int higher = dt.getMaxForDiceType();
		
		for (int i=0; i<nbDice; i++){
			
			listresult.add((int)(Math.random() * (higher+1-lower)) + lower);
		}

		
		
		
		
		
		return listresult;
	}
	
	
	
}
