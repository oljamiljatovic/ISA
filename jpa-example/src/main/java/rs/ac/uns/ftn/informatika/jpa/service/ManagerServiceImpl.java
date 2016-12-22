package rs.ac.uns.ftn.informatika.jpa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import rs.ac.uns.ftn.informatika.jpa.domain.Restaurant;
import rs.ac.uns.ftn.informatika.jpa.domain.users.RestaurantManager;
import rs.ac.uns.ftn.informatika.jpa.repository.ManagerRepository;
import rs.ac.uns.ftn.informatika.jpa.repository.RestaurantRepository;

@Service
@Transactional(
        propagation = Propagation.REQUIRED,
        readOnly = false)
public class ManagerServiceImpl implements ManagerService {
	
	@Autowired
	private ManagerRepository managerRepository;
	
	@Override
	public RestaurantManager addManager(RestaurantManager manag) {
		return this.managerRepository.save(manag);
	}

}
