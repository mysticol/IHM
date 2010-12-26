package bean;

import java.util.HashMap;
import java.util.LinkedList;

public class Modele {

	private LinkedList<Info> infos;
	private LinkedList<Caracteristique> caractPrincipales;
	private LinkedList<Caracteristique> caractSecondaires;
	private HashMap<Categorie, LinkedList<Competence>> competences;
	public LinkedList<Info> getInfos() {
		return infos;
	}
	public void setInfos(LinkedList<Info> infos) {
		this.infos = infos;
	}
	public LinkedList<Caracteristique> getCaractPrincipales() {
		return caractPrincipales;
	}
	public void setCaractPrincipales(LinkedList<Caracteristique> caractPrincipales) {
		this.caractPrincipales = caractPrincipales;
	}
	public LinkedList<Caracteristique> getCaractSecondaires() {
		return caractSecondaires;
	}
	public void setCaractSecondaires(LinkedList<Caracteristique> caractSecondaires) {
		this.caractSecondaires = caractSecondaires;
	}
	public HashMap<Categorie, LinkedList<Competence>> getCompetences() {
		return competences;
	}
	public void setCompetences(
			HashMap<Categorie, LinkedList<Competence>> competences) {
		this.competences = competences;
	}
	public Modele(LinkedList<Info> infos,
			LinkedList<Caracteristique> caractPrincipales,
			LinkedList<Caracteristique> caractSecondaires,
			HashMap<Categorie, LinkedList<Competence>> competences) {
		this.infos = infos;
		this.caractPrincipales = caractPrincipales;
		this.caractSecondaires = caractSecondaires;
		this.competences = competences;
	}
	public Modele() {
	
	}
	
	
	
	
}
