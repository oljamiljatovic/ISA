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

import rs.ac.uns.ftn.informatika.jpa.domain.RatingAll;
import rs.ac.uns.ftn.informatika.jpa.domain.Reservation;
import rs.ac.uns.ftn.informatika.jpa.domain.Restaurant;
import rs.ac.uns.ftn.informatika.jpa.domain.User;
import rs.ac.uns.ftn.informatika.jpa.domain.users.Guest;
import rs.ac.uns.ftn.informatika.jpa.domain.users.RestaurantManager;
import rs.ac.uns.ftn.informatika.jpa.service.GuestService;
import rs.ac.uns.ftn.informatika.jpa.service.ManagerService;
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
		RatingAll addedRating = ratingAllService.addNew(rating);
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

		int br = temp.size();
		int suma = 0;
		for(int i=0; i<br; i++)
			suma = suma + temp.get(i).getRestaurantRating();
		
		float prosek = (float) (suma/br);
		String str = Float.toString(prosek);
		return new ResponseEntity<String>(str, HttpStatus.OK);
	}
}
