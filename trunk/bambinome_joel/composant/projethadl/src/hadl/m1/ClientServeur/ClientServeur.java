package hadl.m1.ClientServeur;

import hadl.Configuration;
import hadl.com.Attachement;
import hadl.com.Binding;
import hadl.com.Lien;
import hadl.m1.client.Client;
import hadl.m1.rpc.Rpc;
import hadl.m1.serveur.Serveur;

public class ClientServeur extends Configuration {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ClientServeur(String name) {
		super(name);
		
		Client client = new Client("cl1");
		Serveur serveur = new Serveur("serv1");
		Rpc rpc = new Rpc("rpc");
		
		this.addComposant(client);
		this.addComposant(serveur);
		this.addConnector(rpc);
		
		Lien lienCS= new Attachement(1, "cl1", "rpc", "rpcMethod",  1, "serv1");

		Lien bindCl= new Binding(1, "cl1", 1);
		Lien bindSrv= new Binding(1, "serv1", 1);
		
		this.addLien(lienCS);
		this.addLien(bindCl);
		this.addLien(bindSrv);
	}


}
