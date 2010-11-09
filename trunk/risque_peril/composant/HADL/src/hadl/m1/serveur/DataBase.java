package hadl.m1.serveur;

import java.util.HashMap;
import java.util.Map;

import hadl.m2.composant.Composant;

public class DataBase extends Composant {

	private Map<String,Map<String,String>> DB;

	public DataBase() {
		super();
		Map<String,String> nom = new HashMap<String, String>();
		nom.put("charles", "peril");
		nom.put("vincent", "risque");
		nom.put("manoel", "joel");
		nom.put("anthony", "bambinome");
		nom.put("frederic", "pedofred");
		
		Map<String,String> taille = new HashMap<String, String>();
		taille.put("peril", "moyen");
		taille.put("vincent", "un peu grand");
		taille.put("joel", "tout petit");
		taille.put("bambinome", "grand");
		taille.put("frederique", "aime les tout petit");
		
		DB = new HashMap<String, Map<String,String>>();
		
		this.DB.put("nom", nom);
		this.DB.put("taille", taille);
		
		this.setPortIn("Security-Manager", "verifTable");
		this.setPortIn("Query-Int", "traiteQuery");
		
		this.setPortOut("verifTable", "Security-Manager");
		this.setPortOut("traiteQuery", "Query-Int");
	}
	
	public void verifTable(String table){
		// System.out.println(7 +" - "+table);
		String rep ="";
		if(DB.containsKey(table)){
			rep = "OK";
		}else{
			rep = "Erreur : table "+table+" inconnu !!";
		}
		this.notifier("verifTable", rep);
	}
	
	public void traiteQuery(String cmd){
		// System.out.println(14 + " - " + cmd);
		String[] elt = cmd.split(" ");
		String rep ="Erreur DataBase inconnu";
		
		if(elt[2].equalsIgnoreCase("INSERT")){
			if(elt.length == 5){
				DB.get(elt[1]).put(elt[3], elt[4]);
			}else{
				rep = "Erreur : format du INSERT incorect - <USER> <TABLE> INSERT <KEY> <VALUE>";
			}
		}else if(elt[2].equalsIgnoreCase("SELECT")){
			if(elt.length == 4){
				if(DB.get(elt[1]).containsKey(elt[3])){
					rep = DB.get(elt[1]).get(elt[3]);
				}else{
					rep = "Erreur : la clez "+elt[3]+" est introuvable dans la table "+elt[1];
				}
			}else{
				rep = "Erreur : format du SELECT incorect - <USER> <TABLE> SELECT <KEY>";
			}
		}else{
			rep = "Erreur : demande "+elt[2]+" incorrect! Choix possible INSERT, SELECT";
		}
		
		this.notifier("traiteQuery",rep);
	}
	
}
