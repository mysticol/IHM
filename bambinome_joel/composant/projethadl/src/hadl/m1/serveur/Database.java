package hadl.m1.serveur;

import java.util.HashMap;
import java.util.Map;

import hadl.m2.Composant;
import hadl.m2.com.param.InOutMapping;
import hadl.m2.com.param.MappingPortService;

public class Database extends Composant {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Map<String,String> db;

	public Database(String name) {
		super(name);
		db = new HashMap<String, String>();
		
		db.put("Joël","petit");
		db.put("Bambinome","rital");
		
		InOutMapping inoutDb= new InOutMapping(1, 2);
		MappingPortService servDb= new MappingPortService(1, "repQuery");
		
		this.addMapingInOut(inoutDb);
		this.addMappingPortService(servDb);
	}
	
	public String repQuery(String key){
		String rep = db.get(key);
		return rep;
	}

}
