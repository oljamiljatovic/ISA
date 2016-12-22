package rs.ac.uns.ftn.informatika.jpa.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import rs.ac.uns.ftn.informatika.jpa.domain.Drink;
import rs.ac.uns.ftn.informatika.jpa.domain.Restaurant;
import rs.ac.uns.ftn.informatika.jpa.domain.users.RestaurantManager;
import rs.ac.uns.ftn.informatika.jpa.service.DrinkService;
import rs.ac.uns.ftn.informatika.jpa.service.ManagerService;
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
	
	@RequestMapping(
			value = "/registerManager",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	//@Transactional(readOnly = false)
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
	public ResponseEntity<Restaurant> addRestaurant(@RequestBody Restaurant rest)  throws Exception {
		this.restaurantService.addRestaurant(rest);
		return new ResponseEntity<Restaurant>(rest, HttpStatus.OK);
	}

}
