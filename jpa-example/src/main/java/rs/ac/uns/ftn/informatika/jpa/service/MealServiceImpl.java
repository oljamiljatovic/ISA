package rs.ac.uns.ftn.informatika.jpa.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import rs.ac.uns.ftn.informatika.jpa.domain.Meal;
import rs.ac.uns.ftn.informatika.jpa.domain.Restaurant;
import rs.ac.uns.ftn.informatika.jpa.repository.MealRepository;

@Service
@Transactional
public class MealServiceImpl implements MealService {
	
	@Autowired
	private MealRepository mealRepository;

	@Override
	public ArrayList<Meal> getMealsByRestaurant(Restaurant rest) {
		return this.mealRepository.findByRestaurant(rest);
	}

	@Override
	public Meal getMeal(Long id) {
		return this.mealRepository.findById(id);
	}

	@Override
	public void deleteMeal(Meal dr) {
		this.mealRepository.delete(dr);
		
	}

	@Override
	public void updateMeal(Meal dr) {
		this.mealRepository.updateMeal(dr.getName(),dr.getDescription(),dr.getPrice(),dr.getId());
	}

	@Override
	public Meal addMeal(Meal dr) {
		return this.mealRepository.save(dr);
	}

	@Override
	public Meal findByName(String name) {
		return this.mealRepository.findByName(name);
	}

	@Override
	public void updateMealFlag(Meal meal) {
		this.mealRepository.updateMealFlag(meal.isExist(), meal.getId());
		
	}
	

}
