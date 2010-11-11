package hadl;

import hadl.com.param.InOutMapping;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;


public abstract class BriqueComposant  extends ObjectHadl{

	private static final long serialVersionUID = 1L;
	private HashMap<Integer, Object> portValueMap;

	/*
	 * Mapping entré sortie pour les appel sur les port
	 * si il y a une entrée 2 pour la valeur 1 signifie que l'appel de port 1
	 * écrira son résultat sur 2. Il faudra s'adresser a cette map pour connaitre la sortie
	 * 
	 */
	private HashMap<Integer,Integer> mapingInOut;
	
	
	
	/*
	 * Int représentant le port
	 * la méthode le service associé, donc une méthode de l'objet
	 */
	
	
	
	//pour mettre un élément dans un port attribut de sortie
	protected final void  setValuePort(Integer i, Object v){
		portValueMap.put(i, v);
	}
	
	

	public BriqueComposant(String name) {
		super(name);
		this.portValueMap= new HashMap<Integer, Object>();
		this.mapingInOut= new HashMap<Integer, Integer>();
	}

	
	//methode pour l'appel d'un port en entré
	public abstract void  appelPortIn(Integer i, Object[] agrs) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException;
	
	//methode pour l'appel d'un port en sortie
	public final Object appelPortOut(Integer i){
		return portValueMap.get(i);
	}




	
	//obtenir le port de sortie correspondant au port d'entré
	public final Integer getOutForIn(Integer in){
		Integer temp=this.mapingInOut.get(in);
		if (temp==null){
			temp=in;
		}
		return temp;
	}
	
	//ajouter un mapping port d'entré/ port de sortie
	public final void addMapingInOut(InOutMapping inout){
		this.mapingInOut.put(inout.getIn(), inout.getOut());
	}
	
	//retirer un mapping port d'entré/ port de sortie
	public final void removeInOut(Integer in){
		mapingInOut.remove(in);
	}
	
	
	protected final Method getMethodByName(Object cible, String name){
		Method resul=null;
		for (Method m: cible.getClass().getMethods()){
			if( m.getName().equalsIgnoreCase(name)){
				resul=m;
			}
		}		
		return resul;
	}
	
	protected final Method getMethodByName(Object cible, String name, Object[] param){
		
		Class<?>[] classes= new Class[param.length];
		Method resul=null;
		
		for( int i=0; i<param.length; i++){
			classes[i]=param[i].getClass();
		}
				
		try{
			resul=cible.getClass().getMethod(name, classes);
		}catch (Exception e){
			e.printStackTrace();
		}
		return resul;
	}
	
	
	
}
