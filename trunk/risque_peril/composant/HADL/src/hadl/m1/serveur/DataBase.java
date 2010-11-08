package hadl.m1.serveur;

import java.util.HashMap;
import java.util.Map;

import hadl.m2.composant.Composant;

public class DataBase extends Composant {

	private Map<String,String> DB;

	public DataBase() {
		super();
		DB = new HashMap<String, String>();
		
		this.DB.put("johnbob", "lewis");
		this.DB.put("johnlewis", "bob");
		this.DB.put("boblewis", "john");
		this.DB.put("Joel", "tout petit");
		this.DB.put("moi", "dieux");
		
		this.setPortIn(1, "traiteQuery");
		this.setPortOut("traiteQuery", 2);
	}
	
	public void traiteQuery(String cmd){
		String rep = DB.get(cmd);
		this.notifier("traiteQuery",rep);
	}
	
}
