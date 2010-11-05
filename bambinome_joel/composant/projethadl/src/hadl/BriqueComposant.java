package hadl;

import hadl.param.InOutMapping;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;


public abstract class BriqueComposant  extends ObjectHadl{

	private static final long serialVersionUID = 1L;
	private HashMap<Integer, Object> portValueMap;
	//XXX P'etre ajouter un mapping entre entr�e et sortie a coup de map<int,int> ou le premier int est l'entr�e et le second la sortie	
	// � g�rer au niveau de l'appel port qui �crit la valeur de sortie
	
	private HashMap<Integer,Integer> mapingInOut;
	
	
	
	/*
	 * Int repr�sentant le port
	 * la m�thode le service associ�, donc une m�thode de l'objet
	 */
	
	
	
	//pour mettre un �l�ment dans un port attribut de sortie
	protected final void  setValuePort(Integer i, Object v){
		portValueMap.put(i, v);
	}
	
	

	public BriqueComposant(String name) {
		super(name);
		this.portValueMap= new HashMap<Integer, Object>();
		this.mapingInOut= new HashMap<Integer, Integer>();
	}

	
	
	public abstract void  appelPortIn(Integer i, Object[] agrs) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException;
	
	public final Object appelPortOut(Integer i){
		return portValueMap.get(i);
	}




	
	
	public final Integer getOutForIn(Integer in){
		Integer temp=this.mapingInOut.get(in);
		if (temp==null){
			temp=in;
		}
		return temp;
	}
	
	public final void addMapingInOut(InOutMapping inout){
		this.mapingInOut.put(inout.getIn(), inout.getOut());
	}
	
	
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
