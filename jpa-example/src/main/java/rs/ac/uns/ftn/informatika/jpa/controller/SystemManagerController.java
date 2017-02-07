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
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import rs.ac.uns.ftn.informatika.jpa.domain.Drink;
import rs.ac.uns.ftn.informatika.jpa.domain.Meal;
import rs.ac.uns.ftn.informatika.jpa.domain.Restaurant;
import rs.ac.uns.ftn.informatika.jpa.domain.User;
import rs.ac.uns.ftn.informatika.jpa.domain.users.RestaurantManager;
import rs.ac.uns.ftn.informatika.jpa.service.DrinkService;
import rs.ac.uns.ftn.informatika.jpa.service.ManagerService;
import rs.ac.uns.ftn.informatika.jpa.service.MealService;
import rs.ac.uns.ftn.informatika.jpa.service.RestaurantService;

@Controller
@RequestMapping("/registerController")
public class SystemManagerController {
	
	@Autowired
	private ManagerService managerService;
	@Autowired
	private DrinkService drinkService;
	@Autowired
	private RestaurantService restaurantService;
	@Autowired
	private MealService mealService;
	
	@RequestMapping(
			value = "/registerManager",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RestaurantManager> addManager(@RequestBody RestaurantManager manag)  
			throws Exception {
		this.managerService.addManager(manag);
		return new ResponseEntity<RestaurantManager>(manag, HttpStatus.OK);
	}
	
	@RequestMapping(
			value = "/uzmiPica",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	//@Transactional(readOnly = false)
	public ResponseEntity<ArrayList<Drink>> uzmiPica()  throws Exception {
		ArrayList<Drink> drinks = this.drinkService.getDrinks();
		return new ResponseEntity<ArrayList<Drink>>(drinks, HttpStatus.OK);
	}
	
	@RequestMapping(
			value = "/registerRestaurant",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	//@Transactional(readOnly = false)
	//@JsonCreator
	public ResponseEntity<Restaurant> addRestaurant(@RequestBody Restaurant rest)  throws Exception {
		this.restaurantService.addRestaurant(rest);
		if(rest.getDrinks().size()>1){
			System.out.println(rest.getDrinks().get(0));
			System.out.println(rest.getDrinks().get(1));
		}
		return new ResponseEntity<Restaurant>(rest, HttpStatus.OK);
	}
	
	@RequestMapping(
			value = "/uzmiRestorane",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	//@Transactional(readOnly = false)
	public ResponseEntity<ArrayList<Restaurant>> uzmiRestorane()  throws Exception {
		ArrayList<Restaurant> restaurants = this.restaurantService.getRestaurants();
		return new ResponseEntity<ArrayList<Restaurant>>(restaurants, HttpStatus.OK);
	}
	
	@RequestMapping(
			value = "/uzmiObroke",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	//@Transactional(readOnly = false)
	public ResponseEntity<ArrayList<Meal>> uzmiObroke()  throws Exception {
		ArrayList<Meal> meals = this.mealService.getMeals();
		return new ResponseEntity<ArrayList<Meal>>(meals, HttpStatus.OK);
	}
	
	@RequestMapping(
			value = "/uzmiRestoranMenadzera",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Restaurant> uzmiRestoranMenadzera()  throws Exception {
		
		ServletRequestAttributes attr = (ServletRequestAttributes) 
			    RequestContextHolder.currentRequestAttributes();
		HttpSession session= attr.getRequest().getSession(true);
		User u = (User) session.getAttribute("korisnik");
		Restaurant r= null;
		if(u.getRole().equals("restaurantManager")){
			RestaurantManager rm= this.managerService.getManager(u.getEmail());
			String nameRest = rm.getRestaurant();
			r = restaurantService.getRestaurant(nameRest);
		}
		
		
		return new ResponseEntity<Restaurant>(r, HttpStatus.OK);
	}
	
}
