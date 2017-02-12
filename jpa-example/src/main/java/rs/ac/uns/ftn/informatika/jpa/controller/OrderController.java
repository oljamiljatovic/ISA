package rs.ac.uns.ftn.informatika.jpa.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import rs.ac.uns.ftn.informatika.jpa.domain.Bill;
import rs.ac.uns.ftn.informatika.jpa.domain.Drink;
import rs.ac.uns.ftn.informatika.jpa.domain.Order;
import rs.ac.uns.ftn.informatika.jpa.domain.User;
import rs.ac.uns.ftn.informatika.jpa.domain.users.Employee;
import rs.ac.uns.ftn.informatika.jpa.service.BillService;
import rs.ac.uns.ftn.informatika.jpa.service.EmployeeService;
import rs.ac.uns.ftn.informatika.jpa.service.OrderService;

@Controller 
@RequestMapping("/orderController")
public class OrderController {
	@Autowired
	private OrderService orderService;
	@Autowired
	private BillService billService;
	@Autowired
	private EmployeeService employeeService;
	
	@RequestMapping(
			value = "/getOrders",
			method = RequestMethod.GET,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getOrders() throws Exception {
		//System.out.println("Usao u calendarForWaiterController/proba");
		ServletRequestAttributes attr = (ServletRequestAttributes) 
			    RequestContextHolder.currentRequestAttributes();
		HttpSession session= attr.getRequest().getSession(true);
		User u = (User) session.getAttribute("korisnik");
		ArrayList<Order> orders = new ArrayList<Order>();
		if(u.getRole().equals("waiter")){
			orders = this.orderService.findByWaiter_id(u.getId());
		}
		return new ResponseEntity<Object>(orders, HttpStatus.OK);
	}
	@RequestMapping(
			value = "/getOrdersForRestaurant",
			method = RequestMethod.GET,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getOrderedMeals() throws Exception {
		//System.out.println("Usao u calendarForWaiterController/proba");
		ServletRequestAttributes attr = (ServletRequestAttributes) 
			    RequestContextHolder.currentRequestAttributes();
		HttpSession session= attr.getRequest().getSession(true);
		User u = (User) session.getAttribute("korisnik");
		ArrayList<Order> orders = new ArrayList<Order>();
		if(u.getRole().equals("cook") || u.getRole().equals("barman")){
			Employee employee = employeeService.findById(u.getId());
			orders = this.orderService.findByRestaurant(employee.getRestaurant());
		}
		return new ResponseEntity<Object>(orders, HttpStatus.OK);
	}
	
	@RequestMapping(
			value = "/addOrder",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Order> addOrder(
			@RequestBody Order order) throws Exception {
		
		ServletRequestAttributes attr = (ServletRequestAttributes) 
			    RequestContextHolder.currentRequestAttributes();
		HttpSession session= attr.getRequest().getSession(true);
		User u = (User) session.getAttribute("korisnik");
		Employee employee = employeeService.findById(u.getId());
		order.setWaiter_id(u.getId());
		order.setRestaurant(employee.getRestaurant());
		Order addedOrder = orderService.createNew(order);
		return new ResponseEntity<Order>(addedOrder, HttpStatus.OK);
	}
	
	@RequestMapping(
			value = "/catchList",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ArrayList<Drink>> catchList(
			@RequestParam(value="listOfDrinks") ArrayList<Drink> listOfDrinks	) throws Exception {
		System.out.println(listOfDrinks.get(0).getName());
		System.out.println(listOfDrinks.get(0).getPrice());
		return new ResponseEntity<ArrayList<Drink>>(listOfDrinks, HttpStatus.OK);
	}
	
	@RequestMapping(
			value = "/change/{id}",
			method = RequestMethod.PUT,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Order> update(
			@RequestBody Order order, @PathVariable Long id) throws Exception {
		ServletRequestAttributes attr = (ServletRequestAttributes) 
			    RequestContextHolder.currentRequestAttributes();
		HttpSession session= attr.getRequest().getSession(true);
		User u = (User) session.getAttribute("korisnik");
		order.setWaiter_id(u.getId());
		Employee employee = employeeService.findById(u.getId());
		order.setRestaurant(employee.getRestaurant());
		Order foundedOrder = orderService.findOne(id);
		//foundedOrder.setUsername(order.getUsername());
		//foundedOrder.setDesk(order.getDesk());
		foundedOrder.setDrinks(order.getDrinks());
		foundedOrder.setMeals(order.getMeals());
		Order changedOrder = orderService.update(foundedOrder, id);
		return new ResponseEntity<Order>(changedOrder, HttpStatus.OK);
	}
	
	@RequestMapping(
			value = "/getBills",
			method = RequestMethod.GET,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getBill() throws Exception {
		System.out.println("Usao u getBills");
		ArrayList<Bill> cfw = this.billService.getBills();		
		return new ResponseEntity<Object>(cfw, HttpStatus.OK);
	}
	
	@RequestMapping(
			value = "/addBill",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Bill> addBill(
			@RequestBody Bill bill) throws Exception {
		Bill addedBill = billService.addNew(bill);
		return new ResponseEntity<Bill>(addedBill, HttpStatus.OK);
	}
	
}
