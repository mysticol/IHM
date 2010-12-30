package dice;

public class maintest {

	public static void main(String[] args) {

		DiceType dsix = DiceType.stringToDiceType("d100");

		System.out.println(dsix);
		
		System.out.println(dsix.getMaxForDiceType());

		System.out.println(Dice.roll(10, dsix));
		
		System.out.println(Dice.rollExplosive(10, dsix));
		
		System.out.println(Dice.rollExplosiveWithOption(10, dsix, 2 , 4));
	}
	
}
