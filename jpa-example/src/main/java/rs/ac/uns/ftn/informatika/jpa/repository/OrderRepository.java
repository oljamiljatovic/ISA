package rs.ac.uns.ftn.informatika.jpa.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import rs.ac.uns.ftn.informatika.jpa.domain.Order;
import rs.ac.uns.ftn.informatika.jpa.domain.Restaurant;
import rs.ac.uns.ftn.informatika.jpa.domain.users.Employee;

public interface OrderRepository extends PagingAndSortingRepository<Order, Long>{
	public ArrayList<Order> findAll();
	
	@Query("select o from Order o where o.waiter = ?1 and o.cook_state <> 'kraj' and o.barman_state <> 'kraj'")
	public ArrayList<Order> findByWaiter(Employee waiter);
	
	@Query("select o from Order o where o.restaurant = ?1 and o.cook_state <> 'kraj' and o.barman_state <> 'kraj'")
	public ArrayList<Order> findByRestaurant(Restaurant restaurant);
	
	public Order findById(Long id);
	
	List<Order> findByDrinks(List drinks);
}
