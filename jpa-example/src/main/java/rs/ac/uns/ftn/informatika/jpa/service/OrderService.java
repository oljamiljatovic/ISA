package rs.ac.uns.ftn.informatika.jpa.service;

import java.util.ArrayList;
import java.util.List;

import rs.ac.uns.ftn.informatika.jpa.domain.Order;
import rs.ac.uns.ftn.informatika.jpa.domain.Restaurant;
import rs.ac.uns.ftn.informatika.jpa.domain.users.Employee;

public interface OrderService {
	ArrayList<Order> getOrders();
	
	Order createNew(Order order);
	
	Order update(Order order, Long id);
	
	Order findOne(Long id);
	
	public ArrayList<Order> findByWaiter(Employee waiter);
	
	public ArrayList<Order> findByRestaurant(Restaurant restaurant);
	
	public Order findById(Long id);
	
	List<Order> findByDrinks(List drinks);
}
