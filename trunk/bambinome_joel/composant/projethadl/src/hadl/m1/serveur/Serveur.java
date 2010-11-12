package hadl.m1.serveur;

import hadl.m2.Configuration;
import hadl.m2.com.Attachement;
import hadl.m2.com.Binding;
import hadl.m2.com.Lien;


public class Serveur extends Configuration {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Serveur(String name) {
		super(name);
		
		ConnectionManager cm = new ConnectionManager("ConnectionManager");
		SecurityDB secuDb = new SecurityDB("SecurityDB");
		Database db = new Database("db");
		
		ClearenceRequest cr = new ClearenceRequest("ClearenceRequest");
		SecurityQuery secuQuery = new SecurityQuery("SecurityQuery");
		SQLQuery sqlQuery = new SQLQuery("SQLQuery");
		
		this.addComposant(cm);
		this.addComposant(secuDb);
		this.addComposant(db);
		
		this.addConnector(cr);
		this.addConnector(secuQuery);
		this.addConnector(sqlQuery);
		
		Lien lienCR= new Attachement(2, "ConnectionManager", "ClearenceRequest", "crMethod",  1, "SecurityDB");
		Lien lienSecuQ= new Attachement(2, "SecurityDB", "SecurityQuery", "secuQMethod",  1, "db");
		Lien lienSQLQ= new Attachement(2, "db", "SQLQuery", "SQLMethod",  3, "ConnectionManager");

		
		Lien bindIn= new Binding(1, "ConnectionManager", 1);
		
		this.addLien(lienCR);
		this.addLien(lienSecuQ);
		this.addLien(lienSQLQ);
		this.addLien(bindIn);
	}

}
