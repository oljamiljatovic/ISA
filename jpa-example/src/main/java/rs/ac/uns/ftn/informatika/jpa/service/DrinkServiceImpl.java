package rs.ac.uns.ftn.informatika.jpa.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import rs.ac.uns.ftn.informatika.jpa.domain.Drink;
import rs.ac.uns.ftn.informatika.jpa.domain.Restaurant;
import rs.ac.uns.ftn.informatika.jpa.repository.DrinkRepository;

@Service
@Transactional
public class DrinkServiceImpl implements DrinkService {
	
	@Autowired
	private DrinkRepository drinkRepository;
	
	@Override
	public ArrayList<Drink> getDrinksByRestaurant(Restaurant rest) {
		
		return this.drinkRepository.findByRestaurant(rest);
	}

	@Override
	public Drink getDrink(Long id) {
		return this.drinkRepository.findById(id);
	}

	@Override
	public void deleteDrink(Drink dr) {
		this.drinkRepository.delete(dr);
	}

	@Override
	public void updateDrink(Drink dr) {
		this.drinkRepository.updateDrink(dr.getName(), dr.getDescription(), 
				dr.getPrice(),  dr.getId());
	}

	@Override
	public Drink addDrink(Drink dr) {
		return this.drinkRepository.save(dr);
	}

	@Override
	public Drink findByName(String name) {
		return this.drinkRepository.findByName(name);
	}

	@Override
	public void updateDrinkFlag(Drink dr) {
		this.drinkRepository.updateDrinkFlag(dr.isExist(), dr.getId());
		
	}
}
