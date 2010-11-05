package hadl;

import java.io.Serializable;
import java.util.Observable;

/*
 * Object g�n�rique de notre hadl
 * tous les �l�ments des composants h�rite de la classe
 */
public abstract class ObjectHadl extends Observable implements Serializable{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	
	public final String getName() {
		return name;
	}



	public final void setName(String name) {
		this.name = name;
	}



	public ObjectHadl(String name) {
		super();
		this.name = name;
	}
	
	
	
	
}
