package rs.ac.uns.ftn.informatika.jpa.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import rs.ac.uns.ftn.informatika.jpa.domain.Order;
import rs.ac.uns.ftn.informatika.jpa.domain.Reservation;
import rs.ac.uns.ftn.informatika.jpa.domain.Restaurant;
import rs.ac.uns.ftn.informatika.jpa.domain.users.Employee;
import rs.ac.uns.ftn.informatika.jpa.repository.OrderRepository;

@Service
@Transactional(isolation=Isolation.SERIALIZABLE)
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

	@Override
	public Order findById(Long id) {
		return orderRepository.findById(id);
	}
	@Override
	public List<Order> findByDrinks(List drinks) {
		return this.orderRepository.findByDrinks(drinks);
	}
	@Override
	public ArrayList<Order> findByWaiter(Employee waiter) {
		return this.orderRepository.findByWaiter(waiter);
	}
	@Override
	public ArrayList<Order> findByRestaurant(Restaurant restaurant) {
		return this.orderRepository.findByRestaurant(restaurant);
	}
	@Override
	public ArrayList<Order> findByReservation(Reservation reservation) {
		return orderRepository.findByReservation(reservation);
	}
}
