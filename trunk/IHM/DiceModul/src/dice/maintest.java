package dice;

public class maintest {

	
	public static void main(String[] args) {

		DiceType dsix= DiceType.D6;
		
		System.out.println(dsix.getMaxForDiceType());
		
		
		System.out.println(Dice.roll(10, dsix));
		
		
		
		
	}
	
}
