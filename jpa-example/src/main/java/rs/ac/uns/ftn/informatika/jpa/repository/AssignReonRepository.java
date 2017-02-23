package rs.ac.uns.ftn.informatika.jpa.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import rs.ac.uns.ftn.informatika.jpa.domain.AssignReon;
import rs.ac.uns.ftn.informatika.jpa.domain.WorkSchedule;
import java.lang.Long;
import java.util.List;

public interface AssignReonRepository extends PagingAndSortingRepository<AssignReon, Long>  {
	
	public ArrayList<AssignReon> findAll();
	
	@Query("select a from AssignReon a where a.waiter_id = ?1")
	public ArrayList<AssignReon> findByWaiter_id(Long waiter_id);
	
	public ArrayList<AssignReon> findByRestaurant(Long restaurant);
}
