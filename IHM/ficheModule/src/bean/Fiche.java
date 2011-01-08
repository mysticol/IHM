package bean;

import java.io.File;
import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedList;

import bean.vie.Vie;

public class Fiche implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3495020224233774946L;
	
	private String nom;
	private String poid;
	private String taille;
	private String age;
	private String concept;
	private String xp;
	private String system;
	private String univers;
	private String campagne;
	private HashMap<String, String> infos;
	private HashMap<String, Caracteristique> caracteristiquesPrincipales;
	private HashMap<String, Caracteristique> caracteristiquesSecondaire;
	private HashMap<Categorie, LinkedList<Competence>> competences;
	private LinkedList<Pouvoir> pouvoirs;
	
	private LinkedList<Equipement> equipements;
	private Vie barreDeVie;
	private String divers;
	
	
	
	



	




	public Fiche(String nom, String poid, String taille, String age,
			String concept, String xp, String system, String univers,
			String campagne, HashMap<String, String> infos,
			HashMap<String, Caracteristique> caracteristiquesPrincipales,
			HashMap<String, Caracteristique> caracteristiquesSecondaire,
			HashMap<Categorie, LinkedList<Competence>> competences,
			LinkedList<Pouvoir> pouvoirs, LinkedList<Equipement> equipements,
			Vie barreDeVie, String divers) {
		this.nom = nom;
		this.poid = poid;
		this.taille = taille;
		this.age = age;
		this.concept = concept;
		this.xp = xp;
		this.system = system;
		this.univers = univers;
		this.campagne = campagne;
		this.infos = infos;
		this.caracteristiquesPrincipales = caracteristiquesPrincipales;
		this.caracteristiquesSecondaire = caracteristiquesSecondaire;
		this.competences = competences;
		this.pouvoirs = pouvoirs;
		this.equipements = equipements;
		this.barreDeVie = barreDeVie;
		this.divers = divers;
	}



	public Fiche(String nom, String poid, String taille, String age,
			String concept, String xp, String system, String univers,
			String campagne, String divers) {
		this.nom = nom;
		this.poid = poid;
		this.taille = taille;
		this.age = age;
		this.concept = concept;
		this.xp = xp;
		this.system = system;
		this.univers = univers;
		this.campagne = campagne;
		this.divers = divers;
	}



	public String getSystem() {
		return system;
	}



	public void setSystem(String system) {
		this.system = system;
	}



	public String getUnivers() {
		return univers;
	}



	public void setUnivers(String univers) {
		this.univers = univers;
	}



	public String getCampagne() {
		return campagne;
	}



	public void setCampagne(String campagne) {
		this.campagne = campagne;
	}



	public String getNom() {
		return nom;
	}



	public void setNom(String nom) {
		this.nom = nom;
	}



	public String getPoid() {
		return poid;
	}



	public void setPoid(String poid) {
		this.poid = poid;
	}



	public String getTaille() {
		return taille;
	}



	public void setTaille(String taille) {
		this.taille = taille;
	}



	public String getAge() {
		return age;
	}



	public void setAge(String age) {
		this.age = age;
	}



	public String getConcept() {
		return concept;
	}



	public void setConcept(String concept) {
		this.concept = concept;
	}



	public String getXp() {
		return xp;
	}



	public void setXp(String xp) {
		this.xp = xp;
	}



	public HashMap<String, String> getInfos() {
		return infos;
	}



	public void setInfos(HashMap<String, String> infos) {
		this.infos = infos;
	}



	public HashMap<String, Caracteristique> getCaracteristiquesPrincipales() {
		return caracteristiquesPrincipales;
	}



	public void setCaracteristiquesPrincipales(
			HashMap<String, Caracteristique> caracteristiquesPrincipales) {
		this.caracteristiquesPrincipales = caracteristiquesPrincipales;
	}



	public HashMap<String, Caracteristique> getCaracteristiquesSecondaire() {
		return caracteristiquesSecondaire;
	}



	public void setCaracteristiquesSecondaire(
			HashMap<String, Caracteristique> caracteristiquesSecondaire) {
		this.caracteristiquesSecondaire = caracteristiquesSecondaire;
	}



	public LinkedList<Pouvoir> getPouvoirs() {
		return pouvoirs;
	}



	public void setPouvoirs(LinkedList<Pouvoir> pouvoirs) {
		this.pouvoirs = pouvoirs;
	}



	public LinkedList<Equipement> getEquipements() {
		return equipements;
	}



	public void setEquipements(LinkedList<Equipement> equipements) {
		this.equipements = equipements;
	}



	public Vie getBarreDeVie() {
		return barreDeVie;
	}



	public void setBarreDeVie(Vie barreDeVie) {
		this.barreDeVie = barreDeVie;
	}



	public String getDivers() {
		return divers;
	}



	public void setDivers(String divers) {
		this.divers = divers;
	}



	public Fiche() {
		
	}



	public HashMap<Categorie, LinkedList<Competence>> getCompetences() {
		return competences;
	}



	public void setCompetences(
			HashMap<Categorie, LinkedList<Competence>> competences) {
		this.competences = competences;
	}
	
	
	public LinkedList<Competence> getListCompetences(){
		LinkedList<Competence> compts= new LinkedList<Competence>();
		
		for (LinkedList<Competence> cc: this.competences.values()){
			compts.addAll(cc);
		}
		
		
		return compts;
		
	}
	
	
	public LinkedList<Caracteristique> getJauges (){
		LinkedList<Caracteristique> jauges= new LinkedList<Caracteristique>();
		
		for( Caracteristique cc: this.caracteristiquesPrincipales.values()){
			if (cc.isJauge()){
				jauges.add(cc);
			}
		}
		for( Caracteristique cc: this.caracteristiquesSecondaire.values()){
			if (cc.isJauge()){
				jauges.add(cc);
			}
		}
		
		
		return jauges;
	}
}
