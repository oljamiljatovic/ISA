
package rs.ac.uns.ftn.informatika.jpa.controller;

import java.util.ArrayList;
import java.util.List;

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

import rs.ac.uns.ftn.informatika.jpa.domain.Meal;
import rs.ac.uns.ftn.informatika.jpa.domain.Order;
import rs.ac.uns.ftn.informatika.jpa.domain.RatingAll;
import rs.ac.uns.ftn.informatika.jpa.domain.Reservation;
import rs.ac.uns.ftn.informatika.jpa.domain.Restaurant;
import rs.ac.uns.ftn.informatika.jpa.domain.User;
import rs.ac.uns.ftn.informatika.jpa.domain.users.Employee;
import rs.ac.uns.ftn.informatika.jpa.domain.users.Guest;
import rs.ac.uns.ftn.informatika.jpa.domain.users.RestaurantManager;
import rs.ac.uns.ftn.informatika.jpa.service.EmployeeService;
import rs.ac.uns.ftn.informatika.jpa.service.GuestService;
import rs.ac.uns.ftn.informatika.jpa.service.ManagerService;
import rs.ac.uns.ftn.informatika.jpa.service.MealService;
import rs.ac.uns.ftn.informatika.jpa.service.OrderService;
import rs.ac.uns.ftn.informatika.jpa.service.RatingAllService;
import rs.ac.uns.ftn.informatika.jpa.service.ReservationService;
import rs.ac.uns.ftn.informatika.jpa.service.RestaurantService;

@Controller 
@RequestMapping("/ratingAllController")
public class RatingAllController {
	@Autowired
	private RatingAllService ratingAllService;
	@Autowired
	private ManagerService managerService;
	@Autowired
	private GuestService guestService;
	@Autowired
	private ReservationService reservationService;
	@Autowired
	private RestaurantService restaurantService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private MealService mealService;
	@Autowired
	private EmployeeService employeeService;

	@RequestMapping(
			value = "/addRating",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RatingAll> addRatingAll(
			@RequestBody RatingAll rating) throws Exception {
		ServletRequestAttributes attr = (ServletRequestAttributes) 
			    RequestContextHolder.currentRequestAttributes();
		HttpSession session= attr.getRequest().getSession(true);
		User u = (User) session.getAttribute("korisnik");
		Guest guest = guestService.findOne(u.getId());
		rating.setGuest(guest);
		System.out.println("guest "+guest.getName());
		Restaurant restaurant = restaurantService.getRestaurant(rating.getRestaurant().getId());
		rating.setRestaurant(restaurant);
		System.out.println("restaurant "+restaurant.getName());
		Reservation reservation = reservationService.findOne(rating.getReservation().getId());
		rating.setReservation(reservation);
		System.out.println("reservation "+reservation.getId());
		ArrayList<Order> orders = orderService.findByReservation(reservation);
		RatingAll addedRating = null;
		//uzme konobara prve porudzbine iz rezervacije
		//ako nije bilo poridzbina zabrani da ocjenjuje
		List<Meal> meals = new ArrayList<Meal>();
		if(orders!=null && !orders.isEmpty()){
			Order order = orders.get(0);
			Employee waiter = order.getWaiter();
			rating.setWaiter(waiter);
			System.out.println("waiter "+waiter.getEmail());
			for(int i=0;i<order.getMeals().size();i++){
				meals.add(order.getMeals().get(i));
				System.out.println("meal "+order.getMeals().get(i).getName());
			}
			rating.setMeals(meals);
			addedRating = ratingAllService.addNew(rating);
		}
		
		return new ResponseEntity<RatingAll>(addedRating, HttpStatus.OK);
	}
	
	@RequestMapping(
			value = "/checkRating/{reservationId}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RatingAll> checkRating(
			@PathVariable Long reservationId) throws Exception {
		ServletRequestAttributes attr = (ServletRequestAttributes) 
			    RequestContextHolder.currentRequestAttributes();
		HttpSession session= attr.getRequest().getSession(true);
		User u = (User) session.getAttribute("korisnik");
		Guest guest = guestService.findOne(u.getId());
		Reservation r = reservationService.findOne(reservationId);
		RatingAll rating = ratingAllService.findByGuestAndReservation(guest,r);
		return new ResponseEntity<RatingAll>(rating, HttpStatus.OK);
	}
	
	
	@RequestMapping(
			value = "/takeMarks",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> takeMarks()  throws Exception {
		
		ServletRequestAttributes attr = (ServletRequestAttributes) 
			    RequestContextHolder.currentRequestAttributes();
		HttpSession session= attr.getRequest().getSession(true);
		User u = (User) session.getAttribute("korisnik");
		RestaurantManager rm= this.managerService.getManager(u.getEmail());
		Restaurant restaurant = restaurantService.getRestaurant(rm.getRestaurant().getId());
		ArrayList<RatingAll> temp = this.ratingAllService.findByRestaurant(restaurant);

		
		String str = null;
		int br = temp.size();
		if(br!=0){
			int suma = 0;
			for(int i=0; i<br; i++)
				suma = suma + temp.get(i).getRestaurantRating();
			
			float prosek = (float) (suma/br);
			str = Float.toString(prosek);
		}else
			str = "0";
		return new ResponseEntity<String>(str, HttpStatus.OK);
	}
	
	
	@RequestMapping(
			value = "/takeMarksForMeal",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> takeMarksForMeal(@RequestBody Meal m)  throws Exception {
		
		Meal meal = this.mealService.getMeal(m.getId());
		ArrayList<RatingAll> temp = this.ratingAllService.findByMeals(meal.getId());

		String str = null;
		int br = temp.size();
		if(br!=0){
			int suma = 0;
			for(int i=0; i<br; i++)
				suma = suma + temp.get(i).getMealRating();
			
			float prosek = (float) (suma/br);
			str = Float.toString(prosek);
		}else
			str = "0";
		return new ResponseEntity<String>(str, HttpStatus.OK);
	}
	
	@RequestMapping(
			value = "/takeMarksForService",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> takeMarksForService(@RequestBody Employee empl)  throws Exception {
		
		Employee e = this.employeeService.findById(empl.getId());
		ArrayList<RatingAll> temp = this.ratingAllService.findByWaiter(e);

		String str = null;
		int br = temp.size();
		if(br!=0){
			int suma = 0;
			for(int i=0; i<br; i++)
				suma = suma + temp.get(i).getServiceRating();
			
			float prosek = (float) (suma/br);
			str = Float.toString(prosek);
		}else
			str = "0";
		return new ResponseEntity<String>(str, HttpStatus.OK);
	}
	
	
	
	
	@RequestMapping(
			value = "/takeMarksForGuest/{id}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> takeMarksForGuest(@PathVariable Long id)  throws Exception {
		
		
		Restaurant restaurant = restaurantService.getRestaurant(id);
		ArrayList<RatingAll> temp = this.ratingAllService.findByRestaurant(restaurant);

		
		String str = null;
		int br = temp.size();
		if(br!=0){
			int suma = 0;
			for(int i=0; i<br; i++)
				suma = suma + temp.get(i).getRestaurantRating();
			
			float prosek = (float) (suma/br);
			str = Float.toString(prosek);
		}else
			str = "0";
		return new ResponseEntity<String>(str, HttpStatus.OK);
	}
	
	
	@RequestMapping(
			value = "/takeMarksForGuestFriends",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> takeMarksForGuestFriends(@RequestBody Guest g)  throws Exception {
		
		Guest guest = this.guestService.findOne(g.getId());
		ArrayList<Guest> friends = (ArrayList<Guest>) guest.getFriends();
		friends.add(guest);

		String str = null;
		int br = friends.size();
		int m = 0;
		int suma = 0;
		if(br!=0){
			
			for(int i=0; i<br; i++){
				ArrayList<RatingAll> temp = this.ratingAllService.findByGuest(friends.get(i));
				
				for(int j=0; j<temp.size(); j++){
					suma = suma + temp.get(j).getRestaurantRating();
					m++;
				}
			}
				
			
			float prosek = (float) (suma/m);
			str = Float.toString(prosek);
		}else
			str = "0";
		
		return new ResponseEntity<String>(str, HttpStatus.OK);
	}
}
