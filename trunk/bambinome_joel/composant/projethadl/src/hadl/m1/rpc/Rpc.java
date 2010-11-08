package hadl.m1.rpc;

import hadl.Connector;

public class Rpc extends Connector {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Rpc(String name) {
		super(name);
	}
	
	public String rpcMethod(String mess){
		return mess;
	}

}
