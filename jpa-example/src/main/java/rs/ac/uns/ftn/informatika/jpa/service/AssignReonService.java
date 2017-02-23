package rs.ac.uns.ftn.informatika.jpa.service;

import java.util.ArrayList;

import rs.ac.uns.ftn.informatika.jpa.domain.AssignReon;

public interface AssignReonService {

	public AssignReon createAssignReon(AssignReon assignReon);
	
	public ArrayList<AssignReon> findAll();
	
	public ArrayList<AssignReon> findByWaiter_id(Long waiter_id);
	
	public ArrayList<AssignReon> findByRestaurant(Long id);
	
	public void delete(Long id);
}
