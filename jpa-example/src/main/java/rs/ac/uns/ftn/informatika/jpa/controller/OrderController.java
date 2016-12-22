package rs.ac.uns.ftn.informatika.jpa.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import rs.ac.uns.ftn.informatika.jpa.domain.Order;
import rs.ac.uns.ftn.informatika.jpa.domain.users.Guest;
import rs.ac.uns.ftn.informatika.jpa.service.OrderService;

@Controller 
@RequestMapping("/orderController")
public class OrderController {
	@Autowired
	private OrderService orderService;
	@RequestMapping(
			value = "/getOrder",
			method = RequestMethod.GET,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getOrder() throws Exception {
		//System.out.println("Usao u calendarForWaiterController/proba");
		ArrayList<Order> cfw = this.orderService.getOrders();
		
		return new ResponseEntity<Object>(cfw, HttpStatus.OK);
	}
	
	@RequestMapping(
			value = "/addOrder",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Order> addOrder(
			@RequestBody Order order) throws Exception {
	
		
		Order addedOrder = orderService.createNew(order);
		
		return new ResponseEntity<Order>(addedOrder, HttpStatus.OK);
	}
	
	@RequestMapping(
			value = "/change/{id}",
			method = RequestMethod.PUT,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Order> update(
			@RequestBody Order order, @PathVariable Long id) throws Exception {
	
		Order foundedOrder = orderService.findOne(id);
		//foundedOrder.setUsername(order.getUsername());
		//foundedOrder.setDesk(order.getDesk());
		foundedOrder.setDrinks(order.getDrinks());
		foundedOrder.setMeals(order.getMeals());
		Order changedOrder = orderService.update(foundedOrder, id);
		
		
		return new ResponseEntity<Order>(changedOrder, HttpStatus.OK);
	}
}