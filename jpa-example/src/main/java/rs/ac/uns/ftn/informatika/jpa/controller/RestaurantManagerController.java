package rs.ac.uns.ftn.informatika.jpa.controller;

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
import rs.ac.uns.ftn.informatika.jpa.service.ManagerService;
import rs.ac.uns.ftn.informatika.jpa.service.RestaurantService;


@Controller
@RequestMapping("/restaurantManagerController")
public class RestaurantManagerController {

	@Autowired
	private RestaurantService restaurantService;
	@Autowired
	private ManagerService managerService;
	
	@RequestMapping(
			value = "/updateRestaurant",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Restaurant> updateRestaurant(@RequestBody Restaurant rest)  throws Exception {
		ServletRequestAttributes attr = (ServletRequestAttributes) 
			    RequestContextHolder.currentRequestAttributes();
		HttpSession session= attr.getRequest().getSession(true);
		User u = (User) session.getAttribute("korisnik");
		Restaurant r= null;
		RestaurantManager rm = null;
		if(u.getRole().equals("restaurantManager")){
			rm= this.managerService.getManager(u.getEmail());
			String nameRest = rm.getRestaurant();
			r = restaurantService.getRestaurant(nameRest);
		}
		Long id = r.getId();
		rest.setId(id);
		this.restaurantService.deleteRestaurant(id);
		this.restaurantService.addRestaurant(rest);
		rm.setRestaurant(rest.getName());
		this.managerService.updateManager(rm);
		//this.restaurantService.updateRestaurant(rest);
		if(rest.getDrinks().size()>1){
			System.out.println(rest.getDrinks().get(0));
			System.out.println(rest.getDrinks().get(1));
		}
		return new ResponseEntity<Restaurant>(rest, HttpStatus.OK);
	}

}
