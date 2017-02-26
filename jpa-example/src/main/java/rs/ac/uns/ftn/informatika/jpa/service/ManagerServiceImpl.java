package rs.ac.uns.ftn.informatika.jpa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import rs.ac.uns.ftn.informatika.jpa.domain.Restaurant;
import rs.ac.uns.ftn.informatika.jpa.domain.users.RestaurantManager;
import rs.ac.uns.ftn.informatika.jpa.repository.RestaurantManagerRepository;
import rs.ac.uns.ftn.informatika.jpa.repository.RestaurantRepository;

@Service
@Transactional(
        propagation = Propagation.REQUIRED,
        readOnly = false)
public class ManagerServiceImpl implements ManagerService {
	
	@Autowired
	private RestaurantManagerRepository managerRepository;
	
	@Override
	public RestaurantManager addManager(RestaurantManager manag) {
		return this.managerRepository.save(manag);
	}

	@Override
	public RestaurantManager getManager(String email) {
		return this.managerRepository.findByEmail(email);
	}

	@Override
	public void updateManager(RestaurantManager rest) {
		// TODO Auto-generated method stub
		managerRepository.updateManager(rest.getName(), rest.getSurname(), rest.getAddress(),
				rest.getContact(), rest.getPassword(), rest.getId());
		
	}

}
