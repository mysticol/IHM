package hadl.m2.com.param;

public class MappingPortService {

	private Integer port;
	private String service;
	public Integer getPort() {
		return port;
	}
	public void setPort(Integer port) {
		this.port = port;
	}
	public String getService() {
		return service;
	}
	public void setService(String service) {
		this.service = service;
	}
	public MappingPortService(Integer port, String service) {
		super();
		this.port = port;
		this.service = service;
	}
	
	
	
	
}
