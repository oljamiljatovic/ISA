package rs.ac.uns.ftn.informatika.jpa.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import rs.ac.uns.ftn.informatika.jpa.domain.Order;
import rs.ac.uns.ftn.informatika.jpa.domain.User;
import rs.ac.uns.ftn.informatika.jpa.domain.users.Guest;
import rs.ac.uns.ftn.informatika.jpa.repository.OrderRepository;

@Service
@Transactional
public class OrderServiceImpl implements OrderService{
	@Autowired
	private OrderRepository orderRepository;
	
	@Override
	public ArrayList<Order> getOrders() {
		
		return this.orderRepository.findAll();
	}
	public Order createNew(Order order) {
		return orderRepository.save(order);
	}
	public Order update(Order order, Long id) {
		order.setId(id);
		return orderRepository.save(order);
	}
	@Override
	public Order findOne(Long id) {
		return orderRepository.findOne(id);
	}
}
