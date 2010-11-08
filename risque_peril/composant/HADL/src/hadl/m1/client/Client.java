package hadl.m1.client;

import hadl.m2.composant.Composant;

public class Client extends Composant {

	public Client(){
		this.contraintes = "";
		this.proprietes = "";
	
		this.setPort(1, "envoie");
		this.setPort(2, "reception");
						
	}
	
	public void envoie(String data){
		Object[] arg = new Object[2];
		arg[0] = new Integer(1);
		arg[1] = data;		
		this.notifyObservers(arg);
	}
	
	public void reception(String data){
		System.out.println(data);
	}
	
	
}
