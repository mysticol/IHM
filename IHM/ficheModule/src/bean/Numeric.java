package bean;

public abstract class Numeric {

	private String nom;
	private Integer valeur;
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public Integer getValeur() {
		return valeur;
	}
	public void setValeur(Integer valeur) {
		this.valeur = valeur;
	}
	public Numeric(String nom, Integer valeur) {
		this.nom = nom;
		this.valeur = valeur;
	}
	public Numeric() {

	}
	
	
	
	
}
