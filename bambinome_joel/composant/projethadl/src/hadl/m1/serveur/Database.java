package hadl.m1.serveur;

import java.util.HashMap;
import java.util.Map;

import hadl.m2.Composant;


public class Database extends Composant {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Map<String,String> db;

	public Database() {
		super("db");
		db = new HashMap<String, String>();
		
		db.put("Joël","petit");
		db.put("Bambinome","rital");
		
		
	}
	
	public String repQuery(String key){
		String rep = db.get(key);
		return rep;
	}

}
