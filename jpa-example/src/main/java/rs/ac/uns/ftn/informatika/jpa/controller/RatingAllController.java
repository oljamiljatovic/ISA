package rs.ac.uns.ftn.informatika.jpa.controller;

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

import rs.ac.uns.ftn.informatika.jpa.domain.RatingAll;
import rs.ac.uns.ftn.informatika.jpa.domain.User;
import rs.ac.uns.ftn.informatika.jpa.service.RatingAllService;

@Controller 
@RequestMapping("/ratingAllController")
public class RatingAllController {
	@Autowired
	private RatingAllService ratingAllService;

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
		rating.setGuestId(u.getId());
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
		RatingAll rating = ratingAllService.findByGuestIdAndReservationId(u.getId(), reservationId);
		return new ResponseEntity<RatingAll>(rating, HttpStatus.OK);
	}
}
