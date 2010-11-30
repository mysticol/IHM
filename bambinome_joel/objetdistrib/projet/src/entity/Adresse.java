package entity;

public class Adresse {
	
	private String ville;
	private String codepostal;
	private String rue;
	private String pays;
	public String getVille() {
		return ville;
	}
	public void setVille(String ville) {
		this.ville = ville;
	}
	public String getCodepostal() {
		return codepostal;
	}
	public void setCodepostal(String codepostal) {
		this.codepostal = codepostal;
	}
	public String getRue() {
		return rue;
	}
	public void setRue(String rue) {
		this.rue = rue;
	}
	public String getPays() {
		return pays;
	}
	public void setPays(String pays) {
		this.pays = pays;
	}
	public Adresse(String ville, String codepostal, String rue, String pays) {
		this.ville = ville;
		this.codepostal = codepostal;
		this.rue = rue;
		this.pays = pays;
	}
	public Adresse() {

	}
	

}
