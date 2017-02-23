package rs.ac.uns.ftn.informatika.jpa.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import rs.ac.uns.ftn.informatika.jpa.domain.Foodstuff;
import rs.ac.uns.ftn.informatika.jpa.repository.FoodstuffRepository;


@Service
@Transactional(
        propagation = Propagation.REQUIRED,
        readOnly = false)
public class FoodstuffServiceImpl implements FoodstuffService {
	
	@Autowired
	private FoodstuffRepository fsRepo;
	
	@Override
	public ArrayList<Foodstuff> getFoodstuffs() {
		return this.fsRepo.findAll();
	}

}
