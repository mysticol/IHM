package dice;

public enum DiceType {

	D2,	D3,	D4,D6,D8,D10,D12,D20,D30,D100;
	
	
	public int getMaxForDiceType( ){
		
		
		return Integer.parseInt(this.name().substring(1));
		
	}
	
}
