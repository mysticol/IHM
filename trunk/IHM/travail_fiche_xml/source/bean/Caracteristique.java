package bean;

public class Caracteristique {

	private String nom;
	private String valeur;
	private boolean jauge=false;
	private boolean consommable=false;
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getValeur() {
		return valeur;
	}
	public void setValeur(String valeur) {
		this.valeur = valeur;
	}
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
	public Caracteristique(String nom, String valeur, boolean jauge,
			boolean consommable) {
		this.nom = nom;
		this.valeur = valeur;
		this.jauge = jauge;
		this.consommable = consommable;
	}
	public Caracteristique(String nom, String valeur) {
		this.nom = nom;
		this.valeur = valeur;
	}
	public Caracteristique() {
	
	}
	
}
