package rs.ac.uns.ftn.informatika.jpa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import rs.ac.uns.ftn.informatika.jpa.domain.users.Provider;
import rs.ac.uns.ftn.informatika.jpa.repository.ProviderRepository;

@Service
@Transactional(
        propagation = Propagation.REQUIRED,
        readOnly = false)
public class ProviderServiceImpl implements ProviderService{

	@Autowired
	private ProviderRepository providerRepository;
	
	@Override
	public Provider addProvider(Provider provider) {
		return this.providerRepository.save(provider);
	}

	@Override
	public Provider getProvider(String email) {
		return this.providerRepository.findByEmail(email);
	}

	@Override
	public void updateProvider(Provider rest) {
		this.providerRepository.updateManager(rest.getName(), rest.getSurname(), rest.getContact(), 
				rest.getAddress(), rest.getLogFirstTime(), rest.getPassword(), rest.getId());
	}

}
