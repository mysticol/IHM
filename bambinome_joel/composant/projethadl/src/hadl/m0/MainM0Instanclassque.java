package hadl.m0;

import hadl.m1.ClientServeur.ClientServeur;
import hadl.m1.client.Client;
import hadl.m1.rpc.Rpc;
import hadl.m1.serveur.ClearenceRequest;
import hadl.m1.serveur.ConnectionManager;
import hadl.m1.serveur.Database;
import hadl.m1.serveur.SQLQuery;
import hadl.m1.serveur.SecurityDB;
import hadl.m1.serveur.SecurityQuery;
import hadl.m1.serveur.Serveur;
import hadl.m2.com.Attachement;
import hadl.m2.com.Binding;
import hadl.m2.com.Lien;
import hadl.m2.com.param.InOutMapping;
import hadl.m2.com.param.MappingPortService;

public class MainM0Instanclassque {

	
	public static void main(String[] args) {
		
		//Client serveur
		
		ClientServeur clientServ= new ClientServeur();
		
		
		//instanciation client
		Client client = new Client();
		MappingPortService servEnvoi= new MappingPortService(1, "envoi");
		MappingPortService servReception= new MappingPortService(2, "reception");
		client.addMappingPortService(servEnvoi);
		client.addMappingPortService(servReception);
		//fin client
		
		
		//serveur
		Serveur serveur = new Serveur();
		
		//Connection manager
		ConnectionManager cm = new ConnectionManager();
		InOutMapping inoutCM1= new InOutMapping(1, 2);
		InOutMapping inoutCM2= new InOutMapping(3, 4);
		MappingPortService servCM1= new MappingPortService(1, "entre");
		MappingPortService servCM2= new MappingPortService(3, "sort");
		
		cm.addMapingInOut(inoutCM1);
		cm.addMapingInOut(inoutCM2);
		cm.addMappingPortService(servCM1);
		cm.addMappingPortService(servCM2);
		//fin connection manager
			
		
		//security db
		SecurityDB secuDb = new SecurityDB();
		InOutMapping inoutSecu= new InOutMapping(1, 2);
		MappingPortService servSecu= new MappingPortService(1, "secu");
		
		secuDb.addMapingInOut(inoutSecu);
		secuDb.addMappingPortService(servSecu);
		//fin security db
		
		//DAtabase	
		Database db = new Database();
		
		InOutMapping inoutDb= new InOutMapping(1, 2);
		MappingPortService servDb= new MappingPortService(1, "repQuery");
		
		db.addMapingInOut(inoutDb);
		db.addMappingPortService(servDb);
		
		
		//fin database
		
		
		
		
		ClearenceRequest cr = new ClearenceRequest("ClearenceRequest");
		SecurityQuery secuQuery = new SecurityQuery("SecurityQuery");
		SQLQuery sqlQuery = new SQLQuery("SQLQuery");
		
		serveur.addComposant(cm);
		serveur.addComposant(secuDb);
		serveur.addComposant(db);
		
		serveur.addConnector(cr);
		serveur.addConnector(secuQuery);
		serveur.addConnector(sqlQuery);
		
		Lien lienCR= new Attachement(2, "ConnectionManager", "ClearenceRequest", "crMethod",  1, "SecurityDB");
		Lien lienSecuQ= new Attachement(2, "SecurityDB", "SecurityQuery", "secuQMethod",  1, "db");
		Lien lienSQLQ= new Attachement(2, "db", "SQLQuery", "SQLMethod",  3, "ConnectionManager");

		
		Lien bindIn= new Binding(1, "ConnectionManager", 1);
		
		serveur.addLien(lienCR);
		serveur.addLien(lienSecuQ);
		serveur.addLien(lienSQLQ);
		serveur.addLien(bindIn);
		

		
		//fin serveur
		
		
		
		
		Rpc rpc = new Rpc("rpc");
		
		clientServ.addComposant(client);
		clientServ.addComposant(serveur);
		clientServ.addConnector(rpc);
		
		Lien lienCS= new Attachement(1, "cl1", "rpc", "rpcMethod",  1, "serv1");

		Lien bindCl= new Binding(1, "cl1", 1);
		Lien bindSrv= new Binding(1, "serv1", 1);
		
		clientServ.addLien(lienCS);
		clientServ.addLien(bindCl);
		clientServ.addLien(bindSrv);
		//fin client serveur
		
		
		
		
	}
	
}
