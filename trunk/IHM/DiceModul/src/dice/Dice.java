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
	
	/**
	 * Les d�s sont relanc� temps qu'il font la valeur maximal possible, il explose
	 * @param nbDice
	 * @param dt
	 * @return
	 */
	public static LinkedList<Integer> rollExplosive( int nbDice, DiceType dt){
		
		LinkedList<Integer> listresult= new LinkedList<Integer>();
		
		int lower = 1;
		int higher = dt.getMaxForDiceType();
		
		
		for (int i=0; i<nbDice; i++){
			do{
				listresult.add((int)(Math.random() * (higher+1-lower)) + lower);
			}while(listresult.getLast() == higher);
		}
		
		return listresult;
	}
	
	/**
	 * Les d�s explosent, mais on peut dire combien d'entre eux explose et donner une valeur minimal d'explosion
	 * @param nbDice : nombre de d�s lanc�
	 * @param dt : type de d�s
	 * @param nbDiceExplosive : nombre de d�s explosif ( ne doit pas etre superieur a nbDice )
	 * @param valMinExplosive : valeur minimal sur la laquel un d�s peut explos� (ne doit pas etre superieur au type de d�s lanc�)
	 * @return
	 */
	public static LinkedList<Integer> rollExplosiveWithOption( int nbDice, DiceType dt, int nbDiceExplosive, int valMinExplosive  ){
		
		LinkedList<Integer> listresult= new LinkedList<Integer>();
		
		int lower = 1;
		int higher = dt.getMaxForDiceType();
		
		
		for (int i=0; i<nbDiceExplosive; i++){
			do{
				listresult.add((int)(Math.random() * (higher+1-lower)) + lower);
			}while(listresult.getLast() >= valMinExplosive);
		}
		
		for (int i=nbDiceExplosive; i<nbDice; i++){
			listresult.add((int)(Math.random() * (higher+1-lower)) + lower);
		}		
		
		return listresult;
	}
	
}
