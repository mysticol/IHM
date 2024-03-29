package hadl.m1.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import hadl.m2.composantSimple.ComposantSimple;

public class Client extends ComposantSimple {

	public Client(){
		super();
		this.contraintes = "";
		this.proprietes = "";
		
		this.setPortIn("Start", "menu");
		
		this.setPortOut("envoie", "Send-Request");
		this.setPortIn("Send-Request", "reception");
	}
	
	public void menu(){
		System.out.print("Entrez votre requete ( quit pour terminer ): ");
		BufferedReader waiter = new BufferedReader(new InputStreamReader(System.in));
		String req = "";
		try {
			req = waiter.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(req.equalsIgnoreCase("quit")){
			System.out.println("Au revoir");
		}else{
			envoie(req);
		}
	}
	
	public void envoie(String req){
		//String data = "moi taille SELECT toto";
		// System.out.println(1 +" - "+data);
		this.notifier("envoie", req);
	}
	
	public void reception(String data){
		System.out.println(" --> Reponse : "+ data);
		this.menu();
	}
}
