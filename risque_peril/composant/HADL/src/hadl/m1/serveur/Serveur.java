package hadl.m1.serveur;

import hadl.m2.configuration.Configuration;

public class Serveur extends Configuration {

	public Serveur() {
		super();
		
		ConnectionManager cmCompo = new ConnectionManager();
		SecurityDB sdbCompo = new SecurityDB();
		DataBase dbCompo = new DataBase();
		
		ClearenceRequest crConnect = new ClearenceRequest();
		SecurityQuery sqConnect = new SecurityQuery();
		SQLQuery sqlqConnect = new SQLQuery();
		
		this.addComposant(cmCompo);	
		this.addComposant(sdbCompo);
		this.addComposant(dbCompo);
		
		this.addConnecteur(crConnect);
		this.addConnecteur(sqConnect);
		this.addConnecteur(sqlqConnect);
		
		this.addBinding(1, cmCompo, 1);
		this.addBinding(cmCompo, 4, 2);
		
		this.addAttachement(cmCompo, 2, crConnect, 1);
		this.addAttachement(crConnect, 2, sdbCompo, 1);
		this.addAttachement(sdbCompo, 2, sqConnect, 1);
		this.addAttachement(sqConnect, 2, dbCompo, 1);
		this.addAttachement(dbCompo, 2, sqlqConnect, 1);
		this.addAttachement(sqlqConnect, 2, cmCompo, 3);	
	}
}
