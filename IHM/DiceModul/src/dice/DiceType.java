package dice;

public enum DiceType {

	D0,D2,D3,D4,D6,D8,D10,D12,D20,D30,D100;
	
	
	public int getMaxForDiceType( ){
		return Integer.parseInt(this.name().substring(1));
	}
	
	static public DiceType stringToDiceType(String s){
		DiceType d;
		
		switch(Integer.valueOf(s.substring(1))){
			case 2   : d=DiceType.D2;	break;
			case 3   : d=DiceType.D3;	break;
			case 4   : d=DiceType.D4;	break;
			case 6   : d=DiceType.D6;	break;
			case 8   : d=DiceType.D8;	break;
			case 10  : d=DiceType.D10;	break;
			case 12  : d=DiceType.D12;	break;
			case 20  : d=DiceType.D20;	break;
			case 30  : d=DiceType.D30;	break;
			case 100 : d=DiceType.D100;	break;
			default  : d=DiceType.D0;	break;
		}
		
		return d;		
	}
	
}
