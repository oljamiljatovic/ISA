package rs.ac.uns.ftn.informatika.jpa.service;

import java.util.ArrayList;

import rs.ac.uns.ftn.informatika.jpa.domain.AssignReon;
import rs.ac.uns.ftn.informatika.jpa.domain.Restaurant;
import rs.ac.uns.ftn.informatika.jpa.domain.users.Employee;

public interface AssignReonService {

	public AssignReon createAssignReon(AssignReon assignReon);
	
	public ArrayList<AssignReon> findAll();
	
	public ArrayList<AssignReon> findByWaiter_id(Long waiter_id);
	
	public ArrayList<AssignReon> findByWaiter(Employee waiter);
	
	public ArrayList<AssignReon> findByRestaurant(Restaurant rest);
	
	public void delete(Long id);
}
