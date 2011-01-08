package parseur;

import java.io.File;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

import bean.Caracteristique;
import bean.Categorie;
import bean.Competence;
import bean.Equipement;
import bean.Fiche;
import bean.Info;
import bean.Pouvoir;
import bean.vie.Case;
import bean.vie.Classic;
import bean.vie.Ligne;
import bean.vie.Vie;

public class ParseurFiche {

	public Fiche parse(File f) {
		Document document;
		Element racine;
		Fiche fiche = new Fiche();
		SAXBuilder sxb = new SAXBuilder();

		try {
			document = sxb.build(f);
			racine = document.getRootElement();

			// parsejeu
			Element jeu = racine.getChild("jeu");
			fiche.setSystem(jeu.getChildText("systeme"));
			fiche.setUnivers(jeu.getChildText("univers"));
			fiche.setCampagne(jeu.getChildText("campagne"));

			// parse les infos
			HashMap<String, String> infos = new HashMap<String, String>();
			Element personnage = racine.getChild("personnage");
			for (Element info : (List<Element>) personnage.getChildren("info")) {
				Info infoTemp = parseInfo(info);
				infos.put(infoTemp.getNom(), infoTemp.getValue());
			}
			fiche.setInfos(infos);

			// parse info générique
			fiche.setAge(personnage.getChildText("age"));
			fiche.setConcept(personnage.getChildText("concept"));
			fiche.setNom(personnage.getChildText("nom"));
			fiche.setPoid(personnage.getChildText("poid"));
			fiche.setTaille(personnage.getChildText("taille"));
			fiche.setXp(personnage.getChildText("xp"));

			// parse caract primaire
			HashMap<String, Caracteristique> mapCaractPrincipale = new HashMap<String, Caracteristique>();
			Element caractPrincipale = racine
					.getChild("caracteristiquesPrincipales");
			for (Element caractPs : (List<Element>) caractPrincipale
					.getChildren("caracteristique")) {
				Caracteristique caractTemp = parseCaracteristique(caractPs);
				mapCaractPrincipale.put(caractTemp.getNom(), caractTemp);
			}
			fiche.setCaracteristiquesPrincipales(mapCaractPrincipale);

			// parse caract secondaire
			HashMap<String, Caracteristique> mapCaractSecondaire = new HashMap<String, Caracteristique>();
			Element caractSecondaire = racine
					.getChild("caracteristiquesSecondaire");
			for (Element caractPs : (List<Element>) caractSecondaire
					.getChildren("caracteristique")) {
				Caracteristique caractTemp = parseCaracteristique(caractPs);
				mapCaractSecondaire.put(caractTemp.getNom(), caractTemp);
			}
			fiche.setCaracteristiquesSecondaire(mapCaractSecondaire);

			// parse les catégorie et compétences
			HashMap<Categorie, LinkedList<Competence>> catComp = new HashMap<Categorie, LinkedList<Competence>>();
			Element competences = racine.getChild("competences");
			for (Element cat : (List<Element>) competences
					.getChildren("categorie")) {
				LinkedList<Competence> comps = new LinkedList<Competence>();

				Categorie myCat = new Categorie();
				myCat.setNom(cat.getChildText("nom"));

				for (Element compte : (List<Element>) cat
						.getChildren("competence")) {
					comps.add(parseCompetence(compte));

				}

				catComp.put(myCat, comps);
			}
			fiche.setCompetences(catComp);

			// parse le pouvoir
			LinkedList<Pouvoir> listPower = new LinkedList<Pouvoir>();
			Element pouv = racine.getChild("pouvoirs");
			for (Element pover : (List<Element>) pouv.getChildren("pouvoir")) {
				listPower.add(parsePouvoir(pover));
			}
			fiche.setPouvoirs(listPower);

			// parse de l'équipement
			LinkedList<Equipement> listEquip = new LinkedList<Equipement>();
			Element equipe = racine.getChild("equipements");
			for (Element equip : (List<Element>) equipe
					.getChildren("equipement")) {
				listEquip.add(parseEquipement(equip));
			}
			fiche.setEquipements(listEquip);

			//

			fiche.setBarreDeVie(parseVie(racine));
			fiche.setDivers(racine.getChildText("divers"));

		} catch (Exception e) {
			e.printStackTrace();
		}

		return fiche;
	}

	private Vie parseVie(Element racine) {
		Element temphp = racine.getChild("classic");

		Vie vie = null;
		if (temphp != null) {
			vie = new Classic(Integer.parseInt(temphp.getChildText("total")),
					Integer.parseInt(temphp.getChildText("actuel")));

		} else {
temphp= racine.getChild("ligne");
			 vie = new Ligne();
			LinkedList<Case> cases= new LinkedList<Case>();
			
			for (Element casetemp:((List<Element>) temphp.getChildren("case")) ){
				
				Case tt= new Case();
				tt.setCoche(Boolean.parseBoolean(casetemp.getChildText("coche")));
				tt.setLabel(casetemp.getChildText("label"));
				
				cases.add(tt);
			}
			((Ligne)vie).setListeCase(cases);
			
			
		}


		return vie;
	}

	public Equipement parseEquipement(Element e) {
		Equipement result = new Equipement();
		result.setNom(e.getChildText("nom"));
		result.setDescription(e.getChildText("desc"));
		return result;
	}

	public Pouvoir parsePouvoir(Element e) {
		Pouvoir result = new Pouvoir();
		result.setNom(e.getChildText("nom"));
		result.setDescription(e.getChildText("desc"));
		return result;
	}

	public Info parseInfo(Element e) {
		Info result = new Info();
		result.setNom(e.getChildText("nom"));
		result.setValue(e.getChildText("valeur"));
		return result;
	}

	public Caracteristique parseCaracteristique(Element e) {
		Caracteristique caract = new Caracteristique();

		caract.setNom(e.getChildText("nom"));

		try {
			caract.setValeur(Integer.parseInt(e.getChildText("valeur")));
			
			caract.setMaximum(Integer.parseInt(e.getChildText("maximum")));
		} catch (Exception ex) {
			System.err.println("Fiche incompatible erreur sur la caract "
					+ e.getChildText("nom"));
		}
		String jauge = e.getChildText("jauge");
		if (jauge != null) {
			caract.setJauge(Boolean.parseBoolean(jauge));
		}

		
		
		
		String consommable = e.getChildText("consommable");
		if (consommable != null) {
			caract.setConsommable(Boolean.parseBoolean(consommable));
		}

		return caract;
	}

	public Competence parseCompetence(Element e) {
		Competence compt = new Competence();

		compt.setNom(e.getChildText("nom"));
		try {
			compt.setValeur(Integer.parseInt(e.getChildText("valeur")));
		} catch (Exception ex) {
			System.err.println("Fiche incompatible a la competence: "
					+ e.getChildText("nom"));
		}
		return compt;
	}

}
