package bean;

import java.io.Serializable;

public class Info implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7137260640714633353L;
	
	private String nom;
	private String value;
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public Info(String nom, String value) {
		this.nom = nom;
		this.value = value;
	}
	public Info() {
		
	}
	
	
	
	
}
