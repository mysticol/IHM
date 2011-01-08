package bean;

import java.io.Serializable;

public class Caracteristique extends Numeric implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1334884974546442411L;
	
	private boolean jauge=false;
	private boolean consommable=false;
	private int maximum;

	public boolean isJauge() {
		return jauge;
	}
	public void setJauge(boolean jauge) {
		this.jauge = jauge;
	}
	public boolean isConsommable() {
		return consommable;
	}
	public void setConsommable(boolean consommable) {
		this.consommable = consommable;
	}
	public Caracteristique(String nom, Integer valeur, boolean jauge,
			boolean consommable) {
		super (nom, valeur);

		this.jauge = jauge;
		this.consommable = consommable;
	}
	public Caracteristique(String nom, Integer valeur) {
		super (nom, valeur);
	}
	public Caracteristique() {
	
	}
	public int getMaximum() {
		return maximum;
	}
	public void setMaximum(int maximum) {
		this.maximum = maximum;
	}
	
}
