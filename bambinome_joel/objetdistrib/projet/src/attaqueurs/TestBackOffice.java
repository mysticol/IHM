package attaqueurs;



import java.util.HashMap;

import javax.naming.Context;
import javax.naming.InitialContext;

import entity.Adresse;
import entity.Client;
import entity.Commande;
import entity.ProduitStub;

import session.ClientBackLocal;
import session.CommandeBackLocal;
import session.ProduitStubBackLocal;

public class TestBackOffice {

	
	public static void main(String[] args) throws Exception{
		
		Context context = new InitialContext();
		// Nom de la classe d'implémentation + /local ou /remote
		ClientBackLocal backofficeclient =
		    (ClientBackLocal) context.lookup("ClientBack/remote");
	
		
		
		Client tt= new Client();
		tt.setAdd(new Adresse("dede", "444", "dd", "ddefz"));
		tt.setNom("tout petit");
		tt.setPrenom("joel");
		
		Client dd=new Client();
		dd.setAdd(new Adresse("defefe", "4g4", "ddzd", "ddezadazz"));
		dd.setNom("toutt");
		dd.setPrenom("jodel");
		
		backofficeclient.createEntity(dd);
		backofficeclient.createEntity(tt);
		
		System.out.println(backofficeclient.getList());
		
		
		
		ProduitStubBackLocal backofficeproduitstub =
		    (ProduitStubBackLocal) context.lookup("ProduitStubLocal/remote");
		
		ProduitStub prd1= new ProduitStub();
		prd1.setMarque("truc");
		prd1.setModele("machin a3");
		prd1.setFournisseur("global");
		
		
		ProduitStub prd2= new ProduitStub();
		prd2.setMarque("truc");
		prd2.setModele("machin a1");
		prd2.setFournisseur("pas globale");
		
		
		
		backofficeproduitstub.createEntity(prd2);
		backofficeproduitstub.createEntity(prd1);
		
		System.out.println(backofficeproduitstub.getList());
		
		CommandeBackLocal backofficecommande =
		    (CommandeBackLocal) context.lookup("CommandeLocal/remote");
		
		
		Commande cmd1= new Commande();
		
		cmd1.setCl(dd);
		HashMap<ProduitStub, Long> map1= new HashMap<ProduitStub, Long>();
		map1.put(prd2, 12l);
		map1.put(prd1, 12l);
		
		
		cmd1.setContenu(map1);
		
		backofficecommande.createEntity(cmd1);
		
		System.out.println(backofficecommande.getList());
		
		
		

		
		
	}
	
}
