package bean;

import java.io.Serializable;

public class Competence extends Numeric implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5580968012570348368L;
	
	public Competence(String nom, Integer valeur) {
		super (nom, valeur);
	}
	public Competence() {
	
	}
	
}
