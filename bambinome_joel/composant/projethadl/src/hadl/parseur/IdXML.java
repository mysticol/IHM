package hadl.parseur;

public class IdXML {

	private String nom;
	private String nomClasse;
	public IdXML(String nom, String nomClasse) {
		this.nom = nom;
		this.nomClasse = nomClasse;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getNomClasse() {
		return nomClasse;
	}
	public void setNomClasse(String nomClasse) {
		this.nomClasse = nomClasse;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nom == null) ? 0 : nom.hashCode());
		result = prime * result
				+ ((nomClasse == null) ? 0 : nomClasse.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		IdXML other = (IdXML) obj;
		if (nom == null) {
			if (other.nom != null)
				return false;
		} else if (!nom.equals(other.nom))
			return false;
		if (nomClasse == null) {
			if (other.nomClasse != null)
				return false;
		} else if (!nomClasse.equals(other.nomClasse))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "IdXML [nom=" + nom + ", nomClasse=" + nomClasse + "]";
	}

	
	
	
}
