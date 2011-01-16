package test;

import java.io.File;

import bean.Fiche;

import parseur.ParseurModele;

public class MainTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		File f = new File("/home/JulienSambre/workspace/ficheModule/ressource/modele_vampire.xml");
		
		ParseurModele pModele = new ParseurModele();
		
		Fiche fiche = pModele.parseToEmptyFiche(f);
		
		fiche.getCompetences();
		
	}

}
