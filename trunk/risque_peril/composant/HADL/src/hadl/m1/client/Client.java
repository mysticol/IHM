package hadl.m1.client;

import hadl.m2.composant.Composant;

public class Client extends Composant {

	public Client(){
		this.contraintes = "";
		this.proprietes = "";
		
		this.setPortIn(3, "envoie");
		this.setPortOut("envoie", 1);
		this.setPortIn(2, "reception");
	}
	
	public void envoie(){
		String data = "Joel";
		this.notifier("envoie", data);
	}
	
	public void reception(String data){
		System.out.println(data);
	}
	
	
}
