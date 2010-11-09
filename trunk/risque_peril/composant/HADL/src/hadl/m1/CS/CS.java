package hadl.m1.CS;

import hadl.m1.RPC.RPC;
import hadl.m1.client.Client;
import hadl.m1.serveur.Serveur;
import hadl.m2.configuration.Configuration;

public class CS extends Configuration {

	public CS() {
		super();
		
		Client client = new Client();
		Serveur serveur = new Serveur();
		RPC rpc = new RPC();
		
		this.addComposant(client);
		this.addComposant(serveur);
		this.addConnecteur(rpc);
		
		this.addAttachement(client, 1, rpc, 1);
		this.addAttachement(rpc, 4, client, 2);
		this.addAttachement(serveur, 2, rpc, 3);
		this.addAttachement(rpc, 2, serveur, 1);
		
		this.addBinding(1, client, 3);
		
		
		this.launch(1);
		this.removeComposant(serveur);
		this.launch(1);
		this.addComposant(serveur);
		this.addAttachement(serveur, 2, rpc, 3);
		this.addAttachement(rpc, 2, serveur, 1);
	}

	public void start() {
		this.launch(1);
	}

}
