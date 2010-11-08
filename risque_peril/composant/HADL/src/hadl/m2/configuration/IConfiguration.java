package hadl.m2.configuration;

import hadl.m2.composant.IComposant;
import hadl.m2.connecteur.IConnecteur;

import java.util.Observer;

public interface IConfiguration extends Observer {

	public void addComposant(IComposant newCompo);
	public void addConnecteur(IConnecteur newConnect);
	
	public void addAttachement(IComposant compoDepart , Integer portOut , IConnecteur connectDest , Integer rolesFrom);
	public void addAttachement(IConnecteur connectDepart , Integer rolesTo , IComposant compoDest , Integer portIn );
	public void addBinding(Integer portConfigDepart , IComposant compoDest , Integer portCompo );
	public void addBinding(IComposant compoDepart , Integer portCompo , Integer portConfigDest );
	
	public void removeComposant(IComposant compo);
	public void removeConnecteur(IConnecteur connect);
	
	public void removeAttachement(IComposant compoDepart , Integer portOut);
	public void removeAttachement(IConnecteur connectDepart , Integer rolesTo);
	public void removeBinding(Integer portConfigDepart);
	public void removeBinding(IComposant compoDepart , Integer portCompo);
	
	
}
