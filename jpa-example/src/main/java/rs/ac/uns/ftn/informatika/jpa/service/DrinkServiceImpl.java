package rs.ac.uns.ftn.informatika.jpa.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import rs.ac.uns.ftn.informatika.jpa.domain.Drink;
import rs.ac.uns.ftn.informatika.jpa.domain.DrinkList;
import rs.ac.uns.ftn.informatika.jpa.repository.DrinkRepository;

@Service
@Transactional
public class DrinkServiceImpl implements DrinkService {
	
	@Autowired
	private DrinkRepository drinkRepository;
	
	@Override
	public ArrayList<Drink> getDrinks() {
		
		return this.drinkRepository.findAll();
	}
}
