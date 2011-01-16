package com.jBzh;

import java.util.HashMap;
import java.util.LinkedList;

import bean.Caracteristique;
import bean.Categorie;
import bean.Competence;
import bean.Fiche;
import bean.Result;
import bean.Roll;
import bean.Systeme;
import dice.DiceType;

public class test {

	static public Systeme createSysteme(){
		Systeme r = new Systeme();

		r.setName("wod1");
		
		LinkedList<String> le1 = new LinkedList<String>();
		le1.add("caracP");
		le1.add("comp");
		r.addRoll("classique", new Roll(DiceType.D10,le1));
		
		LinkedList<String> le2 = new LinkedList<String>();
		le2.add("caracS");
		r.addRoll("secondaire", new Roll(DiceType.D10,le2));
		
		r.addResult("default", new Result());
		return r;
	}
	
	static public Fiche createFiche(){
		Fiche f = new Fiche();

		HashMap<String, Caracteristique> caracP = new HashMap<String, Caracteristique>();
			caracP.put("force",new Caracteristique("force",3));
			caracP.put("dex",new Caracteristique("dex",4));
			caracP.put("endu",new Caracteristique("endu",4));
			caracP.put("intel",new Caracteristique("intel",3));
			caracP.put("charisme",new Caracteristique("charisme",2));
			caracP.put("pouvoir",new Caracteristique("pouvoir",5));
		f.setCaracteristiquesPrincipales(caracP);
			
		HashMap<String, Caracteristique> caracS = new HashMap<String, Caracteristique>();
			Caracteristique c = new Caracteristique("volonte",3);
			c.setJauge(true);
			caracS.put("volonte",c);
			caracS.put("courage",new Caracteristique("courage",4));
			caracS.put("voie",new Caracteristique("voie",4));
		f.setCaracteristiquesSecondaire(caracS);
		
		HashMap<Categorie, LinkedList<Competence>> lcomp= new HashMap<Categorie, LinkedList<Competence>>();
			LinkedList<Competence> compp = new LinkedList<Competence>();
				compp.add(new Competence("baston",3));
				compp.add(new Competence("bouger",4));
				compp.add(new Competence("esquive",1));
			lcomp.put(new Categorie("physique"), compp);
			LinkedList<Competence> compm = new LinkedList<Competence>();
				compp.add(new Competence("penser",1));
				compp.add(new Competence("chercher",2));
				compp.add(new Competence("comprendre",3));
			lcomp.put(new Categorie("mental"), compm);
			LinkedList<Competence> comps = new LinkedList<Competence>();
				comps.add(new Competence("etre beau",2));
				comps.add(new Competence("etre pas beau",3));
				comps.add(new Competence("etre gentil",5));
			lcomp.put(new Categorie("social"), comps);
		f.setCompetences(lcomp);
		
		return f;
	}
	
	
}
