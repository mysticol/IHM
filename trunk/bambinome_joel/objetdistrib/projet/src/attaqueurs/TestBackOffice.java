package attaqueurs;



import javax.naming.Context;
import javax.naming.InitialContext;

import session.ClientBackLocal;
import session.CommandeBackLocal;
import session.ProduitStubBackLocal;

public class TestBackOffice {

	
	public static void main(String[] args) throws Exception{
		
		Context context = new InitialContext();
		// Nom de la classe d'implémentation + /local ou /remote
		ClientBackLocal backofficeclient =
		    (ClientBackLocal) context.lookup("ClientBack/remote");
	
		
		
		CommandeBackLocal backofficecommande =
		    (CommandeBackLocal) context.lookup("CommandeBack/remote");
		
		
		
		ProduitStubBackLocal backofficeproduitstub =
		    (ProduitStubBackLocal) context.lookup("ProduitStubLocal/remote");

		
		
	}
	
}
