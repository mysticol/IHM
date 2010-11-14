package hadl.m2.composant;

public abstract class Composant extends IComposant {
	
	// attribut
	protected String nom;
	
	protected String contraintes;
	protected String proprietes;

	/* constructeur */
	public Composant(String contraintes, String proprietes,
			String nom) {
		super();
		this.contraintes = contraintes;
		this.proprietes = proprietes;

		this.nom = nom;
	}
	
	public Composant() {
		this.nom = this.getClass().getName();
	}
	// -------------------------------
	
	// getters and setters
	
	public String getContraintes() {
		return contraintes;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public void setContraintes(String contraintes) {
		this.contraintes = contraintes;
	}
	public String getProprietes() {
		return proprietes;
	}
	public void setProprietes(String proprietes) {
		this.proprietes = proprietes;
	}
	

}
