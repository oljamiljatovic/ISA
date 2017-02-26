package rs.ac.uns.ftn.informatika.jpa.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import rs.ac.uns.ftn.informatika.jpa.domain.Restaurant;
import rs.ac.uns.ftn.informatika.jpa.repository.RestaurantRepository;

@Service
@Transactional(
        propagation = Propagation.REQUIRED,
        readOnly = false)
public class RestaurantServiceImpl implements RestaurantService {

	@Autowired
	private RestaurantRepository restaurantRepository;

	@Override
	public Restaurant addRestaurant(Restaurant rest) {
		return this.restaurantRepository.save(rest);
	}

	@Override
	public ArrayList<Restaurant> getRestaurants() {
		//return null;
		return (ArrayList<Restaurant>) this.restaurantRepository.findAll();
	}

	@Override
	public Restaurant getRestaurant(Long rest) {
		return this.restaurantRepository.findOne(rest);
	}

	@Override
	public void deleteRestaurant(Long id) {
		this.restaurantRepository.delete(id);
	}

	@Override
	public void updateRestaurant(Restaurant rest) {
		this.restaurantRepository.updateRestaurant(rest.getName(), rest.getType(), rest.getAddress(),
				rest.getContact(), rest.getId());
	}
	
	

}
