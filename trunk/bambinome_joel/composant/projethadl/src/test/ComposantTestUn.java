package test;

import hadl.Composant;

public class ComposantTestUn extends Composant{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2057496817509533929L;

	public ComposantTestUn() {
		super("ComposantUn");
		// TODO Auto-generated constructor stub
	}


	
	public String message(String st){
		System.out.println("passage par le composant :" + this.getName());
		return "Bonjour "+st;
		
	}

}
