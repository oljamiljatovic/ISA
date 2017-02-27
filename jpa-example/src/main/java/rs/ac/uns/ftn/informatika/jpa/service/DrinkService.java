package rs.ac.uns.ftn.informatika.jpa.service;

import java.util.ArrayList;

import rs.ac.uns.ftn.informatika.jpa.domain.Drink;
import rs.ac.uns.ftn.informatika.jpa.domain.Restaurant;

public interface DrinkService {
	
	public ArrayList<Drink> getDrinksByRestaurant(Restaurant rest_id);
	public Drink getDrink(Long id);
	public void deleteDrink(Drink dr);
	public void updateDrink(Drink dr);
	public Drink addDrink(Drink dr);
	public Drink findByName(String name);
	public void updateDrinkFlag(Drink dr);
}
