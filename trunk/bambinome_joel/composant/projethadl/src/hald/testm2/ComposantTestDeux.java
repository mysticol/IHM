package hald.testm2;

import hadl.Composant;

public class ComposantTestDeux extends Composant{
	private static final long serialVersionUID = -2057496817509533929L;
	
	public ComposantTestDeux() {
		super("ComposantDeux");
	
	}


	public String chainage(String st){
		System.out.println("passage par le composant :" + this.getName());
		return st+" !Tu es mauvais";
		
	}
	
}
