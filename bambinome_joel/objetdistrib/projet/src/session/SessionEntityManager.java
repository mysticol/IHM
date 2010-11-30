package session;

import java.util.List;



public interface SessionEntityManager <E>{
	
	
	
	public List<E> getList();
	public E getById(int id);
	public void delete(int id);
	
	
	
	

}
