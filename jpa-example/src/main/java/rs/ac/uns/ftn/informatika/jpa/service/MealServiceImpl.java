package rs.ac.uns.ftn.informatika.jpa.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import rs.ac.uns.ftn.informatika.jpa.domain.Meal;
import rs.ac.uns.ftn.informatika.jpa.repository.MealRepository;

@Service
@Transactional
public class MealServiceImpl implements MealService {
	
	@Autowired
	private MealRepository mealRepository;

	@Override
	public ArrayList<Meal> getMeals() {
		
		return this.mealRepository.findAll();
		//return null;
	}
	

}
