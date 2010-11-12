package hadl.m1.ClientServeur;

import hadl.m1.client.Client;
import hadl.m1.rpc.Rpc;
import hadl.m1.serveur.Serveur;
import hadl.m2.Configuration;
import hadl.m2.com.Attachement;
import hadl.m2.com.Binding;
import hadl.m2.com.Lien;

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
