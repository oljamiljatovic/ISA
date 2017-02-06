package rs.ac.uns.ftn.informatika.jpa.service;

import java.util.ArrayList;

import rs.ac.uns.ftn.informatika.jpa.domain.Restaurant;

public interface RestaurantService  {
	public Restaurant addRestaurant(Restaurant rest);
	public ArrayList<Restaurant> getRestaurants();
	public Restaurant getRestaurant(String name);
	public void deleteRestaurant(Long id);
	public void updateRestaurant(Restaurant rest);
}
