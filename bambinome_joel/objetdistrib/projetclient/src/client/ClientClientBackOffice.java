package client;


import javax.naming.Context;
import javax.naming.InitialContext;

import session.ClientBackRemote;

public class ClientClientBackOffice {

	

	
	
	public static void main(String[] args) throws Exception {
		
		Context context = new InitialContext();
		// Nom de la classe d'implémentation + /local ou /remote
		ClientBackRemote backofficeclient =
		    (ClientBackRemote) context.lookup("ClientBack/remote");
	
		boolean run=true;
		
		while (run){
			
			System.out.println("Back Office Client" +
					"1) Création d'un nouveau client" +
					"2) Liste des clients" +
					"" +
					"" +
					"" +
					"" +
					"");

			
			
			
			
			
			
			
			
			
			
		}
	}
	
}
