package rs.ac.uns.ftn.informatika.jpa.service;

import java.util.ArrayList;

import rs.ac.uns.ftn.informatika.jpa.domain.Order;

public interface OrderService {
	ArrayList<Order> getOrders();
	
	Order createNew(Order order);
	
	Order update(Order order, Long id);
	
	Order findOne(Long id);
	
	public ArrayList<Order> findByWaiter_id(Long waiter_id);
	
	public ArrayList<Order> findByRestaurant(Long restaurant);
	
	public Order findById(Long id);
}
