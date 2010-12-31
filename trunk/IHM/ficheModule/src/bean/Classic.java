package bean;

import java.io.Serializable;

public class Classic implements Vie, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5832364881113959193L;
	
	private Integer total;
	private Integer actuel;
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public Integer getActuel() {
		return actuel;
	}
	public void setActuel(Integer actuel) {
		this.actuel = actuel;
	}
	public Classic(Integer total, Integer actuel) {
		this.total = total;
		this.actuel = actuel;
	}
	
	
	
	
}
