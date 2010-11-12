package hadl.m1.client;

import hadl.m2.Composant;


public class Client extends Composant {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/*
	 * Le client a deux service : l'envoie de données et la réception de données
	 */
	public Client() {
		super("cl1");
		
	}
	
	public String envoi(String mess){
		return mess;
	}
	
	public void reception(String mess){
		System.out.println(mess);
	}

}
