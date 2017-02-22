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
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import rs.ac.uns.ftn.informatika.jpa.domain.Bill;
import rs.ac.uns.ftn.informatika.jpa.domain.Drink;
import rs.ac.uns.ftn.informatika.jpa.domain.Meal;
import rs.ac.uns.ftn.informatika.jpa.domain.Order;
import rs.ac.uns.ftn.informatika.jpa.domain.OrderSurrogate;
import rs.ac.uns.ftn.informatika.jpa.domain.User;
import rs.ac.uns.ftn.informatika.jpa.domain.users.Employee;
import rs.ac.uns.ftn.informatika.jpa.service.BillService;
import rs.ac.uns.ftn.informatika.jpa.service.DrinkService;
import rs.ac.uns.ftn.informatika.jpa.service.EmployeeService;
import rs.ac.uns.ftn.informatika.jpa.service.MealService;
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
	@Autowired
	private DrinkService drinkService;
	@Autowired
	private MealService mealService;
	
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
		System.out.println("Userrrr "+u.getEmail());
		ArrayList<Order> orders = new ArrayList<Order>();
		if(u.getRole().equals("cook") || u.getRole().equals("barman")){
			Employee employee = employeeService.findById(u.getId());
			orders = this.orderService.findByRestaurant(employee.getRestaurant());
		}
		return new ResponseEntity<Object>(orders, HttpStatus.OK);
	}
	
	/*@RequestMapping(
			value = "/addOrder",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Order> addOrder(
			@RequestBody OrderSurrogate surrogateOrder) throws Exception {
		
		ServletRequestAttributes attr = (ServletRequestAttributes) 
			    RequestContextHolder.currentRequestAttributes();
		HttpSession session= attr.getRequest().getSession(true);
		User u = (User) session.getAttribute("korisnik");
		Employee employee = employeeService.findById(u.getId());
		surrogateOrder.setWaiter_id(u.getId());
		surrogateOrder.setRestaurant(employee.getRestaurant());
		System.out.println("Pice "+surrogateOrder.getDrinks().get(0));
		ArrayList<Drink> drinks = new ArrayList<Drink>();
		for(int i=0;i<surrogateOrder.getDrinks().size();i++){
			String name = surrogateOrder.getDrinks().get(i);
			System.out.println(name);
			Drink drink = drinkService.findByName(name);
			drinks.add(drink);
		}
		ArrayList<Meal> meals = new ArrayList<Meal>();
		for(int i=0;i<surrogateOrder.getMeals().size();i++){
			String name = surrogateOrder.getMeals().get(i);
			System.out.println(name);
			Meal meal = mealService.findByName(name);
			meals.add(meal);
		}
		Order order = new Order();
		order.setRestaurant(surrogateOrder.getRestaurant());
		order.setTable_id(surrogateOrder.getTable_id());
		order.setWaiter_id(surrogateOrder.getWaiter_id());
		order.setDrinks(drinks);
		order.setMeals(meals);
		Order addedOrder = orderService.createNew(order);
		
		return new ResponseEntity<Order>(addedOrder, HttpStatus.OK);
	}*/
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
		Order foundedOrder = orderService.findOne(id);
		if(u.getRole().equals("waiter")){
			order.setWaiter_id(u.getId());
			Employee employee = employeeService.findById(u.getId());
			order.setRestaurant(employee.getRestaurant());
			foundedOrder.setDrinks(order.getDrinks());
			foundedOrder.setMeals(order.getMeals());
		}else if(u.getRole().equals("cook")){
			foundedOrder.setCook_state(order.getCook_state());
		}else if(u.getRole().equals("barman")){
			foundedOrder.setBarman_state(order.getBarman_state());
		}
		Order changedOrder = orderService.update(foundedOrder, id);
		return new ResponseEntity<Order>(changedOrder, HttpStatus.OK);
	}
	
	@RequestMapping(
			value = "/addOnOrder/{id}",
			method = RequestMethod.PUT,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Order> addOnOrder(
			@RequestBody Order order, @PathVariable Long id) throws Exception {
		ServletRequestAttributes attr = (ServletRequestAttributes) 
			    RequestContextHolder.currentRequestAttributes();
		HttpSession session= attr.getRequest().getSession(true);
		User u = (User) session.getAttribute("korisnik");
		Order foundedOrder = orderService.findOne(id);
		if(u.getRole().equals("waiter")){
			order.setWaiter_id(u.getId());
			Employee employee = employeeService.findById(u.getId());
			order.setRestaurant(employee.getRestaurant());
			for(int i=0;i<order.getDrinks().size();i++){
				foundedOrder.getDrinks().add(order.getDrinks().get(i));
			}
			for(int i=0;i<order.getMeals().size();i++){
				foundedOrder.getMeals().add(order.getMeals().get(i));
			}
		}
		Order changedOrder = orderService.update(foundedOrder, id);
		return new ResponseEntity<Order>(changedOrder, HttpStatus.OK);
	}
	@RequestMapping(
			value = "/getOrder/{id}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Order> getOrder(@PathVariable Long id) throws Exception {
		Order foundedOrder = orderService.findOne(id);
		System.out.println("Founded order "+foundedOrder.getId());
		return new ResponseEntity<Order>(foundedOrder, HttpStatus.OK);
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
