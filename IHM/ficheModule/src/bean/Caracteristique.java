package bean;

public class Caracteristique extends Numeric{


	private boolean jauge=false;
	private boolean consommable=false;

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
	
}
