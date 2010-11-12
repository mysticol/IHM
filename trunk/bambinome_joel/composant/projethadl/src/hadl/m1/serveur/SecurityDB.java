package hadl.m1.serveur;

import hadl.m2.Composant;
import hadl.m2.com.param.InOutMapping;
import hadl.m2.com.param.MappingPortService;

public class SecurityDB extends Composant {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SecurityDB(String name) {
		super(name);
		
		InOutMapping inoutSecu= new InOutMapping(1, 2);
		MappingPortService servSecu= new MappingPortService(1, "secu");
		
		this.addMapingInOut(inoutSecu);
		this.addMappingPortService(servSecu);
	}
	
	public String secu(String mess){
		System.out.println("Securité de merde");
		return mess;
	}

}
