package parseur;

import java.io.File;
import java.io.FileOutputStream;
import org.jdom.DocType;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
import bean.Caracteristique;
import bean.Categorie;
import bean.Competence;
import bean.Equipement;
import bean.Fiche;
import bean.Modele;
import bean.Pouvoir;

public class EcriteurFiche {

	public void convertFicheToFile(Fiche fiche, String filePathName) {

		Element root = new Element("fiche");
		Document doc = new Document(root);

		doc.setDocType(new DocType("fiche", "fiche.dtd"));

		root.addContent(convertJeu(fiche));
		root.addContent(convertInfos(fiche));
		root.addContent(convertCaractPrincipales(fiche));
		root.addContent(convertCaractSecondaires(fiche));
		root.addContent(convertCompetences(fiche));
		root.addContent(convertPouvoir(fiche));
		root.addContent(convertEquipement(fiche));
		root.addContent(convertBarreDeVie(fiche));
		root.addContent(convertDivers(fiche));

		FileOutputStream fout = null;
		try {

			File fichier = new File(filePathName);
			if (!fichier.exists()) {
				fichier.createNewFile();
			}

			Format form = Format.getPrettyFormat();

			XMLOutputter outputter = new XMLOutputter(form);

			fout = new FileOutputStream(fichier);
			outputter.output(doc, fout);
			fout.close();

		} catch (Exception e) {
			e.printStackTrace();
			if (fout != null)
				try {
					fout.close();
				} catch (Exception ee) {
					ee.printStackTrace();
				}
		}
	}

	private Element convertJeu(Fiche fiche){
		Element jeu = new Element("jeu");
		
		Element univers= new Element("univers");
		univers.addContent(fiche.getUnivers());
		
		Element campagne = new Element ("campagne");
		campagne.addContent(fiche.getCampagne());
		
		
		
		Element system= new Element("system");
		system.addContent(fiche.getSystem());
		
		jeu.addContent(univers);
		jeu.addContent(campagne);
		jeu.addContent(system);
		return jeu;		
	}
	
	
	private Element convertInfos(Fiche fiche) {

		Element personnage = new Element("personnage");

		Element nom = new Element("nom");
		nom.addContent(fiche.getNom());
		personnage.addContent(nom);

		Element taille = new Element("taille");
		taille.addContent(fiche.getTaille());
		personnage.addContent(taille);

		Element poid = new Element("poid");
		poid.addContent(fiche.getPoid());
		personnage.addContent(poid);

		Element concept = new Element("taille");
		concept.addContent(fiche.getConcept());
		personnage.addContent(concept);

		Element age = new Element("age");
		age.addContent(fiche.getAge());
		personnage.addContent(age);

		Element xp = new Element("xp");
		xp.addContent(fiche.getXp());
		personnage.addContent(xp);

		for (String infos : fiche.getInfos().keySet()) {
			Element temp = new Element("info");
			Element tempNom = new Element("nom");
			tempNom.addContent(infos);

			Element tempValeur = new Element("valeur");
			tempValeur.addContent(fiche.getInfos().get(infos));

			temp.addContent(tempNom);
			temp.addContent(tempValeur);

			personnage.addContent(temp);
		}

		return personnage;
	}

	private Element convertPouvoir(Fiche fiche) {

		Element pouvoirs = new Element("pouvoirs");

		for (Pouvoir pwer : fiche.getPouvoirs()) {
			Element pouvoirTemp = new Element("pouvoir");

			Element nomTemp = new Element("nom");
			nomTemp.addContent(pwer.getNom());

			Element descTemp = new Element("desc");
			descTemp.addContent(pwer.getDescription());

			pouvoirTemp.addContent(nomTemp);
			pouvoirTemp.addContent(descTemp);

			pouvoirs.addContent(pouvoirTemp);
		}

		return pouvoirs;
	}

	private Element convertEquipement(Fiche fiche) {
		Element equipements = new Element("equipements");

		for (Equipement equip : fiche.getEquipements()) {
			Element equipementTemp = new Element("equipement");

			Element nomTemp = new Element("nom");
			nomTemp.addContent(equip.getNom());

			Element descTemp = new Element("desc");
			descTemp.addContent(equip.getDescription());

			equipementTemp.addContent(nomTemp);
			equipementTemp.addContent(descTemp);

			equipements.addContent(equipementTemp);
		}

		return equipements;
	}

	private Element convertCaractPrincipales(Fiche fiche) {

		Element caracteristiquesPrincipales = new Element(
				"caracteristiquesPrincipales");

		for (String nomCar : fiche.getCaracteristiquesPrincipales().keySet()) {
			Element caracTemp = new Element("caracteristique");

			Caracteristique caracte = fiche.getCaracteristiquesPrincipales()
					.get(nomCar);

			Element tempNom = new Element("nom");
			tempNom.addContent(caracte.getNom());

			Element tempValeur = new Element("valeur");
			tempValeur.addContent(caracte.getValeur().toString());

			Element tempJauge = new Element("jauge");
			tempJauge.addContent(String.valueOf(caracte.isJauge()));

			Element tempConsommable = new Element("consommable");
			tempConsommable.addContent(String.valueOf(caracte.isConsommable()));

			caracTemp.addContent(tempNom);
			caracTemp.addContent(tempValeur);
			caracTemp.addContent(tempJauge);
			caracTemp.addContent(tempConsommable);

			caracteristiquesPrincipales.addContent(caracTemp);

		}

		return caracteristiquesPrincipales;
	}

	private Element convertCaractSecondaires(Fiche fiche) {
		Element caracteristiquesSecondaires = new Element(
				"caracteristiquesSecondaire");

		for (String nomCar : fiche.getCaracteristiquesSecondaire().keySet()) {
			Element caracTemp = new Element("caracteristique");

			Caracteristique caracte = fiche.getCaracteristiquesSecondaire()
					.get(nomCar);

			Element tempNom = new Element("nom");
			tempNom.addContent(caracte.getNom());

			Element tempValeur = new Element("valeur");
			tempValeur.addContent(caracte.getValeur().toString());

			Element tempJauge = new Element("jauge");
			tempJauge.addContent(String.valueOf(caracte.isJauge()));

			Element tempConsommable = new Element("consommable");
			tempConsommable.addContent(String.valueOf(caracte.isConsommable()));

			caracTemp.addContent(tempNom);
			caracTemp.addContent(tempValeur);
			caracTemp.addContent(tempJauge);
			caracTemp.addContent(tempConsommable);

			caracteristiquesSecondaires.addContent(caracTemp);

		}
		return caracteristiquesSecondaires;

	}

	private Element convertCompetences(Fiche fiche) {

		Element competences = new Element("competences");

		for (Categorie catTemp : fiche.getCompetences().keySet()) {
			Element elCatTemp = new Element("categorie");

			Element nomCatTemp = new Element("nom");
			nomCatTemp.addContent(catTemp.getNom());

			elCatTemp.addContent(nomCatTemp);

			for (Competence compt : fiche.getCompetences().get(catTemp)) {
				Element compTempEl = new Element("competence");

				Element elNom = new Element("nom");
				elNom.addContent(compt.getNom());

				Element elValeur = new Element("valeur");
				elValeur.addContent(compt.getValeur().toString());

				compTempEl.addContent(elNom);
				compTempEl.addContent(elValeur);

				competences.addContent(compTempEl);
			}

			competences.addContent(elCatTemp);
		}
		/*
		 * <!ELEMENT competences (categorie+)> <!ELEMENT competence (nom,
		 * valeur)> <!ELEMENT categorie (nom, competence+)>
		 */

		return competences;
	}

	private Element convertDivers(Fiche fiche) {
		Element div = new Element("divers");
		div.addContent(fiche.getDivers());
		return div;
	}

	private Element convertBarreDeVie(Fiche fiche) {

		Element hp = new Element("barreDeVie");
		hp.addContent(fiche.getBarreDeVie());

		return hp;

	}

	/* http://www.commentcamarche.net/forum/affich-1452566-creer-un-fichier-xml-avec-java
	static public void write(org.jdom.Document doc,
			Object outWriterOrOutputOrFile, String outputEncoding) {
		FileOutputStream fout = null;
		try {
			Format form = Format.getPrettyFormat();
			// if provided, force the output encoding (otherwise, it defaults to
			// "UTF8")
			if (null != outputEncoding) {
				form.setEncoding(outputEncoding);
			}
			XMLOutputter outputter = new XMLOutputter(form);
			if (outWriterOrOutputOrFile instanceof OutputStream)
				outputter.output(doc, (OutputStream) outWriterOrOutputOrFile);
			else if (outWriterOrOutputOrFile instanceof Writer)
				outputter.output(doc, (Writer) outWriterOrOutputOrFile);
			else if (outWriterOrOutputOrFile instanceof File) {
				File f = (File) outWriterOrOutputOrFile;
				fout = new FileOutputStream(f);
				outputter.output(doc, fout);
				fout.close();
			} else {

			}
		} catch (Exception e) {

			e.printStackTrace();
			if (fout != null)
				try {
					fout.close();
				} catch (Exception ee) {
				}
		}
	}
*/
}
