package rs.ac.uns.ftn.informatika.jpa.service;

import java.util.ArrayList;

import rs.ac.uns.ftn.informatika.jpa.domain.Order;
import rs.ac.uns.ftn.informatika.jpa.domain.User;
import rs.ac.uns.ftn.informatika.jpa.domain.users.Guest;

public interface OrderService {
	ArrayList<Order> getOrders();
	
	Order createNew(Order order);
	
	Order update(Order order, Long id);
	
	Order findOne(Long id);
}
