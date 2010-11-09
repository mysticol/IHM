package hadl.m1.serveur;

import hadl.m2.configuration.Configuration;

public class Serveur extends Configuration {

	public ConnectionManager cmCompo;
	public SecurityDB sdbCompo;
	public DataBase dbCompo;
	public ClearenceRequest crConnect;
	public SecurityQuery sqConnect;
	public SQLQuery sqlqConnect;
		
	public Serveur() {
		super();
	}
	
	public void init(){
		cmCompo = new ConnectionManager();
		sdbCompo = new SecurityDB();
		dbCompo = new DataBase();

		crConnect = new ClearenceRequest();
		sqConnect = new SecurityQuery();
		sqlqConnect = new SQLQuery();
		
		this.addComposant(cmCompo);	
		this.addComposant(sdbCompo);
		this.addComposant(dbCompo);
		
		this.addConnecteur(crConnect);
		this.addConnecteur(sqConnect);
		this.addConnecteur(sqlqConnect);
		
		this.addBinding("Receive-Request", cmCompo, "External-Socket");
		this.addBinding(cmCompo, "External-Socket", "Receive-Request");
		
		this.addAttachement(cmCompo, "Security-Check", crConnect, "1");
		this.addAttachement(cmCompo, "DB-Query", sqlqConnect, "1");

		this.addAttachement(crConnect, "2", sdbCompo, "Security-Auth");
		this.addAttachement(crConnect, "4", cmCompo, "Security-Check");
				
		this.addAttachement(sdbCompo, "C-Query", sqConnect, "1");
		this.addAttachement(sdbCompo, "Security-Auth", crConnect, "3");
		
		this.addAttachement(sqConnect, "2", dbCompo, "Security-Manager");
		this.addAttachement(sqConnect, "4", sdbCompo, "C-Query");
		
		this.addAttachement(dbCompo, "Query-Int", sqlqConnect, "3");
		this.addAttachement(dbCompo, "Security-Manager", sqConnect, "3");
		
		this.addAttachement(sqlqConnect, "2", dbCompo, "Query-Int");
		this.addAttachement(sqlqConnect, "4", cmCompo, "DB-Query");
	}
}
