package rs.ac.uns.ftn.informatika.jpa.service;

import rs.ac.uns.ftn.informatika.jpa.domain.users.Provider;

public interface ProviderService {
	
	public Provider addProvider(Provider provider);
	
	public Provider getProvider(String email);
	
	public void updateProvider(Provider rest);	

}
