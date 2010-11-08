package hadl.m1.client;

import hadl.Composant;
import hadl.param.MappingPortService;

public class Client extends Composant {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/*
	 * Le client a deux service : l'envoie de données et la réception de données
	 */
	public Client(String name) {
		super(name);
		MappingPortService servEnvoi= new MappingPortService(1, "envoi");
		MappingPortService servReception= new MappingPortService(2, "reception");
		this.addMappingPortService(servEnvoi);
		this.addMappingPortService(servReception);
	}
	
	public String envoi(String mess){
		return mess;
	}
	
	public void reception(String mess){
		System.out.println(mess);
	}

}
