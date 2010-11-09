package hadl.m1.serveur;

import java.util.LinkedList;
import java.util.List;

import hadl.m2.composant.Composant;

public class SecurityDB extends Composant {

	private List<String> users;	
	
	public SecurityDB() {
		super();
		
		users = new LinkedList<String>();
		
		users.add("moi");
		users.add("john");
		users.add("bob");
		users.add("lewis");
		users.add("dieux");
		users.add("l'autre");
		
		this.setPortIn("Security-Auth", "aVerifier");
		this.setPortIn("C-Query", "retour");
		
		this.setPortOut("aVerifier", "C-Query");
		this.setPortOut("retour", "Security-Auth");
	}

	public void aVerifier(String cmd){
		// System.out.println(5 +" - "+cmd);
		String[] elt = cmd.split(" ");
		
		if(users.contains(elt[0])){
			if(elt.length >= 2){
				this.notifier("aVerifier", elt[1]);
			}else{
				this.retour("Erreur : pas assez d'argument dans la requete. <USER> <TABLE> <ACTION> <OPTION> ...");
			}
		}else{
			this.retour("Erreur : utilisateur "+elt[0]+" inconnu !!");
		}
	}

	public void retour(String rep){
		// System.out.println(9 +" - "+rep);
		this.notifier("retour", rep);
	}
	
}
