package rs.ac.uns.ftn.informatika.jpa.service;

import java.util.ArrayList;

import rs.ac.uns.ftn.informatika.jpa.domain.Meal;

public interface MealService {
	
	public ArrayList<Meal> getDrinksByRestaurant(Long rest_id);
	public Meal getMeal(Long id);
	public void deleteMeal(Meal dr);
	public void updateMeal(Meal dr);
	public Meal addMeal(Meal dr);

}
