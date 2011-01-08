package parseur;

import java.io.File;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import bean.Caracteristique;
import bean.Categorie;
import bean.Competence;
import bean.Fiche;
import bean.Info;
import bean.Modele;
import bean.vie.Case;
import bean.vie.Classic;
import bean.vie.Ligne;
import bean.vie.Vie;


import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

public class ParseurModele {

	public Fiche parseToEmptyFiche(File f) {

		Document document;
		Element racine;
		Fiche fiche = new Fiche();
		
		SAXBuilder sxb = new SAXBuilder();
		try {
			document = sxb.build(f);
			racine = document.getRootElement();

			Element jeu=racine.getChild("jeu");
			fiche.setSystem(jeu.getChildText("systeme"));
			fiche.setUnivers(jeu.getChildText("univers"));
			
			
			if (racine.getChildText("vie")!=null){
			LinkedList<Case> hp = new LinkedList<Case>();
			
			for (int i=0; i < Integer.parseInt(racine.getChildText("vie")); i++){
				hp.add(new Case());
				
			}
			fiche.setBarreDeVie(new Ligne(hp));
			
			}else{
				Vie hp =new Classic(0,0);
				fiche.setBarreDeVie(hp);
			}
			
			
			
			HashMap<String , String> infos= new HashMap<String, String>();
			Element personnage=racine.getChild("personnage");
			for ( Element info:(List<Element> )personnage.getChildren("info")){
				Info infoTemp= parseInfo(info);
				infos.put(infoTemp.getNom(), infoTemp.getValue());
			}
			fiche.setInfos(infos);
			
			HashMap<String, Caracteristique> mapCaractPrincipale= new HashMap<String, Caracteristique>();
			Element caractPrincipale= racine.getChild("caracteristiquesPrincipales");
			for (Element caractPs :(List<Element> )caractPrincipale.getChildren("caracteristique") ){
				Caracteristique caractTemp= parseCaracteristique(caractPs);
				mapCaractPrincipale.put(caractTemp.getNom(), caractTemp);
			}
			fiche.setCaracteristiquesPrincipales(mapCaractPrincipale);
			
			
			
			HashMap<String, Caracteristique> mapCaractSecondaire= new HashMap<String, Caracteristique>();
			Element caractSecondaire= racine.getChild("caracteristiquesSecondaire");
			for (Element caractPs :(List<Element> )caractSecondaire.getChildren("caracteristique") ){
				Caracteristique caractTemp= parseCaracteristique(caractPs);
				mapCaractSecondaire.put(caractTemp.getNom(), caractTemp);
			}
			fiche.setCaracteristiquesSecondaire(mapCaractSecondaire);
			
			
			
			HashMap<Categorie, LinkedList<Competence>> catComp= new HashMap<Categorie, LinkedList<Competence>>();
			Element competences= racine.getChild("competences");
			for (Element cat :(List<Element> )competences.getChildren("categorie") ){
				LinkedList<Competence> comps= new LinkedList<Competence>();
				
				Categorie myCat= new Categorie();
				myCat.setNom(cat.getChildText("nom"));
				
				for (Element compte : (List<Element> )cat.getChildren("competence")){
					comps.add(parseCompetence(compte));
					
				}
				
				catComp.put(myCat, comps);
			}
			fiche.setCompetences(catComp);
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	

		return fiche;
	}

	
	public Modele parse(File f){
		Modele model= new Modele();
		Document document;
		Element racine;
		SAXBuilder sxb = new SAXBuilder();
		
		try {
			document = sxb.build(f);
			racine = document.getRootElement();

			model.setVie(Integer.parseInt(racine.getChildText("vie")));
			
			
			Element jeu=racine.getChild("jeu");
			model.setSystem(jeu.getChildText("systeme"));
			model.setUnivers(jeu.getChildText("univers"));
			
			
			LinkedList<Info> infos= new LinkedList<Info>();
			Element personnage=racine.getChild("personnage");
			for ( Element info:(List<Element> )personnage.getChildren("info")){
				Info infoTemp= parseInfo(info);
				infos.add(infoTemp);
			}
			model.setInfos(infos);
			
			LinkedList<Caracteristique> mapCaractPrincipale= new LinkedList<Caracteristique>();
			Element caractPrincipale= racine.getChild("caracteristiquesPrincipales");
			for (Element caractPs :(List<Element> )caractPrincipale.getChildren("caracteristique") ){
				Caracteristique caractTemp= parseCaracteristique(caractPs);
				mapCaractPrincipale.add( caractTemp);
			}
			model.setCaractPrincipales(mapCaractPrincipale);
			
			
			
			LinkedList<Caracteristique> mapCaractSecondaire= new LinkedList<Caracteristique>();
			Element caractSecondaire= racine.getChild("caracteristiquesSecondaire");
			for (Element caractPs :(List<Element> )caractSecondaire.getChildren("caracteristique") ){
				Caracteristique caractTemp= parseCaracteristique(caractPs);
				mapCaractSecondaire.add( caractTemp);
			}
			model.setCaractSecondaires(mapCaractSecondaire);
			
			
			
			HashMap<Categorie, LinkedList<Competence>> catComp= new HashMap<Categorie, LinkedList<Competence>>();
			Element competences= racine.getChild("competences");
			for (Element cat :(List<Element> )competences.getChildren("categorie") ){
				LinkedList<Competence> comps= new LinkedList<Competence>();
				
				Categorie myCat= new Categorie();
				myCat.setNom(cat.getChildText("nom"));
				
				for (Element compte : (List<Element> )cat.getChildren("competence")){
					comps.add(parseCompetence(compte));
					
				}
				
				catComp.put(myCat, comps);
			}
			model.setCompetences(catComp);
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		
		
		
		
		
		return model;
	}
	
	
	public Info parseInfo(Element e) {
		Info result = new Info();
		result.setNom(e.getText());
		result.setValue(null);

		return result;
	}

	
	public Caracteristique parseCaracteristique (Element e){
		Caracteristique caract= new Caracteristique();
		
		caract.setNom(e.getChildText("nom"));
		caract.setValeur(null);
		
		String jauge =e.getChildText("jauge");
		if (jauge!=null){
			caract.setJauge(Boolean.parseBoolean(jauge));
		}
		
		String max= e.getChildText("maximum");
		if (max!=null){
			caract.setMaximum(Integer.parseInt(max));
		}
		
		
		String consommable =e.getChildText("consommable");
		if (consommable!=null){
			caract.setConsommable(Boolean.parseBoolean(consommable));
		}
		
		return caract;
	}
	
	
	public Competence parseCompetence(Element e){
		Competence compt = new Competence();
		compt.setNom(e.getText());
		compt.setValeur(null);
		
		
		return compt;
	}
}
