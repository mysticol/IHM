package interfaceTest;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import lib.CatalogueRemote;

public class ClientTest {

	/**
	 * @param args
	 * @throws NamingException 
	 */
	public static void main(String[] args) throws NamingException {

		Context context = new InitialContext();
		// Nom de la classe d'implémentation + /local ou /remote
		CatalogueRemote catalogueService = (CatalogueRemote) context.lookup("CatalogueService/remote");
		
		
		
		
		
		
		

	}

}
