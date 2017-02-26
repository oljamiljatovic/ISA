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

import rs.ac.uns.ftn.informatika.jpa.domain.Restaurant;
import rs.ac.uns.ftn.informatika.jpa.domain.User;
import rs.ac.uns.ftn.informatika.jpa.domain.users.RestaurantManager;
import rs.ac.uns.ftn.informatika.jpa.domain.users.SystemManager;
import rs.ac.uns.ftn.informatika.jpa.service.ManagerService;
import rs.ac.uns.ftn.informatika.jpa.service.RestaurantService;
import rs.ac.uns.ftn.informatika.jpa.service.SystemManagerService;

@Controller
@RequestMapping("/registerController")
public class SystemManagerController {
	
	@Autowired
	private ManagerService managerService;
	@Autowired
	private RestaurantService restaurantService;
	@Autowired
	private SystemManagerService smService;
	
	@RequestMapping(
			value = "/registerManager",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RestaurantManager> addManager(@RequestBody RestaurantManager manag)  
			throws Exception {
		Restaurant rest = this.restaurantService.getRestaurant(manag.getRestaurant().getId());
		manag.setRestaurant(rest);
		this.managerService.addManager(manag);
		return new ResponseEntity<RestaurantManager>(manag, HttpStatus.OK);
	}
	
	@RequestMapping(
			value = "/registerManagerSys",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SystemManager> addManagerSis(@RequestBody SystemManager manag)  
			throws Exception {
		this.smService.addManager(manag);
		return new ResponseEntity<SystemManager>(manag, HttpStatus.OK);
	}
	
	@RequestMapping(
			value = "/registerRestaurant",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Restaurant> addRestaurant(@RequestBody Restaurant rest)  throws Exception {
		this.restaurantService.addRestaurant(rest);
		return new ResponseEntity<Restaurant>(rest, HttpStatus.OK);
	}
	
	@RequestMapping(
			value = "/uzmiRestorane",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ArrayList<Restaurant>> uzmiRestorane()  throws Exception {
		ArrayList<Restaurant> restaurants = this.restaurantService.getRestaurants();
		return new ResponseEntity<ArrayList<Restaurant>>(restaurants, HttpStatus.OK);
	}
	
	@RequestMapping(
			value = "/uzmiUlogovanog",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> uzmiUlogovanog()  throws Exception {
		ServletRequestAttributes attr = (ServletRequestAttributes) 
			    RequestContextHolder.currentRequestAttributes();
		HttpSession session= attr.getRequest().getSession(true);
		User user = (User) session.getAttribute("korisnik");
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
}
