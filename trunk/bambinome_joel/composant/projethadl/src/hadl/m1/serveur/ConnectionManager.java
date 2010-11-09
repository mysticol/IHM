package hadl.m1.serveur;

import hadl.Composant;
import hadl.com.param.InOutMapping;
import hadl.com.param.MappingPortService;

public class ConnectionManager extends Composant {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ConnectionManager(String name) {
		super(name);
		
		InOutMapping inoutCM1= new InOutMapping(1, 2);
		InOutMapping inoutCM2= new InOutMapping(3, 4);
		MappingPortService servCM1= new MappingPortService(1, "entre");
		MappingPortService servCM2= new MappingPortService(3, "sort");
		
		this.addMapingInOut(inoutCM1);
		this.addMapingInOut(inoutCM2);
		this.addMappingPortService(servCM1);
		this.addMappingPortService(servCM2);
	}
	
	public String entre(String mess){
		System.out.println("Entre dans le ConnectionManager");
		return mess;
	}
	
	public String sort(String mess){
		System.out.println("Sort du ConnectionManager");
		return mess;
	}

}
