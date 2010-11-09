package hadl.m1.CS;

import hadl.m1.RPC.RPC;
import hadl.m1.client.Client;
import hadl.m1.serveur.Serveur;
import hadl.m2.configuration.Configuration;

public class CS extends Configuration {

	public Client client;
	public Serveur serveur;
	public RPC rpc;
	
	public CS() {
		super();	
	}

	public void init(){
		
		client = new Client();
		serveur = new Serveur();
		rpc = new RPC();
		
		serveur.init();
		
		this.addComposant(client);
		this.addComposant(serveur);
		this.addConnecteur(rpc);
		
		this.addAttachement(client, "Send-Request", rpc, "1");
		this.addAttachement(rpc, "4", client, "Send-Request");
		this.addAttachement(serveur, "Receive-Request", rpc, "3");
		this.addAttachement(rpc, "2", serveur, "Receive-Request");
		
		this.addBinding("Start-CS", client, "Start");
	}
	
	public void start() {
		this.launch("Start-CS");
		/*this.removeComposant(serveur);
		this.launch("start-CS");
		this.addComposant(serveur);
		this.addAttachement(serveur, "Receive-Request", rpc, "3");
		this.addAttachement(rpc, "2", serveur, "Receive-Request");
		this.launch("start-CS");*/
	}

}
