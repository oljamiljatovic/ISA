package rs.ac.uns.ftn.informatika.jpa.service;

import java.util.ArrayList;

import rs.ac.uns.ftn.informatika.jpa.domain.Drink;

public interface DrinkService {
	
	public ArrayList<Drink> getDrinks();
	public Drink getDrink(Long id);
	public void deleteDrink(Drink dr);

}
