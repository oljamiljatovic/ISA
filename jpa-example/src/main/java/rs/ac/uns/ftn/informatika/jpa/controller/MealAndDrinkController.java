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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.fasterxml.jackson.annotation.JsonCreator;

import rs.ac.uns.ftn.informatika.jpa.domain.Drink;
import rs.ac.uns.ftn.informatika.jpa.domain.Foodstuff;
import rs.ac.uns.ftn.informatika.jpa.domain.Meal;
import rs.ac.uns.ftn.informatika.jpa.domain.Offer;
import rs.ac.uns.ftn.informatika.jpa.domain.Reservation;
import rs.ac.uns.ftn.informatika.jpa.domain.Restaurant;
import rs.ac.uns.ftn.informatika.jpa.domain.User;
import rs.ac.uns.ftn.informatika.jpa.domain.users.Employee;
import rs.ac.uns.ftn.informatika.jpa.domain.users.Provider;
import rs.ac.uns.ftn.informatika.jpa.domain.users.RestaurantManager;
import rs.ac.uns.ftn.informatika.jpa.service.DrinkService;
import rs.ac.uns.ftn.informatika.jpa.service.EmployeeService;
import rs.ac.uns.ftn.informatika.jpa.service.FoodstuffService;
import rs.ac.uns.ftn.informatika.jpa.service.ManagerService;
import rs.ac.uns.ftn.informatika.jpa.service.MealService;
import rs.ac.uns.ftn.informatika.jpa.service.OfferService;
import rs.ac.uns.ftn.informatika.jpa.service.ProviderService;
import rs.ac.uns.ftn.informatika.jpa.service.ReservationService;
import rs.ac.uns.ftn.informatika.jpa.service.RestaurantService;


@Controller
@RequestMapping("/mealAndDrinkController")
public class MealAndDrinkController {
	
	@Autowired
	private DrinkService drinkService;
	@Autowired
	private MealService mealService;
	@Autowired
	private FoodstuffService fsService;
	@Autowired
	private ManagerService managerService;
	@Autowired
	private RestaurantService restaurantService;
	@Autowired
	private OfferService offerService;
	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private ProviderService providerService;
	@Autowired
	private ReservationService reservationService;
	
	
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
		Provider providerr = null;
		if(u.getRole().equals("restaurantManager")){
			rm= this.managerService.getManager(u.getEmail());
			r = rm.getRestaurant();
			drinks = this.drinkService.getDrinksByRestaurant(r);
		}else if(u.getRole().equals("waiter") || u.getRole().equals("barman")){
			employee = employeeService.findById(u.getId());
			drinks = this.drinkService.getDrinksByRestaurant(employee.getRestaurant());
		}else if(u.getRole().equals("provider")){
			providerr = this.providerService.getProvider(u.getEmail());
			drinks = this.drinkService.getDrinksByRestaurant(providerr.getRestaurant());
		}
		
		ArrayList<Drink> temp = new ArrayList<Drink>();
		for(int i=0; i<drinks.size(); i++){
			if(drinks.get(i).isExist()){
				temp.add(drinks.get(i));
			}
		}
		
		return new ResponseEntity<ArrayList<Drink>>(temp, HttpStatus.OK);
	}
	
	@RequestMapping(
			value = "/deleteDrink",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value = HttpStatus.OK)
	public void deleteDrink(@RequestBody Drink drink)  throws Exception {
		this.drinkService.updateDrinkFlag(drink);
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
			r = restaurantService.getRestaurant(rm.getRestaurant().getId());
		}
		drink.setRestaurant(r);
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
		Provider providerr = null;
		if(u.getRole().equals("restaurantManager")){
			rm= this.managerService.getManager(u.getEmail());
			r = rm.getRestaurant();
			meals = this.mealService.getMealsByRestaurant(r);
		}else if(u.getRole().equals("waiter") || u.getRole().equals("cook") || u.getRole().equals("saladCook") || u.getRole().equals("grilledCook")){
			employee = employeeService.findById(u.getId());
			meals = this.mealService.getMealsByRestaurant(employee.getRestaurant());
		}else if(u.getRole().equals("provider")){
			providerr = this.providerService.getProvider(u.getEmail());
			meals = this.mealService.getMealsByRestaurant(providerr.getRestaurant());
		}
		
		ArrayList<Meal> temp = new ArrayList<Meal>();
		for(int i=0; i<meals.size(); i++){
			if(meals.get(i).isExist()){
				temp.add(meals.get(i));
			}
		}
		return new ResponseEntity<ArrayList<Meal>>(temp, HttpStatus.OK);
	}
	
	@RequestMapping(
			value = "/deleteMeal",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value = HttpStatus.OK)
	public void deleteMeal(@RequestBody Meal meal)  throws Exception {
		this.mealService.updateMealFlag(meal);
	}
	
	@RequestMapping(
			value = "/updateMeal",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@JsonCreator
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
			r = restaurantService.getRestaurant(rm.getRestaurant().getId());
		}
		meal.setRestaurant(r);
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
			r = restaurantService.getRestaurant(rm.getRestaurant().getId());
		}
		offer.setRestaurant(r);
		this.offerService.addOffer(offer);
		return new ResponseEntity<Offer>(offer, HttpStatus.OK);
	}
	
	
	@RequestMapping(
			value = "/uzmiNamirnice",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ArrayList<Foodstuff>> uzmiNamirnice()  throws Exception {
		
		ArrayList<Foodstuff> fs = this.fsService.getFoodstuffs();
				
		return new ResponseEntity<ArrayList<Foodstuff>>(fs, HttpStatus.OK);
	}
	
	
	
	
	
	@RequestMapping(
			value = "/getAllDrinks/{idReservation}",
			method = RequestMethod.PUT,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ArrayList<Drink>> getAllDrinks(@PathVariable Long idReservation)  throws Exception {
		
			ArrayList<Drink> drinks = new ArrayList<Drink>();
			Reservation reservation = reservationService.findOne(idReservation);
			drinks = this.drinkService.getDrinksByRestaurant(reservation.getIdRestaurant());
	
		
		return new ResponseEntity<ArrayList<Drink>>(drinks, HttpStatus.OK);
	}
	
	

	@RequestMapping(
			value = "/getAllMeals/{idReservation}",
			method = RequestMethod.PUT,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ArrayList<Meal>> getAllMeals(@PathVariable Long idReservation)  throws Exception {
		
			ArrayList<Meal> meals = new ArrayList<Meal>();
			Reservation reservation = reservationService.findOne(idReservation);
			meals = this.mealService.getMealsByRestaurant(reservation.getIdRestaurant());
	
		
		return new ResponseEntity<ArrayList<Meal>>(meals, HttpStatus.OK);
	}
	
}
