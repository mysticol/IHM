package bean;

public class Competence {

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
	public void setValue(String val) {
		this.value = val;
	}
	public Competence(String nom, String val) {
		this.nom = nom;
		this.value = val;
	}
	public Competence() {
	
	}
	
}
