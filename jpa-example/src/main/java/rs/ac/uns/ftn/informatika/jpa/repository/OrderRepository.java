package rs.ac.uns.ftn.informatika.jpa.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import rs.ac.uns.ftn.informatika.jpa.domain.Order;

public interface OrderRepository extends PagingAndSortingRepository<Order, Long>{
	public ArrayList<Order> findAll();
	
	@Query("select o from Order o where o.waiter_id = ?1 and o.cook_state <> 'kraj' and o.barman_state <> 'kraj'")
	public ArrayList<Order> findByWaiter_id(Long waiter_id);
	
	@Query("select o from Order o where o.restaurant = ?1 and o.cook_state <> 'kraj' and o.barman_state <> 'kraj'")
	public ArrayList<Order> findByRestaurant(Long restaurant);
	
	public Order findById(Long id);
}
