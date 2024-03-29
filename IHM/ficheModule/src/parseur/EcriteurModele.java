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
import bean.Info;
import bean.Modele;


public class EcriteurModele {

	public void convertModeleToFile(Modele model, String filePathName) {

		Element root = new Element("fiche");
		Document doc = new Document(root);

		doc.setDocType(new DocType("fiche", "extension.dtd"));

		root.addContent(convertJeu(model));
		root.addContent(convertInfos(model));
		root.addContent(convertCaractPrincipales(model));
		root.addContent(convertCaractSecondaires(model));
		root.addContent(convertCompetences(model));
		if ( model.getVie() !=null){
			Element vie= new Element("vie");
			vie.addContent(model.getVie().toString());
			root.addContent(vie);
		}

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

	private Element convertJeu(Modele fiche){
		Element jeu = new Element("jeu");
		
		Element univers= new Element("univers");
		univers.addContent(fiche.getUnivers());
		
		Element system= new Element("system");
		system.addContent(fiche.getSystem());
		
		jeu.addContent(univers);
		jeu.addContent(system);
		return jeu;		
	}
	
	
	private Element convertInfos(Modele fiche) {

		Element personnage = new Element("personnage");

		for (Info infos : fiche.getInfos()) {
			Element temp = new Element("info");
			temp.addContent(infos.getNom());
			personnage.addContent(temp);
		}

		return personnage;
	}

	private Element convertCaractPrincipales(Modele fiche) {

		Element caracteristiquesPrincipales = new Element(
				"caracteristiquesPrincipales");

		for (Caracteristique car : fiche.getCaractPrincipales()) {
			Element caracTemp = new Element("caracteristique");

			Element tempNom = new Element("nom");
			tempNom.addContent(car.getNom());
			
			Element tempMax= new Element("maximum");
			tempMax.addContent(String.valueOf(car.getMaximum()));

			Element tempJauge = new Element("jauge");
			tempJauge.addContent(String.valueOf(car.isJauge()));

			Element tempConsommable = new Element("consommable");
			tempConsommable.addContent(String.valueOf(car.isConsommable()));

			caracTemp.addContent(tempNom);
			caracTemp.addContent(tempMax);
			caracTemp.addContent(tempJauge);
			caracTemp.addContent(tempConsommable);

			caracteristiquesPrincipales.addContent(caracTemp);

		}

		return caracteristiquesPrincipales;
	}

	private Element convertCaractSecondaires(Modele fiche) {
		Element caracteristiquesSecondaires = new Element(
				"caracteristiquesSecondaire");

		for (Caracteristique caracte : fiche.getCaractSecondaires()) {
			Element caracTemp = new Element("caracteristique");

			Element tempNom = new Element("nom");
			tempNom.addContent(caracte.getNom());

			Element tempJauge = new Element("jauge");
			tempJauge.addContent(String.valueOf(caracte.isJauge()));

			Element tempMax= new Element("maximum");
			tempMax.addContent(String.valueOf(caracte.getMaximum()));
			
			
			Element tempConsommable = new Element("consommable");
			tempConsommable.addContent(String.valueOf(caracte.isConsommable()));

			caracTemp.addContent(tempNom);
			caracTemp.addContent(tempMax);
			caracTemp.addContent(tempJauge);
			caracTemp.addContent(tempConsommable);

			caracteristiquesSecondaires.addContent(caracTemp);

		}
		return caracteristiquesSecondaires;

	}

	private Element convertCompetences(Modele fiche) {

		Element competences = new Element("competences");

		for (Categorie catTemp : fiche.getCompetences().keySet()) {
			Element elCatTemp = new Element("categorie");

			Element nomCatTemp = new Element("nom");
			nomCatTemp.addContent(catTemp.getNom());

			elCatTemp.addContent(nomCatTemp);

			for (Competence compt : fiche.getCompetences().get(catTemp)) {
				Element compTempEl = new Element("competence");

				compTempEl.addContent(compt.getNom());

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

}
