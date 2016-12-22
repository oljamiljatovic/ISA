package rs.ac.uns.ftn.informatika.jpa.service;

import java.util.ArrayList;

import rs.ac.uns.ftn.informatika.jpa.domain.Order;
import rs.ac.uns.ftn.informatika.jpa.domain.User;

public interface OrderService {
	ArrayList<Order> getOrders();
	
	Order createNew(Order order);
}
