package rs.ac.uns.ftn.informatika.jpa.repository;

import java.util.ArrayList;

import org.springframework.data.repository.PagingAndSortingRepository;

import rs.ac.uns.ftn.informatika.jpa.domain.AssignReon;
import rs.ac.uns.ftn.informatika.jpa.domain.Restaurant;

public interface AssignReonRepository extends PagingAndSortingRepository<AssignReon, Long>  {
	
	public ArrayList<AssignReon> findAll();
	
	public ArrayList<AssignReon> findByWaiter_id(Long waiter_id);
	
	//List<AssignReon> findByWaiter(Employee waiter);findby
	
	public ArrayList<AssignReon> findByRestaurant(Restaurant rest);
}
