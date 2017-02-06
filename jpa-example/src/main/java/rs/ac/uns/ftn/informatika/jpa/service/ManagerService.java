package rs.ac.uns.ftn.informatika.jpa.service;

import rs.ac.uns.ftn.informatika.jpa.domain.Restaurant;
import rs.ac.uns.ftn.informatika.jpa.domain.users.RestaurantManager;

public interface ManagerService {
	
	public RestaurantManager addManager(RestaurantManager rest);
	
	public RestaurantManager getManager(String email);
	
	public void updateManager(RestaurantManager rest);
}
