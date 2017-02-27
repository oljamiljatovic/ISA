package rs.ac.uns.ftn.informatika.jpa.service;

import java.util.ArrayList;

import rs.ac.uns.ftn.informatika.jpa.domain.Meal;
import rs.ac.uns.ftn.informatika.jpa.domain.Restaurant;

public interface MealService {
	
	public ArrayList<Meal> getMealsByRestaurant(Restaurant rest);
	public Meal getMeal(Long id);
	public void deleteMeal(Meal dr);
	public void updateMeal(Meal dr);
	public Meal addMeal(Meal dr);
	public Meal findByName(String name);
	public void updateMealFlag(Meal meal);
}
