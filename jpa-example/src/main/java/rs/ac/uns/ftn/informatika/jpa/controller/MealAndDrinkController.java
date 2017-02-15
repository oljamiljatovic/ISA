package rs.ac.uns.ftn.informatika.jpa.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import rs.ac.uns.ftn.informatika.jpa.domain.Drink;
import rs.ac.uns.ftn.informatika.jpa.domain.Meal;
import rs.ac.uns.ftn.informatika.jpa.domain.Offer;
import rs.ac.uns.ftn.informatika.jpa.domain.Restaurant;
import rs.ac.uns.ftn.informatika.jpa.domain.User;
import rs.ac.uns.ftn.informatika.jpa.domain.users.Employee;
import rs.ac.uns.ftn.informatika.jpa.domain.users.RestaurantManager;
import rs.ac.uns.ftn.informatika.jpa.service.DrinkService;
import rs.ac.uns.ftn.informatika.jpa.service.EmployeeService;
import rs.ac.uns.ftn.informatika.jpa.service.ManagerService;
import rs.ac.uns.ftn.informatika.jpa.service.MealService;
import rs.ac.uns.ftn.informatika.jpa.service.OfferService;
import rs.ac.uns.ftn.informatika.jpa.service.RestaurantService;


@Controller
@RequestMapping("/mealAndDrinkController")
public class MealAndDrinkController {
	
	@Autowired
	private DrinkService drinkService;
	@Autowired
	private MealService mealService;
	@Autowired
	private ManagerService managerService;
	@Autowired
	private RestaurantService restaurantService;
	@Autowired
	private OfferService offerService;
	@Autowired
	private EmployeeService employeeService;
	
	@RequestMapping(
			value = "/uzmiPica",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ArrayList<Drink>> uzmiPica()  throws Exception {
		ServletRequestAttributes attr = (ServletRequestAttributes) 
			    RequestContextHolder.currentRequestAttributes();
		HttpSession session= attr.getRequest().getSession(true);
		User u = (User) session.getAttribute("korisnik");
		ArrayList<Drink> drinks = new ArrayList<Drink>();
		Restaurant r= null;
		RestaurantManager rm=null;
		Employee employee = null;
		if(u.getRole().equals("restaurantManager")){
			rm= this.managerService.getManager(u.getEmail());
			Long idRest = rm.getRestaurant();
			r = restaurantService.getRestaurant(idRest);
			drinks = this.drinkService.getDrinksByRestaurant(r.getId());
		}else if(u.getRole().equals("waiter") || u.getRole().equals("barman")){
			employee = employeeService.findById(u.getId());
			drinks = this.drinkService.getDrinksByRestaurant(employee.getRestaurant());
		}
		
		return new ResponseEntity<ArrayList<Drink>>(drinks, HttpStatus.OK);
	}
	
	@RequestMapping(
			value = "/deleteDrink",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value = HttpStatus.OK)
	public void deleteDrink(@RequestBody Drink drink)  throws Exception {
		
		Drink dr = this.drinkService.getDrink(drink.getId());
		this.drinkService.deleteDrink(dr);
	}
	
	@RequestMapping(
			value = "/updateDrink",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Drink> updateDrink(@RequestBody Drink drink)  throws Exception {
		this.drinkService.updateDrink(drink);
		return new ResponseEntity<Drink>(drink, HttpStatus.OK);
	}
	
	@RequestMapping(
			value = "/addDrink",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Drink> addDrink(@RequestBody Drink drink)  
			throws Exception {
		
		ServletRequestAttributes attr = (ServletRequestAttributes) 
			    RequestContextHolder.currentRequestAttributes();
		HttpSession session= attr.getRequest().getSession(true);
		User u = (User) session.getAttribute("korisnik");
		Restaurant r= null;
		RestaurantManager rm=null;
		if(u.getRole().equals("restaurantManager")){
			rm= this.managerService.getManager(u.getEmail());
			Long idRest = rm.getRestaurant();
			r = restaurantService.getRestaurant(idRest);
		}
		drink.setRestaurant(r.getId());
		this.drinkService.addDrink(drink);
		return new ResponseEntity<Drink>(drink, HttpStatus.OK);
	}
	
	
	@RequestMapping(
			value = "/uzmiObroke",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ArrayList<Meal>> uzmiObroke()  throws Exception {

		ServletRequestAttributes attr = (ServletRequestAttributes) 
			    RequestContextHolder.currentRequestAttributes();
		HttpSession session= attr.getRequest().getSession(true);
		User u = (User) session.getAttribute("korisnik");
		ArrayList<Meal> meals = new ArrayList<Meal>();
		Restaurant r= null;
		RestaurantManager rm=null;
		Employee employee = null;
		if(u.getRole().equals("restaurantManager")){
			rm= this.managerService.getManager(u.getEmail());
			Long idRest = rm.getRestaurant();
			r = restaurantService.getRestaurant(idRest);
			meals = this.mealService.getMealsByRestaurant(r.getId());
		}else if(u.getRole().equals("waiter") || u.getRole().equals("cook")){
			employee = employeeService.findById(u.getId());
			meals = this.mealService.getMealsByRestaurant(employee.getRestaurant());
		}
		return new ResponseEntity<ArrayList<Meal>>(meals, HttpStatus.OK);
	}
	
	@RequestMapping(
			value = "/deleteMeal",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value = HttpStatus.OK)
	public void deleteMeal(@RequestBody Meal meal)  throws Exception {
		
		Meal m = this.mealService.getMeal(meal.getId());
		this.mealService.deleteMeal(m);
	}
	
	@RequestMapping(
			value = "/updateMeal",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Meal> updateMeal(@RequestBody Meal meal)  throws Exception {
		this.mealService.updateMeal(meal);
		return new ResponseEntity<Meal>(meal, HttpStatus.OK);
	}
	
	@RequestMapping(
			value = "/addMeal",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Meal> addMeal(@RequestBody Meal meal)  
			throws Exception {
		
		ServletRequestAttributes attr = (ServletRequestAttributes) 
			    RequestContextHolder.currentRequestAttributes();
		HttpSession session= attr.getRequest().getSession(true);
		User u = (User) session.getAttribute("korisnik");
		Restaurant r= null;
		RestaurantManager rm=null;
		if(u.getRole().equals("restaurantManager")){
			rm= this.managerService.getManager(u.getEmail());
			Long idRest = rm.getRestaurant();
			r = restaurantService.getRestaurant(idRest);
		}
		meal.setRestaurant(r.getId());
		this.mealService.addMeal(meal);
		return new ResponseEntity<Meal>(meal, HttpStatus.OK);
	}
	
	@RequestMapping(
			value = "/addOffer",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Offer> addOffer(@RequestBody Offer offer)  
			throws Exception {
		
		ServletRequestAttributes attr = (ServletRequestAttributes) 
			    RequestContextHolder.currentRequestAttributes();
		HttpSession session= attr.getRequest().getSession(true);
		User u = (User) session.getAttribute("korisnik");
		Restaurant r= null;
		RestaurantManager rm=null;
		if(u.getRole().equals("restaurantManager")){
			rm= this.managerService.getManager(u.getEmail());
			Long idRest = rm.getRestaurant();
			r = restaurantService.getRestaurant(idRest);
		}
		offer.setRestaurant(r.getId());
		this.offerService.addOffer(offer);
		return new ResponseEntity<Offer>(offer, HttpStatus.OK);
	}
}
