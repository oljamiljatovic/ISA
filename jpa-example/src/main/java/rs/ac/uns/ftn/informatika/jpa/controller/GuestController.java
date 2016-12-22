package rs.ac.uns.ftn.informatika.jpa.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import rs.ac.uns.ftn.informatika.jpa.domain.User;
import rs.ac.uns.ftn.informatika.jpa.domain.users.Guest;
import rs.ac.uns.ftn.informatika.jpa.service.GuestService;


@Controller
@RequestMapping("/guestController")
public class GuestController {

	@Autowired
	private GuestService guestService;
	
	
	@GetMapping("/guests")
	@ResponseBody
	@Transactional(readOnly = true)
	public ArrayList<String> getGuests() {
	
		ArrayList<String> guestEmails= new ArrayList<String>();
		ArrayList<Guest> guests = this.guestService.getGuests();
		
		for(int i = 0 ; i <guests.size(); i++){
			guestEmails.add(guests.get(i).getEmail());
			
		}
		
		return guestEmails;
	}
	
	
	@RequestMapping(
			value = "/findGuestByEmail",
			method = RequestMethod.POST,
			consumes = MediaType.TEXT_PLAIN_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Guest> findGuestByEmail(
			@RequestBody String email) throws Exception {
		
		Guest foundGuest = guestService.findGuestByEmail(email);
		
		
		return new ResponseEntity<Guest>(foundGuest, HttpStatus.OK);
	}
	

	@RequestMapping(
			value = "/regIn",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Guest> regIn(
			@RequestBody Guest guest) throws Exception {
	
		
		Guest addedGuest = guestService.createNew(guest);
		
		return new ResponseEntity<Guest>(addedGuest, HttpStatus.OK);
	}
	
	
	@RequestMapping(
			value = "/change/{id}",
			method = RequestMethod.PUT,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Guest> update(
			@RequestBody Guest guest, @PathVariable Long id) throws Exception {
	
		Guest foundedGuest = guestService.findOne(id);
		foundedGuest.setName(guest.getName());
		foundedGuest.setSurname(guest.getSurname());
		Guest changedGuest = guestService.update(foundedGuest, id);
		
		
		return new ResponseEntity<Guest>(changedGuest, HttpStatus.OK);
	}
	
	
}
