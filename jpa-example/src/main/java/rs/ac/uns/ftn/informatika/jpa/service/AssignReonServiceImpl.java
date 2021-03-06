package rs.ac.uns.ftn.informatika.jpa.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import rs.ac.uns.ftn.informatika.jpa.domain.AssignReon;
import rs.ac.uns.ftn.informatika.jpa.domain.Reon;
import rs.ac.uns.ftn.informatika.jpa.domain.Restaurant;
import rs.ac.uns.ftn.informatika.jpa.domain.users.Employee;
import rs.ac.uns.ftn.informatika.jpa.repository.AssignReonRepository;
@Service
@Transactional
public class AssignReonServiceImpl implements AssignReonService{

	@Autowired
	private AssignReonRepository assignReonRepository;
	
	@Override
	public AssignReon createAssignReon(AssignReon assignReon) {
		return this.assignReonRepository.save(assignReon);
	}

	@Override
	public ArrayList<AssignReon> findAll() {
		return assignReonRepository.findAll();
	}

	@Override
	public ArrayList<AssignReon> findByWaiter_id(Long waiter_id) {
		return assignReonRepository.findByWaiter_id(waiter_id);
	}

	@Override
	public ArrayList<AssignReon> findByRestaurant(Restaurant rest) {
		// TODO Auto-generated method stub
		return this.assignReonRepository.findByRestaurant(rest);
	}

	@Override
	public void delete(Long id) {
		
		this.assignReonRepository.delete(id);
	}

	@Override
	public ArrayList<AssignReon> findByWaiter(Employee waiter) {
		// TODO Auto-generated method stub
		return this.assignReonRepository.findByWaiter(waiter);
	}

	@Override
	public ArrayList<AssignReon> findByReonAndRestaurant(Reon reon, Restaurant restaurant) {
		return this.assignReonRepository.findByReonAndRestaurant(reon, restaurant);
	}



}
